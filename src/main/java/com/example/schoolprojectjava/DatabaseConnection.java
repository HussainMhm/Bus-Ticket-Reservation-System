package com.example.schoolprojectjava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "bus_reservation_system";
        String databaseUser = "root";
        String databasePassword = "asdasd@123";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
