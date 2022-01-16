package com.example.schoolprojectjava;

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

public class UpdateInfoController{
    @FXML
    private TextField name, mail, password, phone, age;
    @FXML
    private ToggleGroup gender;
    @FXML
    private RadioButton male, female;
    @FXML
    private Button update;
    @FXML
    private Button back;

// setting connections and queries of database
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Bus bus = null;

    public void updateInfo(){
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.getConnection();

            String valName = name.getText();
            String valMail = mail.getText();
            String valPassword = password.getText();
            String valPhone = phone.getText();
            String valAge = age.getText();

            String valGender = "";
            if (male.isSelected())
                valGender = "male";
            else if(female.isSelected())
                valGender = "female";

            query = "UPDATE UserAccounts set name= '"+valName+"',email= '"+valMail+"',password= '"+
                    valPassword+"',phone= '"+valPhone+"',age= '"+valAge+"', gender= '"+valGender+"' " +
                    "where email='"+PassengerLoginController.userMailIndicator +"' ";


            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Updated Succesfully");
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void goToPassengerDashboard(ActionEvent event){
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
    }

}
