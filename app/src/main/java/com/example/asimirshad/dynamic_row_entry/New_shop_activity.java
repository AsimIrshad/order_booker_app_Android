package com.example.asimirshad.dynamic_row_entry;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asimirshad.dynamic_row_entry.Model.Area;
import com.example.asimirshad.dynamic_row_entry.Model.Product;
import com.example.asimirshad.dynamic_row_entry.Model.Shop;

import java.util.ArrayList;

public class New_shop_activity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    Spinner area;
    EditText shop_name;
    EditText shop_keeper;
    EditText cnic;
    EditText phone_no;
    EditText address;
    Button btn,update_btn;
    View focusView = null;
    boolean cancel = false;

    ArrayList<Area> areaList = new ArrayList<Area>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_shop);
//temp Area

        areaList=db.getarea();

        int[] values = new int[ areaList.size()+1];
        String[] areas_ = new String[areaList.size()+1];


        areas_[0]="Choose From";
        values[0]=0;
        for(int i=0;i<areaList.size();i++){
         areas_[i+1]=areaList.get(i).getArea_name();
         values[i+1]=areaList.get(i).getArea_Id();
        }


        area=(Spinner)findViewById(R.id.area_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,areas_);
        area.setAdapter(adapter);



        shop_name=(EditText)findViewById(R.id.N_shop_name);
        shop_keeper=(EditText)findViewById(R.id.N_keeper);
        cnic=(EditText)findViewById(R.id.N_cnic);
        phone_no=(EditText)findViewById(R.id.N_phone);
        address=(EditText)findViewById(R.id.N_address);
        btn=(Button) findViewById(R.id.N_save_shop);

        update_btn=(Button)findViewById(R.id.N_update_shop);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_shop(view);
            }
        });

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =String.valueOf( b.get("Shop_for _edit"));
            System.out.println(j);
            go_for_edit(Integer.parseInt(j));
        }



    }

    private void go_for_edit(int i) {

        Shop edit_shop=db.getshop(i);
            System.out.println("Shop ID"+edit_shop.getId());



        area.setSelection(edit_shop.getArea().getArea_Id());
        shop_name.setText(edit_shop.getShop_name());
        shop_keeper.setText(edit_shop.getShop_keeper());
        cnic.setText(String.valueOf(edit_shop.getCnic()));
        phone_no.setText(String.valueOf(edit_shop.getPhone_no()));
        address.setText(String.valueOf(edit_shop.getAddress()));
        btn.setVisibility(View.INVISIBLE);
        update_btn.setTag(edit_shop.getId());
        update_btn.setVisibility(View.VISIBLE);
    }

    public void Update_shop(View view){

        shop_name.setError(null);
        shop_keeper.setError(null);
        cnic.setError(null);
        phone_no.setError(null);
        address.setError(null);

        if(area.getSelectedItem().equals("Choose From")){
            TextView errorText = (TextView)area.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("This Field is required");
            focusView = area;
            cancel = true;

        }
        else if( TextUtils.isEmpty(shop_name.getText().toString())) {
            shop_name.setError("This Field is Required");
            focusView = shop_name;
            cancel = true;

        }else if( TextUtils.isEmpty(shop_keeper.getText().toString())) {
            shop_keeper.setError("This Field is Required");
            focusView = shop_keeper;
            cancel = true;

        }else if( TextUtils.isEmpty(cnic.getText().toString())) {
            cnic.setError("This Field is Required");
            focusView = cnic;
            cancel = true;

        }else if( TextUtils.isEmpty(phone_no.getText().toString())) {
            phone_no.setError("This Field is Required");
            focusView = phone_no;
            cancel = true;

        }else if( TextUtils.isEmpty(address.getText().toString())) {
            address.setError("This Field is Required");
            focusView = address;
            cancel = true;

        }
        else {

            int[] values = new int[ areaList.size()+1];
            String[] areas_ = new String[areaList.size()+1];


            areas_[0]="Choose From";
            values[0]=0;
            for(int i=0;i<areaList.size();i++){
                areas_[i+1]=areaList.get(i).getArea_name();
                values[i+1]=areaList.get(i).getArea_Id();
            }

            Area area1 = null;
            String area_selected=area.getSelectedItem().toString();
            for(int i=0;i<areas_.length;i++){
                if(areas_[i].equals(area_selected)){

                    Area temp_area=new Area(area_selected,values[i]);
                    area1=temp_area;
                    break;
                }
            }

            Shop shop=new Shop(Integer.parseInt(update_btn.getTag().toString()),shop_name.getText().toString(),shop_keeper.getText().toString(),cnic.getText().toString(),phone_no.getText().toString(),address.getText().toString(),area1);

            System.out.println("Update ");
            shop.getPhone_no();
            db.update_shop(shop);
            this.finish();
            Intent i=new Intent(New_shop_activity.this,Shops_Activity.class);
            this.startActivity(i);

        }


    }


    public void add_shop(View view){



        shop_name.setError(null);
        shop_keeper.setError(null);
        cnic.setError(null);
        phone_no.setError(null);
        address.setError(null);

        if(area.getSelectedItem().equals("Choose From")){
            TextView errorText = (TextView)area.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("This Field is required");
            focusView = area;
            cancel = true;

        }
        else if( TextUtils.isEmpty(shop_name.getText().toString())) {
            shop_name.setError("This Field is Required");
            focusView = shop_name;
            cancel = true;

        }else if( TextUtils.isEmpty(shop_keeper.getText().toString())) {
            shop_keeper.setError("This Field is Required");
            focusView = shop_keeper;
            cancel = true;

        }else if( TextUtils.isEmpty(cnic.getText().toString())) {
            cnic.setError("This Field is Required");
            focusView = cnic;
            cancel = true;

        }else if( TextUtils.isEmpty(phone_no.getText().toString())) {
            phone_no.setError("This Field is Required");
            focusView = phone_no;
            cancel = true;

        }else if( TextUtils.isEmpty(address.getText().toString())) {
            address.setError("This Field is Required");
            focusView = address;
            cancel = true;

        }
        else {
            int[] values = new int[ areaList.size()+1];
            String[] areas_ = new String[areaList.size()+1];


            areas_[0]="Choose From";
            values[0]=0;
            for(int i=0;i<areaList.size();i++){
                areas_[i+1]=areaList.get(i).getArea_name();
                values[i+1]=areaList.get(i).getArea_Id();
            }
            //Add product to database
            Area area1 = null;
             String area_selected=area.getSelectedItem().toString();
            for(int i=0;i<areas_.length;i++){
                if(areas_[i].equals(area_selected)){

                       Area temp_area=new Area(area_selected,values[i]);
                        area1=temp_area;
                        break;
                }
            }

            Shop shop=new Shop(shop_name.getText().toString(),shop_keeper.getText().toString(),cnic.getText().toString(),phone_no.getText().toString(),address.getText().toString(),area1);

            db.addshop(shop);
            this.finish();
            Intent i=new Intent(New_shop_activity.this,Shops_Activity.class);
            this.startActivity(i);

        }




    }
}
