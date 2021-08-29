package com.example.asimirshad.dynamic_row_entry;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asimirshad.dynamic_row_entry.Model.Area;
import com.example.asimirshad.dynamic_row_entry.Model.Order;
import com.example.asimirshad.dynamic_row_entry.Model.Order_detail;
import com.example.asimirshad.dynamic_row_entry.Model.Product;
import com.example.asimirshad.dynamic_row_entry.Model.Shop;
import com.example.asimirshad.dynamic_row_entry.Model.productArrayAdapter;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class New_Orders_Activity extends AppCompatActivity {
    private LinearLayout parentLinearLayout;

    Spinner product;
    Spinner shop,area;
    TextView amount;
    EditText qtn,discount,specical_dic,product_discount,rate;
    TextView  gst_tax,total_amount1;
    View focusView = null;
    boolean cancel = false;
    Button save_order_btn,update_order_btn,calculate_order1,calcualte_order2;

    DatabaseHandler db = new DatabaseHandler(this);
    ArrayList<Area> areaList = new ArrayList<Area>();
    ArrayList<Shop> shopList = new ArrayList<Shop>();
    ArrayList<Product> productList = new ArrayList<Product>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__orders);
        parentLinearLayout=(LinearLayout)findViewById(R.id.parent_linear_layout);

        save_order_btn=findViewById(R.id.save_order);
        calculate_order1=findViewById(R.id.calculate_order);
        save_order_btn.setVisibility(View.VISIBLE);
        calculate_order1.setVisibility(View.VISIBLE);
        update_order_btn=findViewById(R.id.update_order);
        calcualte_order2=findViewById(R.id.calculate_order1);


//        qtn=(EditText)findViewById(R.id.number_edit_text);
//        amount=(TextView)findViewById(R.id.product_amount);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();


        discount=(EditText)findViewById(R.id.discount);
        specical_dic=(EditText)findViewById(R.id.s_discount);
        gst_tax=(TextView)findViewById(R.id.order_gst);
        total_amount1=(TextView)findViewById(R.id.new_order_amount);

             discount.setText("0");
             specical_dic.setText("0");
             gst_tax.setText("0");
             total_amount1.setText("0");


        areaList=db.getarea();
        productList=db.getproducts();

        final int[] area_values = new int[ areaList.size()+1];
        final String[] areas_ = new String[areaList.size()+1];

// Area Spinner
        areas_[0]="Choose From";
        area_values[0]=0;
        for(int i=0;i<areaList.size();i++){
            areas_[i+1]=areaList.get(i).getArea_name();
            area_values[i+1]=areaList.get(i).getArea_Id();
        }


        area=(Spinner)findViewById(R.id.area_spinner_order);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,areas_);
        area.setAdapter(adapter);
        shop=(Spinner)findViewById(R.id.shop_spinner);
//Shop Spinner
        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String temp_area=area.getSelectedItem().toString();

                int area_index=find_area(temp_area,areas_,area_values);
                shopList=db.getShop_withArea(area_index);

                int[] shop_values = new int[shopList.size()+1];
                String[] shop_ = new String[shopList.size()+1];


                shop_[0]="Choose From";
                shop_values[0]=0;
                for(int j=0;j<shopList.size();j++){
                    shop_[j+1]=shopList.get(j).getShop_name();
                    shop_values[j+1]=shopList.get(j).getId();
                    System.out.println(shop_[j].toString());
                }




                shop.setAdapter(null);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(New_Orders_Activity.this, android.R.layout.simple_spinner_dropdown_item,shop_);
                adapter1.notifyDataSetChanged();

                shop.setAdapter(adapter1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(b!=null)
        {
            String j =String.valueOf( b.get("order_id"));
            System.out.println(j);
            go_for_edit_order(Integer.parseInt(j));
        }

    }

    private void go_for_edit_order(int ij) {
        final Order temp_order=db.getOrder(ij);


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(temp_order.getArea().getArea_name()));
        area.setAdapter(adapter);

        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String temp_area=area.getSelectedItem().toString();

                //int area_index=find_area(temp_area,areas_,area_values);
                shopList=db.getShop_withArea(temp_order.getArea().getArea_Id());

                for(int j=0;j<shopList.size();j++){
                    if(temp_order.getShop_id()==shopList.get(j).getId()){
                        shop.setSelection(j+1);
                        System.out.println("shop id"+(j+1));
                        break;



                    }
                }


                //shop.setSelection();
                shop.setAdapter(null);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(New_Orders_Activity.this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(temp_order.getShop_name()));
                adapter1.notifyDataSetChanged();
                shop.setAdapter(adapter1);



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        for(int j=0;j<temp_order.getOrder_detail().size();j++){

            onAddField(temp_order,temp_order.getOrder_detail().get(j));
        }

        save_order_btn.setVisibility(View.INVISIBLE);
        calculate_order1.setVisibility(View.INVISIBLE);
        update_order_btn.setTag(temp_order.getId());
        update_order_btn.setVisibility(View.VISIBLE);
        calcualte_order2.setVisibility(View.VISIBLE);



    }

    private void onAddField(Order temp_order, Order_detail order_detail) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        rowView.setId((parentLinearLayout.getChildCount() - 1));
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);

        final int[] product_values = new int[ productList.size()+1];
        final String[] product_ = new String[productList.size()+1];

            // Area Spinner
        product_[0]="Choose From";
        product_values[0]=0;
        for(int i=0;i<productList.size();i++){
            product_[i+1]=productList.get(i).getName();
            product_values[i+1]=productList.get(i).getId();
        }

        qtn=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.number_edit_text);
        product=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.product_spinner);
        product_discount=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.new_orderdiscount);
        amount=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.product_amount);
        rate=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.p_rate);

        //Product Spinner
        // product=(Spinner)findViewById(R.id.product_spinner);
        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,product_);
        product.setAdapter(adapter3);

        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getlayoutId(view);




                System.out.println("Parent retunr"+view.getParent().toString());

                int Index_id_child=((LinearLayout)view.getParent().getParent()).getId();

                product=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.product_spinner);

                //String temp_product=product.getSelectedItem().toString();
                int product_index=find_product(product.getSelectedItem().toString(),product_,product_values);
                product_discount=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.new_orderdiscount);
                rate=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.p_rate);
                System.out.println("Child Id: "+Index_id_child+" Product Index: "+product.getSelectedItem().toString());
                qtn=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.number_edit_text);
                amount=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.product_amount);

                rate.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                //  System.out.println("Dynamic Rate"+rate.getText());
                rate.setText(String.valueOf(productList.get(product_index-1).getT_p_gst_price()));
                System.out.println("Dynamic Rate"+rate.getText());
                rate.addTextChangedListener(rateChangeTextWatcher);

                //qtn.addTextChangedListener(amountEditTextWatcher);
                //product_discount.addTextChangedListener(new disamountEditTextWatcher(amount,rate,qtn,product_discount,view));
                qtn.addTextChangedListener(new amountEditTextWatcher(amount,rate,qtn,view));
                rate.addTextChangedListener(new amountEditTextWatcher(amount,rate,qtn,view));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        product.setSelection(Integer.parseInt(order_detail.getProduct_id()));
        qtn.setText(order_detail.getQtn());
        rate.setText(order_detail.getRate());
        amount.setText(order_detail.getAmount());
        System.out.println("Pro_dis"+order_detail.getDis_product());
        product_discount.setText(order_detail.getDis_product());
        discount.setText(temp_order.getDiscount());
        specical_dic.setText(temp_order.getSpec_discount());
        gst_tax.setText(temp_order.getGst());
        total_amount1.setText(temp_order.getTotal_amount());


    }

    private int find_product(String temp_product, String[] product_, int[] product_values) {
        System.out.println("TEmp Product"+temp_product);
        for(int i=1;i<product_.length;i++){
            if (product_[i].equals(temp_product)){
                // System.out.println("TEmp Area1"+);
                System.out.println("TEmp Product_id"+product_values[i]);
                return product_values[i];
            }

        }
        return 1;
    }

    private int find_area(String temp_area, String[] areas_, int[] area_values) {
       System.out.println("TEmp Area"+temp_area);
        for(int i=1;i<areas_.length;i++){
            if (areas_[i].equals(temp_area)){
               // System.out.println("TEmp Area1"+);

                return area_values[i];
            }

        }
        return 0;
    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        rowView.setId((parentLinearLayout.getChildCount() - 1));
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
        System.out.println("Child of Linear"+parentLinearLayout.getChildAt(parentLinearLayout.getChildCount()-2).getId());

        final int[] product_values = new int[ productList.size()+1];
        final String[] product_ = new String[productList.size()+1];

// Area Spinner
        product_[0]="Choose From";
        product_values[0]=0;
        for(int i=0;i<productList.size();i++){
            product_[i+1]=productList.get(i).getName();
            product_values[i+1]=productList.get(i).getId();
        }

        qtn=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.number_edit_text);
        product=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.product_spinner);
        amount=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.product_amount);
        rate=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.p_rate);
        product_discount=parentLinearLayout.getChildAt(parentLinearLayout.getChildCount() - 2).findViewById(R.id.new_orderdiscount);

        //Product Spinner
      // product=(Spinner)findViewById(R.id.product_spinner);
        final ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,product_);
        product.setAdapter(adapter3);

        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getlayoutId(view);




                System.out.println("Parent retunr"+view.getParent().toString());

               int Index_id_child=((LinearLayout)view.getParent().getParent()).getId();

                 product=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.product_spinner);

                //String temp_product=product.getSelectedItem().toString();
                int product_index=find_product(product.getSelectedItem().toString(),product_,product_values);

                product_discount=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.new_orderdiscount);

                rate=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.p_rate);
                System.out.println("Child Id: "+Index_id_child+" Product Index: "+product.getSelectedItem().toString());
                qtn=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.number_edit_text);
                amount=parentLinearLayout.getChildAt(Index_id_child).findViewById(R.id.product_amount);

              //  System.out.println("Dynamic Rate"+rate.getText());
                rate.setText(String.valueOf(productList.get(product_index-1).getT_p_gst_price()));
                System.out.println("Dynamic Rate"+rate.getText());
                rate.addTextChangedListener(rateChangeTextWatcher);
                //qtn.addTextChangedListener(amountEditTextWatcher);
                //product_discount.addTextChangedListener(new disamountEditTextWatcher(amount,rate,qtn,product_discount,view));

                qtn.addTextChangedListener(new amountEditTextWatcher(amount,rate,qtn,view));


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void getlayoutId(View parentLinearLayout) {

        System.out.println("Prew"+(parentLinearLayout.getParent().getParent()));

    }


    public void onDelete(View v) {

        parentLinearLayout.removeView((View) v.getParent());
    }

    public  void Calculate_order(View v){
        double gst=0.0;
        double amt=0.0;

        System.out.println("Parent child count)"+parentLinearLayout.getChildCount());


        final int[] product_values = new int[ productList.size()+1];
        final String[] product_ = new String[productList.size()+1];

// Area Spinner
        product_[0]="Choose From";
        product_values[0]=0;
        for(int i=0;i<productList.size();i++){
            product_[i+1]=productList.get(i).getName();
            product_values[i+1]=productList.get(i).getId();
        }





        qtn.setError(null);
        discount.setError(null);
        specical_dic.setError(null);

        if(area.getSelectedItem().equals("Choose From")){
            TextView errorText = (TextView)area.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("This Field is required");
            focusView = area;
            cancel = true;

        }
        else if(shop.getSelectedItem().equals("Choose From")){
            TextView errorText = (TextView)shop.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("This Field is required");
            focusView = shop;
            cancel = true;

        }
        else if(product.getSelectedItem().equals("Choose From")){
            TextView errorText = (TextView)product.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("This Field is required");
            focusView = product;
            cancel = true;

        }
        else if( TextUtils.isEmpty(qtn.getText().toString())) {
            qtn.setError("This Field is Required");
            focusView = qtn;
            cancel = true;

        }else if( TextUtils.isEmpty(discount.getText().toString())) {
            discount.setError("This Field is Required");
            focusView = discount;
            cancel = true;

        }else if( TextUtils.isEmpty(specical_dic.getText().toString())) {
            specical_dic.setError("This Field is Required");
            focusView = specical_dic;
            cancel = true;

        }
        else if( TextUtils.isEmpty(product_discount.getText().toString())) {
            product_discount.setText("0");

        }
        else{
             for(int i=3;i<parentLinearLayout.getChildCount()-1;i++){
                EditText temp_qtn= parentLinearLayout.getChildAt(i).findViewById(R.id.number_edit_text);
                TextView temp_amt= parentLinearLayout.getChildAt(i).findViewById(R.id.product_amount);
                Spinner pro=(parentLinearLayout.getChildAt(i).findViewById(R.id.product_spinner));
                String selected_item=pro.getSelectedItem().toString();
                int product_index=find_product(selected_item,product_,product_values);
                gst=gst+(productList.get(product_index-1).getGst_price()*Double.parseDouble(temp_qtn.getText().toString()));

                amt=amt+Double.parseDouble(temp_amt.getText().toString());
            }
            //gst_tax.setText(String.valueOf(gst));
            gst_tax.setText("0.00");
            double disc=Double.parseDouble(discount.getText().toString());

            double s_dic=Double.parseDouble(specical_dic.getText().toString());
            Double amt_dic=((amt-gst)*disc)/100+s_dic;


            amt=amt-amt_dic;
            gst=0.00;
            total_amount1.setText(String.valueOf(amt+gst));
        }





    }
    public  void Save_order(View v){



        qtn.setError(null);
        discount.setError(null);
        specical_dic.setError(null);
        if(area.getSelectedItem().equals("Choose From")){
            TextView errorText = (TextView)area.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("This Field is required");
            focusView = area;
            cancel = true;

        }
        else if(shop.getSelectedItem().equals("Choose From")){
            TextView errorText = (TextView)shop.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("This Field is required");
            focusView = shop;
            cancel = true;

        }
        else if(product.getSelectedItem().equals("Choose From")){
            TextView errorText = (TextView)product.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("This Field is required");
            focusView = product;
            cancel = true;

        }
        else if( TextUtils.isEmpty(qtn.getText().toString())) {
            qtn.setError("This Field is Required");
            focusView = qtn;
            cancel = true;

        }else if( TextUtils.isEmpty(discount.getText().toString())) {
            discount.setError("This Field is Required");
            focusView = discount;
            cancel = true;

        }else if( TextUtils.isEmpty(specical_dic.getText().toString())) {
            specical_dic.setError("This Field is Required");
            focusView = specical_dic;
            cancel = true;

        }
        else if( TextUtils.isEmpty(product_discount.getText().toString())) {
            product_discount.setText("0");

        }
        else{
            double gst=0.0;
            double amt=0.0;

            System.out.println("Parent child count)"+parentLinearLayout.getChildCount());


            final int[] product_values = new int[ productList.size()+1];
            final String[] product_ = new String[productList.size()+1];

// Area Spinner
            product_[0]="Choose From";
            product_values[0]=0;
            for(int i=0;i<productList.size();i++){
                product_[i+1]=productList.get(i).getName();
                product_values[i+1]=productList.get(i).getId();
            }


            for(int i=3;i<parentLinearLayout.getChildCount()-1;i++){
                EditText temp_qtn= parentLinearLayout.getChildAt(i).findViewById(R.id.number_edit_text);
                TextView temp_amt= parentLinearLayout.getChildAt(i).findViewById(R.id.product_amount);
                Spinner pro=(parentLinearLayout.getChildAt(i).findViewById(R.id.product_spinner));
                String selected_item=pro.getSelectedItem().toString();

                int product_index=find_product(selected_item,product_,product_values);
                gst=gst+(productList.get(product_index-1).getGst_price()*Double.parseDouble(temp_qtn.getText().toString()));

                amt=amt+Double.parseDouble(temp_amt.getText().toString());
            }
          //  gst_tax.setText(String.valueOf(gst));
            gst_tax.setText("0.00");

            double disc=Double.parseDouble(discount.getText().toString());
            double s_dic=Double.parseDouble(specical_dic.getText().toString());
            Double amt_dic=((amt-gst)*disc)/100+s_dic;

            amt=amt-amt_dic;
            gst=0.00;
            total_amount1.setText(String.valueOf(amt+gst));



            //insert into data base
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy" ,Locale.ENGLISH);
            Date dateobj = new Date();

            Order temp_order=new Order(df.format(dateobj),getArea_(),discount.getText().toString(),specical_dic.getText().toString(),String.valueOf(gst),String.valueOf(amt+gst),getShop_id(shop.getSelectedItem().toString()),shop.getSelectedItem().toString());
            db.insert_order(temp_order);
            int order_id=db.getlastinserted_order();

            for(int i=3;i<parentLinearLayout.getChildCount()-1;i++){
                EditText temp_rate=parentLinearLayout.getChildAt(i).findViewById(R.id.p_rate);
                EditText temp_qtn= parentLinearLayout.getChildAt(i).findViewById(R.id.number_edit_text);
                TextView temp_amt= parentLinearLayout.getChildAt(i).findViewById(R.id.product_amount);
                Spinner pro=(parentLinearLayout.getChildAt(i).findViewById(R.id.product_spinner));


                EditText temp_product_dis= parentLinearLayout.getChildAt(i).findViewById(R.id.new_orderdiscount);


                System.out.println("Temp Product Discount"+temp_product_dis.getText().toString());

                String selected_item=pro.getSelectedItem().toString();

                int product_index=find_product(selected_item,product_,product_values);
                //gst=gst+(productList.get(product_index-1).getGst_price()*Double.parseDouble(temp_qtn.getText().toString()));
                gst=0.0;
                amt=amt+Double.parseDouble(temp_amt.getText().toString());
                DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy" ,Locale.ENGLISH);
                Date dateobj1 = new Date();
                if(temp_product_dis.getText().toString().isEmpty()){

                    Order_detail temp_order_detail=new Order_detail(df1.format(dateobj1),order_id,String.valueOf(product_index),selected_item,temp_rate.getText().toString(),temp_qtn.getText().toString(),temp_amt.getText().toString(),"0");
                    db.insert_order_detail(temp_order_detail);
                }
                else{
                    Order_detail temp_order_detail=new Order_detail(df1.format(dateobj1),order_id,String.valueOf(product_index),selected_item,temp_rate.getText().toString(),temp_qtn.getText().toString(),temp_amt.getText().toString(),temp_product_dis.getText().toString());
                    db.insert_order_detail(temp_order_detail);
                }


            }
            Toast.makeText(New_Orders_Activity.this, "Order has been Booked", Toast.LENGTH_LONG).show();
            this.finish();
            Intent i=new Intent(New_Orders_Activity.this,New_Orders_Activity.class);
            this.startActivity(i);
        }


    }

    public Area getArea_() {

        final int[] area_values = new int[ areaList.size()+1];
        final String[] areas_ = new String[areaList.size()+1];

// Area Spinner
        areas_[0]="Choose From";
        area_values[0]=0;
        for(int i=0;i<areaList.size();i++){
            areas_[i+1]=areaList.get(i).getArea_name();
            area_values[i+1]=areaList.get(i).getArea_Id();
        }
        String temp_area=area.getSelectedItem().toString();

        int area_index=find_area(temp_area,areas_,area_values);



        return new Area(temp_area,area_index);
    }

    public int getShop_id(String shop_name) {


        int[] shop_values = new int[ shopList.size()+1];
        String[] shop_ = new String[shopList.size()+1];


        shop_[0]="Choose From";
        shop_values[0]=0;
        for(int j=0;j<shopList.size();j++){
            shop_[j+1]=shopList.get(j).getShop_name();
            shop_values[j+1]=shopList.get(j).getId();
            //System.out.println(shop_[j].toString());
        }
        for(int i=1;i<shop_.length;i++){
            if (shop_[i].equals(shop_name)){
                // System.out.println("TEmp Area1"+);

                return shop_values[i];
            }

        }

        return 0;
    }
    private final TextWatcher amountEditTextWatcher = new TextWatcher() {

        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            int qtn=0;
            amount.setText("0");
            try {
                System.out.println("Text Watcher");
                qtn=Integer.parseInt(s.toString());

                //  amount.setText("hello");
                amount.setText(String.valueOf(qtn*(Double.parseDouble(rate.getText().toString()))));
            } catch (NumberFormatException nfe) {
                amount.setText("0");
                nfe.printStackTrace();
            }

        }

        @Override
        public void afterTextChanged(Editable s) { }
        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }

    };
    private final TextWatcher rateChangeTextWatcher = new TextWatcher() {

        // called when the user modifies the bill amount
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {


           // int qtn=0;
            amount.setText("0");
            try {
                System.out.println("Text Watcher");
               // qtn=Integer.parseInt(s.toString());

                //  amount.setText("hello");
                amount.setText(String.valueOf(Integer.parseInt(qtn.getText().toString())*(Double.parseDouble(rate.getText().toString()))));
            } catch (NumberFormatException nfe) {
                amount.setText("0");
                nfe.printStackTrace();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {


            amount.setText("0");
            try {
                System.out.println("Text Watcher Rate Change" );
            //    qtn=Integer.parseInt(s.toString());

                //  amount.setText("hello");
                amount.setText(String.valueOf(Integer.parseInt(qtn.getText().toString())*(Double.parseDouble(rate.getText().toString()))));
            } catch (NumberFormatException nfe) {
                amount.setText("0");
                nfe.printStackTrace();
            }
        }
        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) { }

    };
    public static class amountEditTextWatcher implements TextWatcher {


        private TextView amount;
        private EditText rate;
        private EditText qtn;
        private View view;

        public amountEditTextWatcher(TextView amount, EditText rate, EditText qtn, View view) {

            this.amount=amount;
            this.rate=rate;
            this.qtn=qtn;
            this.view=view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int qtn=0;
            amount.setText("0");
            try {
                System.out.println("Text Watcher");
                qtn=Integer.parseInt(s.toString());

                //  amount.setText("hello");
                amount.setText(String.valueOf(qtn*(Double.parseDouble(rate.getText().toString()))));
            } catch (NumberFormatException nfe) {
                amount.setText("0");
                nfe.printStackTrace();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
    public static class disamountEditTextWatcher implements TextWatcher {


        private TextView amount;
        private EditText rate;
        private EditText qtn;
        private EditText disproduct;
        private View view;

        public disamountEditTextWatcher(TextView amount, EditText rate, EditText qtn, EditText disproduct, View view1) {


            this.amount=amount;
            this.rate=rate;
            this.qtn=qtn;
            this.view=view;
            this.disproduct=disproduct;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            double dis=0;
            amount.setText("0");
            try {
                System.out.println("Text Watcher");
                dis=Integer.parseInt(s.toString());

                System.out.println("Text Watcher Discount"+dis);
                Double temp_rate=Double.parseDouble(rate.getText().toString());
                Double temp_qtn=Double.parseDouble(qtn.getText().toString());
                dis=((temp_qtn*temp_rate)*dis/100);
                Double temp_amount=(temp_rate*temp_qtn)-dis;
                //  amount.setText("hello");
                amount.setText(String.valueOf(temp_amount));
            } catch (NumberFormatException nfe) {
                amount.setText("0");
                nfe.printStackTrace();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    public  void Update_order(View v){



        qtn.setError(null);
        discount.setError(null);
        specical_dic.setError(null);

         if(product.getSelectedItem().equals("Choose From")){
            TextView errorText = (TextView)product.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("This Field is required");
            focusView = product;
            cancel = true;

        }
        else if( TextUtils.isEmpty(qtn.getText().toString())) {
            qtn.setError("This Field is Required");
            focusView = qtn;
            cancel = true;

        }else if( TextUtils.isEmpty(discount.getText().toString())) {
            discount.setError("This Field is Required");
            focusView = discount;
            cancel = true;

        }else if( TextUtils.isEmpty(specical_dic.getText().toString())) {
            specical_dic.setError("This Field is Required");
            focusView = specical_dic;
            cancel = true;

        }
        else{
            double gst=0.0;
            double amt=0.0;

            System.out.println("Parent child count)"+parentLinearLayout.getChildCount());


            final int[] product_values = new int[ productList.size()+1];
            final String[] product_ = new String[productList.size()+1];

// Area Spinner
            product_[0]="Choose From";
            product_values[0]=0;
            for(int i=0;i<productList.size();i++){
                product_[i+1]=productList.get(i).getName();
                product_values[i+1]=productList.get(i).getId();
            }


            for(int i=3;i<parentLinearLayout.getChildCount()-1;i++){
                EditText temp_qtn= parentLinearLayout.getChildAt(i).findViewById(R.id.number_edit_text);
                TextView temp_amt= parentLinearLayout.getChildAt(i).findViewById(R.id.product_amount);
                Spinner pro=(parentLinearLayout.getChildAt(i).findViewById(R.id.product_spinner));
                String selected_item=pro.getSelectedItem().toString();

                int product_index=find_product(selected_item,product_,product_values);
                gst=gst+(productList.get(product_index-1).getGst_price()*Double.parseDouble(temp_qtn.getText().toString()));

                amt=amt+Double.parseDouble(temp_amt.getText().toString());
            }
            //  gst_tax.setText(String.valueOf(gst));
            gst_tax.setText("0.00");

            double disc=Double.parseDouble(discount.getText().toString());
            double s_dic=Double.parseDouble(specical_dic.getText().toString());
            Double amt_dic=((amt-gst)*disc)/100+s_dic;

            amt=amt-amt_dic;
            gst=0.00;
            total_amount1.setText(String.valueOf(amt+gst));



            //insert into data base
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy" ,Locale.ENGLISH);
            Date dateobj = new Date();

            Order temp_order=new Order(df.format(dateobj),getArea_(),discount.getText().toString(),specical_dic.getText().toString(),String.valueOf(gst),String.valueOf(amt+gst),getShop_id(shop.getSelectedItem().toString()),shop.getSelectedItem().toString());

           db.update_order(temp_order,Integer.parseInt(update_order_btn.getTag().toString()));


           //  db.insert_order(temp_order);
           db.delete_order_detail_child(Integer.parseInt(update_order_btn.getTag().toString()));
            int order_id=Integer.parseInt(update_order_btn.getTag().toString());

            for(int i=3;i<parentLinearLayout.getChildCount()-1;i++){
                EditText temp_rate=parentLinearLayout.getChildAt(i).findViewById(R.id.p_rate);
                EditText temp_qtn= parentLinearLayout.getChildAt(i).findViewById(R.id.number_edit_text);
                TextView temp_amt= parentLinearLayout.getChildAt(i).findViewById(R.id.product_amount);
                Spinner pro=(parentLinearLayout.getChildAt(i).findViewById(R.id.product_spinner));
                String selected_item=pro.getSelectedItem().toString();
                EditText temp_product_dis= parentLinearLayout.getChildAt(i).findViewById(R.id.new_orderdiscount);

                int product_index=find_product(selected_item,product_,product_values);
                //gst=gst+(productList.get(product_index-1).getGst_price()*Double.parseDouble(temp_qtn.getText().toString()));
                gst=0.0;
                amt=amt+Double.parseDouble(temp_amt.getText().toString());
                DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy" ,Locale.ENGLISH);
                Date dateobj1 = new Date();

                Order_detail temp_order_detail=new Order_detail(df1.format(dateobj1),order_id,String.valueOf(product_index),selected_item,temp_rate.getText().toString(),temp_qtn.getText().toString(),temp_amt.getText().toString(),temp_product_dis.getText().toString());
                db.insert_order_detail(temp_order_detail);
            }
            Toast.makeText(New_Orders_Activity.this, "Order has been Updated", Toast.LENGTH_LONG).show();
            this.finish();

            Intent i=new Intent(New_Orders_Activity.this,OrderList.class);
             i.putExtra("order_date_for _view",temp_order.getDate());
             i.putExtra("orderarea_for _view",temp_order.getArea().getArea_Id());
            this.startActivity(i);
        }


    }


}
