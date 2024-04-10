module com.academix.academix {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.academix.academix to javafx.fxml;
    exports com.academix.academix;
}