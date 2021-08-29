package com.example.asimirshad.dynamic_row_entry.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import com.example.asimirshad.dynamic_row_entry.DatabaseHandler;
import com.example.asimirshad.dynamic_row_entry.OrderList;
import com.example.asimirshad.dynamic_row_entry.R;

import org.json.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderArray_adapter extends ArrayAdapter<Order_group> {

    private Context c;
    private DatabaseHandler db;
    Button code;
    Button view_orders;



    public OrderArray_adapter(Context context, ArrayList<Order_group> path, DatabaseHandler db) {
        super(context, 0, path);
        c=context;
        this.db=db;


    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Order_group temp_order_group = getItem(position);
        // System.out.println(temp_product.getName());
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_listview, parent, false);
        }
        // Lookup view for data population



        TextView area = (TextView) convertView.findViewById(R.id.v_order_area_);
        TextView date_ = (TextView) convertView.findViewById(R.id.date_order_v);
        TextView amount = (TextView) convertView.findViewById(R.id.orders_amount);

        code = (Button) convertView.findViewById(R.id.get_code);
        view_orders = (Button) convertView.findViewById(R.id.view_orders);



        area.setText(temp_order_group.getArea_name());
        date_.setText(temp_order_group.getDate());
        amount.setText(temp_order_group.getAmount());


        view_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(c,OrderList.class);
                i.putExtra("order_date_for _view",temp_order_group.getDate());
                i.putExtra("orderarea_for _view",temp_order_group.getArea_id());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Activity a=(Activity) getContext() ;
                a.finish();

                c.startActivity(i);
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Order> temp_order_detail = new ArrayList<Order>();

                temp_order_detail=db.getorderList(temp_order_group.getDate(),temp_order_group.getArea_id(),temp_order_group.getOrder_id());
                createPdf(temp_order_detail);
                //JSONArray jsArray = new JSONArray(temp_order_detail);

                JSONArray jsonArray = new JSONArray();
                for (int i=0; i < temp_order_detail.size(); i++) {
                    jsonArray.put(temp_order_detail.get(i).getJSONObject(db));
                }

                 System.out.println("Json"+jsonArray);
                try {
                    create_code(jsonArray,temp_order_detail.get(0).getArea(),temp_order_detail.get(0).getDate());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        // Return the completed view to render on screen
        return convertView;
    }

    private void create_code(JSONArray jsonArray, Area area, String date) throws IOException {
        String file_name=area.getArea_name()+" "+(date.replace("/"," "));
        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Syed Brothers/code/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path+file_name+".txt";


        FileWriter fileWriter=new FileWriter(targetPdf);
        try {
            fileWriter.write(jsonArray.toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            fileWriter.flush();
            fileWriter.close();
        }
    }

    private void createPdf(ArrayList<Order> orders){
        // create a new document


        double final_amount=0.0;
        double final_discount=0.0;

        ArrayList<Product_Qtn> temp_product_qtn = new ArrayList<Product_Qtn>();

        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo;
        PdfDocument.Page page;
        Canvas canvas ;
        Paint paint ;
        DateFormat df1 = new SimpleDateFormat("dd MM yyyy");
        Date dateobj1 = new Date();


       // makeDispacth(temp_product_qtn,orders,final_amount,final_discount);

        for(int i=0;i<orders.size();i++){

            double amount=0.0;



            for(int j=0;j<orders.get(i).getOrder_detail().size();j++){

                String temp_product  =orders.get(i).getOrder_detail().get(j).getProduct_name();
                String temp_qtn= orders.get(i).getOrder_detail().get(j).getQtn();
                String temp_schme_qtn= orders.get(i).getOrder_detail().get(j).getDis_product();

                temp_product_qtn=isProduct_Availiable(temp_product,temp_qtn,temp_product_qtn,temp_schme_qtn);

                amount=amount+Double.parseDouble(orders.get(i).getOrder_detail().get(j).getAmount());


            }
            final_amount=final_amount+amount;
            final_discount=final_discount+(amount*Double.parseDouble(orders.get(i).getDiscount()))/100+Double.parseDouble(orders.get(i).getSpec_discount());


        }


        pageInfo = new PdfDocument.PageInfo.Builder(600, 600, 1).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();
        Paint paintf = new TextPaint();
        paintf.setTextSize(10);
        paintf.setTypeface(Typeface.create("Arial",Typeface.BOLD_ITALIC));
        paintf.setColor(Color.BLACK);
        canvas.drawText("Order Booking SKU Detail", 120, 20, paintf);

        canvas.drawText("Date:", 10, 50, paintf);
        canvas.drawText("Sale Man:", 120, 50, paintf);
        canvas.drawText("Order Booker:", 280, 50, paintf);
        canvas.drawText(orders.get(0).getDate(), 40, 50, paintf);
        canvas.drawText("----------------------", 165, 50, paintf);
        canvas.drawText("----------------------", 340, 50, paintf);


        canvas.drawText("Total Amount:", 10, 60, paintf);
        canvas.drawText("Discount", 120, 60, paintf);
        canvas.drawText("%Age:", 250, 60, paintf);

        canvas.drawText(String.valueOf(Math.round(final_amount)), 80, 60, paintf);
        canvas.drawText(String.valueOf(Math.round(final_discount)), 170, 60, paintf);
        canvas.drawText(String.valueOf((final_discount/final_amount*100)), 300, 60, paintf);


        canvas.drawText("Total SKU:", 10, 70, paintf);
        canvas.drawText("P.Calls", 120, 70, paintf);

        canvas.drawText(String.valueOf(temp_product_qtn.size()), 80, 70, paintf);
        canvas.drawText(String.valueOf(orders.size()), 170, 70, paintf);


        int y1=85;
        for(int j=0;j<temp_product_qtn.size();j++){


            canvas.drawText("("+(j+1)+")  "+temp_product_qtn.get(j).getProduct_name(), 10, y1, paintf);
            canvas.drawText("("+(j+1)+")  "+temp_product_qtn.get(j).getQtn(), 350, y1, paintf);
            canvas.drawText("("+(j+1)+")  "+temp_product_qtn.get(j).getScheme(), 450, y1, paintf);

            y1=y1+10;
        }

        document.finishPage(page);



        pageInfo = new PdfDocument.PageInfo.Builder(600, 600, 1).create();
        page = document.startPage(pageInfo);
        canvas = page.getCanvas();
        Paint paintg = new TextPaint();
        Paint paintgf = new TextPaint();

        paintf.setTextSize(10);
        paintf.setTypeface(Typeface.create("Arial",Typeface.BOLD_ITALIC));
        paintg.setTypeface(Typeface.create("Arial",Typeface.NORMAL));
        paintgf.setStyle(Paint.Style.STROKE);
        paintf.setColor(Color.BLACK);
        canvas.drawText("Shops Detail", 120, 20, paintf);
        canvas.drawText("Shop Name:", 10, 50, paintf);
        canvas.drawText("Amount:", 350, 50, paintf);


        int y12=85;
        for(int j=0;j<orders.size();j++){

            canvas.drawText(orders.get(j).getShop_name(), 10, y12, paintg);
            canvas.drawText(orders.get(j).getTotal_amount(), 350, y12, paintg);
            canvas.drawCircle( 425, y12,5, paintgf);
            y12=y12+10;
        }

        document.finishPage(page);


        for(int i=0;i<orders.size();i++){

            double amount=0.0;
            double dic_amount=0.0;
            pageInfo = new PdfDocument.PageInfo.Builder(600, 600, i+1).create();
            page = document.startPage(pageInfo);
            canvas = page.getCanvas();

            paint = new TextPaint();
            Paint paint1 = new TextPaint();
            paint1.setTextSize(10);
            paint1.setTypeface(Typeface.create("Arial",Typeface.BOLD_ITALIC));
            paint1.setColor(Color.BLACK);
            paint.setTextSize(10);
            paint.setTextAlign(Paint.Align.LEFT);

            paint.setTypeface(Typeface.create("Arial", Typeface.BOLD));

            paint.setColor(Color.BLACK);

//            Bitmap bitmap = BitmapFactory.decodeResource(c.getResources(), R.drawable.download);
//
//            canvas.drawBitmap(bitmap,300,400,paint);


            canvas.drawText("Invoice", 300, 20, paint);
            canvas.drawText("No:", 350, 20, paint);
            canvas.drawText(String.valueOf(i+1), 275, 20, paint);

            canvas.drawText("Syed Brothers", 10, 40, paint1);
            canvas.drawText("0300-0880924", 10, 55, paint1);
            canvas.drawText("NTN No: 7632442-1", 10, 65, paint1);
            canvas.drawText("25 No Stop, Shahdara Lahore", 10, 75, paint1);

            canvas.drawText("Shop Name:", 10, 90, paint1);
            canvas.drawText(orders.get(i).getShop_name(), 70, 90, paint);
            canvas.drawText("Date:", 180, 40, paint1);
            canvas.drawText(orders.get(i).getDate(), 220, 40, paint);

            canvas.drawLine(8,100,650,100,paint1);

            canvas.drawText("No", 10, 110, paint);
            canvas.drawText("ITEM", 60, 110, paint);
            canvas.drawText("QTY", 300, 110, paint);
            canvas.drawText(" RATE", 360, 110, paint);
            canvas.drawText(" SCHEME", 410, 110, paint);
            canvas.drawText(" AMOUNT", 490, 110, paint);

            canvas.drawLine(8,120,650,120,paint);
            Paint paint2 = new Paint();
            int x=10;
            int y=140;
            for(int j=0;j<orders.get(i).getOrder_detail().size();j++){

                paint2.setTextSize(10);
                paint2.setColor(Color.BLACK);
                paint2.setTypeface(Typeface.create("Arial", Typeface.NORMAL));

                //  System.out.println("Order Detail with Product"+orders.get(2).getOrder_detail().get(j).getProduct_name());
                canvas.drawText(String.valueOf(j+1), x, y, paint2);
                canvas.drawText(orders.get(i).getOrder_detail().get(j).getProduct_name(), x+50, y, paint2);
                canvas.drawText(orders.get(i).getOrder_detail().get(j).getQtn(), x+300, y, paint2);
                canvas.drawText(orders.get(i).getOrder_detail().get(j).getRate(), x+350, y, paint2);

                canvas.drawText(orders.get(i).getOrder_detail().get(j).getDis_product(), x+410, y, paint2);
                canvas.drawText(String.valueOf(Math.round(Double.parseDouble(orders.get(i).getOrder_detail().get(j).getAmount()))), x+490, y, paint2);
                amount=amount+Double.parseDouble(orders.get(i).getOrder_detail().get(j).getAmount());


                x=10;
                y=y+10;

            }
            final_amount=final_amount+amount;
            canvas.drawLine(8,y+90,650,y+90,paint);


            canvas.drawText("Sub Total:", x+320, y+100, paint2);
            canvas.drawText("Discount %age:", x+320, y+120, paint2);
            canvas.drawText("Special Discount:", x+320, y+130, paint2);
            canvas.drawText(" Tax :", x+320, y+140, paint2);
            canvas.drawText("Total Amount:", x+320, y+160, paint2);


            canvas.drawText(String.valueOf(Math.round(amount)), x+410, y+100, paint2);
            canvas.drawText(orders.get(i).getDiscount()+"%", x+410, y+120, paint2);
            canvas.drawText(orders.get(i).getSpec_discount(), x+410, y+130, paint2);
            canvas.drawText(orders.get(i).getGst(), x+410, y+140, paint2);
            canvas.drawText(String.valueOf(Math.round(Double.parseDouble(orders.get(i).getTotal_amount()))), x+410, y+160, paint);

          //canvas.drawText("Syed Brothers", 140, 20, paint);

            //canvas.drawTextOnPath();
            document.finishPage(page);

        }

       // JSONObject jsonObject=new JSONObject(orders.toString());

        // orders.
       // orders.

        String file_name=orders.get(0).getArea().getArea_name()+" "+(orders.get(0).getDate().replace("/"," "));
        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Syed Brothers/Dispatch/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = directory_path+file_name+".pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(c, "Pdf has been Generated .Go to File Manager", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error "+e.toString());
            Toast.makeText(c, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
    }

    private void makeDispacth(ArrayList<Product_Qtn> temp_product_qtn, ArrayList<Order> orders, double final_amount, double final_discount) {



        for(int i=0;i<orders.size();i++){

            double amount=0.0;



            for(int j=0;j<orders.get(i).getOrder_detail().size();j++){

                    String temp_product  =orders.get(i).getOrder_detail().get(j).getProduct_name();
                    String temp_qtn= orders.get(i).getOrder_detail().get(j).getQtn();
                    String temp_schme_qtn=orders.get(i).getOrder_detail().get(j).getDis_product();
                temp_product_qtn=isProduct_Availiable(temp_product,temp_qtn,temp_product_qtn,temp_schme_qtn);

                amount=amount+Double.parseDouble(orders.get(i).getOrder_detail().get(j).getAmount());


            }
            final_amount=final_amount+amount;
            final_discount=final_discount+(amount*Double.parseDouble(orders.get(i).getDiscount()))/100+Double.parseDouble(orders.get(i).getSpec_discount());


        }


    }

    private ArrayList<Product_Qtn> isProduct_Availiable(String temp_product, String temp_qtn, ArrayList<Product_Qtn> temp_product_qtn, String temp_schme_qtn){


        if(temp_product_qtn.size()==0){
            temp_product_qtn.add(new Product_Qtn(temp_product,temp_qtn,temp_schme_qtn));
        }
        else{
            Boolean flag=true;
            for(int i=0;i<temp_product_qtn.size();i++){
                if(temp_product_qtn.get(i).getProduct_name().equals(temp_product)){
                    temp_product_qtn.get(i).setQtn(String.valueOf(Double.parseDouble(temp_product_qtn.get(i).getQtn())+Double.parseDouble(temp_qtn)));
                    if(temp_schme_qtn.isEmpty()){
                        temp_schme_qtn="0";
                        System.out.println("hellow o am here" +
                                temp_product_qtn.get(i).getScheme());
                    }
                    if(temp_product_qtn.get(i).getScheme().isEmpty()){
                        temp_product_qtn.get(i).setScheme("0");
                    }
                        temp_product_qtn.get(i).setScheme(String.valueOf(Double.parseDouble(temp_product_qtn.get(i).getScheme()) + Double.parseDouble(temp_schme_qtn)));

                    flag=false;
                    break;

                }
            }
            if(flag){
                temp_product_qtn.add(new Product_Qtn(temp_product,temp_qtn,temp_schme_qtn));
            }
        }



        return temp_product_qtn;
    }

}
