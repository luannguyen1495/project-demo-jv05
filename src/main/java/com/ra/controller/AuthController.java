package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    @GetMapping("/logon")
    public String logon(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "admin/logon";
    }

    @PostMapping("/logon")
    public String postLogon(@ModelAttribute("user") User user,
                            HttpSession httpSession){
        User userLogin = userService.logon(user.getEmail(),user.getPassword());

        if(userLogin != null){
            httpSession.setAttribute("admin",userLogin);
            return "redirect:/admin";
        }
        return "redirect:/logon";
    }

}
