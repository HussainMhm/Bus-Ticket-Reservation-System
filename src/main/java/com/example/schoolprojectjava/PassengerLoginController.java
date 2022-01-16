package com.example.schoolprojectjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class PassengerLoginController {

    public static String userMailIndicator = null;

    @FXML private TextField mailField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginBtn;
    @FXML private Button signupBtn;
    @FXML private Button adminLoginBtn;

    public void passengerLogin(ActionEvent event) {
        if (!mailField.getText().isBlank() && !passwordField.getText().isBlank()){
            validateLogin(event);
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Username and Password");
            alert.setHeaderText("Enter Username and Password");
            alert.showAndWait();
        }
    }

    public void goToSignUp(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup.fxml")));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToAdminLogin(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminLogin.fxml")));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validateLogin(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM UserAccounts WHERE email = '" + mailField.getText() + "' AND password = '" + passwordField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if (queryResult.getInt(1) == 1){
                    userMailIndicator = mailField.getText();
                    try {
                        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("passengerDashboard.fxml")));
                        Scene scene = new Scene(parent);
                        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                        window.setScene(scene);
                        window.show();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Mail and Password");
                    alert.setHeaderText("Wrong mail or password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
