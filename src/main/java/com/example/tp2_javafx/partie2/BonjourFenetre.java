package com.example.tp2_javafx.partie2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BonjourFenetre extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Création d'un conteneur VBox avec ses éléments centrés
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        // Création et ajout du label au conteneur
        Label helloLabel = new Label("Bonjour à tous !");
        vbox.getChildren().add(helloLabel);

        // Création de la scene
        Scene scene = new Scene(vbox);

        // Ajout de la scene à la fenêtre
        primaryStage.setScene(scene);

        primaryStage.setTitle("Hello application");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();

        // Ajout d'un champ de saisi de texte de taille 180 pixels
        TextField nameField = new TextField("Veuillez saisir un nom");
        nameField.setMaxWidth(180.0d);
        nameField.setFont(Font.font("Courier", FontWeight.NORMAL, 12));
        vbox.getChildren().add(nameField);

        // Ajout d'un bouton avec du texte
        Button button = new Button("Dire bonjour");
        vbox.getChildren().add(button);

        // Chargement de l'image
        Image image = new Image(BonjourFenetre.class.getResource("silver_button.png").toString());

        // Création d'un composant avec l'image peinte à l'intérieur
        ImageView iv = new ImageView();
        iv.setImage(image);

        // Intégration de l'image dans le bouton
        button.setGraphic(iv);

        // Changement du texte après un clic sur le bouton
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, actionEvent -> {
            helloLabel.setText("Bonjour à toi, " + nameField.getText());
        });

        // Création d'un gestionnaire d'événements pour le champ de saisie de texte
        EventHandler<ActionEvent> textFieldHandler = event -> {
            helloLabel.setText("Bonjour à toi, " + nameField.getText());
        };

        // Associer le gestionnaire d'événements au champ de saisie de texte
        nameField.setOnAction(textFieldHandler);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
