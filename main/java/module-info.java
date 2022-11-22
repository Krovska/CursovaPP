module com.example.cursova2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.cursova2 to javafx.fxml;
    exports com.example.cursova2;

    exports com.example.cursova2.controller;
    opens com.example.cursova2.controller to javafx.fxml;
}