package com.example.asimirshad.dynamic_row_entry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.asimirshad.dynamic_row_entry.Model.Product;

public class MainActivity extends AppCompatActivity {
    private LinearLayout parentLinearLayout;
    private Toolbar mTopToolbar;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_page);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
        //parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);
      //  System.out.println("Product-availabilty"+db.getproducts().toString());
  if(db.getproducts().size()==0){
addnewProduct();

        }
    }

    private void addnewProduct() {
         db.addproduct(new Product("CHLE-CARE HONEY LOTION ECONOMY","310ml",366,0.0,352,440));
         db.addproduct(new Product("CHLF-CARE HONEY LOTION FAMILY","210ml",266,0.0,256,330.00));
         db.addproduct(new Product("CHLL-CARE HONEY LOTION LARGE","110ml",166,0.0,144,180.00));
         db.addproduct(new Product("CHLS-CARE HONEY LOTION SMALL","60ml",83,0.0,80,100.00));
         db.addproduct(new Product("CHLJ-CARE HONEY LOTION JUNIOR","27ml",42,0.0,35,40.00));
         db.addproduct(new Product("CCS-CARE COLD CREAM SMALL","70ml",166,0.0,152,180.00));
         db.addproduct(new Product("CCJ-CARE COLD CREAM JUNIOR","35ml",83,0.0,67,80.00));
         db.addproduct(new Product("CVCS-CARE VANISHING CREAM SMALL","70ml",166,0.0,152,190.00));
         db.addproduct(new Product("CVCJ-CARE VANISHING CREAM JUNIOR","35ml",83,0.0,80,100.00));
         db.addproduct(new Product("CPJL-CARE PURE PETROLEUM JELLY LARGE","100ml",83,0.0,80,100.00));
         db.addproduct(new Product("CPJM-CARE PURE PETROLEUM JELLY MEDIUM","50ml",50,0.0,48,60.00));
         db.addproduct(new Product("CPJS-CARE PURE PETROLEUM JELLY SMALL","30ml",33.16,0.0,32,40.00));
         db.addproduct(new Product("CBCE-CARE BLEACH CREAM ECONOMY","500ml",315,0.0,288,360.00));
         db.addproduct(new Product("CBCF-CARE BLEACH CREAM FAMILY","70ml",108,0.0,104,130));
         db.addproduct(new Product("CBCL-CARE BLEACH CREAM LARGE","35ml",67,0.0,64,80.00));
         db.addproduct(new Product("CBCS-CARE BLEACH CREAM SMALL","17ml",42,0.0,40,50.00));
         db.addproduct(new Product("CBCJ-CARE BLEACH CREAM JUNIOR","10ml",25,0.0,16,20.00));
         db.addproduct(new Product("CBTS-CARE BLEACH CREAM TUBE SMALL","20ml",32,0.0,32,40.00));
         db.addproduct(new Product("CBCP-CARE BLEACH CREAM POUCH","10ml",12,0.0,14.77,20));
         db.addproduct(new Product("PBN-CARE PEACH BLEACH CREAM POUCH","10ml",8.29,0.0,7,10));
         db.addproduct(new Product("COBP-CARE OLIVE OIL BLEACH CREAM POUCH","10ml",8.29,0.0,8,10));
         db.addproduct(new Product("CHTS-CARE HEEL CREAM TUBE SMALL","25ml",108,0.0,104,130.00));
         db.addproduct(new Product("CNCS-CARE NAPPY RASH CREAM TUBE SMALL","25ml",58.3,0.0,56,70));
         db.addproduct(new Product("CRLF-CARE HAIR REMOVER LOTION FAMILY","120ml",94,0.0,108,180.00));
         db.addproduct(new Product("CRLL-CARE HAIR REMOVER LOTION LARGE","80ml",43.5,0.0,78,130.00));
         db.addproduct(new Product("CRLS-CARE HAIR REMOVER LOTION SMALL","40ml",43.5,0.0,42,70.00));
         db.addproduct(new Product("CHRS-CARE HAIR REMOVER CREAM SMALL","25ml",43.5,0.0,42,60.00));
         db.addproduct(new Product("CHRP-CARE ROSE HAIR REMOVER LOTION SACHET","20ml",15,0.0,16,20.00
         ));
         db.addproduct(new Product("CPHL-CARE PRICKLY HEAT POWDER LARGE","150gm",131,26.15,149.23,180.00));
         db.addproduct(new Product("CPHS-CARE PRICKLY HEAT POWDER SMALL","100gm",102,20.34,116.07,140.00));
         db.addproduct(new Product("EFCL-H-EXCELLENCE BABY FEEDER LARGE WITH HANDLE","250ml",82,0.0,82.00,125.00));
         db.addproduct(new Product("EFCS-H-EXCELLENCE BABY FEEDER SMALL WITH HANDLE","125ml",72,0.0,72.00,110));
         db.addproduct(new Product("EFCL-EXCELLENCE BABY FEEDER LARGE","250ml",67,0.0,67.00,100.00));
         db.addproduct(new Product("EFCS-EXCELLENCE BABY FEEDER SMALL","125ml",57,0.0,57.00,90.00));
         db.addproduct(new Product("EBN-EXCELLENCE BABY NIPPLE","5gm",16,0.0,14.00,20.00));
         db.addproduct(new Product("NPRL-Care Nail Polish Remover Large","115ml",100,0.0,72,90.00));
         db.addproduct(new Product("NPRS-Care Nail Polish Remover Small","55ml",50,0.0,48,50.00));

            db.addproduct(new Product("CTS-CARE TALCUM SMALL CLASSIC","115ml",91.2,0.0,91.2,90.00));
            db.addproduct(new Product("CGL","55ml",88,0.0,107,50.00));
            db.addproduct(new Product("CGS","10ml",56,0.0,58.03,20.00));
            db.addproduct(new Product("CGJ","55ml",40,0.0,41.45,50.00));

        db.addproduct(new Product("CCML-CARE CLEANSING MILK LARGE","10ml",80,0.0,80,20.00));
        db.addproduct(new Product("CCMS-CARE CLEANSING MILK SMALL","10ml",64,0.0,64,20.00));


    }
    private void addnewProduct1() {
        db.addproduct(new Product("001    AHMAREEN 240ML","240ml",210,0.0,210,210));
        db.addproduct(new Product("002    ALERZEE CAP 20CAP","20CAP",140,0.0,140,140));
        db.addproduct(new Product("004    AKSER-E-PECHUS 25TAB","25 TAB",90,0.0,90 ,90));
        db.addproduct(new Product("004-X AKSER-E-PECHUSS 500TAB","500TAB",1100,0.0,1100,1100));
        db.addproduct(new Product("005   AKSER-E-PECHUSS 60 ML","60 ML",65,0.0,65,65.00));
        db.addproduct(new Product("006    LIVAKSEER 50TAB","50TAB",140,0.0,140,140));
        db.addproduct(new Product("006-X AKSER-E-JIGAR 500TAB","500TAB",850,0.0,850,850));
        db.addproduct(new Product("007    AKSER-E-JIGAR 120ML","120ml",120,0.0,120,120.00));
        db.addproduct(new Product("008    ALERJEX 40GRM","40 GRM",130,0.0,130,130));
        db.addproduct(new Product("009    ASTHAMINA 120ML","120ml",100,0.0,100,100));
        db.addproduct(new Product("010    ASPHATON 25TAB","25TAB",155,0.0,155,155));
        db.addproduct(new Product("010-X ASPHATON 500TAB","500TAB",1200,0.0,1200,1200));
        db.addproduct(new Product("012    IMSAKI JAWAHIR 10CAP","10CAP",180,0.0,180,180.00));
        db.addproduct(new Product("012-X IMSAKI JAWAHIR 100CAP","100CAP",1300,0.0,1300,1300.00));
        db.addproduct(new Product("013    ANTI FAT 75TAB","75TAB",260,0.0,260,260.00));
        db.addproduct(new Product("014   ATRIFAL USTOKHUDOS 50GRM","50GRM",55,0.0,55,55));
        db.addproduct(new Product("014-A ATRIFAL USTOKHUDOS 100GRM","100GRM",120,0.0,120,120.00));
        db.addproduct(new Product("015    ATRIFAL ZAMANI 50GRM","50GRM",45,0.0,45,45));
        db.addproduct(new Product("015-A ATRIFAL ZAMANI 100GRM","100GRM",130,0.0,130,130));
        db.addproduct(new Product("15-X ATRIFAL ZAMANI 500GRM","500GRM",350,0.0,350,350));
        db.addproduct(new Product("016    ATRIFAL KASHNIZI 50GRM","50GRM",25,0.0,25,25.00));
        db.addproduct(new Product("016-A ATRIFAL KASHNIZI 100GRM","100GRM",100,0.0,100,100));
        db.addproduct(new Product("016-X ATRIFAL KASHNIZI 500GRM","500GRM",225,0.0,225,225));
        db.addproduct(new Product("018    ISPAGHOL HUSK 25GRM","25GRM",70,0.0,70,70));
        db.addproduct(new Product("018-A ISPAGHOL HUSK 50 GRM","130GRM",130,0.0,130,130));
        db.addproduct(new Product("018-U ISPAGHOL HUSK 140 GRM","140 GRM",390,0.0,390,390));
        db.addproduct(new Product("019     ISPAGHOL COMPUND","10ml",70,0.0,70,70));
        db.addproduct(new Product("020    AMRIT DHARA 10ML","10ml",105,0.0,105,105));
        db.addproduct(new Product("022-A BARSHASHA 50GRM","50GRM",250,0.0,250,250.00));
        db.addproduct(new Product("023    BUKHAREEN 25TAB","25TAB",90,0.0,90,90));
        db.addproduct(new Product("023-X BUKHAREEN 500TAB","500TAB",900,0.0,900,900));
        db.addproduct(new Product("025-X B M 81 500TAB","500TAB",300,0.0,300,300));
        db.addproduct(new Product("031    PATHRI TOOR 25TAB","25TAB",155,0.0,155,155));
        db.addproduct(new Product("031-X PATHRI TOOR 500TAB","500TAB",1750,0.0,1750,1750.00));
        db.addproduct(new Product("032    PILEEN 50TAB","50TAB",130,0.0,130,130
        ));
        db.addproduct(new Product("32-X PILEEN 500TAB","500TAB",700,0.0,700,700));
        db.addproduct(new Product("033    PILS X 60ML","60ML",105,0.0,105,105));
        db.addproduct(new Product("034    PROSTENAL 15DOS","15DOS",120,0.0,120,120));
        db.addproduct(new Product("040-A TARYAQ NAZLA 100GRM","100GRM",80,0.0,80,80.00));
        db.addproduct(new Product("041    TARYAQ-E-BUKHAR 120ML","120ml",100,0.0,100,100));
        db.addproduct(new Product("046    THANDAK 60 GRM","60GRM",140,0.0,140,140));
        db.addproduct(new Product("047     TYPHEX 60 ML","60ML",65,0.0,65,65.00));
        db.addproduct(new Product("048    TON UP 120 ML","120ml",100,0.0,100,100.00));
        db.addproduct(new Product("052-A JAWARISH AMLA 100 GRM","100 GRM",70,0.0,70,70));
        db.addproduct(new Product("053-A JAWARISH NAREEN 100 GRM","100GRM",85,0.0,85.00,85.00));
        db.addproduct(new Product("053-X JARISH ANAREEN 500 GRM","500 GRM",300,0.0,300.00,300.00));
        db.addproduct(new Product("054-A JAWARISH TABKHIR 100 GRM","100gRm",100,0.0,100,100.00));
        db.addproduct(new Product("055    JAWARISH JALENOUS 50 GRM","50GRM",110,0.0,110,110.00));
        db.addproduct(new Product("055-A JAWARISH JALENOUS 100 GRM","100GRM",320,0.0,320,320));
        db.addproduct(new Product("056-A JAWARISH ZAROONI 100 GRM","100 GRM",130,0.0,130,130.00));
        db.addproduct(new Product("056-X JAWARISH ZAROONI 500 GRM","500GRM",380,0.0,380,380.00));
        db.addproduct(new Product("057    JAWARISH SHAHI 50 GRM","50GRM",55,0.0,55,55.00));
        db.addproduct(new Product("057-A JAWARISH SHAHI 100 GRM","100GRM",130,0.0,130,130.00));
        db.addproduct(new Product("057-X JAWARISH SHAHI 500 GRM","500GRM",380,0.0,380,380.00));
        db.addproduct(new Product("058 JAWARISH KAMONI 100 GRM","100grm",45,0.0,45,45.00));
        db.addproduct(new Product("058-A JAWARISH KAMONI 100 GRM","120GRM",120,0.0,120,120.00));
        db.addproduct(new Product("058-X JAWARISH KAMONI 500 GRM","500GRM",420,0.0,420,420));
        db.addproduct(new Product("059    JAWRISH MEDI 50 GRM","50 GRM",110,0.0,110,110.00));
        db.addproduct(new Product("059-A JAWARISH MEDI 100 GRM","100GRM",390,0.0,390,390.00));
        db.addproduct(new Product("060    JAWAHIR MOHRA KHASS 12 CAP","12CAP",180,0.0,180,180.00));
        db.addproduct(new Product("062    JARYANEEN 40 TAB","40 TAB",120,0.0,120,120));
        db.addproduct(new Product("062-X JARYANEEN 500 TAB","500TAB",900,0.0,900,900));
        db.addproduct(new Product("066    GEN XING 30 CAP","30 CAP",450,0.0,450,450));
        db.addproduct(new Product("069    CHOWARQA 240 ML","240ML",70,0.0,70,70));
        db.addproduct(new Product("069-A CHOWARQA 800 ML","800ML",140,0.0,140,140));
        db.addproduct(new Product("070-G CHAMAN RUSS GOLD 250 GRM","250GRM",425,0.0,425,425));
        db.addproduct(new Product("070-S CHAMAN RUSS SILVER 250 GRM","250GRM",350,0.0,350,350));
        db.addproduct(new Product("073    HUB-E-AZRAQI 25 TAB","25 TAB",70,0.0,70,70));
        db.addproduct(new Product("073-X HUB-E-AZRAQI 500 TAB","500TAB",650,0.0,650,650));
        db.addproduct(new Product("074    HUB-E-TINKAR 40 TAB","40 TAB",70,0.0,70,70));
        db.addproduct(new Product("074-X HUB-E-TINKAR 500 TAB","500 TAB",550,0.0,550,550));
        db.addproduct(new Product("076    HUB-E-AMBERMOMIA 20 TAB","20 TAB",300,0.0,300,300));

        db.addproduct(new Product("076-X HUB-E AMBERMOMIA 500 TAB","500 TAB",4800,0.0,4800,4800));
        db.addproduct(new Product("077    HUB-E-KABAD 100 TAB","100 TAB",70,0.0,70,70));
        db.addproduct(new Product("077-X HUB-E-KABAD 500 TAB","500 TAB",270,0.0,270,270));
        db.addproduct(new Product("078    HUB-E-GURDA 50 TAB","50 TAB",130,0.0,130,130));
        db.addproduct(new Product("078-X HUB-E-GURDA 500 TAB","500 TAB",870,0.0,870,870));
        db.addproduct(new Product("079    HABIS 60 GRM","60 GRM",140,0.0,140,140));
        db.addproduct(new Product("080    HAYATEEN 20 TAB","20 TAB",120,0.0,120,120));
        db.addproduct(new Product("080-X HAYATEEN 500 TAB","500 TAB",1900,0.0,1900,1900));
        db.addproduct(new Product("081    HUB-E-MUQAL KHAS 20 TAB","20 TAB",60,0.0,60,60));

        db.addproduct(new Product("081-X HUB-E-MUQAL KHAS 500 TAB","500TAB",750,0.0,750,750));
        db.addproduct(new Product("084    KHATOONI 120 ML","120ML",90,0.0,90,90));
        db.addproduct(new Product("085-A KHAMIRA ABRESHUM 100 GRM","100GRM",130,0.0,130,130));
        db.addproduct(new Product("085-X KHAMIRA ABRESHUM 500 GRM","500GRM",150,0.0,150,150));
        db.addproduct(new Product("086    KHAMIRA BRESHUM JAWAHIR 50","50GM",360,0.0,360,360));
        db.addproduct(new Product("086-A KAHMIRA ABRESHUM JAWAHIR 100 GRM","100GRM",800,0.0,800,800));
        db.addproduct(new Product("087-A KHAMIRA BANAFSHA 100 GRM","100GRM",130,0.0,130,130));
        db.addproduct(new Product("089    KHAMIRA GAOZUBAN 50 GRM","50GRM",55,0.0,55,55));
        db.addproduct(new Product("089-A KHAMIRA GAOZUBAN 100 GRM","100GRM",115,0.0,115,115));

        db.addproduct(new Product("089-X KHAMIRA GAOZUBAN 500 GRM","500GRM",420,0.0,420,420));
        db.addproduct(new Product("090   KHAMIRA GAOZUBAN AMBARI 50 GRM","50GRM",145,0.0,145,145));
        db.addproduct(new Product("090-A KHAMIRA GAOZUBAN AMBARI 100 GRM","100 GRM",300,0.0,300,300));
        db.addproduct(new Product("090-X KHAMIRA GAOZUBAN AMRARI 500 GRM","500 GRM",1000,0.0,1000,1000));
        db.addproduct(new Product("091    KHAMIRA GAOZUBAN JAWAHIR 50 GRM","50 GRM",240,0.0,240,240));
        db.addproduct(new Product("091-A KHAMIRA GAOZUBAN JAWAHIR 100 GRM","100 GRM",450,0.0,450,450));
        db.addproduct(new Product("092   KHAMIRA MARWARID 50 GRM","50 GRM",200,0.0,200,200));
        db.addproduct(new Product("092-A KHAMIRA MARWARID 100 GRM","100 GRM",390,0.0,390,390));
        db.addproduct(new Product("092-X KHAMIRA MARWARID 500 GRM","500 GRM",1450,0.0,1450,1450));

        db.addproduct(new Product("096    DAWAUL MISK SADA 50 GRM","50 GRM",150,0.0,150,150));
        db.addproduct(new Product("096-A DAWAUL MISK SADA 100 GRM","100 GRM",290,0.0,290,290));
        db.addproduct(new Product("096-X DAWAUL MISK SADA 500 GRM","500 GRM",850,0.0,850,850));
        db.addproduct(new Product("097    DAWAUL MISK JAWAHIR 50 GRM","50 GRM",300,0.0,300,300));
        db.addproduct(new Product("097-A DAWAUL MISK JAWAHIR 100 GRM","100 GRM",575,0.0,575,575));
        db.addproduct(new Product("098    DEMAGHI 100 GRM","100 GRM",150,0.0,150,150));
        db.addproduct(new Product("098-S DEMAGHI 250 GRM","250GRM",320,0.0,320,320));
        db.addproduct(new Product("098-X DEMAGHI 500 GRM","500 GRM",500,0.0,500,500));
        db.addproduct(new Product("099    DEDANI 15 GRM","15 GRM",60,0.0,60,60));

        db.addproduct(new Product("104    ZUBEX 50 TAB","50 TAB",150,0.0,150,150));
        db.addproduct(new Product("110    ROGHAN-E-UJA 60 ML","60 ML",100,0.0,100,100));
        db.addproduct(new Product("111    ROGHAN-E-BADAM 30 ML","30 ML",225,0.0,225,225));
        db.addproduct(new Product("111-A ROGHAN-E-BADAM 60 ML","60 ML",300,0.0,300,300));
        db.addproduct(new Product("111-D ROGHAN-E-BADAM 10 ML","10 ML",95,0.0,95,95));
        db.addproduct(new Product("114    ROGHAN-E-ARIND 60 ML","60 ML",90,0.0,90,90));
        db.addproduct(new Product("115    ROGHAN-E-LONG 8 ML","8 ML",60,0.0,60,60));
        db.addproduct(new Product("118    RENO 50 TAB","50 TAB",85,0.0,85,85));
        db.addproduct(new Product("118-X RENO 500 TAB","500 TAB",650,0.0,650,650));


        db.addproduct(new Product("120    RAHAT 40 TAB","40TAB",150,0.0,150,150));
        db.addproduct(new Product("120-X RAHAT 500 TAB","500 TAB",1200,0.0,1200,1200));
        db.addproduct(new Product("121    RENEX 25 GRM","25 GRM",90,0.0,90,90));
        db.addproduct(new Product("125    ZARJAM 10 CAP","10 CAP",300,0.0,300,300));
        db.addproduct(new Product("125-X ZARJAM 100 CAP","100 CAP",1800,0.0,1800,1800));
        db.addproduct(new Product("126    ZANJABEN 20CAP","20CAP",100,0.0,100,100));
        db.addproduct(new Product("130    SUPARI PAK 70 GRM","70 GRM",110,0.0,110,110));
        db.addproduct(new Product("131    SAFOOF TABKHIR 60 GRM","60 GRM",130,0.0,130,130));
        db.addproduct(new Product("133    SAFOOF JARYAN 60 GRM","60 GRM",140,0.0,140,140));

        db.addproduct(new Product("134    SAFOOF MUGHALIZ 60 GRM","60 GRM",180,0.0,180,180));
        db.addproduct(new Product("136-C SURANJEEN 30 CAP","30 CAP",150,0.0,150,150));
        db.addproduct(new Product("137    SADABAHAR 120 ML","120 ml",100,0.0,100,100));
        db.addproduct(new Product("138    SURFICOL 60 ML","60 ml",65,0.0,65,65));
        db.addproduct(new Product("138-A SURFICOL 120 ML","120 ml",90,0.0,90,90));
        db.addproduct(new Product("139    SURFICOL 250 TAB","250 TAB",215,0.0,215,215));
        db.addproduct(new Product("139-X SURFICOL 400 TAB","400 TAB",160,0.0,160,160));
        db.addproduct(new Product("140    SOZAK 25 DOS","25 DOS",110,0.0,110,110));
        db.addproduct(new Product("141    SHARBAT ANJABAR 240 ML","240 ML",140.0,0.0,140,140));

        db.addproduct(new Product("142    SHARBAT BAZOORI 240ML","240ml",130,0.0,130,130));
        db.addproduct(new Product("143    SHARBAT BANFSHA 240 ML","240ml",140,0.0,140,140));
        db.addproduct(new Product("144    SHARBAT UNAB 240 ML","240ml",140,0.0,140,140));
        db.addproduct(new Product("145    SHAFAI 25 TAB","25 TAB",110,0.0,110,110));
        db.addproduct(new Product("145-X SHAFAI 500 TAB","500 TAB",1700,0.0,1700,1700));
        db.addproduct(new Product("146    SHAHI CAP 10 CAP","10 CAP",180,0.0,180,180));
        db.addproduct(new Product("146-X SHAHI CAP 100 CAP","100 CAP",1300,0.0,1300,1300));
        db.addproduct(new Product("147    SHARBAT FULAD 240 ML","240 ML",130,0.0,130,130));
        db.addproduct(new Product("148    SHARBAT TOOT SIAH 60 ML","60 ML",65,0.0,65,65));

        db.addproduct(new Product("148-A SHARBAT TOOT SIAH 120 ML","120 ML",90,0.0,90,90));
        db.addproduct(new Product("148-S SHARBAT TOOT SIAH 240 ML","240 ML",140,0.0,140,140));
        db.addproduct(new Product("153    TILAMAQAVI SHAHI 8ML","8 ML",250,0.0,250,250));
        db.addproduct(new Product("154    TILAMASAKAN NO.1  8ML","8ML",180,0.0,180,180));
        db.addproduct(new Product("155    ARQ-E-BADIAN 240ML","240ML",70,0.0,70,70));
        db.addproduct(new Product("155-A ARQ-E-BADIAN 800ML","800ML",140,0.0,140,140));
        db.addproduct(new Product("156     ARQ-E-SHEREEN 240 ML","240ML",80,0.0,80,80));
        db.addproduct(new Product("157    ARQ-E-GAWZUBAN 240 ML","240 ML",70,0.0,70,70));
        db.addproduct(new Product("157-A ARQ-E-GAWZUBAN 800ML","800ML",140,0.0,140,140));

        db.addproduct(new Product("158    ARQ-E-MAKO  240 ML","240ML",70,0.0,70,70));
        db.addproduct(new Product("158-A ARQ-E-MAKOO 800 ML","800ML",140,0.0,140,140));
        db.addproduct(new Product("159    AMBARENA 120 ML","120 ML",140,0.0,140,140));
        db.addproduct(new Product("159-A AMBARENA 240 ML","240 ML",225,0.0,225,225));
        db.addproduct(new Product("160    ARQ-E-GULAB 60 ML","60 ML",35,0.0,35,35));
        db.addproduct(new Product("160-A ARQ-E-GULAB 120 ML","120ML",45,0.0,45,45));
        db.addproduct(new Product("160-D ARQ-E-GULAB 25 ML","25 ML",25,0.0,25,25));
        db.addproduct(new Product("160-X ARQ-E-GULAB 800 ML","800ML",150,0.0,150,150));
        db.addproduct(new Product("164    FULADI TONIC 120 ML","120 ML",100,0.0,100,100));

        db.addproduct(new Product("164-A FULADI TONIC 240 ML","240ML",150,0.0,150,150));
        db.addproduct(new Product("169    QARSHI GHUTTI 60 ML","60ML",70,0.0,70,70));
        db.addproduct(new Product("170    SHARBAT SADAR 120 ML","120ML",120,0.0,120,0.0));
        db.addproduct(new Product("171    QEROTI KHASS","0ML",85,0.0,85,85));
        db.addproduct(new Product("177    KUSHTA ABRAK SUFEED 10 GRM","10  GRM",80,0.0,80,80));
        db.addproduct(new Product("180    KUSHTA BEZA MURGH 10 GRM","10 GRM",80,0.0,80,80));
        db.addproduct(new Product("182    KUSHTA SADAF 10 GRM","10 GRM",80,0.0,80,80));
        db.addproduct(new Product("183    KUSHTA AQEEQ 10 GRM","10 GRM",80,0.0,80,80));
        db.addproduct(new Product("184     KUSHTA FULAD 10 GRM","10 GRM",120,0.0,120,120));
        db.addproduct(new Product("185    KUSHTA QALEE 10 GRM","10 GRM",180,0.0,180,180));
        db.addproduct(new Product("186    KUSHTA BEKH MARJAN 7 GRM","7 GRM",180,0.0,180,180));
        db.addproduct(new Product("187    KUSHTA MARGANG 10 GRM","10 GRM",180,0.0,180,180));
        db.addproduct(new Product("190    COREZCOL 60 ML","60 ML",70,0.0,70,70));
        db.addproduct(new Product("192    KARDIL 25 TAB","25 TAB",110,0.0,110,110));
        db.addproduct(new Product("192-X KARDIL 500 TAB","500 TAB",1200,0.0,1200,1200));
        db.addproduct(new Product("197    GOSHINA 8 ML","8 ML",65,0.0,65,65));
        db.addproduct(new Product("198    GESTOFILL 120 ML","120 ML",90,0.0,90,90));
        db.addproduct(new Product("199    GESTOFILL 100 TAB","100 TAB",60,0.0,60,60));
        db.addproduct(new Product("203    LAL SHARBAT 120 ML","120 ML",100,0.0,100,100));
        db.addproduct(new Product("203-A LAL SHARBAT 240 ML","240 ML",150,0.0,150,150));
        db.addproduct(new Product("204 LABOOB-E-BARD 50 GRM","0 ml",150,0.0,150,150));
        db.addproduct(new Product("204-A LABOB-E-BARD 100 GRM","0 ml",290,0.0,290,290));
        db.addproduct(new Product("204-X LABOB-E-BARD 500 GRM","0 ml",1140,0.0,1140,1140));
        db.addproduct(new Product("205 LABOB-E-KHASS JAWAHIR 50 GRM","0 ml",300,0.0,300,300));
        db.addproduct(new Product("205-A LABOB-E-KHASS JAWAHIR 100 GRM","0 ml",635,0.0,635,635));
        db.addproduct(new Product("205-X LABOB-E-KHASS 500 GRM","0 ml",2240,0.0,2240,2240));
        db.addproduct(new Product("206 LABOB-E-KABIR 50 GRM","0 ml",180,0.0,180,180));
        db.addproduct(new Product("206-A LABOB-E-KABIR 100 GRM","0 ml",350,0.0,350,350));
        db.addproduct(new Product("206-X LABOB-E-KABIR 500 GRM","0 ml",1300,0.0,1300,1300));
        db.addproduct(new Product("207 LAUQ-E-SAPISTAN 50 GRM","0 ml",55,0.0,55,55));
        db.addproduct(new Product("207-A LAUQ SAPISTAN 100 GRM","0 ml",100,0.0,100,100));
        db.addproduct(new Product("207-X LAUQ SAPISTAN 500 GRM","0 ml",340,0.0,340,340));
        db.addproduct(new Product("208 LECODEEN 50 TAB","0 ml",120,0.0,120,120));
        db.addproduct(new Product("208-X LECODEEN 500 TAB","0 ml",650,0.0,650,650));
        db.addproduct(new Product("214-A MAJOON-E-ARDKHURMA 100 GRM","0 ml",120,0.0,120,120));
        db.addproduct(new Product("214-X MAJOON-E-ARDKHURMA 500 GRM","0 ml",340,0.0,340,340));
        db.addproduct(new Product("215-A MAJOON-E-AZRAKI 100 GRM","0 ml",105,0.0,105,105));
        db.addproduct(new Product("215-X MAJOON-E-AZRAKI 500 GRM","0 ml",340,0.0,340,340));
        db.addproduct(new Product("216-A MAJOON-E-DABEDULWARD 100 GRM","0 ml",180,0.0,180,180));
        db.addproduct(new Product("216-X MAJOON-E-DAVEDULWARD 500 GRM","0 ml",500,0.0,500,500));
        db.addproduct(new Product("217-A MAJOON-E-SURANJAN 100 GRM","0 ml",130,0.0,130,130));
        db.addproduct(new Product("217-X MAJOON-E-SURANJAN 500 GRM","0 ml",420,0.0,420,420));
        db.addproduct(new Product("218-A MAJOON-E-SANGDANA MURGH 100 GRM","0 ml",105,0.0,105,105));
        db.addproduct(new Product("219 MAJOON-E-SANGDANA MURGH 25 GRM","0 ml",35,0.0,35,35));
        db.addproduct(new Product("219-A MAJOON-E-FALASFA 100 GRM","0 ml",140,0.0,140,140));
        db.addproduct(new Product("219-X MAJOON-E-FALASFA 500 GRM","0 ml",480,0.0,480,480));
        db.addproduct(new Product("220-A MAJOON-E-MUGHALIZ JAWAHIR 100 GRM","0 ml",325,0.0,325,325));
        db.addproduct(new Product("220-X MAJOON-E-MUGHALIZ 500 GRM","0 ml",1200,0.0,1200,1200));
        db.addproduct(new Product("221 MAJOON-E-NIJAH 100 GRM","0 ml",120,0.0,120,120));
        db.addproduct(new Product("222-A MAJOON-E-KHUBSUL HADID 100 GRM","0 ml",100,0.0,100,100));
        db.addproduct(new Product("223 MUSAFEEN 120 ML","0 ml",80,0.0,80,80));
        db.addproduct(new Product("224 MASIK TILAI 5*5 CAP","0 ml",1500,0.0,1500,1500));
        db.addproduct(new Product("225 MAMSIK SULTANI 6 CAP","0 ml",150,0.0,150,150));
        db.addproduct(new Product("225-X MAMSIK SULTANI 100 CAP","0 ml",1700,0.0,1700,1700));
        db.addproduct(new Product("226 MUSAFEEN 40 TAB","0 ml",100,0.0,100,100));
        db.addproduct(new Product("226-X MUSAFEEN 500 TAB","0 ml",720,0.0,720,720));
        db.addproduct(new Product("227 MENSOOFAR 20 TAB","0 ml",110,0.0,110,110));
        db.addproduct(new Product("227-X MENSOOFAR 500 TAB","0 ml",1450,0.0,1450,1450));
        db.addproduct(new Product("249 UROSINAL 120 ML","0 ml",100,0.0,100,100));
        db.addproduct(new Product("250-A ABB-E-NUQRA 120 ML","0 ml",95,0.0,95,95));
        db.addproduct(new Product("4012 PROTONE 10 CAP","0 ml",250,0.0,250,250));
        db.addproduct(new Product("4010 GENSOVITE 30 CAP","0 ml",330,0.0,330,330));
        db.addproduct(new Product("4011 ZANCID 20 CAP","0 ml",100,0.0,100,100));
        db.addproduct(new Product("4001 GESTOPAN120 ML","0 ml",85,0.0,85,85));
        db.addproduct(new Product("4002 GESTONE 100 TAB","0 ml",85,0.0,85,85));
        db.addproduct(new Product("4013 GARLICAR 30 TAB","0 ml",270,0.0,270,270));
        db.addproduct(new Product("4006 VITAVIG 10 CAP","0 ml",150,0.0,150,150));
        db.addproduct(new Product("4014 HAPTACARE KIT","0 ml",3350,0.0,3350,3350));
        db.addproduct(new Product("233 NAUBAHAR 120 ML","0 ml",40,0.0,40,40));
        db.addproduct(new Product("233-A NAUBAHAR 240 ML","0 ml",60,0.0,60,60));
        db.addproduct(new Product("18-C ISPAGHOL HUSK 4 GRM","0 ml",176,0.0,176,176));
        db.addproduct(new Product("18-B ISPAGHOL HUSK 95 GRM","0 ml",260,0.0,260,260));
        db.addproduct(new Product("016-A ATRIFAL KASHNIZI 100GM","0 ml",100,0.0,100,100));
        db.addproduct(new Product("032-X PILEEN 500","0 ml",850,0.0,850,850));
        db.addproduct(new Product("161 KASHNI 240ML","0 ml",60,0.0,60,60));
        db.addproduct(new Product("018-C ISPAGHOL HUSK 4GM","0 ml",220,0.0,220,220));
        db.addproduct(new Product("711 SUDURI","0 ml",50,0.0,50,50));
        db.addproduct(new Product("018-B ISPAGHOL HUSK 95GM","0 ml",260,0.0,260,260));
        db.addproduct(new Product("700 JOSHANDA","0 ml",170,0.0,170,170));
        db.addproduct(new Product("712 NUNAHAL","0 ml",50,0.0,50,50));
        db.addproduct(new Product("721 ARQ GULAB 50ML","0 ml",21,0.0,21,21));
        db.addproduct(new Product("722 ARQ GULAB 120ML","0 ml",30,0.0,30,30));
        db.addproduct(new Product("731 ALMOND OIL 10ML","0 ml",34,0.0,34,34));
        db.addproduct(new Product("732 ALMOND OIL 25ML","0 ml",81,0.0,81,81));
        db.addproduct(new Product("733 ALMOND OIL 50ML","0 ml",153,0.0,153,153));
        db.addproduct(new Product("741 HUSK 4GM","0 ml",185,0.0,185,185));
        db.addproduct(new Product("4010 GINSOVIT","0 ml",270,0.0,270,270));
        db.addproduct(new Product("713 SAFI","0 ml",60,0.0,60,60));
        db.addproduct(new Product("752 HONEY 80GM","0 ml",58,0.0,58,58));
        db.addproduct(new Product("761 ZATOOT OIL 50ML","0 ml",49,0.0,49,49));
        db.addproduct(new Product("723 ARQ GULAB SAPRAY","0 ml",60,0.0,60,60));
        db.addproduct(new Product("751 HONEY TUBE","0 ml",40,0.0,40,40));
        db.addproduct(new Product("753 HONEY 300GM","0 ml",216,0.0,216,216));
        db.addproduct(new Product("742 ISPAGHOL 150GM","0 ml",246,0.0,246,246));
        db.addproduct(new Product("743 ISPAGHOL 25GM","0 ml",33,0.0,33,33));
        db.addproduct(new Product("744 ISPAGHOL 95GM","0 ml",162,0.0,162,162));
        db.addproduct(new Product("710 GHUTI","0 ml",25,0.0,25,25));
        db.addproduct(new Product("724 ARQGULAB DRP 50ML","0 ml",17,0.0,17,17));
        db.addproduct(new Product("161-A KASHNI 800ML","0 ml",120,0.0,120,120));
        db.addproduct(new Product("744 ISPAGHOL 50GM","0 ml",60,0.0,60,60));
        db.addproduct(new Product("771 CASTROL 25ML","0 ml",26,0.0,26,26));
        db.addproduct(new Product("100 QARSHI JOSHANDA","0 ml",180,0.0,180,180));
        db.addproduct(new Product("714 MASTURIN","0 ml",45,0.0,45,45));
        db.addproduct(new Product("772 CASTROL 50ML","0 ml",38,0.0,38,38));
        db.addproduct(new Product("773 CASTROL 100ML","0 ml",53,0.0,53,53));
        db.addproduct(new Product("734 ALMOND OIL 100ML","0 ml",196,0.0,196,196));
        db.addproduct(new Product("10 PAPER MINT 120ML","0 ml",60,0.0,60,60));
        db.addproduct(new Product("30 DAMGUN 500GM","0 ml",450,0.0,450,450));
        db.addproduct(new Product("58 ANAR 240ML","0 ml",85,0.0,85,85));
        db.addproduct(new Product("98 ROGHAN-E- KAADU","0 ml",95,0.0,95,95));
        db.addproduct(new Product("754 HONEY 400GM","0 ml",248,0.0,248,248));
        db.addproduct(new Product("755 HONEY 235 GM","0 ml",166,0.0,166,166));
        db.addproduct(new Product("725 ARQ-E-GULAB 800ML","0 ml",102,0.0,102,102));
        db.addproduct(new Product("715 SAPARI PAK","0 ml",110,0.0,110,110));
        db.addproduct(new Product("716 VIGURINE 60ML","0 ml",45,0.0,45,45));
        db.addproduct(new Product("781 KALWANJI OIL 25ML","0 ml",64,0.0,64,64));
        db.addproduct(new Product("756 HONEY 1000GM","0 ml",585,0.0,585,585));
        db.addproduct(new Product("SANDAL 1500ML","0 ml",285,0.0,285,285));
        db.addproduct(new Product("BAZURI 1500","0 ml",285,0.0,285,285));
        db.addproduct(new Product("SANDAL 800ML","0 ml",160,0.0,160,160));
        db.addproduct(new Product("BAZURI 800ML","0 ml",160,0.0,160,160));
        db.addproduct(new Product("IMLI ALU BHUKRA 800","0 ml",0,0.0,0,0));
        db.addproduct(new Product("ELLACHI 800ML","0 ml",160,0.0,160,160));
        db.addproduct(new Product("GULBAHAR 800ML","0 ml",160,0.0,160,160));
        db.addproduct(new Product("GULBAHAR 1500ML","0 ml",260,0.0,260,260));
        db.addproduct(new Product("SADURI SYP (S/F)","0 ml",50,0.0,50,50));
        db.addproduct(new Product("KARMINA TAB","0 ml",0,0.0,0,0));
        db.addproduct(new Product("DIL ROZ","0 ml",90,0.0,90,90));
        db.addproduct(new Product("717 VIGURINE 120ml","0 ml",85,0.0,85,85));
        db.addproduct(new Product("LATEEF SURMA Sukh Chean","0 ml",120,0.0,120,120));
        db.addproduct(new Product("COLIC DROPS (Brick)","0 ml",60,0.0,60,60));
        db.addproduct(new Product("DIGAS DROP","0 ml",68,0.0,68,68));
        db.addproduct(new Product("HARALINE","0 ml",70,0.0,70,70));
        db.addproduct(new Product("TOOT MALATHI","0 ml",90,0.0,90,90));
        db.addproduct(new Product("PASPAN 120ml","0 ml",90,0.0,90,90));
        db.addproduct(new Product("FAKIRI S","0 ml",70,0.0,70,70));
        db.addproduct(new Product("SYP COLIC","0 ml",90,0.0,90,90));
        db.addproduct(new Product("ALKI","0 ml",150,0.0,150,150));
        db.addproduct(new Product("AKSEER HAZMON","0 ml",70,0.0,70,70));
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.opening_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Setting) {

            this.finish();
            Intent i=new Intent(MainActivity.this,SettingsActivity.class);
            this.startActivity(i);
            Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        else if(id == R.id.area){
            this.finish();
            Intent i=new Intent(MainActivity.this,Area_activity.class);
            this.startActivity(i);
            Toast.makeText(MainActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    public void my_order(View view){

        Intent i=new Intent(MainActivity.this,My_Orders_Activity.class);
        this.startActivity(i);

    }
    public void new_orders(View  view){
        Intent i=new Intent(MainActivity.this,New_Orders_Activity.class);
        this.startActivity(i);
    }
    public void products(View view){
        Intent i=new Intent(MainActivity.this,Products_Activity.class);
        this.startActivity(i);
    }
    public void shops(View view){
        Intent i=new Intent(MainActivity.this,Shops_Activity.class);
        this.startActivity(i);
    }
}
