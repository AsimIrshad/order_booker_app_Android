package com.example.asimirshad.dynamic_row_entry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asimirshad.dynamic_row_entry.Model.Order;
import com.example.asimirshad.dynamic_row_entry.Model.OrderArray_adapter;
import com.example.asimirshad.dynamic_row_entry.Model.Order_group;
import com.example.asimirshad.dynamic_row_entry.Model.Product;
import com.example.asimirshad.dynamic_row_entry.Model.productArrayAdapter;

import java.util.ArrayList;

public class My_Orders_Activity extends AppCompatActivity {

    private OrderArray_adapter orderArray_adapter;
    private ArrayList<Order_group> orderList = new ArrayList<Order_group>();
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__orders);


        orderList=db.getorders();
        orderArray_adapter = new OrderArray_adapter(this, (ArrayList<Order_group>) orderList,db);

        ListView listView = null;
        listView = (ListView) findViewById(R.id._dynamic_my_order);
        listView.setAdapter(orderArray_adapter);

 }


}
