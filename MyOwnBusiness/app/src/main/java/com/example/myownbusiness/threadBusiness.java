package com.example.myownbusiness;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
//This thread download all bussiness from a database and inserts into arraylist of objects of dealClass.
public class threadBusiness extends Thread{
    //Contants
    private BufferedInputStream is;
    private String line=null;
    private String result=null;
    String URLser;
    String[] json_codigo_servicio, json_codigo_usuario, json_nombre,
            json_categoria1, json_categoria2, json_categoria3,
            json_localizacion,json_precio,json_descripcion,json_rango;
    dealClass business;
    ArrayList<dealClass> alist= null;

    //Contuctor with al vars.
    public threadBusiness(ArrayList<dealClass> alist) {
        this.alist = alist;

    }

    public void run() {
       URLser = "http://192.168.1.36:8080/myownbusinessphp/select_service.php";
       getbusiness();

       for (int i = 0; i < json_codigo_servicio.length; i ++ ) {
           //Generate new Object dealClass and insert into arrayList into MainMenu.
           business = new dealClass(json_codigo_servicio[i],json_codigo_usuario[i],json_nombre[i],json_categoria1[i],
                   json_categoria2[i],json_categoria3[i],json_localizacion[i],json_precio[i],json_descripcion[i],
                   json_rango[i]);
           alist.add(business);
       }
    }

    //Get all business from BBDD.
    public void getbusiness() {
        try {
            //getting URL from string xml.
            URL url = new URL(URLser);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            is = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while ((line=br.readLine())!=null) {
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();
        }catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //Inicialize array with json length.
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            json_codigo_servicio = new String[ja.length()];
            json_codigo_usuario = new String[ja.length()];
            json_nombre= new String[ja.length()];
            json_categoria1= new String[ja.length()];
            json_categoria2= new String[ja.length()];
            json_categoria3= new String[ja.length()];
            json_localizacion= new String[ja.length()];
            json_precio= new String[ja.length()];
            json_descripcion= new String[ja.length()];
            json_rango= new String[ja.length()];

            //Insert data into every array position.
            for(int i = 0; i <= ja.length()-1; i++) {
                jo=ja.getJSONObject(i);
                json_codigo_servicio[i] = jo.getString("codigo_servicio");
                json_codigo_usuario[i] = jo.getString("codigo_usuario");
                json_nombre[i] = jo.getString("nombre");
                json_categoria1[i] = jo.getString("categoria1");
                json_categoria2[i] = jo.getString("categoria2");
                json_categoria3[i] = jo.getString("categoria3");
                json_localizacion[i] = jo.getString("localizacion");
                json_precio[i] = jo.getString("precio");
                json_descripcion[i] = jo.getString("descripcion");
                json_rango[i] = jo.getString("rango");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
