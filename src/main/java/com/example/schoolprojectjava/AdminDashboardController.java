package com.example.schoolprojectjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminDashboardController {
    @FXML
    private Button addBusBtn;
    @FXML
    private Button viewBookingBtn;
    @FXML
    private Button viewAllUsersBtn;
//    @FXML
//    private Button passengerLoginBtn;
    @FXML
    private Button backBtn;

    public void goToAddBus(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminAddBuses.fxml")));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToViewBooking(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminViewBookings.fxml")));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToViewUsers(ActionEvent event){
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("adminViewUsers.fxml")));
            Scene scene = new Scene(parent);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void bookTickets(ActionEvent event){
//        try {
//            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("availableJourneys.fxml")));
//            Scene scene = new Scene(parent);
//            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
//            window.setScene(scene);
//            window.show();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    public void signOut(ActionEvent event){
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
}
