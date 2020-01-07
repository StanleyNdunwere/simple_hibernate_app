package com.centdom.hibernate_app;

import lombok.NonNull;
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

import javax.persistence.EntityManager;
import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {

    @Autowired
    private Student student;
    public EntityManager manager;
    private SessionFactory fac;

    @Autowired
    public HomeController(EntityManager manager) {
        String man = (manager != null) ?
                "manager created" : "manager not created";
        log.info(man);
        this.manager = manager;
    }

    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("student", this.student);
        return "home";
    }

    @PostMapping
    public String showEnteredDetails(Model model, @Valid Student student, Errors errors) {
        if (!errors.hasErrors()) {
            log.info(student.toString());
            Session session = manager.unwrap(Session.class);
            boolean saveSuccessful = this.saveStudentToDatabase(student, session);
            if (saveSuccessful) {
                model.addAttribute("student", student);
                return "submitted-form";
            }else{
                model.addAttribute("failed", "Failed To Save Record");
                return "home";
            }
        }
        return "home";
    }


    private boolean saveStudentToDatabase(@NonNull Student student, Session session) {
        try {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
