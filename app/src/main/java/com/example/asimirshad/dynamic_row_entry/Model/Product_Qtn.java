package com.example.asimirshad.dynamic_row_entry.Model;

public class Product_Qtn {
    private String Product_name;
    private String qtn;
    private String scheme;

    public String getQtn() {
        return qtn;
    }

    public void setQtn(String qtn) {
        this.qtn = qtn;
    }

    public String getProduct_name() {

        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public Product_Qtn() {

    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Product_Qtn(String product_name, String qtn, String scheme) {
        Product_name = product_name;
        this.qtn = qtn;
        this.scheme = scheme;
    }
}
