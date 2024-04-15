package com.project.agroworldapp.viewmodel;

public class CartModel {
    private int productImageResource; // Resource ID for the product image
    private String productName;
    private double productPrice;
    private int itemCount;

    public CartModel(int productImageResource, String productName, double productPrice, int itemCount) {
        this.productImageResource = productImageResource;
        this.productName = productName;
        this.productPrice = productPrice;
        this.itemCount = itemCount;
    }

    public int getProductImageResource() {
        return productImageResource;
    }

    public void setProductImageResource(int productImageResource) {
        this.productImageResource = productImageResource;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}

