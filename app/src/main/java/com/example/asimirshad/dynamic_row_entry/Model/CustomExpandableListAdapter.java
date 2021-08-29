package com.example.asimirshad.dynamic_row_entry.Model;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.asimirshad.dynamic_row_entry.DatabaseHandler;
import com.example.asimirshad.dynamic_row_entry.New_shop_activity;
import com.example.asimirshad.dynamic_row_entry.R;
import com.example.asimirshad.dynamic_row_entry.Shops_Activity;

import java.util.HashMap;
import java.util.List;



public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context1;
    private List<Area> expandableListTitle;
    private HashMap<Area, List<Shop>> expandableListDetail;
    private DatabaseHandler db;
    Button edit;
    Button delete1;

    public CustomExpandableListAdapter(Context context, List<Area> expandableListTitle,
                                       HashMap<Area, List<Shop>> expandableListDetail) {
        this.context1 = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
         Shop temp_shop = (Shop) getChild(listPosition, expandedListPosition);


        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context1
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.shop_list_view, null);
        }

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

                Intent i=new Intent(context1,New_shop_activity.class);
                i.putExtra("Shop_for _edit",(Integer) (view.getTag()));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Activity a=(Activity) context1 ;
                a.finish();

                context1.startActivity(i);
            }
        });

        System.out.println(temp_shop.getId());
        delete1.setTag(temp_shop.getId());
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog diaBox = AskOption(Integer.parseInt(delete1.getTag().toString()));
                diaBox.show();


            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Area listTitle = (Area) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context1.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);


        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle.getArea_name());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
    public AlertDialog AskOption(final int id)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(context1)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setIcon(R.drawable.ic_delete_forever_black_24dp)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        db.Delete_shop(id);
                        dialog.dismiss();
                        Intent i=new Intent(context1,Shops_Activity.class);

                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context1.startActivity(i);

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