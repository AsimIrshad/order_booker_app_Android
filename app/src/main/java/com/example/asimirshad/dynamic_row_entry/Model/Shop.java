package com.example.asimirshad.dynamic_row_entry.Model;

public class Shop {

    private int Id;
    private String Shop_name;
    private String Shop_keeper;
    private String cnic;
    private String phone_no;
    private String address;
     private Area area;

    @Override
    public String toString() {
        return "Shop{" +
                "Id=" + Id +
                ", Shop_name='" + Shop_name + '\'' +
                ", Shop_keeper='" + Shop_keeper + '\'' +
                ", cnic='" + cnic + '\'' +
                ", phone_no='" + phone_no + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                '}';
    }

    public Shop() {
        Id=0;
        Shop_name=" ";
        Shop_keeper=" ";
        cnic=" ";
        this.phone_no =" ";
        this.address =" ";
        area=null;
    }

    public Shop(String shop_name, String shop_keeper, String cnic, String phone_no, String address,Area area) {
        Id=0;
        Shop_name = shop_name;
        Shop_keeper = shop_keeper;
        this.cnic = cnic;
        this.phone_no = phone_no;
        this.address = address;
        this.area=area;
    }

    public void setShop_name(String shop_name) {

        Shop_name = shop_name;
    }

    public void setShop_keeper(String shop_keeper) {
        Shop_keeper = shop_keeper;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {

        Id = id;
    }

    public int getId() {

        return Id;
    }

    public String getShop_name() {
        return Shop_name;
    }

    public String getShop_keeper() {
        return Shop_keeper;
    }

    public String getCnic() {
        return cnic;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getAddress() {
        return address;
    }

    public Shop(int id, String shop_name, String shop_keeper, String cnic, String phone_no, String address,Area area) {

        Id = id;
        Shop_name = shop_name;
        Shop_keeper = shop_keeper;
        this.cnic = cnic;
        this.phone_no = phone_no;
        this.address = address;
        this.area=area;
    }
    public Shop(int id, String shop_name, String shop_keeper, String cnic, String phone_no, String address) {

        Id = id;
        Shop_name = shop_name;
        Shop_keeper = shop_keeper;
        this.cnic = cnic;
        this.phone_no = phone_no;
        this.address = address;
        this.area=null;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Area getArea() {

        return area;
    }
}
