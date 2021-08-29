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
import com.example.asimirshad.dynamic_row_entry.My_Orders_Activity;
import com.example.asimirshad.dynamic_row_entry.New_Orders_Activity;
import com.example.asimirshad.dynamic_row_entry.New_product_Activity;
import com.example.asimirshad.dynamic_row_entry.OrderList;
import com.example.asimirshad.dynamic_row_entry.Products_Activity;
import com.example.asimirshad.dynamic_row_entry.R;

import java.util.ArrayList;

public class OrderList_Array_Adapter extends ArrayAdapter<Order> {

    private Context c;
    private DatabaseHandler db;
    Button edit;
    Button view_detail, delete_btn;



    public OrderList_Array_Adapter(Context context, ArrayList<Order> path, DatabaseHandler db) {
        super(context, 0, path);
        c=context;
        this.db=db;


    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Order temp_order_list = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_list_listview, parent, false);
        }
        // Lookup view for data population



        TextView shop_name = (TextView) convertView.findViewById(R.id.L_shop_name);
        TextView area = (TextView) convertView.findViewById(R.id.L_area_name);
        TextView amount = (TextView) convertView.findViewById(R.id.L_amount);

        edit = (Button) convertView.findViewById(R.id.edit_order);
        view_detail = (Button) convertView.findViewById(R.id.view_orders);
        delete_btn = (Button) convertView.findViewById(R.id.Delete_order);


        shop_name.setText(temp_order_list.getShop_name());
        area.setText( temp_order_list.getArea().getArea_name());
        amount.setText(temp_order_list.getTotal_amount());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(c,New_Orders_Activity.class);
                i.putExtra("order_id",temp_order_list.getId());
                 //   i.putExtra("orderarea_for _view",temp_order_group.getArea_id());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Activity a=(Activity) getContext() ;
                a.finish();

                c.startActivity(i);
            }
        });

        delete_btn.setTag(temp_order_list.getId());
        delete_btn.setOnClickListener(new View.OnClickListener() {
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
                        db.Delete_order(id);
                        dialog.dismiss();
                        Intent i=new Intent(c,My_Orders_Activity.class);

                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        Activity a=(Activity) getContext() ;
                        a.finish();
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
