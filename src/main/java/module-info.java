module com.example.tp2_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.example.tp2_javafx to javafx.fxml;
    exports com.example.tp2_javafx.partie2;
    exports com.example.tp2_javafx.partie1;
    exports com.example.tp2_javafx.Exo6;
    exports com.example.tp2_javafx.TP2;
}