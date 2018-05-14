package com.sakarisson.kristian.androidassignment7;

import java.util.Date;

public class Call {
    private String number;
    private double latitude;
    private double longitude;
    private String timestamp;

    public Call() {}

    public Call(String number, double latitude, double longitude, String timestamp) {
        this.setNumber(number);
        this.setLatitude(latitude);
        this.setLongitude(longitude);
        this.setTimestamp(timestamp);
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

    public String getTimestamp() {
        return timestamp;
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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
