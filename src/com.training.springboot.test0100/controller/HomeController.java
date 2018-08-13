//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.training.springboot.test0100.controller;

import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    public HomeController() {
    }

    @GetMapping({"/home", "/"})
    public String home(HttpSession session) {
        String name = session.getAttribute("username") == null ? "no name" : (String)session.getAttribute("username");
        log.info("loading home page...name:{}", name);
        return "home";
    }
}
