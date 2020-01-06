package com.centdom.hibernate_app;

import java.sql.Connection;
import java.sql.DriverManager;

public class testJdbc {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306?useSSL=false&serverTimezone=UTC";
        String user = "sndunwere";
        String password = "Jahdulle99.";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection.isClosed());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

