package com.centdom.hibernate_app;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {

    @Autowired
    private Student student;
    private SessionFactory fac;



    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("student", this.student);
        return "home";
    }

    @PostMapping
    public String showEnteredDetails(Model model, @Valid Student student, Errors errors) {
        if (!errors.hasErrors()) {
            model.addAttribute("student", student);
            return "submitted-form";
        }
        return "home";
    }
}
