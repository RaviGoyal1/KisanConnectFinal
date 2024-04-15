package com.project.agroworldapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Crop implements Parcelable {
    private String name;
    private double price;
    private String ownerPhoneNumber;
    private int imageResource;

    private int quantity;
    public Crop(String name, double price, String ownerPhoneNumber, int imageResource,int quantity) {
        this.name = name;
        this.price = price;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.imageResource = imageResource;
        this.quantity=quantity;
    }

    protected Crop(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        ownerPhoneNumber = in.readString();
        imageResource = in.readInt();
        quantity=in.readInt();
    }

    public static final Creator<Crop> CREATOR = new Creator<Crop>() {
        @Override
        public Crop createFromParcel(Parcel in) {
            return new Crop(in);
        }

        @Override
        public Crop[] newArray(int size) {
            return new Crop[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeString(ownerPhoneNumber);
        parcel.writeInt(imageResource);
        parcel.writeInt(quantity);
    }
}
