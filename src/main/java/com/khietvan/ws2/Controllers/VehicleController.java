package com.khietvan.ws2.Controllers;

import com.khietvan.ws2.Models.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VehicleController implements Initializable {
    //With example data
    private ObservableList<Vehicle> m_vehicles = FXCollections.observableArrayList(
            new Vehicle("2018", "Honda", "Accord", "Sedan"),
            new Vehicle("2022", "Toyota", "Camry", "Sedan"),
            new Vehicle("2009", "Toyota", "Highlander", "SUV"),
            new Vehicle("2021", "Ford", "F-150", "Truck"),
            new Vehicle("2019", "Chevrolet", "Malibu", "Sedan"),
            new Vehicle("2020", "Subaru", "Outback", "SUV"),
            new Vehicle("2023", "Tesla", "Model 3", "Sedan"),
            new Vehicle("2017", "Hyundai", "Elantra", "Sedan"),
            new Vehicle("2016", "Mazda", "CX-5", "SUV"),
            new Vehicle("2015", "Nissan", "Altima", "Sedan"),
            new Vehicle("2020", "BMW", "X5", "SUV"),
            new Vehicle("2022", "Kia", "Sportage", "SUV"),
            new Vehicle("2023", "Audi", "Q3", "SUV")
    );

    @FXML
    private TableView<Vehicle> table;

    @FXML
    private TableColumn<Vehicle, String> colMaintenance;

    @FXML
    private TableColumn<Vehicle, String> colMake;

    @FXML
    private TableColumn<Vehicle, String> colModel;

    @FXML
    private TableColumn<Vehicle, String> colType;

    @FXML
    private TableColumn<Vehicle, String> colUsage;

    @FXML
    private TableColumn<Vehicle, String> colYear;

    @FXML
    private Button logMaintenanceButton;

    @FXML
    private Button logUsageButton;

    @FXML
    private TextField m_year;

    @FXML
    private TextField m_make;

    @FXML
    private TextField m_model;

    //Type option:
    @FXML
    private ComboBox<String> m_comboBox;

    @FXML
    private Button searchButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button clearButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        m_comboBox.setItems(FXCollections.observableArrayList("SUV","Sedan","HatchBack","Coupe","Truck"));

        // Set up the TableView columns
        colYear.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getM_year()));
        colMake.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getM_make()));
        colModel.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getM_model()));
        colType.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getM_type()));

        // Optional: You can leave Maintenance and Usage blank for now
        colMaintenance.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMaintenance()));
        colUsage.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getUsage()));

        // Bind the list to the table
        table.setItems(m_vehicles);

        //Click on the table:
        table.setOnMouseClicked(mouseEvent -> {
            Vehicle selected = table.getSelectionModel().getSelectedItem();
            if(selected!=null){
                m_year.setText(selected.getM_year());
                m_make.setText(selected.getM_make());
                m_model.setText(selected.getM_model());
                m_comboBox.getSelectionModel().select(selected.getM_type());
            }
        });
    }

    @FXML
    public void searchVehicle(ActionEvent event){
        Vehicle input = getData();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(input!= null){
            List<Vehicle> matches = new ArrayList<>();
            for(Vehicle v : m_vehicles){
                if(
                        v.getM_type().equalsIgnoreCase(input.getM_type()) &&
                                v.getM_model().equalsIgnoreCase(input.getM_model()) &&
                                v.getM_year().equalsIgnoreCase(input.getM_year()) &&
                                v.getM_make().equalsIgnoreCase(input.getM_make())
                ){
                    matches.add(v);
                }
            }
            if(!matches.isEmpty()){
                table.setItems(FXCollections.observableArrayList(matches));
            }
        }
        else{
            alert.setTitle("NOT MATCH");
            alert.setContentText("No vehicle is matching with your input, please try again.");
            alert.show();
        }
    }

    @FXML
    private void clearSearch(ActionEvent event) {
        table.setItems(m_vehicles);// Show full list again
        m_year.setText("");
        m_make.setText("");
        m_model.setText("");
        m_comboBox.getSelectionModel().clearSelection(); //clear combo box
    }

    @FXML
    //Get and Validate
    public Vehicle getData(){
        Alert a = new Alert(Alert.AlertType.ERROR); //set alert = ERROR type
        String year = m_year.getText().trim();
        String make = m_make.getText().trim();
        String model = m_model.getText().trim();
        String type = m_comboBox.getSelectionModel().getSelectedItem();

        // Basic validation
        if (year.isEmpty() || make.isEmpty() || model.isEmpty() || type == null) {
            a.setContentText("Please fill in all fields.");
            a.show();
            return null;
        }
        return new Vehicle(year,make,model,type);
    }

    @FXML
    public void saveData(ActionEvent event){
        Vehicle temp = getData();
        System.out.println(temp);
        if(temp != null){
            m_vehicles.add(temp);
        }
    }

    @FXML
    private void handleMaintenance(ActionEvent e) throws IOException {
        Vehicle selected = table.getSelectionModel().getSelectedItem();

        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a vehicle from the table first.");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/khietvan/Views/MaintenanceDialog.fxml"));
        Parent root = loader.load();

        // Get the controller and pass the vehicle
        MaintenanceController controller = loader.getController();
        controller.setVehicle(selected);

        Stage stage = new Stage();
        stage.setTitle("Vehicle Maintenance");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Refresh table to reflect updated data
        table.refresh();
    }

    @FXML
    public void handleUsage(ActionEvent event) throws IOException{
        Vehicle selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a vehicle from the table first.");
            alert.show();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/khietvan/Views/UsageDialog.fxml"));
        Parent root = loader.load();

        // Get the controller and pass the vehicle
        UsageController controller = loader.getController();
        controller.setVehicle(selected);

        Stage stage = new Stage();
        stage.setTitle("Vehicle Usage");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Refresh table to reflect updated data
        table.refresh();
    }
}

