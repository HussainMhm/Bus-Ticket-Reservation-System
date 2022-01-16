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
import java.util.Objects;

public class AdminLoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button passenger;

    public void adminLogin(ActionEvent event){
        if (username.getText().equals("admin") && password.getText().equals("admin")){
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminDashboard.fxml")));
                Scene scene = new Scene(parent);
                Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Wrong Username or Password!");
            alert.showAndWait();
        }
    }

    public void goToPassengerLogin(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("passengerLogin.fxml")));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
