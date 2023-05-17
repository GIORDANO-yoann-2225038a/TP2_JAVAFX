package com.example.tp2_javafx.partie1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.security.cert.PolicyNode;


public class VBoxAndHBoxApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();

        // Création du conteneur principal
        VBox vbox = new VBox();

       // create File menu
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem openItem = new MenuItem("Open");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(newItem, openItem, saveItem, exitItem);

        Menu editMenu = new Menu("Edit");
        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        editMenu.getItems().addAll(cutItem, copyItem, pasteItem);

        // ajoute un menu dans la bar de menu
        menuBar.getMenus().addAll(fileMenu, editMenu, new Menu("Help"));

        // ajoute une bar de menu
        root.setTop(menuBar);

        // create left section with buttons
        VBox leftBox = new VBox();
        leftBox.setAlignment(Pos.CENTER);
        leftBox.setSpacing(10);

        Label buttonLabel = new Label("Boutons :");

        Button button1 = new Button("Bouton 1");
        Button button2 = new Button("Bouton 2");
        Button button3 = new Button("Bouton 3");
        leftBox.getChildren().addAll(buttonLabel,button1, button2, button3);

        // add separator between left section and center section
        root.setLeft(new HBox(leftBox, new Separator(Orientation.VERTICAL)));

        // create center section with form
        GridPane grilleFormulaire = new GridPane();
        grilleFormulaire.setAlignment(Pos.CENTER);
        grilleFormulaire.setHgap(10);
        grilleFormulaire.setVgap(10);
        grilleFormulaire.setPadding(new Insets(10));

        grilleFormulaire.addRow(0, new Label("Name:"), new TextField());
        grilleFormulaire.addRow(1, new Label("Email:"), new TextField());
        grilleFormulaire.addRow(2, new Label("Password:"), new TextField());

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        buttonBox.getChildren().addAll(new Button("Submit"), new Button("Cancel"));

        grilleFormulaire.add(buttonBox, 0, 3, 2, 1);

        root.setCenter(grilleFormulaire);

        // créer un bouton
        Label statusLabel = new Label("Ceci est un label de bas de page");
        VBox bas = new VBox(new Separator(Orientation.HORIZONTAL),statusLabel);
        bas.setAlignment(Pos.CENTER);
        root.setBottom(bas);


        // afficher la scene
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Premier exemple manipulant les conteneurs");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}