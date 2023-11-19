package com.example.myapplication.Model;

public class Image {
    private String resourceID;

    public Image(String resourceID) {
        this.resourceID = resourceID;
    }

    public String getResourceID() {
        return resourceID;
    }

    public void setResourceID(String resourceID) { this.resourceID = resourceID; }
}
