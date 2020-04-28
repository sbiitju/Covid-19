package com.sbiitju.covid_19;

public class Ask {
   private String name,number,district,address,problem,type,date;

    public Ask(String name, String number, String district, String address, String problem, String type, String date) {
        this.name = name;
        this.number = number;
        this.district = district;
        this.address = address;
        this.problem = problem;
        this.type = type;
        this.date = date;
    }

    public Ask() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
