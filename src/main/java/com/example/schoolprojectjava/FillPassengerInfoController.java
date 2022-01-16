package com.example.schoolprojectjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class FillPassengerInfoController {
    @FXML
    private TextField journeySrc;
    @FXML
    private TextField journeyDest;
    @FXML
    private TextField journeyDate;
    @FXML
    private TextField journeyId;

    @FXML
    private Button complete;
    @FXML
    private TextField passengerName;
    @FXML
    private TextField passengerPhone;
    @FXML
    private TextField passengerMail;
    @FXML
    private TextField noOfTickets;
    @FXML
    private Button backBtn;

    private String val_id;
    private String val_source;
    private String val_destination;
    private String val_date;
    private String val_depart;
    private String val_arrive;
    private int val_total_seats;

// setting connections and queries of database
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Bus bus = null;

    public void getData(String id ,String source, String destination, String date, String depart, String arrive, int seats){
        journeyId.setText(id);
        journeySrc.setText(source);
        journeyDest.setText(destination);
        journeyDate.setText(date);
        passengerMail.setText(PassengerLoginController.userMailIndicator);

        val_id = id;
        val_source = source;
        val_destination = destination;
        val_date = date;
        val_depart = depart;
        val_arrive = arrive;
        val_total_seats = seats;
    }

    public void completeBooking(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();

        // Check that all fields are entered
        String s_busId = val_id;
        String s_name = passengerName.getText();
        String s_mail = passengerMail.getText();
        String s_phone = passengerPhone.getText();
        String s_numberOfTickets = noOfTickets.getText();
        String s_source = val_source;
        String s_destination = val_destination;
        String s_depart = val_depart;
        String s_arrive = val_arrive;
        String s_date = val_date;

        if (s_name.isEmpty() || s_mail.isEmpty() || s_numberOfTickets.isEmpty() || s_phone.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Fields");
            alert.showAndWait();
        } else {
            bookingQuery();
            insertBookingQuery();
            decreaseSeats();
        }
    }

    private void bookingQuery() {

        String insertFields = "INSERT INTO Bookings(BusId, Name, Mail, Phone, NumberOfTickets, Source, Destination, Depart, Arrive, Date) VALUES ('";
        String insertValues = val_id + "', '" + passengerName.getText() + "', '" + passengerMail.getText() + "', '" + passengerPhone.getText() +
                "', '" + noOfTickets.getText() + "', '" + val_source + "', '" + val_destination + "', '" + val_depart + "', '" + val_arrive
                + "', '" + val_date + "')";

        query = insertFields + insertValues;
    }

    private void insertBookingQuery() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate(query);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Booking Done Successfully!");
            alert.showAndWait();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void decreaseSeats(){
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            connection = databaseConnection.getConnection();

            String busId = val_id;
            int totalSeats = val_total_seats;
            int ticketsBooked = Integer.parseInt(noOfTickets.getText());
            String seatsLeft = String.valueOf(totalSeats - ticketsBooked);

            query = "UPDATE Buses set AvailableSeats = '" + seatsLeft + "' where BusId='"+busId+"' ";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();

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
