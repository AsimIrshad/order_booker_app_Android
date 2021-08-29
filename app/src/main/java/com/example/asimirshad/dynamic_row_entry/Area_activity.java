package com.example.asimirshad.dynamic_row_entry;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.asimirshad.dynamic_row_entry.Model.Area;

import com.example.asimirshad.dynamic_row_entry.Model.areaArrayAdapter;

import java.util.ArrayList;

public class Area_activity extends AppCompatActivity {

    private Toolbar mTopToolbar;
    public areaArrayAdapter AreaArrayAdapter;
    private ArrayList<Area> areaList = new ArrayList<Area>();
    private String m_Text = "";
    DatabaseHandler db = new DatabaseHandler(this);

    public Area_activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_activity2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  RadioGroup rg=(RadioGroup)findViewById(R.id.area_radio_group);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new_area_alert_box();


            }
        });

        areaList=db.getarea();
        AreaArrayAdapter = new areaArrayAdapter(this, (ArrayList<Area>) areaList,db);
        // Attach the adapter to a ListView
        ListView listView = null;
        listView = (ListView) findViewById(R.id._dynamic_area);
        listView.setAdapter(AreaArrayAdapter);

    }

    public void new_area_alert_box(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                System.out.println(" Input Text Received "+m_Text);
                db.add_area(new Area(m_Text));
                finish();
                Intent i=new Intent(Area_activity.this,Area_activity.class);
                startActivity(i);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }


}
