package com.example.myownbusiness;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class threadUsers extends Thread{

    BufferedInputStream is;
    String line=null;
    String result=null;
    String[] json_userCode,json_Birthdate,json_userName,json_userPassword,json_emailAddress;

    public void run(String[] array1,String[] array2,String[] array3,String[] array4,String[] array5) {
            try {
                URL url = new URL("http://192.168.1.37:8080/myownbusinessphp/select_usuarios.php");
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

                JSONArray ja = new JSONArray(result);
                JSONObject jo = null;
                array1 = new String[ja.length()];
                array2 = new String[ja.length()];
                array3= new String[ja.length()];
                array4= new String[ja.length()];
                array5= new String[ja.length()];

                for(int i = 0; i <= ja.length()-1; i++) {
                    jo=ja.getJSONObject(i);
                    array1[i] = jo.getString("codigo_usuario");
                    array2[i] = jo.getString("nombre_cuenta");
                    array3[i] = jo.getString("contraseña_cuenta");
                    array4[i] = jo.getString("correo_elec");
                    array5[i] = jo.getString("fecha_naci");
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
}
