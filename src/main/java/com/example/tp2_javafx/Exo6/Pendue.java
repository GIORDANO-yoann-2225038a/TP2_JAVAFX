package com.example.tp2_javafx.Exo6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Pendue extends Application {
    private String motSecret;
    private int viesRestantes = 7;
    private StringBuilder motActuel;

    private Label labelMotActuel;
    private Label labelViesRestantes;
    private ImageView imageViewPendu;
    private GridPane clavierGrid;

    private List<String> motsPossibles = Arrays.asList("PENDU", "JAVA", "PROGRAMMATION", "ORDINATEUR", "OPENAI");

    @Override
    public void start(Stage primaryStage) {
        choisirMotSecret();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: lightblue;");

        // Création de l'image du pendu
        imageViewPendu = new ImageView();
        VBox penduBox = new VBox(imageViewPendu);
        penduBox.setAlignment(Pos.TOP_CENTER);
        root.setTop(penduBox);

        // Création du label pour afficher le mot actuel
        labelMotActuel = new Label();
        labelMotActuel.setTextFill(Color.BLACK);
        labelMotActuel.setStyle("-fx-font-size: 24px;");
        VBox motActuelBox = new VBox(labelMotActuel);
        motActuelBox.setAlignment(Pos.CENTER);
        root.setCenter(motActuelBox);

        // Création du clavier avec les lettres de l'alphabet
        clavierGrid = new GridPane();
        clavierGrid.setHgap(10);
        clavierGrid.setVgap(10);
        clavierGrid.setPadding(new Insets(10));

        int row = 0;
        int col = 0;
        for (char lettre = 'A'; lettre <= 'Z'; lettre++) {
            Button lettreButton = new Button(Character.toString(lettre));
            lettreButton.setOnAction(event -> verifierLettre(lettreButton.getText().toUpperCase()));

            clavierGrid.add(lettreButton, col, row);
            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }

        VBox clavierBox = new VBox(clavierGrid);
        clavierBox.setAlignment(Pos.CENTER);
        clavierBox.setPadding(new Insets(10));
        root.setBottom(clavierBox);

        // Création du label pour afficher le nombre de vies restantes
        labelViesRestantes = new Label("Vies restantes : " + viesRestantes);
        labelViesRestantes.setStyle("-fx-font-size: 16px;");
        HBox viesRestantesBox = new HBox(labelViesRestantes);
        viesRestantesBox.setAlignment(Pos.CENTER_RIGHT);
        viesRestantesBox.setPadding(new Insets(10));
        root.setRight(viesRestantesBox);    // Création de la scène
        Scene scene = new Scene(root, 800, 800);
        primaryStage.setTitle("Jeu du Pendu");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Initialisation du mot actuel
        motActuel = new StringBuilder();
        for (int i = 0; i < motSecret.length(); i++) {
            motActuel.append("-");
        }
        labelMotActuel.setText(motActuel.toString());

        // Affichage de l'image du pendu
        mettreAJourImagePendu();

    }

    private void choisirMotSecret() {
        Random random = new Random();
        int index = random.nextInt(motsPossibles.size());
        motSecret = motsPossibles.get(index);
    }

    private void verifierLettre(String lettre) {
        boolean lettreTrouvee = false;
        for (int i = 0; i < motSecret.length(); i++) {
            if (Character.toString(motSecret.charAt(i)).equals(lettre)) {
                motActuel.setCharAt(i, lettre.charAt(0));
                lettreTrouvee = true;
            }
        }

        if (!lettreTrouvee) {
            viesRestantes--;
            mettreAJourImagePendu();
        }

        if (viesRestantes == 0) {
            finPartie(false);
        } else if (motActuel.indexOf("-") == -1) {
            finPartie(true);
        } else {
            labelMotActuel.setText(motActuel.toString());
            labelViesRestantes.setText("Vies restantes : " + viesRestantes);
        }
    }

    private void mettreAJourImagePendu() {
        int indexImage = 7 - viesRestantes;
        Image imagePendu = new Image(getClass().getResourceAsStream("pendu" + indexImage + ".png"));
        imageViewPendu.setImage(imagePendu);
    }

    private void finPartie(boolean victoire) {
        // Désactiver le clavier
        disableClavier();

        if (victoire) {
            labelMotActuel.setText("Bravo ! Vous avez trouvé le mot : " + motActuel.toString());
            Image imageWin = new Image(getClass().getResourceAsStream("penduWin.png"));
            imageViewPendu.setImage(imageWin);
        } else {
            labelMotActuel.setText("Dommage ! Le mot était : " + motSecret);
            Image imageLose = new Image(getClass().getResourceAsStream("penduLose.png"));
            imageViewPendu.setImage(imageLose);
        }
    }

    private void disableClavier() {
        // Désactiver tous les boutons du clavier
        for (Node node : clavierGrid.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}