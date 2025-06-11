package com.khietvan.ws2.Controllers;

import com.khietvan.ws2.Models.Vehicle;
import com.khietvan.ws2.Views.IFunctional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;


public class UsageController implements IFunctional {
    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField usageKilometer;

    private Vehicle selectedVehicle;

    public void setVehicle(Vehicle vel){
        this.selectedVehicle = vel;
    }

    @FXML
    @Override
    public void cancelAction(ActionEvent event){
        startDate.getEditor().clear();
        endDate.getEditor().clear();
        usageKilometer.setText("");
    }

    @FXML
    @Override
    public void confirmAction(ActionEvent event){
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setTitle("WARNING");
        try{
            Double kilometer = Double.parseDouble(usageKilometer.getText());

            LocalDate startD = startDate.getValue();
            LocalDate endD = endDate.getValue();
            LocalDate currentDate = LocalDate.now();
            if(startD.isAfter(endD) || endD.isAfter(currentDate)){
                a.setContentText("INVALID DATE");
                a.show();
            }
            else{
                String startDateString = startD.toString();
                String endDateString = endD.toString();
                if(!startDateString.isEmpty() && !endDateString.isEmpty() && selectedVehicle != null){
                    selectedVehicle.setUsage(startD +" - "+endD+" | Driven (km/h): " +kilometer);
                    closeWindow();
                }
            }
        } catch (NumberFormatException e) {
            a.setContentText("YOU MUST TYPE A VALID NUMBER OF KILOMETERS");
            a.show();
        }
    }

    @Override
    public void closeWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
