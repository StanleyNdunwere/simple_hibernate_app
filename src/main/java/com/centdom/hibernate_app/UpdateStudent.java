package com.centdom.hibernate_app;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class UpdateStudent {

    private String firstName;

    private String lastName;

    private String email;

    private String oldFirstName;

    private String oldLastName;

    private String oldEmail;

}
