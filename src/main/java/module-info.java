module com.example.k {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.k to javafx.fxml;
    exports com.example.k;
}