module com.example.the7wonders {
   
    requires javafx.fxml;
    requires javafx.controls;

    opens com.example.the7wonders to javafx.fxml;
    exports com.example.the7wonders;
}