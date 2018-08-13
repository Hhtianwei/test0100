//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.training.springboot.test0100.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/stu"})
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    public StudentController() {
    }

    @RequestMapping
    public String testPage(Model model) {
        log.info("test spring boot page...");
        model.addAttribute("testAttr", "there is a SB super boy cccc");
        return "stu/stuList";
    }
}
