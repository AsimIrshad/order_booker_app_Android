package com.example.asimirshad.dynamic_row_entry.Model;

import java.util.Date;

public class Order_detail {

    private int id;
    private String date;
    private int  order_id;
    private String product_id;
    private String product_name;
    private String dis_product;

    public String getDis_product() {
        return dis_product;
    }

    public void setDis_product(String dis_product) {
        this.dis_product = dis_product;
    }

    private String rate;
    private String qtn;
    private String amount;



    public Order_detail() {

        this.id = 0;
        this.date = null;
        this.order_id = 0;
        this.product_id = " ";
        this.product_name = " ";
        this.rate = " ";
        this.qtn = " ";
        this.amount = " ";
        this.dis_product=" ";

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setQtn(String qtn) {
        this.qtn = qtn;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getId() {

        return id;
    }

    public String getDate() {
        return date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getRate() {
        return rate;
    }

    public String getQtn() {
        return qtn;
    }

    public String getAmount() {
        return amount;
    }

    public Order_detail(int id, String date, int order_id, String product_id, String product_name, String rate, String qtn, String amount,String dis_product) {

        this.id = id;
        this.date = date;
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.rate = rate;
        this.qtn = qtn;
        this.amount = amount;
        this.dis_product=dis_product;
    }

    @Override
    public String toString() {
        return "Order_detail{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", order_id=" + order_id +
                ", product_id='" + product_id + '\'' +
                ", product_name='" + product_name + '\'' +
                ", dis_product='" + dis_product + '\'' +
                ", rate='" + rate + '\'' +
                ", qtn='" + qtn + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }

    public Order_detail(String date, int order_id, String product_id, String product_name, String rate, String qtn, String amount, String dis_product) {
        this.id=0;
        this.date = date;
        this.order_id = order_id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.rate = rate;
        this.qtn = qtn;
        this.amount = amount;
        this.dis_product=dis_product;
    }
}
