package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(@CookieValue(required = false,name = "email") String emailCookie, Model model, HttpServletRequest request){
        User user = new User();
        // check cuc co kho
        if(emailCookie != null){
            user.setEmail(emailCookie);
            model.addAttribute("checked",true);
        }
        model.addAttribute("user",user);
        return "login";
    }
    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user,
                            HttpSession httpSession,
                            RedirectAttributes redirectAttributes,
                            @RequestParam(required = false, name = "checked") Boolean isCheck,
                            HttpServletResponse response,HttpServletRequest request){
        User userLogin = userService.login(user.getEmail(),user.getPassword());

        if(isCheck !=null){
            Cookie cookieEmail = new Cookie("email",user.getEmail());
            cookieEmail.setMaxAge(24*60*60);
            response.addCookie(cookieEmail);

       }else {
            Cookie[] cookie = request.getCookies();
            for (int i = 0; i < cookie.length ; i++) {
                if(cookie[i].getName().equals("email")){
                    cookie[i].setMaxAge(0);
                    response.addCookie(cookie[i]);
                    break;
                }
            }

        }
        if(userLogin != null)
        {
            httpSession.setAttribute("user",userLogin);
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("err","Sai tt dang nhap");
        return  "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user){

        if(userService.register(user)){
            return "login";
        }
        return "register";
    }
}
