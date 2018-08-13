//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.training.springboot.test0100;

import com.tim.generator.NameGenerator;
import com.training.springboot.test0100.entity.StudentModel;
import com.training.springboot.test0100.repository.StudentRepository;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test0100ApplicationTests {
    @Autowired
    private StudentRepository studentRepository;

    public Test0100ApplicationTests() {
    }

    @Test
    public void contextLoads() {
        for(int i = 0; i < 9999; ++i) {
            StudentModel model = new StudentModel();
            model.setName(NameGenerator.generatorNoramlName());
            model.setAddress("永达国际" + (int)(Math.random() * 80.0D + 19.0D) + "号");
            model.setAge((int)(Math.random() * 80.0D + 19.0D));
            model.setCreateTime(new Date());
            this.studentRepository.save(model);
        }

        System.out.println("=======================end===========================:");
    }

    @Test
    public void test() {
        for(int i = 0; i < 100; ++i) {
            System.out.println("num:" + (int)(Math.random() * 80.0D + 19.0D));
        }

    }
}
