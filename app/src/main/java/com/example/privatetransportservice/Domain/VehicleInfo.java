package com.example.privatetransportservice.Domain;

public class VehicleInfo {
    String vehicleName;
    String vehicleNumber;
    int vehicleSeats;

    public VehicleInfo(String vehicleName, String vehicleNumber, int vehicleSeats) {
        this.vehicleName = vehicleName;
        this.vehicleNumber = vehicleNumber;
        this.vehicleSeats = vehicleSeats;
    }

    public VehicleInfo() {
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public int getVehicleSeats() {
        return vehicleSeats;
    }

    public void setVehicleSeats(int vehicleSeats) {
        this.vehicleSeats = vehicleSeats;
    }
}
