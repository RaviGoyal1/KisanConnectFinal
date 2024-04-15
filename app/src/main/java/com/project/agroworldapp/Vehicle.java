package com.project.agroworldapp;

import com.project.agroworldapp.R;
import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private String name;
    private String location;
    private double price;
    private String ownerPhoneNumber;
    private int imageResource; // Resource ID for the vehicle's image

    public Vehicle(String name, String location, double price, String ownerPhoneNumber, int imageResource) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    // Static method to generate a list of Vehicle objects
    public static List<Vehicle> generateVehicleList() {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Vehicle("Car", "Location 1", 20000.0, "1234567890", R.drawable.disease));
        vehicleList.add(new Vehicle("Truck", "Location 2", 30000.0, "0987654321", R.drawable.disease));
        vehicleList.add(new Vehicle("Motorcycle", "Location 3", 10000.0, "9876543210", R.drawable.disease));
        // Add more vehicles as needed
        return vehicleList;
    }
}
