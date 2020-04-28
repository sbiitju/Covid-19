package com.sbiitju.covid_19;

public class DonationData {
    private String name,number,email,amount,type;

    public DonationData(String name, String number, String email, String amount, String type) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.amount = amount;
        this.type = type;
    }

    public DonationData() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
