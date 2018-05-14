package com.sakarisson.kristian.androidassignment7;

public class Call {
    private String number;
    private double latitude;
    private double longitude;

    public Call() {}

    public Call(String number, double latitude, double longitude) {
        this.setNumber(number);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
    }

    public String getNumber() {
        return number;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
