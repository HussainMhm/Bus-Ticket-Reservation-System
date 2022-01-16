module com.example.schoolprojectjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.schoolprojectjava to javafx.fxml;
    exports com.example.schoolprojectjava;
}