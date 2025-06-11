module com.khietvan.ws2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.khietvan.ws2 to javafx.fxml;
    exports com.khietvan.ws2;
    exports com.khietvan.ws2.Controllers;
    opens com.khietvan.ws2.Controllers to javafx.fxml;
}