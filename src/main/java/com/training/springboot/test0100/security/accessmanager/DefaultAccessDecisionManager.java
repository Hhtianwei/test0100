package com.training.springboot.test0100.security.accessmanager;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by 田伟 on 2018-08-14.
 */
public class DefaultAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        boolean permitAll = false;
        Iterator<ConfigAttribute> iterator2 = collection.iterator();
        while(iterator2.hasNext()){
            ConfigAttribute configAttribute = iterator2.next();
            if("permitAll".equalsIgnoreCase(configAttribute.toString())){
                permitAll = true;
                break;
            }
        }

        if(permitAll){
            return;
        }

        String requestUri = "";
        if(o instanceof FilterInvocation){
            FilterInvocation fi = (FilterInvocation) o;
            requestUri = fi.getHttpRequest().getServletPath();
        }

        Collection<? extends GrantedAuthority> colls =  authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = colls.iterator();
        boolean access = false;
        while(iterator.hasNext()){
            GrantedAuthority grantedAuthority = iterator.next();
            if(grantedAuthority.getAuthority().equals(requestUri)){
                access = true;
                break;
            }
        }

        if(!access){
            throw new AccessDeniedException(String.format("AbstractAccessDecisionManager.accessDenied", "Access is denied"));
        }

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
