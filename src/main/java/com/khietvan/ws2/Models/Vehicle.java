package com.khietvan.ws2.Models;

import com.khietvan.ws2.Controllers.MaintenanceController;
import javafx.event.ActionEvent;

public class Vehicle {
    private String year;
    private String make;
    private String model;
    private String type;
    private String maintenance;
    private String usage;

    public Vehicle(String m_year, String m_make, String m_model, String m_type) {
        this.year = m_year;
        this.make = m_make;
        this.model = m_model;
        this.type = m_type;
    }

    public Vehicle(){
        this.year = null;
        this.make = null;
        this.model = null;
        this.type = null;
    }

    public String getM_year() {
        return year;
    }

    public void setM_year(String m_year) {
        this.year = m_year;
    }

    public String getM_make() {
        return make;
    }

    public void setM_make(String m_make) {
        this.make = m_make;
    }

    public String getM_model() {
        return model;
    }

    public void setM_model(String m_model) {
        this.model = m_model;
    }

    public String getM_type() {
        return type;
    }

    public void setM_type(String m_type) {
        this.type = m_type;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "year='" + year + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
