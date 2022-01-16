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
import javafx.scene.control.*;
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

public class AdminAddBusesController implements Initializable {

// Nodes on scenebuilder screen
    @FXML private TextField busId;
    @FXML private TextField busSource;
    @FXML private TextField busDestination;
    @FXML private TextField busTicketPrice;
    @FXML private TextField busDepartTime;
    @FXML private TextField busArrivalTime;
    @FXML private TextField busTotalSeats;
    @FXML private DatePicker busDate;
    @FXML private Button addBusBtn;
    @FXML private Button back;
    @FXML private Button showBusesBtn;

// Table and columns in scenebuilder
    @FXML private TableView<Bus> busesTable;
    @FXML private TableColumn<Bus, String> col_id;
    @FXML private TableColumn<Bus, String> col_from;
    @FXML private TableColumn<Bus, String> col_to;
    @FXML private TableColumn<Bus, String> col_date;
    @FXML private TableColumn<Bus, String> col_depart;
    @FXML private TableColumn<Bus, String> col_arrival;
    @FXML private TableColumn<Bus, String> col_total_seats;
    @FXML private TableColumn<Bus, String> col_available_seats;
    @FXML private TableColumn<Bus, String> col_price;

// setting connections and queries of database
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Bus bus = null;

// list to hold the records coming from database
    ObservableList<Bus> busList = FXCollections.observableArrayList();

// (ADD BUS BUTTON) FUNCTIONS
    public void addBus(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();

    // Check that all fields are entered
        String id = busId.getText();
        String source = busSource.getText();
        String destination = busDestination.getText();
        String price = busTicketPrice.getText();
        String depart = busDepartTime.getText();
        String arrival = busArrivalTime.getText();
        String totalSeat = busTotalSeats.getText();
        String date = String.valueOf(busDate.getValue());

        if (id.isEmpty() || source.isEmpty() || destination.isEmpty() || price.isEmpty() || depart.isEmpty() ||
                arrival.isEmpty() || totalSeat.isEmpty() || date.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Fields");
            alert.showAndWait();
        } else {
            addBusQuery();
            insertAddBusQuery();
            emptyFields();
        }
    }

        private void addBusQuery(){
        String insertFields = "INSERT INTO Buses(BusId, BusSource, BusDestination, BusDepart, BusArrival, TotalSeats, AvailableSeats, BusTicketPrice, BusDate) VALUES ('";
        String insertValues = busId.getText() + "', '" + busSource.getText() + "', '" + busDestination.getText() + "', '" + busDepartTime.getText() +
                "', '" + busArrivalTime.getText() + "', '" + busTotalSeats.getText() + "', '" + busTotalSeats.getText() + "', '" + busTicketPrice.getText() +
                "', '" + String.valueOf(busDate.getValue()) + "')";

        query = insertFields + insertValues;
    }

        private void insertAddBusQuery() {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate(query);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("Bus Succesfully Added!");
                alert.showAndWait();

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

        private void emptyFields(){
            busId.setText("");
            busSource.setText("");
            busDestination.setText("");
            busTicketPrice.setText("");
            busDepartTime.setText("");
            busArrivalTime.setText("");
            busTotalSeats.setText("");
        }

// (SHOW BUS BUTTON) FUNCTIONS
    public void showBuses(){
        busList.clear();
        query = "SELECT * FROM Buses";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                busList.add(new Bus(
                        resultSet.getString("BusId"),
                        resultSet.getString("BusSource"),
                        resultSet.getString("BusDestination"),
                        resultSet.getInt("BusTicketPrice"),
                        resultSet.getString("BusDepart"),
                        resultSet.getString("BusArrival"),
                        resultSet.getInt("TotalSeats"),
                        resultSet.getInt("AvailableSeats"),
                        resultSet.getDate("BusDate")));
                busesTable.setItems(busList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();

    // Enter data to table cells from the database
        col_id.setCellValueFactory(new PropertyValueFactory<>("busId"));
        col_from.setCellValueFactory(new PropertyValueFactory<>("busSource"));
        col_to.setCellValueFactory(new PropertyValueFactory<>("busDestination"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_depart.setCellValueFactory(new PropertyValueFactory<>("depart"));
        col_arrival.setCellValueFactory(new PropertyValueFactory<>("arrival"));
        col_total_seats.setCellValueFactory(new PropertyValueFactory<>("totalSeats"));
        col_available_seats.setCellValueFactory(new PropertyValueFactory<>("availableSeats"));
        col_price.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            loadData();
            showBuses();
    }

// (PREVIOUS PAGE BUTTON) FUNCTIONS
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
}
