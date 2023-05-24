package com.example.tp2_javafx.TP2;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Exo1 extends Application {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 200;

    private Pane centerPane;
    private int greenButtonClickCount = 0;
    private int redButtonClickCount = 0;
    private int blueButtonClickCount = 0;
    private Label resultLabel;
    private IntegerProperty nbFois;
    private StringProperty message;
    private StringProperty couleurPanneau;
    private BorderPane root;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Couleur Fond");

        root = new BorderPane();
        root.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);;

        // Top - Label
        resultLabel = new Label();
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

        // Initialize properties
        nbFois = new SimpleIntegerProperty(0);
        message = new SimpleStringProperty("");
        couleurPanneau = new SimpleStringProperty("#000000");

        // Event handlers for buttons
        greenButton.setOnAction(e -> {
            greenButtonClickCount++;
            nbFois.set(greenButtonClickCount);
            couleurPanneau.set("green");
            message.set("Le Vert est une jolie couleur !");
        });

        redButton.setOnAction(e -> {
            redButtonClickCount++;
            nbFois.set(redButtonClickCount);
            couleurPanneau.set("red");
            message.set("Le Rouge est une jolie couleur !");
        });

        blueButton.setOnAction(e -> {
            blueButtonClickCount++;
            nbFois.set(blueButtonClickCount);
            couleurPanneau.set("blue");
            message.set("Le Bleu est une jolie couleur !");
        });

        // Bindings
        createBindings();

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createBindings() {
        BooleanProperty pasEncoreDeClic = new SimpleBooleanProperty(true);
        pasEncoreDeClic.bind(nbFois.isEqualTo(0));

        resultLabel.textProperty().bind(
                Bindings.concat(
                        Bindings.when(pasEncoreDeClic)
                                .then("Aucun bouton n'a été cliqué")
                                .otherwise(
                                        Bindings.concat(
                                                nbFois.asString(),
                                                " ",
                                                Bindings.when(nbFois.isEqualTo(1))
                                                        .then("fois")
                                                        .otherwise("fois")
                                        )
                                )
                )
        );

        centerPane.styleProperty().bind(
                Bindings.concat("-fx-background-color: ", couleurPanneau, ";")
        );


        Label bottomLabel = new Label();
        bottomLabel.textProperty().bind(
                Bindings.when(pasEncoreDeClic)
                        .then("")
                        .otherwise(
                                Bindings.concat(
                                        Bindings.when(couleurPanneau.isEqualTo("green"))
                                                .then("-fx-text-fill: green; ")
                                                .otherwise(
                                                        Bindings.when(couleurPanneau.isEqualTo("red"))
                                                                .then("-fx-text-fill: red; ")
                                                                .otherwise("-fx-text-fill: blue; ")
                                                ),
                                        message
                                )
                        )
        );
        BorderPane.setAlignment(bottomLabel, Pos.CENTER);
        root.setBottom(bottomLabel);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

