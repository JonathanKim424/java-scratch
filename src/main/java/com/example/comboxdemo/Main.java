package com.example.comboxdemo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


//        String items[] = {
//                "Monday",
//                "Tuesday",
//                "Wednesday",
//                "Thursday",
//                "Friday"
//        };
//        SearchableComboBox comboBox1 = new SearchableComboBox(FXCollections.observableArrayList(items));
//        HBox root = new HBox();
//        root.getChildren().add(comboBox1);
//        Scene scene = new Scene(root);
    }

    public static void main(String[] args) {
        launch();
    }
}