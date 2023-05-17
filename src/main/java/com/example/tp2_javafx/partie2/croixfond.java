package com.example.tp2_javafx.partie2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;



public class croixfond extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane grille = new GridPane();

        Image rond_img = new Image(getClass().getResource("Rond.png").toString());
        Image croix_img = new Image(getClass().getResource("Croix.png").toString());
        Image vide_img = new Image(getClass().getResource("Vide.png").toString());

        ImageView rond = new ImageView(rond_img);
        ImageView croix = new ImageView(croix_img);
        ImageView vide = new ImageView(vide_img);

        ArrayList<Label> ListLabel = new ArrayList<>();
        ImageView[][] imageViewArray = new ImageView[3][3];

        Random random = new Random();
        int index = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                int nombre = random.nextInt(3);
                ListLabel.add(new Label(" "));
                imageViewArray[i][j] = new ImageView();
                switch (nombre) {
                    case 0:
                        imageViewArray[i][j].setImage(vide_img);
                        ListLabel.get(index).setGraphic(vide);
                        grille.add(ListLabel.get(index), j, i);
                        grille.add(imageViewArray[i][j], j, i);
                        break;
                    case 1:
                        imageViewArray[i][j].setImage(rond_img);
                        ListLabel.get(index).setGraphic(rond);
                        grille.add(ListLabel.get(index), j, i);
                        grille.add(imageViewArray[i][j], j, i);
                        break;
                    case 2:
                        imageViewArray[i][j].setImage(croix_img);
                        ListLabel.get(index).setGraphic(croix);
                        grille.add(ListLabel.get(index), j, i);
                        grille.add(imageViewArray[i][j], j, i);
                        break;
                }
                ++index;
            }
        }

        primaryStage.setTitle("croixfond");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(grille));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
