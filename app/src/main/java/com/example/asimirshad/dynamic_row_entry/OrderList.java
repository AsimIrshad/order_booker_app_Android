package com.example.asimirshad.dynamic_row_entry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.asimirshad.dynamic_row_entry.Model.Order;
import com.example.asimirshad.dynamic_row_entry.Model.OrderArray_adapter;
import com.example.asimirshad.dynamic_row_entry.Model.OrderList_Array_Adapter;
import com.example.asimirshad.dynamic_row_entry.Model.Order_group;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {

    private OrderList_Array_Adapter orderArray_adapter;
    private ArrayList<Order> orderList = new ArrayList<Order>();
    DatabaseHandler db = new DatabaseHandler(this);
    String date,area_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            date =String.valueOf( b.get("order_date_for _view"));
            area_id =String.valueOf( b.get("orderarea_for _view"));
            System.out.println(date+area_id);
            //go_for_edit(Integer.parseInt(j));
        }


        orderList=db.getordersList(date,area_id);
        orderArray_adapter = new OrderList_Array_Adapter(this, (ArrayList<Order>) orderList,db);

        ListView listView = null;
        listView = (ListView) findViewById(R.id._dynamic_order_list);
        listView.setAdapter(orderArray_adapter);
    }


}
