package com.training.springboot.test0100;

import com.training.springboot.test0100.entity.User;
import com.training.springboot.test0100.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by 田伟 on 2018-08-20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegister(){
        User user = userService.register("test05","1","18208200005");
        System.out.println(user);
    }



}
