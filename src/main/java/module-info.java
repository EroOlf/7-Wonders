module com.example.the7wonders {
   
    requires javafx.fxml;
    requires javafx.controls;
    requires java.desktop;
    requires junit;

    opens com.example.the7wonders to javafx.fxml;
    exports com.example.the7wonders;
    exports com.example.the7wonders.tests;
}