package com.example.schoolprojectjava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminVewBookingsController implements Initializable {

    @FXML
    private Button showBookingsBtn;
    @FXML
    private Button backBtn;

    @FXML
    private TableView<Booking> bookingsTable;
    @FXML
    private TableColumn<Booking, String> col_arrive;
    @FXML
    private TableColumn<Booking, String> col_bus_id;
    @FXML
    private TableColumn<Booking, String> col_date;
    @FXML
    private TableColumn<Booking, String> col_depart;
    @FXML
    private TableColumn<Booking, String> col_from;
    @FXML
    private TableColumn<Booking, String> col_name;
    @FXML
    private TableColumn<Booking, String> col_phone;
    @FXML
    private TableColumn<Booking, String> col_tickets;
    @FXML
    private TableColumn<Booking, String> col_to;

// setting connections and queries of database
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Booking booking = null;

// list to hold the records coming from database
    ObservableList<Booking> bookingList = FXCollections.observableArrayList();

    public void showBookings(){
        bookingList.clear();
        query = "SELECT * FROM Bookings";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                bookingList.add(new Booking(
                        resultSet.getString("BusId"),
                        resultSet.getString("Name"),
                        resultSet.getString("Mail"),
                        resultSet.getString("Phone"),
                        resultSet.getString("NumberOfTickets"),
                        resultSet.getString("Source"),
                        resultSet.getString("Destination"),
                        resultSet.getString("Depart"),
                        resultSet.getString("Arrive"),
                        resultSet.getString("Date")));
                bookingsTable.setItems(bookingList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();

        // Enter data to table cells from the database
        col_bus_id.setCellValueFactory(new PropertyValueFactory<>("busId"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_from.setCellValueFactory(new PropertyValueFactory<>("source"));
        col_to.setCellValueFactory(new PropertyValueFactory<>("destination"));
        col_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        col_arrive.setCellValueFactory(new PropertyValueFactory<>("arrive"));
        col_tickets.setCellValueFactory(new PropertyValueFactory<>("numberOfTickets"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public void goToAdminHome(ActionEvent event){
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }
}
