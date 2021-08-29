package com.example.asimirshad.dynamic_row_entry.Model;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.asimirshad.dynamic_row_entry.Area_activity;
import com.example.asimirshad.dynamic_row_entry.DatabaseHandler;
import com.example.asimirshad.dynamic_row_entry.New_shop_activity;
import com.example.asimirshad.dynamic_row_entry.R;
import com.example.asimirshad.dynamic_row_entry.Shops_Activity;

import java.util.ArrayList;

public class areaArrayAdapter extends ArrayAdapter<Area> {

    private Context c;
    private DatabaseHandler db;
    Button edit;




    public areaArrayAdapter(Context context, ArrayList<Area> path, DatabaseHandler db) {
        super(context, 0, path);
        c=context;
        this.db=db;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Area temp_area = getItem(position);
        // System.out.println(temp_product.getName());
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.area_radio_button, parent, false);
        }
        // Lookup view for data population



        TextView area_radio = (TextView) convertView.findViewById(R.id.area_radio);

        edit = (Button) convertView.findViewById(R.id.edit_aera);



        area_radio.setText(temp_area.getArea_name());


        edit.setTag(temp_area.getArea_Id());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println("Button" +view.getTag());

                Open_AlertBox_for_edit(temp_area);

            }
        });



        // Return the completed view to render on screen
        return convertView;
    }

    public void Open_AlertBox_for_edit(final Area temp_area){

        final String[] m_Text = {""};
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Title");

        // Set up the input
        final EditText input = new EditText(c);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        input.setText(temp_area.getArea_name());
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text[0] = input.getText().toString();
                System.out.println(" Input Text Received "+ m_Text[0]);

                db.update_area(new Area(m_Text[0],temp_area.getArea_Id()));

                Activity a=(Activity) getContext() ;
                a.finish();

                Intent i=new Intent(c,Area_activity.class);
                c.startActivity(i);
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
