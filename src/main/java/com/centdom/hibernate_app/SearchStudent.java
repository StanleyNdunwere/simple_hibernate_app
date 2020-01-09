package com.centdom.hibernate_app;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Data
public class SearchStudent {

    @NotNull(message = "This field cannot be empty")
    @NotEmpty(message = "This field cannot be blank")
    private String param;

    @NotNull(message = "This field cannot be blank")
    @NotEmpty(message = "This field cannot be blank")
    private String paramValue;

    private List<String> paramType =  Arrays.asList("First Name", "Last Name", "Email");
}
