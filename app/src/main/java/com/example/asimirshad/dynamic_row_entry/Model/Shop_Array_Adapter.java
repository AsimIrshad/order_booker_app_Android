package com.example.asimirshad.dynamic_row_entry.Model;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.asimirshad.dynamic_row_entry.DatabaseHandler;
import com.example.asimirshad.dynamic_row_entry.New_product_Activity;
import com.example.asimirshad.dynamic_row_entry.New_shop_activity;
import com.example.asimirshad.dynamic_row_entry.Products_Activity;
import com.example.asimirshad.dynamic_row_entry.R;
import com.example.asimirshad.dynamic_row_entry.Shops_Activity;

import java.util.ArrayList;

public class Shop_Array_Adapter extends ArrayAdapter<Shop> {

    private Context c;
    private DatabaseHandler db;
    Button edit;
    Button delete1;
    public Shop_Array_Adapter(Context context, ArrayList<Shop> path, DatabaseHandler db) {
        super(context, 0, path);
        c=context;
        this.db=db;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Shop temp_shop = getItem(position);
        // System.out.println(temp_product.getName());
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_list_view, parent, false);
        }
        // Lookup view for data population

        TextView shop_name = (TextView) convertView.findViewById(R.id.V_shop_name);
        TextView keeper = (TextView) convertView.findViewById(R.id.v_keeper);
        TextView cnic = (TextView) convertView.findViewById(R.id.v_cnic);
        TextView phone = (TextView) convertView.findViewById(R.id.v_phone);
        TextView address = (TextView) convertView.findViewById(R.id.V_address);
        TextView area = (TextView) convertView.findViewById(R.id.V_area);
        edit = (Button) convertView.findViewById(R.id.edit_shop);
        delete1 = (Button) convertView.findViewById(R.id.delete_shop);


        shop_name.setText(temp_shop.getShop_name());
        keeper.setText((String.valueOf(temp_shop.getShop_keeper()) ));
        cnic.setText((String.valueOf(temp_shop.getCnic()) ));
        phone.setText((String.valueOf(temp_shop.getPhone_no()) ));
        address.setText((String.valueOf(temp_shop.getAddress()) ));
        area.setText(temp_shop.getArea().getArea_name());
        edit.setTag(temp_shop.getId());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                System.out.println("Button" +view.getTag());

                Intent i=new Intent(c,New_shop_activity.class);
                i.putExtra("Shop_for _edit",(Integer) (view.getTag()));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Activity a=(Activity) getContext() ;
                a.finish();

                c.startActivity(i);
            }
        });

        System.out.println(temp_shop.getId());
        delete1.setTag(temp_shop.getId());
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
                        db.Delete_shop(id);
                        dialog.dismiss();
                        Intent i=new Intent(c,Shops_Activity.class);

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
