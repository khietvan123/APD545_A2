/**********************************************
 Workshop #2
 Course:APD545 - SU25
 Last Name:Phan
 First Name:Khiet Van
 ID:147072235
 Section:NAA
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature
 Date:11th June, 2025
 **********************************************/
package com.khietvan.ws2.Controllers;

import com.khietvan.ws2.Models.IFunctional;
import com.khietvan.ws2.Models.Vehicle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class MaintenanceController implements IFunctional {

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField maintenanceDescription;

    private Vehicle selectedVehicle;

    public void setVehicle(Vehicle vehicle) {
        this.selectedVehicle = vehicle;
    }

    @FXML
    @Override
    public void cancelAction(ActionEvent event){
        maintenanceDescription.setText("");
        datePicker.getEditor().clear();
        closeWindow();
    }

    @FXML
    @Override
    public void confirmAction(ActionEvent e){
//        String date = datePicker.getEditor().getText();
        String desc = maintenanceDescription.getText();
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("WARNING");
        LocalDate date = datePicker.getValue();
        LocalDate currentDate = LocalDate.now();
        if(date.isAfter(currentDate)){
            a.setContentText("INVALID DATE");
            a.show();
        }
        else{
            String inputDate = date.toString();
            if (!inputDate.isEmpty() && !desc.isEmpty() && selectedVehicle != null) {
                selectedVehicle.setMaintenance(date + " | " + desc);
            }
            closeWindow();
        }
    }

    @Override
    public void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

}
