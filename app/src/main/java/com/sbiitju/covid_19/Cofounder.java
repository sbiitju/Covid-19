package com.sbiitju.covid_19;

public class Cofounder {
    String name,number,email,institute,subject,district;

    public Cofounder(String name, String number, String email, String institute, String subject, String district) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.institute = institute;
        this.subject = subject;
        this.district = district;
    }

    public Cofounder() {
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

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
