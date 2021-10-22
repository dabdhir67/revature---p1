package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private Connection connection;

    public ConnectionService(){
        super();
        try{
            Class.forName("org.postgresql.Driver");

            String url = System.getenv("DB_URL");
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASS");

//            System.out.println("DB_URL: " + url + "DB_Username" + username + "DB_Password" + password);


            connection = DriverManager.getConnection(url,username,password);


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
