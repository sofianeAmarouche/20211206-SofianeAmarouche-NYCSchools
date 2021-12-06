package com.app.school.management.system.app.schoolapp.model;

public class School {
    private String name;
    private String readscore;
    private String mathscore;
    private String writescore;
    private String overview;
    private String address;
    private String phone;
    private String website;
    private String latitude;
    private String longitude;


    public School() {
    }

    public School(String name, String readscore, String mathscore, String writescore, String overview, String address, String phone, String website , String latitude , String longitude) {
        this.name = name;
        this.readscore = readscore;
        this.mathscore = mathscore;
        this.writescore = writescore;
        this.overview = overview;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReadscore() {
        return readscore;
    }

    public void setReadscore(String readscore) {
        this.readscore = readscore;
    }

    public String getMathscore() {
        return mathscore;
    }

    public void setMathscore(String mathscore) {
        this.mathscore = mathscore;
    }

    public String getWritescore() {
        return writescore;
    }

    public void setWritescore(String writescore) {
        this.writescore = writescore;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
