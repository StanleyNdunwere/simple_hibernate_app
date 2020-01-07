package com.centdom.hibernate_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private Student student;

    @GetMapping
    public String showHome(Model model){
        model.addAttribute("student",  this.student);
        return "home";
    }

    @PostMapping
    public String showEnteredDetails(Model model, Student student){
        model.addAttribute("student", student);
        return "home";
    }
}
