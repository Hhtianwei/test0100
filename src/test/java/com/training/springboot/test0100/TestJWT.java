package com.training.springboot.test0100;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.training.springboot.test0100.dao.UserDao;
import com.training.springboot.test0100.entity.User;
import com.training.springboot.test0100.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 田伟 on 2018-08-16.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJWT {

    @Test
    public void test(){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret1");
            String token = JWT.create()
                    .withClaim("username","tianwei")
                    .sign(algorithm);
            System.out.println("token:" + token);
            //token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.izVguZPRsBQ5Rqw6dhMvcIwy8_9lQnrO3vpxGwPCuzs
            //token:eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.D-i3uQrZd0QJEJRKNZ0-_2t5b3-3V4MEkdUSbWQOSV4
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            System.out.println("exception:" + exception);
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Test
    public void register(){
        User user = new User();
        user.setUsername("test04");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passowrd = encoder.encode("1");
        user.setPassword(passowrd);
        user.setMobile("18208200004");
        User result = userRepository.save(user);
        int result2 = userDao.setRole(result.getId(),1);
        System.out.println("update user_role:" + result2);
        System.out.println(result);
    }

    @Autowired
    private UserDao userDao;
    @Test
    public void testUserDao(){
        int result = userDao.setRole(6,1);
        System.out.println("result:" + result);
    }

    @Test
    public void test2(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsInVzZXJuYW1lIjoidGlhbndlaSJ9.sUDkh6q-dWvj-I7YYUkpxMaGthk0OKynDz_6z6nTRuY";
        try {
            Algorithm algorithm = Algorithm.HMAC384("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt.toString());
        } catch (JWTVerificationException exception){
            System.out.println("exception:" + exception);
            //Invalid signature/claims
        }
    }
}
