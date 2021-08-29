package com.example.asimirshad.dynamic_row_entry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asimirshad.dynamic_row_entry.Model.Product;
import com.example.asimirshad.dynamic_row_entry.Model.productArrayAdapter;

import java.util.ArrayList;

public class Products_Activity extends AppCompatActivity {

    private Toolbar mTopToolbar;
    private productArrayAdapter ProductArrayAdapter;
    private ArrayList<Product> productsList = new ArrayList<Product>();
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar3);
        setSupportActionBar(mTopToolbar);

        productsList=db.getproducts();
        ProductArrayAdapter = new productArrayAdapter(this, (ArrayList<Product>) productsList,db);
        // Attach the adapter to a ListView
        ListView listView = null;
        listView = (ListView) findViewById(R.id._dynamic_product);
        listView.setAdapter(ProductArrayAdapter);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_product) {
            Toast.makeText(Products_Activity.this, "Action clicked", Toast.LENGTH_LONG).show();
            this.finish();
            Intent i=new Intent(Products_Activity.this,New_product_Activity.class);
            this.startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
