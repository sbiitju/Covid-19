package com.sbiitju.covid_19;

public class FundInfo {
    private  String name,amount,reason;

    public FundInfo(String name, String amount, String reason) {
        this.name = name;
        this.amount = amount;
        this.reason = reason;
    }

    public FundInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
