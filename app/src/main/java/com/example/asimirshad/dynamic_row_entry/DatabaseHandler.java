package com.example.asimirshad.dynamic_row_entry;

/**
 * Created by nouman.ali on 12/28/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asimirshad.dynamic_row_entry.Model.Area;
import com.example.asimirshad.dynamic_row_entry.Model.Order;
import com.example.asimirshad.dynamic_row_entry.Model.Order_detail;
import com.example.asimirshad.dynamic_row_entry.Model.Order_group;
import com.example.asimirshad.dynamic_row_entry.Model.Product;
import com.example.asimirshad.dynamic_row_entry.Model.Shop;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 72;

    // Database Name
    private static final String DATABASE_NAME = "OrderApp1";

    // Path table name
    private static final String TABLE_product = "product";
   //move table name
    private static final String TABLE_area = "area";
    private static final String TABLE_Order = "order_";
    private static final String TABLE_Order_detail = "order_detail";
    private static final String TABLE_shops = "shop";




    // Product Table Columns names
    private static final String KEY_Product = "name";
    private static final String KEY_Product_ID = "product_id";
    private static final String KEY_size = "size";
    private static final String KEY_trade_price = "trade_price";
    private static final String KEY_gst = "gst_tax";
    private static final String KEY_t_p_tax_price = "t_p_tax_price";
    private static final String KEY_retail_price = "retail_price";

    // Shop Table Columns names
    private static final String KEY_Shop_name = "shop_name";
    private static final String KEY_Shop_ID = "shop_id";
    private static final String KEY_Keeper = "keeper";
    private static final String KEY_cnic = "cnic";
    private static final String KEY_phone = "phone";
    private static final String KEY_address = "address";
    private static final String KEY_shop_area_id = "shop_area_id";

    //area Table Colums
    private static final String KEY_area_name = "area_name";
    private static final String KEY_area_ID = "area_id";


    // order Table Colums
    private static final String KEY_order_ID = "order_id";
    private static final String KEY_order_date = "order_date";
    private static final String KEY_Name_area = "area_name";
    private static final String KEY_ID_area = "area_id";
    private static final String KEY_discount = "discount";
    private static final String KEY_special_discount = "special_discount";
    private static final String KEY_new_order_gst = "gst";
    private static final String KEY_total_amount = "total_amount";
    private static final String KEY_ID_shop = "shop_id";
    private static final String KEY_Name_shop = "shop_name";

    // order Detail Table Colums
    private static final String KEY_order_detail_id = "order_detail_id";
    private static final String KEY_order_detail_date = "order_detail_date";
    private static final String KEY_order_order_detail_ID = "order_order_detail_id";

    private static final String KEY_new_product = "product_name";
    private static final String KEY_new_product_id = "product_id";
    private static final String KEY_new_rate = "product_rate";
    private static final String KEY_new_qtn = "product_qtn";
    private static final String KEY_new_amount = "product_amount";
    private static final String KEY_product_discount = "product_discount";



    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create product table
        String CREATE_product_TABLE = "CREATE TABLE " + TABLE_product + "("
                + KEY_Product_ID + " INTEGER PRIMARY KEY ," + KEY_Product + " TEXT,"+KEY_size + " TEXT,"+KEY_trade_price + " REAL,"+KEY_gst + " REAL,"+KEY_t_p_tax_price+ " REAL,"+KEY_retail_price+ " REAL"+")";
        db.execSQL(CREATE_product_TABLE);

        String CREATE_area_TABLE = "CREATE TABLE " + TABLE_area + "("
                + KEY_area_ID + " INTEGER PRIMARY KEY ," + KEY_area_name + " TEXT"+")";
        db.execSQL(CREATE_area_TABLE);

        String CREATE_shop_TABLE = "CREATE TABLE " + TABLE_shops + "("
                + KEY_Shop_ID + " INTEGER PRIMARY KEY ," + KEY_Shop_name + " TEXT,"+KEY_Keeper + " TEXT,"+KEY_cnic + " TEXT,"+KEY_phone + " TEXT,"+KEY_address+ " TEXT,"+KEY_shop_area_id+ " INTEGER,"+
                "FOREIGN KEY(shop_area_id) REFERENCES TABLE_area(area_id)"+")";
        db.execSQL(CREATE_shop_TABLE);

        String CREATE_order_Table = "CREATE TABLE " + TABLE_Order + "("
                + KEY_order_ID + " INTEGER PRIMARY KEY ," + KEY_order_date + " TEXT,"+ KEY_Name_area + " TEXT,"+ KEY_ID_area + " INTEGER,"+  KEY_discount + " TEXT,"+ KEY_special_discount +" TEXT,"+ KEY_new_order_gst +" TEXT,"+ KEY_total_amount +" TEXT,"+ KEY_ID_shop + " INTEGER,"+ KEY_Name_shop + " TEXT"+")";
        db.execSQL(CREATE_order_Table);

        String CREATE_order_detail_TABLE = "CREATE TABLE " + TABLE_Order_detail + "("
                + KEY_order_detail_id + " INTEGER PRIMARY KEY ," + KEY_order_detail_date + " TEXT,"+KEY_new_product + " TEXT,"+KEY_new_product_id + " INTEGER,"+KEY_new_rate + " TEXT,"+KEY_new_qtn+ " TEXT,"+KEY_new_amount+ " TEXT,"+KEY_order_order_detail_ID+ " INTEGER,"+KEY_product_discount+" TEXT,"+
                "FOREIGN KEY(order_order_detail_id) REFERENCES TABLE_Order(order_id)"+")";
        db.execSQL(CREATE_order_detail_TABLE);


//        String CREATE_move_TABLE = "CREATE TABLE " + TABLE_move + "("
//                + KEY_ID + " INTEGER PRIMARY KEY ," + KEY_move + " TEXT,"+KEY_path_id + " INTEGER," +
//                " FOREIGN KEY(path_id) REFERENCES TABLE_path(id)"+")";
//        db.execSQL(CREATE_move_TABLE);
            }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_product);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_area);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_shops);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Order);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Order_detail);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_move);

        // Create tables again
        onCreate(db);
    }

// CRUD OPERATION OF PRODUCT
    public void addproduct(Product product) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Product, product.getName()); // Contact Name
        values.put(KEY_size, product.getSize()); // Contact Name
        values.put(KEY_trade_price, product.getTrade_price()); // Contact Name
        values.put(KEY_gst, product.getGst_price()); // Contact Name
        values.put(KEY_t_p_tax_price, product.getT_p_gst_price()); // Contact Name
        values.put(KEY_retail_price, product.getRetail_price()); // Contact Name
        // Contact Phone
        // Inserting Row
        db.insert(TABLE_product, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<Product> getproducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_product;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int path_id=0;
        // looping through all rows and adding to list
        System.out.println("Product_count "+cursor.getCount());

        if (cursor.moveToFirst()) {

            do {
                System.out.println("Product "+cursor.getString(0)+cursor.getString(1)+cursor.getString(2)+cursor.getString(3)+cursor.getString(4)+cursor.getString(5)+cursor.getString(6));
                Product temp_pro=new Product(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Double.parseDouble(cursor.getString(3)),Double.parseDouble(cursor.getString(4)),Double.parseDouble(cursor.getString(5)),Double.parseDouble(cursor.getString(6)));
                products.add(temp_pro);

            } while (cursor.moveToNext());




    }
        return products;
    }


    public Product getProduct(int id) {

        Product product = new Product();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_product +" WHERE product.Product_ID ="+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        System.out.println("Count path "+cursor.getCount());

        if (cursor.moveToFirst()) {

            do {

                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));
                product.setSize(cursor.getString(2));
                product.setTrade_price(Double.parseDouble(cursor.getString(3)));
                product.setGst_price(Double.parseDouble(cursor.getString(4)));
                product.setT_p_gst_price(Double.parseDouble(cursor.getString(5)));
                product.setRetail_price(Double.parseDouble(cursor.getString(6)));

            } while (cursor.moveToNext());
        }



        return product;

    }

    public void Delete_product(int id) {


            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_product, KEY_Product_ID + " = ?",
                    new String[] { String.valueOf(id) });
            db.close();

    }

    public int updateproduct(Product product) {


            SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Product, product.getName()); // Contact Name
        values.put(KEY_size, product.getSize()); // Contact Name
        values.put(KEY_trade_price, product.getTrade_price()); // Contact Name
        values.put(KEY_gst, product.getGst_price()); // Contact Name
        values.put(KEY_t_p_tax_price, product.getT_p_gst_price()); // Contact Name
        values.put(KEY_retail_price, product.getRetail_price()); // Contact Name

            // updating row
            return db.update(TABLE_product, values, KEY_Product_ID + " = ?",
                    new String[] { String.valueOf(product.getId()) });

    }


    // Shop Crud operations
    public void addshop(Shop shop) {

        //change it when area crud made
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Shop_name, shop.getShop_name()); // Contact Name
        values.put(KEY_Keeper, shop.getShop_keeper()); // Contact Name
        values.put(KEY_cnic, shop.getCnic()); // Contact Name
        values.put(KEY_phone, shop.getPhone_no()); // Contact Name
        values.put(KEY_address, shop.getAddress()); // Contact Name
        values.put(KEY_shop_area_id, shop.getArea().getArea_Id()); // Contact Name
        // Contact Phone
        // Inserting Row
        db.insert(TABLE_shops, null, values);
        db.close(); // Closing database connection
    }

    public Shop getshop(int id) {

        Shop temp_shop = new Shop();
        // Select All Query
        //String selectQuery = "SELECT  * FROM " + TABLE_shops +" WHERE shop.shop_id ="+id;
        String selectQuery = "SELECT  * FROM " + TABLE_shops +" INNER JOIN "+TABLE_area +" ON shop.shop_area_id = area.area_id AND shop.shop_id ="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        System.out.println("Count path "+cursor.getCount());

        if (cursor.moveToFirst()) {

            do {
                temp_shop.setCnic(cursor.getString(3));//cnic
                temp_shop.setPhone_no(cursor.getString(4));//phone
                temp_shop.setAddress(cursor.getString(5));//address
                temp_shop.setArea(new Area(cursor.getString(8),Integer.parseInt(cursor.getString(7))));//area

                System.out.println(cursor.getString(0)+cursor.getString(1)+cursor.getString(2)+cursor.getString(3)+cursor.getString(4)+cursor.getString(5)+cursor.getString(6)+cursor.getString(7)+cursor.getString(8));
                temp_shop.setId(Integer.parseInt(cursor.getString(0)));//id
                temp_shop.setShop_name(cursor.getString(1));//shopname
                temp_shop.setShop_keeper(cursor.getString(2));//keeper


            } while (cursor.moveToNext());
        }



        return temp_shop;

    }

    public ArrayList<Shop> getshops() {
        ArrayList<Shop> shops = new ArrayList<Shop>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_area +" INNER JOIN "+TABLE_shops +" ON shop.shop_area_id = area.area_id";

        //String selectQuery = "SELECT  * FROM " + TABLE_shops;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int path_id=0;
        // looping through all rows and adding to list
        System.out.println("Product_count "+cursor.getCount());

        if (cursor.moveToFirst()) {

            do {
                // first two colum show area colum than shops list
                System.out.println(cursor.getString(0)+cursor.getString(1)+cursor.getString(2)+cursor.getString(3));
                Area temp_area=new Area(cursor.getString(1),Integer.parseInt(cursor.getString(0)));
                Shop temp_shop=new Shop(Integer.parseInt(cursor.getString(2)),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),temp_area);
             //   Shop temp_shop=new Shop(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Double.parseDouble(cursor.getString(3)),Double.parseDouble(cursor.getString(4)),Double.parseDouble(cursor.getString(5)),Double.parseDouble(cursor.getString(6)));
                shops.add(temp_shop);

            } while (cursor.moveToNext());




        }
        return shops;
    }

    public void Delete_shop(int id) {


        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_shops, KEY_Shop_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();


    }

    public int update_shop(Shop shop) {
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println("Update shop" +shop.getId());
        ContentValues values = new ContentValues();
        values.put(KEY_Shop_name, shop.getShop_name()); // Contact Name
        values.put(KEY_Keeper, shop.getShop_keeper()); // Contact Name
        values.put(KEY_cnic, shop.getCnic()); // Contact Name
        values.put(KEY_phone, shop.getPhone_no()); // Contact Name
        values.put(KEY_address, shop.getAddress()); // Contact Name
        values.put(KEY_shop_area_id, shop.getArea().getArea_Id()); // Contact Name

        // updating row
        return db.update(TABLE_shops, values, KEY_Shop_ID + " = ?",
                new String[] { String.valueOf(shop.getId()) });
    }

    public void add_area(Area area) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_area_name, area.getArea_name()); // Contact Name

        // Inserting Row
        db.insert(TABLE_area, null, values);
        db.close(); // Closing database connection
    }

    public ArrayList<Area> getarea() {
        ArrayList<Area> area = new ArrayList<Area>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_area;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        System.out.println("Area_count "+cursor.getCount());

        if (cursor.moveToFirst()) {

            do {
                System.out.println("Area "+cursor.getString(0)+cursor.getString(1));
                Area temp_area=new Area(cursor.getString(1),Integer.parseInt(cursor.getString(0)));
                area.add(temp_area);

            } while (cursor.moveToNext());




        }
        return area;



    }

    public int update_area(Area area) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_area_ID, area.getArea_Id()); // Contact Name
        values.put(KEY_area_name, area.getArea_name()); // Contact Name

        // updating row
        return db.update(TABLE_area, values, KEY_area_ID + " = ?",
                new String[] { String.valueOf(area.getArea_Id()) });
    }


    public ArrayList<Shop> getShop_withArea(int area_index) {
        System.out.println("Area-Index "+area_index);
        ArrayList<Shop> shop = new ArrayList<Shop>();


        String selectQuery = "SELECT  * FROM " + TABLE_shops +" WHERE shop_area_id ="+area_index +"";

        //String selectQuery = "SELECT  * FROM " + TABLE_shops;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int path_id=0;
        // looping through all rows and adding to list
        System.out.println("Product_count11 "+cursor.getCount());

        if (cursor.moveToFirst()) {

            do {
                // first two colum show area colum than shops list
                System.out.println(cursor.getString(0)+cursor.getString(1)+cursor.getString(2)+cursor.getString(3));

                Shop temp_shop=new Shop(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                //   Shop temp_shop=new Shop(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),Double.parseDouble(cursor.getString(3)),Double.parseDouble(cursor.getString(4)),Double.parseDouble(cursor.getString(5)),Double.parseDouble(cursor.getString(6)));
                shop.add(temp_shop);

            } while (cursor.moveToNext());




        }

        return shop;


    }

    public void insert_order(Order temp_order) {


System.out.println("Date format "+ temp_order.getDate());

        //change it when area crud made
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_order_date, temp_order.getDate().toString()); // Contact Name
        values.put(KEY_Name_area, temp_order.getArea().getArea_name()); // Contact Name
        values.put(KEY_ID_area, temp_order.getArea().getArea_Id()); // Contact Name
        values.put(KEY_discount, temp_order.getDiscount()); // Contact Name
        values.put(KEY_special_discount, temp_order.getSpec_discount()); // Contact Name
        values.put(KEY_new_order_gst, temp_order.getGst()); // Contact Name
        values.put(KEY_total_amount, temp_order.getTotal_amount()); // Contact Name
        values.put(KEY_ID_shop, temp_order.getShop_id()); // Contact Name
        values.put(KEY_Name_shop, temp_order.getShop_name()); // Contact Name
        // Contact Phone
        // Inserting Row
        db.insert(TABLE_Order, null, values);
        db.close(); // Closing database connection

    }

    public int getlastinserted_order() {


        // Select All Query

        //String selectQuery = " SELECT last_insert_rowid()";
        String selectQuery = "SELECT MAX(order_id) FROM " + TABLE_Order+"";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        System.out.println("Last Inserted_count "+cursor.getCount());

        if (cursor.moveToFirst()) {

            do {
                System.out.println("ID Inserted "+cursor.getString(0));
                return Integer.parseInt(cursor.getString(0));

            } while (cursor.moveToNext());




        }
        return 0;
    }

    public void insert_order_detail(Order_detail temp_order_detail) {




        //change it when area crud made
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_order_detail_date, temp_order_detail.getDate().toString()); // Contact Name
        values.put(KEY_new_product, temp_order_detail.getProduct_name()); // Contact Name
        values.put(KEY_new_product_id, temp_order_detail.getProduct_id()); // Contact Name
        values.put(KEY_new_rate, temp_order_detail.getRate()); // Contact Name
        values.put(KEY_new_qtn, temp_order_detail.getQtn()); // Contact Name
        values.put(KEY_new_amount, temp_order_detail.getAmount()); // Contact Name
        values.put(KEY_order_order_detail_ID, temp_order_detail.getOrder_id()); // Contact Name
       if(!temp_order_detail.getDis_product().equals(' ')) {
           values.put(KEY_product_discount, temp_order_detail.getDis_product());
       }
       else{
           values.put(KEY_product_discount,0);
           // Contact Name
       }


        // Contact Phone
        // Inserting Row
        db.insert(TABLE_Order_detail, null, values);
        db.close(); // Closing database connection


    }

    public ArrayList<Order_group> getorders() {

        ArrayList<Order_group> temp_order_group = new ArrayList<Order_group>();


        String selectQuery = "SELECT  order_id, order_date,SUM(total_amount) as total_amount,area_name,area_id FROM " + TABLE_Order +" GROUP BY order_date , area_name ORDER BY order_date DESC; ";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int path_id=0;
        // looping through all rows and adding to list
        System.out.println("Product_count11 "+cursor.getCount());

        if (cursor.moveToFirst()) {

            do {
                // first two colum show area colum than shops list
                System.out.println("Oder Detail "+ cursor.getString(0)+" "+cursor.getString(1)+cursor.getString(2)+cursor.getString(3)+cursor.getString(4));
                System.out.println("Date count" +cursor.getString(1).length());
                temp_order_group.add(new Order_group(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),Integer.parseInt(cursor.getString(4))));


            } while (cursor.moveToNext());




        }

        return temp_order_group;



    }

    public ArrayList<Order> getordersList(String date, String area_id) {

        ArrayList<Order> temp_order_list = new ArrayList<Order>();

        System.out.println("Order date "+date.length() +" id" +area_id);

        String selectQuery = "SELECT  *  FROM " + TABLE_Order +" WHERE  order_.area_id ="+ Integer.parseInt(area_id) +" ";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        System.out.println("Order List "+selectQuery);
        int path_id=0;
        // looping through all rows and adding to list
        System.out.println("Order List "+cursor.getCount());

        if (cursor.moveToFirst()) {


            do {
                // first two colum show area colum than shops list
                   if(cursor.getString(1).equals(date)){
                       System.out.println("Oder list Detail "+ cursor.getString(0)+" "+cursor.getString(1)+cursor.getString(2)+cursor.getString(3)+cursor.getString(4));
                       temp_order_list.add(new Order(Integer.parseInt(cursor.getString(0)),cursor.getString(1),new Area(cursor.getString(2),Integer.parseInt(cursor.getString(3))),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),Integer.parseInt(cursor.getString(8)),(cursor.getString(9))));

                   }


            } while (cursor.moveToNext());




        }

        return temp_order_list;


    }

    public ArrayList<Order> getorderList(String date, int area_id, int order_id) {
        ArrayList<Order> orders_list = new ArrayList<Order>();

        ArrayList<Order_detail> orders_detail_list = new ArrayList<Order_detail>();
        // Select All Query

        String selectQuery = "SELECT  * FROM " + TABLE_Order_detail +" INNER JOIN "+TABLE_Order +" ON order_.order_id = order_detail.order_order_detail_id  WHERE  order_.area_id ="+ area_id ;


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        System.out.println("Order List "+selectQuery);
        int order_ID=0;
        int size=0;
        // looping through all rows and adding to list
        System.out.println("Order List "+cursor.getCount());

        if (cursor.moveToFirst()) {


            do {
                // first two colum show area colum than shops list
                System.out.println("Order list Detail2 "+ cursor.getString(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getString(6)+cursor.getString(7)+" Order"+" "+cursor.getString(8)+" "+cursor.getString(9)+" "+cursor.getString(10)+" "+cursor.getString(11)+" "+cursor.getString(12)+" "+
                        cursor.getString(13)+" "+cursor.getString(14)+" "+cursor.getString(15)+" "+cursor.getString(16)+" "+cursor.getString(17));
//Order Object
                if(cursor.getString(1).equals(date)){

                        if( order_ID==Integer.parseInt(cursor.getString(9))){
                            Order_detail temp_detail=new Order_detail(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(7)),cursor.getString(3),cursor.getString(2),
                                    cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(8));
                                System.out.println("Order Detail"+temp_detail.toString());
                            orders_detail_list.add(temp_detail);
                            orders_list.get(size-1).setOrder_detail(orders_detail_list);
                            System.out.println("Order Detail size"+orders_list.get(0).getOrder_detail().size());

                        }
                        else{
                            orders_detail_list=new ArrayList<Order_detail>();;
                            order_ID=Integer.parseInt(cursor.getString(9));
                              //null detail array
                                Order_detail temp_detail=new Order_detail(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(7)),cursor.getString(3),cursor.getString(2),
                                        cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(8));

                                orders_detail_list.add(temp_detail);
                                Order temp_order=new Order(Integer.parseInt(cursor.getString(9)),cursor.getString(10),new Area(cursor.getString(11),Integer.parseInt(cursor.getString(12)))
                                        ,cursor.getString(13),cursor.getString(14),cursor.getString(15),cursor.getString(16),Integer.parseInt(cursor.getString(17)),cursor.getString(18),orders_detail_list);
                           // Order Detail object

                            size++;
                            orders_list.add(temp_order);
                            System.out.println("Order size"+orders_detail_list.size()+"  size"+size);
                    }


                }


            } while (cursor.moveToNext());




        }


        return orders_list;

    }

    public Order getOrder(int i) {
        Order temp_order=new Order();

       // ArrayList<Order> orders_list = new ArrayList<Order>();

        ArrayList<Order_detail> orders_detail_list = new ArrayList<Order_detail>();


        // Select All Query
        //String selectQuery = "SELECT  * FROM " + TABLE_shops +" WHERE shop.shop_id ="+id;
        String selectQuery = "SELECT  * FROM " + TABLE_Order_detail +" INNER JOIN "+TABLE_Order +" ON order_.order_id = order_detail.order_order_detail_id  AND order_.order_id ="+i ;

       // String selectQuery = "SELECT  * FROM " + TABLE_Order +" INNER JOIN "+TABLE_Order_detail +" ON order_.order_id = order_detail.order_order_detail_id  ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int order_ID=0;
        int size=0;
        // looping through all rows and adding to list
        System.out.println("Count path "+cursor.getCount());

        if (cursor.moveToFirst()) {

            do {

                System.out.println(cursor.getString(0)+" "+ cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getString(3)+" "+cursor.getString(4)+" "+cursor.getString(5)+" "+cursor.getString(6)+" "+cursor.getString(7)+" "+cursor.getString(8)+" "+cursor.getString(9)+" "+cursor.getString(10));
                if( order_ID==Integer.parseInt(cursor.getString(9))){
                    Order_detail temp_detail=new Order_detail(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(7)),cursor.getString(3),cursor.getString(2),
                            cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(8));

                    orders_detail_list.add(temp_detail);
                    temp_order.setOrder_detail(orders_detail_list);
                    //System.out.println("Order Detail size"+orders_list.get(0).getOrder_detail().size());

                }
                else{
                    orders_detail_list=new ArrayList<Order_detail>();;
                    order_ID=Integer.parseInt(cursor.getString(9));
                    //null detail array
                    Order_detail temp_detail=new Order_detail(Integer.parseInt(cursor.getString(0)),cursor.getString(1),Integer.parseInt(cursor.getString(7)),cursor.getString(3),cursor.getString(2),
                            cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(8));

                    orders_detail_list.add(temp_detail);
                     temp_order=new Order(Integer.parseInt(cursor.getString(9)),cursor.getString(10),new Area(cursor.getString(11),Integer.parseInt(cursor.getString(12)))
                            ,cursor.getString(13),cursor.getString(14),cursor.getString(15),cursor.getString(16),Integer.parseInt(cursor.getString(17)),cursor.getString(18),orders_detail_list);
                    // Order Detail object

                    size++;
                   // orders_list.add(temp_order);
                    System.out.println("Order size"+orders_detail_list.size()+"  size"+size);
                }

            } while (cursor.moveToNext());
        }




        return temp_order;


    }

    public int update_order(Order temp_order, int i) {
        SQLiteDatabase db = this.getWritableDatabase();
       // System.out.println("Amount Total"+temp_order.getTotal_amount());
        ContentValues values = new ContentValues();
        values.put(KEY_order_date, temp_order.getDate().toString()); // Contact Name
        values.put(KEY_Name_area, temp_order.getArea().getArea_name()); // Contact Name
        values.put(KEY_ID_area, temp_order.getArea().getArea_Id()); // Contact Name
        values.put(KEY_discount, temp_order.getDiscount()); // Contact Name
        values.put(KEY_special_discount, temp_order.getSpec_discount()); // Contact Name
        values.put(KEY_new_order_gst, temp_order.getGst()); // Contact Name
        values.put(KEY_total_amount, temp_order.getTotal_amount()); // Contact Name
        values.put(KEY_ID_shop, temp_order.getShop_id()); // Contact Name
        values.put(KEY_Name_shop, temp_order.getShop_name()); // Contact Name


        // updating row
        return db.update(TABLE_Order, values, KEY_order_ID + " = ?",
                new String[] { String.valueOf(i) });

        //change it when area crud made

        // Contact Phone

    }

    public void delete_order_detail_child(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Order_detail, KEY_order_order_detail_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();

    }

    public void Delete_order(int id) {


        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Order, KEY_order_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
        delete_order_detail_child( id);

    }
}