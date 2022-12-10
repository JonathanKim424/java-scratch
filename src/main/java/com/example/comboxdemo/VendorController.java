package com.example.comboxdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

import java.io.IOException;

public class VendorController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToHome(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToVendor(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Vendor.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToInvoice(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Invoice.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToVendorAdd(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("VendorAdd.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private ListView vendorList;
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    Dotenv dotenv = Dotenv.load();
    public void initialize() throws Exception {
        try {
            connect = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s?" + "user=%s&password=%s",dotenv.get("DB_NAME"),dotenv.get("DB_USER"),dotenv.get("DB_PW")));
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM vendors");
            while (resultSet.next()) {
                String vendorName = resultSet.getString("vendorname");
                vendorList.getItems().add(vendorName);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }

    public void close() throws SQLException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
