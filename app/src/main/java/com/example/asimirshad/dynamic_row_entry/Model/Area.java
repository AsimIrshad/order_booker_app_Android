package com.example.asimirshad.dynamic_row_entry.Model;

public class Area {

    private String Area_name;
    private int Area_Id;

    public Area(String area_name) {
        Area_Id=0;
        Area_name = area_name;
    }

    public Area() {
        Area_name=" ";
        Area_Id=0;
    }

    public void setArea_name(String area_name) {

        Area_name = area_name;
    }

    public void setArea_Id(int area_Id) {
        Area_Id = area_Id;
    }

    public String getArea_name() {
        return Area_name;
    }

    public int getArea_Id() {
        return Area_Id;
    }

    public Area(String area_name, int area_Id) {

        Area_name = area_name;
        Area_Id = area_Id;
    }



}
