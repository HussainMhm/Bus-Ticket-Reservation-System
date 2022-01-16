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

public class AvailableJourneysController implements Initializable {
    @FXML private TextField source;
    @FXML private TextField destination;
    @FXML private DatePicker datePicker;
    @FXML private Button search;
    @FXML private Button continueBtn;
    @FXML private Button backBtn;
    @FXML private Button showAllBtn;

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

// Data to be transfered to next scene
    private String busId;
    private String src;
    private String dst;
    private String dat;
    private String dep;
    private String arv;
    private int totalSeats;

// setting connections and queries of database
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Bus bus = null;

// list to hold the records coming from database
    ObservableList<Bus> busList = FXCollections.observableArrayList();

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

    public void searchJourney(){
        String valFrom = source.getText();
        String valTo = destination.getText();
        String valDate = String.valueOf(datePicker.getValue());

        if (valFrom.isEmpty() || valTo.isEmpty() || valDate.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("All fields required");
            alert.showAndWait();
        }

        else {
            busList.clear();
            query = "SELECT * FROM Buses WHERE BusSource='"+valFrom+"' AND BusDestination='"+valTo+"' AND BusDate='"+valDate+"'";

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
    }

    public Boolean getSelected(){
        Bus bus = busesTable.getSelectionModel().getSelectedItem();
        if (bus==null) {
            return false;
        }
        else {
            busId = bus.getBusId();
            src = bus.getBusSource();
            dst = bus.getBusDestination();
            dat = bus.getDate().toString();
            dep = bus.getDepart();
            arv = bus.getArrival();
            totalSeats = bus.getTotalSeats();
            return true;
        }
    }

    public void goToFillInfo(ActionEvent event){
        if (getSelected()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fillPassengerInfo.fxml"));
                Parent parent = loader.load();

                //TRANSFERRING
                FillPassengerInfoController fillPassengerInfoController = loader.getController();
                fillPassengerInfoController.getData(busId, src, dst, dat, dep, arv, totalSeats);

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
            alert.setHeaderText(null);
            alert.setContentText("Select Journey!");
            alert.showAndWait();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        showBuses();
    }
}
