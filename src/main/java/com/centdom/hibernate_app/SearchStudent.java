package com.centdom.hibernate_app;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SearchStudent {

    private String param;

    private String paramValue;
}
