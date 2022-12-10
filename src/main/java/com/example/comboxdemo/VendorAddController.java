package com.example.comboxdemo;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;

public class VendorAddController {

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

    @FXML
    private TextField vendorName;
    @FXML
    private TextField vendorPhone;
    @FXML
    private TextField vendorAddress;
    @FXML
    private Label statusText;

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void add(ActionEvent e) throws SQLException {
        String newVendorName = vendorName.getText();
        String newVendorPhone = vendorPhone.getText();
        String newVendorAddress = vendorAddress.getText();
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost/javademo?" + "user=?&password=?");
            preparedStatement = connect.prepareStatement("INSERT INTO vendors VALUES (default,?,?,?)");
            preparedStatement.setString(1, newVendorName);
            preparedStatement.setString(2, newVendorPhone);
            preparedStatement.setString(3, newVendorAddress);
            preparedStatement.executeUpdate();
            statusText.setText("Vendor added!");
        } catch (Exception exc) {
            throw exc;
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
