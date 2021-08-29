package com.example.asimirshad.dynamic_row_entry.Model;

import com.example.asimirshad.dynamic_row_entry.DatabaseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Order {

    private int id;
    private String date;
    private Area area;
    private String discount;
    private String spec_discount;
    private String gst;
    private String total_amount;
    private int shop_id;
    private String shop_name;
    private ArrayList<Order_detail> order_detail;

    public ArrayList<Order_detail> getOrder_detail() {
        return order_detail;
    }

    public void setOrder_detail(ArrayList<Order_detail> order_detail) {
        this.order_detail = order_detail;
    }
    JSONArray jsonArray = new JSONArray();

    public Order(int id, String date, Area area, String discount, String spec_discount, String gst, String total_amount, int shop_id, String shop_name, ArrayList<Order_detail> order_detail) {

        this.id = id;
        this.date = date;
        this.area = area;
        this.discount = discount;
        this.spec_discount = spec_discount;
        this.gst = gst;
        this.total_amount = total_amount;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.order_detail = order_detail;
    }

    public Order(String date, Area area, String discount, String spec_discount, String gst, String total_amount, int shop_id, String shop_name) {
       this.id=0;
        this.date = date;
        this.area = area;
        this.discount = discount;
        this.spec_discount = spec_discount;
        this.gst = gst;
        this.total_amount = total_amount;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.order_detail = null;
    }

    public Order(int id, String date, Area area, String discount, String spec_discount, String gst, String total_amount, int shop_id, String shop_name) {
        this.id = id;
        this.date = date;
        this.area = area;
        this.discount = discount;
        this.spec_discount = spec_discount;
        this.gst = gst;
        this.total_amount = total_amount;
        this.shop_id = shop_id;
        this.shop_name = shop_name;
        this.order_detail = null;
    }

    public Order() {
        this.id = 0;
        this.date = null;
        this.area = null;
        this.discount = " ";
        this.spec_discount = " ";
        this.gst = " ";
        this.total_amount = " ";
        this.shop_id = 0;
        this.shop_name = " ";
        this.order_detail = null;

    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public Area getArea() {
        return area;
    }

    public String getDiscount() {
        return discount;
    }

    public String getSpec_discount() {
        return spec_discount;
    }

    public String getGst() {
        return gst;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public int getShop_id() {
        return shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setSpec_discount(String spec_discount) {
        this.spec_discount = spec_discount;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public JSONObject getJSONObject(DatabaseHandler db) {
        DatabaseHandler temp_db=db;
        Shop temp_shop=temp_db.getshop(shop_id);
        jsonArray=getOrder_d();
        JSONObject obj = new JSONObject();
        try {
            obj.put("Id", id);
            obj.put("Area_name", area.getArea_name());
            obj.put("Address", temp_shop.getAddress());
            obj.put("Phone_number", temp_shop.getPhone_no());
            obj.put("Area_id", area.getArea_Id());
            obj.put("discount", discount);
            obj.put("s_discount", spec_discount);
            obj.put("gst", gst);
            obj.put("total_amount", total_amount);
            obj.put("shop_id", shop_id);
            obj.put("shop_name", shop_name);
            obj.put("Order_detail",jsonArray);
        } catch (JSONException e) {
            System.out.println("DefaultListItem.toString JSONException: "+e.getMessage());
        }
        return obj;
    }

    public JSONArray getOrder_d() {
        JSONArray outer_array = new JSONArray();

        JSONObject inner_array = new JSONObject();
        for(int i=0;i<order_detail.size();i++){
            System.out.println("size orderD "+inner_array.toString());
            try {

                inner_array.put("id",order_detail.get(i).getId());
                inner_array.put("date",order_detail.get(i).getDate());
                inner_array.put("product_id",order_detail.get(i).getProduct_id());
                inner_array.put("product_name",order_detail.get(i).getProduct_name());
                inner_array.put("product_rate",order_detail.get(i).getRate());
                inner_array.put("product_qtn",order_detail.get(i).getQtn());
                inner_array.put("product_amount",order_detail.get(i).getAmount());
                inner_array.put("order_id",order_detail.get(i).getOrder_id());
                inner_array.put("Product_dis",order_detail.get(i).getDis_product());
               // inner_array.put("i",order_detail.get(i).toString());
             String temp_object=inner_array.toString();
             outer_array.put(temp_object);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            //inner_array=inner_array+inner_array;


        }
        return outer_array;

    }
}
