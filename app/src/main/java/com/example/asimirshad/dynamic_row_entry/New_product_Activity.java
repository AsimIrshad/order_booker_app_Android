package com.example.asimirshad.dynamic_row_entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.asimirshad.dynamic_row_entry.Model.Product;

public class New_product_Activity extends AppCompatActivity {
    DatabaseHandler db = new DatabaseHandler(this);
    EditText n_p_n;
    EditText p_size;
    EditText n_t_p;
    EditText tax;
    EditText n_t_p_t;
    EditText n_r_p;
    Button btn,update_btn;
    View focusView = null;
    boolean cancel = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_product);

         n_p_n=(EditText)findViewById(R.id.new_product_name);
         p_size=(EditText)findViewById(R.id.new_pro_size);
         n_t_p=(EditText)findViewById(R.id.new_trade_price);
        tax=(EditText)findViewById(R.id.new_gst);
         n_t_p_t=(EditText)findViewById(R.id.new_trade_plus_tax);
        n_r_p=(EditText)findViewById(R.id.new_retail_price);
         btn=(Button) findViewById(R.id.Save_new_product);
        update_btn=(Button)findViewById(R.id.update_new_product);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_product(view);
            }
        });

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =String.valueOf( b.get("Product_for _edit"));
            System.out.println(j);
            go_for_edit(Integer.parseInt(j));
        }


    }

    private void go_for_edit(int i) {
        Product edit_product=db.getProduct(i);

        n_p_n.setText(edit_product.getName());
        p_size.setText(edit_product.getSize());
        tax.setText(String.valueOf(edit_product.getGst_price()));
        n_t_p.setText(String.valueOf(edit_product.getTrade_price()));
        n_t_p_t.setText(String.valueOf(edit_product.getT_p_gst_price()));
        n_r_p.setText(String.valueOf(edit_product.getRetail_price()));
        btn.setVisibility(View.INVISIBLE);
        update_btn.setTag(i);
        update_btn.setVisibility(View.VISIBLE);


    }
    public void Update_product(View view){
        n_p_n.setError(null);
        p_size.setError(null);
        n_t_p.setError(null);
        tax.setError(null);
        n_t_p_t.setError(null);
        n_r_p.setError(null);

        if (TextUtils.isEmpty(n_p_n.getText().toString())) {
            n_p_n.setError("This Field is Required");
            focusView = n_p_n;
            cancel = true;
        }
        else if( TextUtils.isEmpty(p_size.getText().toString())) {
            p_size.setError("This Field is Required");
            focusView = p_size;
            cancel = true;

        }else if( TextUtils.isEmpty(n_t_p.getText().toString())) {
            n_t_p.setError("This Field is Required");
            focusView = n_t_p;
            cancel = true;

        }else if( TextUtils.isEmpty(tax.getText().toString())) {
            tax.setError("This Field is Required");
            focusView = tax;
            cancel = true;

        }else if( TextUtils.isEmpty(n_t_p_t.getText().toString())) {
            n_t_p_t.setError("This Field is Required");
            focusView = n_t_p_t;
            cancel = true;

        }else if( TextUtils.isEmpty(n_r_p.getText().toString())) {
            n_r_p.setError("This Field is Required");
            focusView = n_r_p;
            cancel = true;

        }
        else {
            //Add product to database
            Product product=new Product(Integer.parseInt(update_btn.getTag().toString()),n_p_n.getText().toString(),p_size.getText().toString(),java.lang.Double.parseDouble(n_t_p.getText().toString()),java.lang.Double.parseDouble(tax.getText().toString()),java.lang.Double.parseDouble(n_t_p_t.getText().toString()),java.lang.Double.parseDouble(n_r_p.getText().toString()));

            db.updateproduct(product);
            this.finish();
            Intent i=new Intent(New_product_Activity.this,Products_Activity.class);
            this.startActivity(i);

        }


    }

    public void add_product(View view){


        n_p_n.setError(null);
        p_size.setError(null);
        n_t_p.setError(null);
        tax.setError(null);
        n_t_p_t.setError(null);
        n_r_p.setError(null);

        if (TextUtils.isEmpty(n_p_n.getText().toString())) {
            n_p_n.setError("This Field is Required");
            focusView = n_p_n;
            cancel = true;
        }
        else if( TextUtils.isEmpty(p_size.getText().toString())) {
            p_size.setError("This Field is Required");
            focusView = p_size;
            cancel = true;

        }else if( TextUtils.isEmpty(n_t_p.getText().toString())) {
            n_t_p.setError("This Field is Required");
            focusView = n_t_p;
            cancel = true;

        }else if( TextUtils.isEmpty(tax.getText().toString())) {
            tax.setError("This Field is Required");
            focusView = tax;
            cancel = true;

        }else if( TextUtils.isEmpty(n_t_p_t.getText().toString())) {
            n_t_p_t.setError("This Field is Required");
            focusView = n_t_p_t;
            cancel = true;

        }else if( TextUtils.isEmpty(n_r_p.getText().toString())) {
            n_r_p.setError("This Field is Required");
            focusView = n_r_p;
            cancel = true;

        }
        else {
            //Add product to database
            Product product=new Product(n_p_n.getText().toString(),p_size.getText().toString(),java.lang.Double.parseDouble(n_t_p.getText().toString()),java.lang.Double.parseDouble(tax.getText().toString()),java.lang.Double.parseDouble(n_t_p_t.getText().toString()),java.lang.Double.parseDouble(n_r_p.getText().toString()));

            db.addproduct(product);
            this.finish();
            Intent i=new Intent(New_product_Activity.this,Products_Activity.class);
            this.startActivity(i);

        }




    }
}
