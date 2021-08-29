package com.example.asimirshad.dynamic_row_entry.Model;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asimirshad.dynamic_row_entry.DatabaseHandler;
import com.example.asimirshad.dynamic_row_entry.New_product_Activity;
import com.example.asimirshad.dynamic_row_entry.Products_Activity;
import com.example.asimirshad.dynamic_row_entry.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class productArrayAdapter extends ArrayAdapter<Product> {

   private Context c;
    private DatabaseHandler db;
    Button edit;
    Button delete1;
    public productArrayAdapter(Context context, ArrayList<Product> path, DatabaseHandler db) {
        super(context, 0, path);
        c=context;
        this.db=db;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product temp_product = getItem(position);
       // System.out.println(temp_product.getName());
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.products_list_view, parent, false);
        }
        // Lookup view for data population

        TextView product_name = (TextView) convertView.findViewById(R.id.V_Product_name);
        TextView t_price = (TextView) convertView.findViewById(R.id.v_trade_price);
        TextView gst = (TextView) convertView.findViewById(R.id.v_gst);
        TextView r_price = (TextView) convertView.findViewById(R.id.V_retail_price);
        TextView t_plus_tax_price = (TextView) convertView.findViewById(R.id.v_t_p_plus_tax);
         edit = (Button) convertView.findViewById(R.id.edit_product);
         delete1 = (Button) convertView.findViewById(R.id.delete_product1);


        product_name.setText(temp_product.getName().toString()+"    "+temp_product.getSize());
        t_price.setText((String.valueOf(temp_product.getTrade_price()) ));
        gst.setText((String.valueOf(temp_product.getGst_price()) ));
        r_price.setText((String.valueOf(temp_product.getRetail_price()) ));
        t_plus_tax_price.setText((String.valueOf(temp_product.getT_p_gst_price()) ));

        edit.setTag(temp_product.getId());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            System.out.println("Button" +view.getTag());

                Intent i=new Intent(c,New_product_Activity.class);
                i.putExtra("Product_for _edit",(Integer) (view.getTag()));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 Activity a=(Activity) getContext() ;
                 a.finish();

                c.startActivity(i);
            }
        });

        System.out.println(temp_product.getId());
        delete1.setTag(temp_product.getId());
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog diaBox = AskOption((Integer) (view.getTag()));
                diaBox.show();


            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
    public AlertDialog AskOption(final int id)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(c)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.ic_delete_forever_black_24dp)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        db.Delete_product(id);
                        dialog.dismiss();
                        Intent i=new Intent(c,Products_Activity.class);

                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        c.startActivity(i);

                    }

                })



                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

}
