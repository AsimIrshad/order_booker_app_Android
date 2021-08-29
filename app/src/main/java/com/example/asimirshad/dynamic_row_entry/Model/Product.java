package com.example.asimirshad.dynamic_row_entry.Model;

public class Product {


    private int id;
    private String name;
    private String size_;
    private double trade_price;
    private double gst_price;
    private double t_p_gst_price;
    private double retail_price;

    public Product() {

        id=0;
        name=" ";
        size_=" ";
        trade_price=0;
        gst_price=0;
        t_p_gst_price=0;
        retail_price=0;

    }

    public void setName(String name) {

        this.name = name;
    }

    public void setSize(String size) {
        this.size_ = size;
    }

    public void setTrade_price(double trade_price) {
        this.trade_price = trade_price;
    }

    public void setGst_price(double gst_price) {
        this.gst_price = gst_price;
    }

    public void setT_p_gst_price(double t_p_gst_price) {
        this.t_p_gst_price = t_p_gst_price;
    }

    public void setRetail_price(double retail_price) {
        this.retail_price = retail_price;
    }

    public String getName() {

        return name;
    }

    public String getSize() {
        return size_;
    }

    public double getTrade_price() {
        return trade_price;
    }

    public double getGst_price() {
        return gst_price;
    }

    public double getT_p_gst_price() {
        return t_p_gst_price;
    }

    public double getRetail_price() {
        return retail_price;
    }

    public Product(int id, String name, String size_, double trade_price, double gst_price, double t_p_gst_price, double retail_price) {
        this.id = id;
        this.name = name;
        this.size_ = size_;
        this.trade_price = trade_price;
        this.gst_price = gst_price;
        this.t_p_gst_price = t_p_gst_price;
        this.retail_price = retail_price;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }



    public Product(String name, String size, double trade_price, double gst_price, double t_p_gst_price, double retail_price) {

        id=0;

        this.name = name;
        this.size_ = size;
        this.trade_price = trade_price;
        this.gst_price = gst_price;
        this.t_p_gst_price = t_p_gst_price;
        this.retail_price = retail_price;

    }
}
