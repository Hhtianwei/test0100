//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.training.springboot.test0100.controller;

import com.fasterxml.jackson.annotation.JsonView;

import com.training.springboot.test0100.entity.StudentModel;
import com.training.springboot.test0100.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);
    @Autowired
    private StudentRepository studentRepository;

    public HelloController() {
    }

    @GetMapping({"/hello"})
    public String sayHello() {
        return "hello world";
    }

    @GetMapping({"/testStu"})
    @JsonView({StudentModel.DetailView.class})
    public StudentModel getStuById() {
        Optional<StudentModel> optional = this.studentRepository.findById(21L);
        return (StudentModel)optional.get();
    }
}
