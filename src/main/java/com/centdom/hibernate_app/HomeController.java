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
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/")
public class HomeController {

    private Student student;
    private EntityManager manager;
    private SearchStudent searchStudent;
    private Session session;

    @Autowired
    public HomeController(EntityManager manager, Student student, SearchStudent searchStudent) {
        String man = (searchStudent != null) ?
                "manager created" : "manager not created";
        this.manager = manager;
        this.student = student;
        this.searchStudent = searchStudent;
    }

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("student", this.student);
        return "home";
    }

    @GetMapping("/search")
    public String showSearch(Model model) {
        model.addAttribute("searchStudent", this.searchStudent);
        return "search";
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
            } else {
                model.addAttribute("failed", "Failed To Save Record");
                return "home";
            }
        }
        return "home";
    }

    @PostMapping("/search")
    public String searchByParameter(Model model, @Valid SearchStudent searchStudent, Errors errors) {
        log.info(searchStudent.toString());
        if (!errors.hasErrors()) {
            model.addAttribute("search_params", searchStudent);
            List<Student> students = this.retrieveStudentFromDatabase(searchStudent.getParam(), searchStudent.getParamValue());
            model.addAttribute("student", students);
            return "search-result";
        }

        return "search";
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

    @GetMapping("/delete")
    public String deleteRecord(Model model) {
        model.addAttribute("searchStudent", this.searchStudent);
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteRecord(Model model, @Valid SearchStudent searchStudent, Errors errors) {
        if (!errors.hasErrors()) {
            model.addAttribute("search_params", searchStudent);
            List<Student> students = this.retrieveStudentFromDatabase(searchStudent.getParam(), searchStudent.getParamValue());
            model.addAttribute("student", students);
            this.deleteStudentFromDatabase(searchStudent.getParam(), searchStudent.getParamValue());
            return "delete-result";
        }

        return "delete";
    }

    private List<Student> retrieveStudentFromDatabase(@NonNull String studentDetail, @NonNull String parameter) {
        String searchParam = this.switchSearchParams(studentDetail);
        String hql = "FROM Student where " + searchParam + " = " + "\'" + parameter + "\'";
        Session session = manager.unwrap(Session.class);
        session.beginTransaction();
        List<Student> students = session.createQuery(hql).getResultList();
        session.getTransaction().commit();
        return students;
    }

    private String switchSearchParams(String searchParam) {
        String studentDetail = "";
        switch (searchParam) {
            case "First Name":
                studentDetail = "firstName";
                break;
            case "Last Name":
                studentDetail = "lastName";
                break;
            case "Email":
                studentDetail = "email";
                break;
            default:
                studentDetail = "";
                break;
        }
        return studentDetail;
    }


    private void deleteStudentFromDatabase(@NonNull String studentDetail, @NonNull String parameter) {
        String detailToDelete = this.switchSearchParams(studentDetail);
        String hql = "Delete FROM Student where " + detailToDelete + " = " + "\'" + parameter + "\'";
        Session session = manager.unwrap(Session.class);
        session.beginTransaction();
        session.createQuery(hql).executeUpdate();
        session.getTransaction().commit();
    }

    @PostMapping("/error")
    public String errorPage() {
        return "error";
    }
}
