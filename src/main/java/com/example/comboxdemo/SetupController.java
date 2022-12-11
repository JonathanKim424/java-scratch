package com.example.comboxdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;

import org.apache.ibatis.jdbc.ScriptRunner;
import io.github.cdimascio.dotenv.Dotenv;

public class SetupController {

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

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    Dotenv dotenv = Dotenv.load();
    public void resetDB(ActionEvent event) throws FileNotFoundException, SQLException {
        try {
            System.out.println(dotenv.get("DB_NAME"));
            System.out.println(dotenv.get("DB_USER"));
            System.out.println(dotenv.get("DB_PW"));
            connect = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s?" + "user=%s&password=%s",dotenv.get("DB_NAME"),dotenv.get("DB_USER"),dotenv.get("DB_PW")));
            System.out.println("Connection established...");
            ScriptRunner sr = new ScriptRunner(connect);
            Reader reader = new BufferedReader(new FileReader("src/main/resources/schema.sql"));
            sr.runScript(reader);
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
