package com.agri.kissanTrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }
    @RequestMapping("/productManagement")
    public String productManagement(){
        return "productManagement";
    }


}

