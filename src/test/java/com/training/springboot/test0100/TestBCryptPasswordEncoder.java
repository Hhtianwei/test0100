package com.training.springboot.test0100;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 田伟 on 2018-08-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestBCryptPasswordEncoder {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void encoding(){
        String str = "7";
        String result = passwordEncoder.encode(str);
        System.out.println("encoding result:" + result);

        String str2 = "$2a$10$xQjpgfaUOgFup8iqPFMHfekZyiUrVenTtPYgKyYESuxWTM.k1weKC";

        boolean flag = passwordEncoder.matches("1",str2);
        System.out.println("flag: " + flag);

    }

    @Test
    public void logtest() throws InterruptedException {
        log.info("thread test.............");
        log.error("测试日志。。。aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

}
