package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user, HttpSession httpSession, RedirectAttributes redirectAttributes){
        if(userService.login(user))
        {
            httpSession.setAttribute("email",user.getEmail());
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("err","Sai tt dang nhap");
        return  "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("email");
        return "redirect:/";
    }
}
