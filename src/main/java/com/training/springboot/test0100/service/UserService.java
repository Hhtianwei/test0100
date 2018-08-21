package com.training.springboot.test0100.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.training.springboot.test0100.dao.UserDao;
import com.training.springboot.test0100.entity.User;
import com.training.springboot.test0100.repository.UserRepository;
import com.training.springboot.test0100.security.MyWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by 田伟 on 2018-08-20.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyWebSecurityConfigurerAdapter myWebSecurityConfigurerAdapter;

    @Autowired
    private UserDetailsService defaultUserDetailsService;

    @Transactional
    public User register(String username, String password, String mobile) {
        User user = new User();
        user.setUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String resultPassowrd = encoder.encode(password);
        user.setPassword(resultPassowrd);
        user.setMobile(mobile);
        User result = userRepository.save(user);
        //System.out.println(1/0);
        userDao.setRole(result.getId(), 1);
        return user;
    }

    public String createToken(String username, String password) {

        AuthenticationManager authenticationManager = null;
        try {
            authenticationManager = myWebSecurityConfigurerAdapter.authenticationManagerBean();
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
            final Authentication authentication = authenticationManager.authenticate(upToken);
            if (authentication != null && authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);

                Algorithm algorithm = Algorithm.HMAC256("secret1");
                String token = JWT.create()
                        .withClaim("username", username)
                        .sign(algorithm);
                return token;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
