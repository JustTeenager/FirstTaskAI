module com.example.firsttaskai {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.example.firsttaskai to javafx.fxml;
    exports com.example.firsttaskai;
}