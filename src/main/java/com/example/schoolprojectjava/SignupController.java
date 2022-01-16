package com.example.schoolprojectjava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SignupController {
    @FXML
    private TextField name, mail, phone, age;
    @FXML
    private PasswordField password;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton male, female;
    @FXML
    private Button signup;
    @FXML
    private Button back;

// setting connections and queries of database
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Bus bus = null;

// list to hold the records coming from database
    ObservableList<Bus> busList = FXCollections.observableArrayList();

    // (ADD BUS BUTTON) FUNCTIONS
    public void signUp(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();

        // Check that all fields are entered
        String s_name = name.getText();
        String s_mail = mail.getText();
        String s_password = password.getText();
        String s_phone = phone.getText();
        String s_age = age.getText();

        String s_gender = null;
            if (male.isSelected())
                s_gender = "male";
            else if (female.isSelected())
                s_gender = "female";

        if (s_name.isEmpty() || s_mail.isEmpty() || s_password.isEmpty() || s_phone.isEmpty() ||
                s_age.isEmpty() || s_gender.isEmpty()){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Fields");
            alert.showAndWait();
        } else {
            signupQuery();
            insertSignupQuery();
        }
    }

    private void signupQuery() {
        String s_gender = null;
        if (male.isSelected())
            s_gender = "male";
        else if (female.isSelected())
            s_gender = "female";

        String insertFields = "INSERT INTO UserAccounts(name, email, password, phone, age, gender) VALUES ('";
        String insertValues = name.getText() + "', '" + mail.getText() + "', '" + password.getText() + "', '" + phone.getText() +
                "', '" + age.getText() + "', '" + s_gender + "')";

        query = insertFields + insertValues;
    }

    private void insertSignupQuery() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Signed Up Succesfully!");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
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
