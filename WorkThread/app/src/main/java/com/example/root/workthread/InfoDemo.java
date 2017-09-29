package com.example.root.workthread;

import java.util.HashMap;

/**
 * Created by root on 28/09/17.
 */

public class InfoDemo {

    private String countryCode;
    private String countryName;
    private String topic;
    private int startYear;
    private int endYear;
    private HashMap<Integer,String> results = new HashMap<>();

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public HashMap<Integer, String> getAllResults() {
        return results;
    }

    public String getResult(int year){
        return results.get(year);
    }

    public void setResults(int year, String data){
        this.results.put(year, data);
    }
}
