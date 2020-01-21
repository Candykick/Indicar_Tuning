package com.iindicar.indicar.model;

import com.google.gson.annotations.SerializedName;

public class Car {

    @SerializedName("car_name") private String carName;

    @SerializedName("car_name_kor") private String carNameKor;

    @SerializedName("company") private String company;

    @SerializedName("company_kor") private String companyKor;

    @SerializedName("img_url") private String carImageUrl;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarNameKor() {
        return carNameKor;
    }

    public void setCarNameKor(String carNameKor) {
        this.carNameKor = carNameKor;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyKor() {
        return companyKor;
    }

    public void setCompanyKor(String companyKor) {
        this.companyKor = companyKor;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }
}
