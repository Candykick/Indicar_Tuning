package com.iindicar.indicar.model;

import com.google.gson.annotations.SerializedName;

public class Version {

    @SerializedName("app_version") private int app_version;

    @SerializedName("car_spec_version") private String car_spec_version;

    public Version() {}

    public Version(int app_version, String car_spec_version) {
        this.app_version = app_version;
        this.car_spec_version = car_spec_version;
    }

    public int getApp_version() { return app_version; }

    public void setApp_version(int app_version) { this.app_version = app_version; }

    public String getCar_spec_version() { return car_spec_version; }

    public void setCar_spec_version(String car_spec_version) { this.car_spec_version = car_spec_version; }

}
