package com.example.objectsnreference;

/**
 *
 */
public class Car {

    private String brand_name;
    private String color;
    private int year_of_manufacture;

    Car(String brand, String color, int year) {
        this.brand_name = brand;
        this.color = color;
        this.year_of_manufacture = year;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear_of_manufacture() {
        return year_of_manufacture;
    }

    public void setYear_of_manufacture(int year_of_manufacture) {
        this.year_of_manufacture = year_of_manufacture;
    }


}
