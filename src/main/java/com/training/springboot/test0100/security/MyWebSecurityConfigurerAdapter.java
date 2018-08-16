//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.training.springboot.test0100.security;

import java.util.function.Function;

import com.training.springboot.test0100.security.accessmanager.DefaultAccessDecisionManager;
import com.training.springboot.test0100.security.handler.MyAuthenticationFailureHandler;
import com.training.springboot.test0100.security.service.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer.AuthorizedUrl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private UserDetailsService defaultUserDetailsService;

    @Value("${spring.security.loginFailure.defaulturl}")
    private String defaultFailureUrl;

    protected void configure(HttpSecurity http) throws Exception {
                http
                    .authorizeRequests()
                    .antMatchers(new String[]{"/login", "/logout", "/home","/"}).permitAll()
                       //    .antMatchers(new String[]{"/testStu"}).access("hasRole('ADMIN')")
                    .anyRequest()
                    .authenticated()
                    .accessDecisionManager(getDefaultAccessDecisionManager())
                .and()
                    .authenticationProvider(this.getAuthenticationProvider())
                    .formLogin().loginPage("/login")
                    .defaultSuccessUrl("/home", true)
                    .successHandler(this.myAuthenticationSuccessHandler)
                    .failureHandler(this.getMyAuthenticationFailureHandler())
                .and()
                    .logout().logoutSuccessUrl("/login");
    }

    @Bean
    public AccessDecisionManager getDefaultAccessDecisionManager(){
        return new DefaultAccessDecisionManager();
    }

    @Bean
    public AuthenticationFailureHandler getMyAuthenticationFailureHandler() {
        MyAuthenticationFailureHandler myAuthenticationFailureHandler = new MyAuthenticationFailureHandler();
        myAuthenticationFailureHandler.setDefaultFailureUrl(this.defaultFailureUrl);
        return myAuthenticationFailureHandler;
    }

    @Bean
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setHideUserNotFoundExceptions(false);
        authenticationProvider.setUserDetailsService(defaultUserDetailsService);
        authenticationProvider.setPasswordEncoder(this.passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public UserDetailsService myUserDeatilsServie() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("a").password("1").passwordEncoder(new Function<String, String>() {
            public String apply(String a) {
                return passwordEncoder().encode(a);
            }
        }).roles(new String[]{"user"}).build());
        return manager;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(new String[]{"/resources/**"});
    }
}
