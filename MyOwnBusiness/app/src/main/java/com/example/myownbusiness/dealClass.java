package com.example.myownbusiness;

import java.io.Serializable;

public class dealClass implements Serializable {
    //VAR
    String Name;
    String Description;
    String Location;
    String firstCat;
    String secondCat;
    String thirdCat;
    short Price;

    //DECLARATION
    public dealClass(String name, String description, String location, String firstcat, String secondcat, String thirdcat, short price) {

        Name = name;
        Description = description;
        Location = location;
        firstCat = firstcat;
        secondCat = secondcat;
        thirdCat = thirdcat;
        Price = price;

    }

    //SETTERS-------------------------------------------------------------------------------------->

    public void setName(String name) {
        Name = name;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public void setLocation(String location) {
        Location = location;
    }
    public void setFirstCat(String firstcat) {
        firstCat = firstcat;
    }
    public void setSecondCat(String secondcat) {
        secondCat = secondcat;
    }
    public void setThirdCat(String thirdcat) {
        thirdCat = thirdcat;
    }
    public void setPrice(short price) {
        Price = price;
    }

    //GETTERS ------------------------------------------------------------------------------------->
    public String getName() {
        return Name;
    }
    public String getDescription() {
        return Description;
    }
    public String getLocation() {
        return Location;
    }
    public String getFirstCat() {
        return firstCat;
    }
    public String getSecondCat() {
        return secondCat;
    }
    public String getThirdCat() {
        return thirdCat;
    }
    public short getPrice() {
        return Price;
    }
}
