package com.example.asimirshad.dynamic_row_entry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.asimirshad.dynamic_row_entry.Model.Area;
import com.example.asimirshad.dynamic_row_entry.Model.CustomExpandableListAdapter;
import com.example.asimirshad.dynamic_row_entry.Model.Shop;
import com.example.asimirshad.dynamic_row_entry.Model.Shop_Array_Adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shops_Activity extends AppCompatActivity {

    private Toolbar mTopToolbar;
    private Shop_Array_Adapter ShopArrayAdapter;
    private ArrayList<Shop> shopsList = new ArrayList<Shop>();
    DatabaseHandler db = new DatabaseHandler(this);

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<Area> expandableListTitle;
    HashMap<Area, List<Shop>> expandableListDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
        mTopToolbar = (Toolbar) findViewById(R.id.shop_toolbar);
        setSupportActionBar(mTopToolbar);

//
//        shopsList=db.getshops();
//        ShopArrayAdapter = new Shop_Array_Adapter(this, (ArrayList<Shop>) shopsList,db);
//        // Attach the adapter to a ListView
//        ListView listView = null;
//        listView = (ListView) findViewById(R.id._dynamic_shop);
//        listView.setAdapter(ShopArrayAdapter);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expandableListDetail = getData();
        expandableListTitle = new ArrayList<Area>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shop_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.New_shop) {
            Toast.makeText(Shops_Activity.this, "Action clicked", Toast.LENGTH_LONG).show();
            this.finish();
            Intent i=new Intent(Shops_Activity.this,New_shop_activity.class);
            this.startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public  HashMap<Area, List<Shop>> getData() {
        HashMap<Area, List<Shop>> expandableListDetail = new HashMap<Area, List<Shop>>();

        ArrayList<Area> Area_names = db.getarea();
        shopsList=db.getshops();

        for(int j=0;j<Area_names.size();j++){
            ArrayList<Shop> Shop_list_detail=new ArrayList<Shop>();
                for(int i=0;i<shopsList.size();i++){

                    if(shopsList.get(i).getArea().getArea_name().equals(Area_names.get(j).getArea_name())){
                        Shop_list_detail.add(shopsList.get(i));
                    }
                }
            expandableListDetail.put(Area_names.get(j), Shop_list_detail);

        }

        return expandableListDetail;
    }


}
