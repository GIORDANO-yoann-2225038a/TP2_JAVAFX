package com.example.tp2_javafx.partie2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

 public class Couleurfond extends Application {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 200;

    private Pane centerPane;
    private int greenButtonClickCount = 0;
    private int redButtonClickCount = 0;
    private int blueButtonClickCount = 0;
    private Label resultLabel; // Déclaration de la variable resultLabel

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Couleur Fond");

        BorderPane root = new BorderPane();
        root.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Top - Label
        resultLabel = new Label("Aucun bouton n'a été cliqué");
        resultLabel.setStyle("-fx-font-size: 20;");
        BorderPane.setAlignment(resultLabel, Pos.CENTER);
        root.setTop(resultLabel);

        // Center - Pane
        centerPane = new Pane();
        root.setCenter(centerPane);

        // Bottom - Button
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        Button greenButton = new Button("Vert");
        Button redButton = new Button("Rouge");
        Button blueButton = new Button("Bleu");
        buttonBox.getChildren().addAll(greenButton, redButton, blueButton);
        root.setBottom(buttonBox);

        // Event handlers for buttons
        greenButton.setOnAction(e -> {
            greenButtonClickCount++;
            changeColor("green");
            updateResultLabel("Vert", greenButtonClickCount);
        });
        redButton.setOnAction(e -> {
            redButtonClickCount++;
            changeColor("red");
            updateResultLabel("Rouge", redButtonClickCount);
        });
        blueButton.setOnAction(e -> {
            blueButtonClickCount++;
            changeColor("blue");
            updateResultLabel("Bleu", blueButtonClickCount);
        });

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createColorButton(String text, String style) {
        Button button = new Button(text);
        button.setPrefSize(80, 40);
        button.setStyle(style);
        return button;
    }

    private void changeColor(String color) {
        centerPane.setStyle("-fx-background-color: " + color + ";");
    }

    private void updateResultLabel(String buttonColor, int clickCount) {
        String labelText = buttonColor + " choisi " + clickCount + " fois";
        resultLabel.setText(labelText);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
