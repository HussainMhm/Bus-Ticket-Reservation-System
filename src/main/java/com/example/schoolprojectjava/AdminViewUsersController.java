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

public class AdminViewUsersController implements Initializable {

    @FXML
    private Button backBtn;
    @FXML
    private Button showUsersBtn;

    @FXML
    private TableView<User> allUsersTable;
    @FXML
    private TableColumn<User, String> col_age;
    @FXML
    private TableColumn<User, String> col_gender;
    @FXML
    private TableColumn<User, String> col_mail;
    @FXML
    private TableColumn<User, String> col_name;
    @FXML
    private TableColumn<User, String> col_phone;

// setting connections and queries of database
    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    User user = null;

// list to hold the records coming from database
    ObservableList<User> userList = FXCollections.observableArrayList();

    public void showUsers(){
        userList.clear();
        query = "SELECT * FROM UserAccounts";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                userList.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("phone"),
                        resultSet.getString("age"),
                        resultSet.getString("gender")));
                allUsersTable.setItems(userList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();

    // Enter data to table cells from the database
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
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
}
