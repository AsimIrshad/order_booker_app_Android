package com.example.asimirshad.dynamic_row_entry.Model;

public class Order_group {

    private int order_id;
    private  String date;
    private  String amount;
    private  String area_name;
    private  int area_id;

    public Order_group(int order_id, String date, String amount, String area_name, int area_id) {

        this.order_id = order_id;
        this.date = date;
        this.amount = amount;
        this.area_name = area_name;
        this.area_id = area_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getDate() {

        return date;
    }

    public String getAmount() {
        return amount;
    }

    public String getArea_name() {
        return area_name;
    }

    public int getArea_id() {
        return area_id;
    }

    public int getOrder_id() {

        return order_id;
    }
}
