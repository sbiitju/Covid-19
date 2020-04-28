package com.sbiitju.covid_19;

public class UpoharModel {
    private  String title,description,fulldes;
    int imagedata;

    public UpoharModel() {
    }

    public UpoharModel(String title, String description, String fulldes, int imagedata) {
        this.title = title;
        this.description = description;
        this.fulldes = fulldes;
        this.imagedata = imagedata;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFulldes() {
        return fulldes;
    }

    public void setFulldes(String fulldes) {
        this.fulldes = fulldes;
    }

    public int getImagedata() {
        return imagedata;
    }

    public void setImagedata(int imagedata) {
        this.imagedata = imagedata;
    }
}
