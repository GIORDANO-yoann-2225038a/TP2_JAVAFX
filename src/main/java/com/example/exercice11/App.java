package com.example.exercice11;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Exo11.fxml"));
        primaryStage.setTitle("Whale App");
        primaryStage.setScene(new Scene(root, 900, 400));
        primaryStage.show();
    }
}