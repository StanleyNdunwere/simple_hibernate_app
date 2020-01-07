package com.centdom.hibernate_app;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
@Data
@Component
public class Student {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotEmpty(message = "This field cannot be empty")
    @NotNull(message = "This field cannot be empty")
    @Size(min = 3, max = 100, message = "Enter a valid First Name")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "This field cannot be empty")
    @NotNull(message = "This field cannot be empty")
    @Size(min = 3, max = 100, message = "Enter a valid Last Name")
    private String lastName;

    @Column(name = "email")
    @NotEmpty(message = "This field cannot be empty")
    @NotNull(message = "This field cannot be empty")
    @Size(min = 3, max = 100, message = "Email address field must contain valid characters and cannot be empty")
    @Email(message = "Enter a valid email address")
    private String email;

}
