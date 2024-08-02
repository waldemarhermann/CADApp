module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example to javafx.fxml;
    exports org.example;

    opens application to javafx.fxml, javafx.graphics;
    exports application;
}