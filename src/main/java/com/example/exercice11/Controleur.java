package com.example.exercice11;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    @FXML
    private String date1 = "Date 1";
    @FXML
    private String date2 = "Date 2";
    @FXML
    private String date3 = "Date 3";

    @FXML
    private int nbDate1 = 0;
    @FXML
    private int nbDate2 = 0;
    @FXML
    private int nbDate3 = 0;

    @FXML
    private TextField Date1;
    @FXML
    private TextField Date2;
    @FXML
    private TextField Date3;

    @FXML
    private Button bouton;

    @FXML
    private CategoryAxis xaxe = new CategoryAxis();
    @FXML
    private NumberAxis yaxe = new NumberAxis(0, 15, 0);

    @FXML
    private BarChart<String, Number> graphique = new BarChart<>(xaxe, yaxe);

    @FXML
    private XYChart.Series<String, Number> serie = new XYChart.Series<>();

    @FXML
    public void update() {
        serie.getData().clear();
        if (!Date2.getText().isEmpty()) {
            if (isInteger(Date2.getText())) {
                nbDate2 = Integer.parseInt(Date2.getText());
                serie.getData().add(new XYChart.Data<>(date2, nbDate2));
            } else {
                showErrorAlert("Invalid Input", "Please enter a valid integer for Date 2.");
            }
        }
        if (!Date1.getText().isEmpty()) {
            if (isInteger(Date1.getText())) {
                nbDate1 = Integer.parseInt(Date1.getText());
                serie.getData().add(new XYChart.Data<>(date1, nbDate1));
            } else {
                showErrorAlert("Invalid Input", "Please enter a valid integer for Date 1.");
            }
        }

        if (!Date3.getText().isEmpty()) {
            if (isInteger(Date3.getText())) {
                nbDate3 = Integer.parseInt(Date3.getText());
                serie.getData().add(new XYChart.Data<>(date3, nbDate3));
            } else {
                showErrorAlert("Invalid Input", "Please enter a valid integer for Date 3.");
            }
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initializing controller...");
        xaxe.setLabel("Date");
        yaxe.setLabel("Population");
        serie.setName("Évolution du nombre de baleines");
        graphique.setAnimated(true);
        graphique.getData().add(serie);
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}