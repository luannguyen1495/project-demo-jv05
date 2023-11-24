package com.ra.controller;

import com.ra.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){

        return "home";
    }
    @RequestMapping("/about")
    public String about(Model model){

        model.addAttribute("user",new User());
        return "about";
    }

}
