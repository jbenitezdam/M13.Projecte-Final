package com.example.myownbusiness;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SearchBusiness extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    //Constant for getting  array of all business.
    String[] json_codigo_servicio,json_codigo_usuario,json_categoria1,json_categoria2,json_categoria3,json_precio,json_descripcion,json_rango;
    BufferedInputStream is;
    String line=null;
    String result=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search_business);
        instanceXML();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this,getMyList());
        mRecyclerView.setAdapter(myAdapter);
    }

    //FUNCTIONS ----------------------------------------------------------------------------------->

    public void instanceXML() {
        mRecyclerView = findViewById(R.id.Recycler);
    }

    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("First Rank");
        m.setDesc("This Simbol represents top 1 Business.");
        m.setImg(R.mipmap.rankone_round);
        models.add(m);

        Model mi = new Model();
        mi.setTitle("Second Rank");
        mi.setDesc("This Simbol represents top 2 Business.");
        mi.setImg(R.mipmap.rankone_round);
        models.add(mi);

        Model me = new Model();
        me.setTitle("Third");
        me.setDesc("This Simbol represents top 3 Business.");
        me.setImg(R.mipmap.rankone_round);
        models.add(me);

        Model ma = new Model();
        ma.setTitle("Four");
        ma.setDesc("Not on top but give a chance.");
        ma.setImg(R.mipmap.rankone_round);
        models.add(ma);

        return models;
    }

    //Function that creates the option Menu via inflate function.
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popup.inflate(R.menu.desplegable_menu);
        popup.show();
    }

    public void getusers() {
        try {
            //Getting url  from string xml
            URL url = new URL(getString(R.string.URL_GET_SERVICIOS));
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
            //Inicialize array with JsonArray length.
            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            json_codigo_servicio = new String[ja.length()];
            json_codigo_usuario = new String[ja.length()];
            json_categoria1= new String[ja.length()];
            json_categoria2= new String[ja.length()];
            json_categoria3= new String[ja.length()];
            json_precio= new String[ja.length()];
            json_descripcion= new String[ja.length()];
            json_rango= new String[ja.length()];

            //Insert into every array position the json data.
            for(int i = 0; i <= ja.length(); i++) {
                jo=ja.getJSONObject(i);
                json_codigo_servicio[i] = jo.getString("codigo_servicio");
                json_codigo_usuario[i] = jo.getString("codigo_usuario");
                json_categoria1[i] = jo.getString("categoria1");
                json_categoria2[i] = jo.getString("categoria2");
                json_categoria3[i] = jo.getString("categoria3");
                json_precio[i] = jo.getString("precio");
                json_descripcion[i] = jo.getString("descripcion");
                json_rango[i] = jo.getString("rango");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    //This function set a method into every button of the popup.
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Profile:
                goProfile();
                return true;
            case R.id.Menu:
                goMainMenu();
                return true;
            case R.id.Exit:
                Leave();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //Functions with intents.
    public void goProfile(){
        Intent intent = new Intent(SearchBusiness.this,Profile.class);
        startActivity(intent);

    }

    public void goMainMenu(){
        Intent intent = new Intent(SearchBusiness.this,MainMenu.class);
        startActivity(intent);
    }

    public void Leave() {
        Intent intent = new Intent(SearchBusiness.this,MainActivity.class);
        startActivity(intent);
    }
}
