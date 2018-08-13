//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.training.springboot.test0100.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public LoginController() {
    }

    @GetMapping({"/login"})
    public String loginPage(Model model, HttpSession session, @RequestParam(value = "error",required = false) boolean error, @RequestParam(value = "test",required = false) String test) {
        log.info("loading login page...");
        if (error) {
            AuthenticationException authenticationException = (AuthenticationException)session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            if (authenticationException instanceof BadCredentialsException) {
                log.error("登录失败，密码错误。", authenticationException);
                model.addAttribute("errorStatus", 1);
            } else if (authenticationException instanceof UsernameNotFoundException) {
                log.error("登录失败，用户名不存在。", authenticationException);
                model.addAttribute("errorStatus", 2);
            } else {
                log.error("登录失败，其他认证原因", authenticationException);
                model.addAttribute("errorStatus", -1);
            }
        }

        return "login/login";
    }
}
