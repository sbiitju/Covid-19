package com.sbiitju.covid_19;

public class Demo {
    String country,continent,flag;
    long totaltests,cases,todaycases,deaths,todaydathes,recovered,todayrecovered,active,critical,getCasesperonemillion,deathsperonemilion;

    public Demo(String country, String continent, String flag, long totaltests, long cases, long todaycases, long deaths, long todaydathes, long recovered, long active, long critical, long getCasesperonemillion, long deathsperonemilion) {
        this.country = country;
        this.continent = continent;
        this.flag = flag;
        this.totaltests = totaltests;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydathes = todaydathes;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
        this.getCasesperonemillion = getCasesperonemillion;
        this.deathsperonemilion = deathsperonemilion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Demo() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public long getTotaltests() {
        return totaltests;
    }

    public void setTotaltests(long totaltests) {
        this.totaltests = totaltests;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }

    public long getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(long todaycases) {
        this.todaycases = todaycases;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getTodaydathes() {
        return todaydathes;
    }

    public void setTodaydathes(long todaydathes) {
        this.todaydathes = todaydathes;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getTodayrecovered() {
        return todayrecovered;
    }

    public void setTodayrecovered(long todayrecovered) {
        this.todayrecovered = todayrecovered;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }

    public long getCritical() {
        return critical;
    }

    public void setCritical(long critical) {
        this.critical = critical;
    }

    public long getGetCasesperonemillion() {
        return getCasesperonemillion;
    }

    public void setGetCasesperonemillion(long getCasesperonemillion) {
        this.getCasesperonemillion = getCasesperonemillion;
    }

    public long getDeathsperonemilion() {
        return deathsperonemilion;
    }

    public void setDeathsperonemilion(long deathsperonemilion) {
        this.deathsperonemilion = deathsperonemilion;
    }

    public Demo(String country, String continent, long totaltests, long cases, long todaycases, long deaths, long todaydathes, long recovered, long todayrecovered, long active, long critical, long getCasesperonemillion, long deathsperonemilion) {
        this.country = country;
        this.continent = continent;
        this.totaltests = totaltests;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydathes = todaydathes;
        this.recovered = recovered;
        this.todayrecovered = todayrecovered;
        this.active = active;
        this.critical = critical;
        this.getCasesperonemillion = getCasesperonemillion;
        this.deathsperonemilion = deathsperonemilion;
    }

    @Override
    public String toString() {
        return "Country: " + country + "\n" +
                "Continent: " + continent + "\n" +
                "Total Tests: " + totaltests +"\n" +
                "Cases: " + cases + "\n" +
                "Today New Cases: " + todaycases + "\n" +
                "Total Deaths: " + deaths + "\n" +
                "Today Deaths: " + todaydathes + "\n" +
                "Recovered: " + recovered + "\n" +
                "Today New Recovered: " + todayrecovered + "\n" +
                "Active Cases: " + active + "\n" +
                "Critical Cases: " + critical + "\n" +
                "Cases Per Million: " + getCasesperonemillion + "\n" +
                "Deaths Per Million: " + deathsperonemilion + "\n"
                ;
    }
}
