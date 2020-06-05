package com.example.myownbusiness;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static com.example.myownbusiness.R.drawable.circularborderbuttonshape;
import static com.example.myownbusiness.R.drawable.clickedcircularborderbuttonshape;

public class SearchBusiness extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter myAdapter;
    //Constant for getting  array of all business.
    BufferedInputStream is;
    int cAlist;
    dealClass business,DealSelected;
    String line=null;
    String result=null;
    int nrank;
    ImageButton ic_search;
    EditText searchEl;
    Button sByPrice, sByName, sByLocate;
    ArrayList<dealClass> alist= null;
    ArrayList<dealClass> alistsearch= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search_business);
        //instance all xml objects.
        alist = new ArrayList<>();
        alistsearch = new ArrayList<>();
        instanceXML();
        //Get Intent
        Intent i = getIntent();
        alist = (ArrayList<dealClass>) getIntent().getSerializableExtra("alistbusiness");
        //Instance RecyclerView.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this,getMyList());
        mRecyclerView.setAdapter(myAdapter);
        cAlist = alist.size();
        mRecyclerView.addOnItemTouchListener(
                //Click listener for every item on the list taking position.
                new RecyclerItemClickListener(this, mRecyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //Create object deal class to pass it between activities.
                        DealSelected = new dealClass(alist.get(position).codigo_servicio,alist.get(position).codigo_usuario,
                                alist.get(position).nombre,alist.get(position).categoria1,alist.get(position).categoria2,alist.get(position).categoria3
                        ,alist.get(position).localizacion,alist.get(position).precio,alist.get(position).descripcion,alist.get(position).rango);
                        nrank = position;
                        goMyBusiness();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        Toast.makeText(SearchBusiness.this, alist.get(position).localizacion.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        );

        ic_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchElement();
            }
        });

        sByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sByName.setBackgroundResource(clickedcircularborderbuttonshape);
                sByLocate.setBackgroundResource(circularborderbuttonshape);
                sByPrice.setBackgroundResource(circularborderbuttonshape);
            }
        });

        sByPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sByName.setBackgroundResource(circularborderbuttonshape);
                sByLocate.setBackgroundResource(circularborderbuttonshape);
                sByPrice.setBackgroundResource(clickedcircularborderbuttonshape);
            }
        });

        sByLocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sByName.setBackgroundResource(circularborderbuttonshape);
                sByLocate.setBackgroundResource(clickedcircularborderbuttonshape);
                sByPrice.setBackgroundResource(circularborderbuttonshape);
            }
        });
    }

    //FUNCTIONS ----------------------------------------------------------------------------------->

    private void instanceXML() {
        mRecyclerView = findViewById(R.id.Recycler);
        ic_search = findViewById(R.id.ic_search);
        searchEl = findViewById(R.id.search);
        sByPrice = findViewById(R.id.searchbyprice);
        sByName = findViewById(R.id.searchname);
        sByLocate = findViewById(R.id.searchbylocation);
    }

    //Function searching elements into arraylist.
    private void searchElement() {
        String Searching = searchEl.getText().toString();
        int i = 0;
        while (i != cAlist) {
            //if name of Businees is equal to element searched then include it into new arraylist.
            if (alist.get(i).getNombre().toString().equals(Searching)) {
                alistsearch.add(alist.get(i));
            }
            alist.add(alist.get(i));
            i++;
        }
        //now Create model to create the new list.
        myAdapter = new MyAdapter(this,getMyListSearched());
        mRecyclerView.setAdapter(myAdapter);

    }


    //method for creating all  list elements  from ArrayList.size();
    private ArrayList<Model> getMyList() {
        ArrayList<Model> models = new ArrayList<>();
        cAlist = 0;
        while (cAlist != alist.size()) {
            //Create new object dealclass
            business = new dealClass();
            business = alist.get(cAlist);
            Model m = new Model();
            m.setTitle(business.getNombre().toString());
            m.setDesc(business.getDescripcion().toString());
            switch (cAlist) {
                case 0: m.setImg(R.mipmap.rangouno);
                    break;
                case 1: m.setImg(R.mipmap.rangodos);
                    break;
                case 2: m.setImg(R.mipmap.rangotres);
                    break;
                default:m.setImg(R.mipmap.sinrango);
                    break;
            }

            models.add(m);
            cAlist++;
        }
        return models;
    }

    private ArrayList<Model> getMyListSearched() {
        ArrayList<Model> models = new ArrayList<>();
        cAlist = 0;
        while (cAlist != alistsearch.size()) {
            //Create new object dealclass
            business = new dealClass();
            business = alistsearch.get(cAlist);
            Model m = new Model();
            m.setTitle(business.getNombre().toString());
            m.setDesc(business.getDescripcion().toString());
            m.setImg(R.mipmap.rankone_round);
            models.add(m);
            cAlist++;
        }
        return models;
    }



    //Function that creates the option Menu via inflate function.
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popup.inflate(R.menu.desplegable_menu);
        popup.show();
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

    public void goMyBusiness() {

        Intent intent = new Intent(SearchBusiness.this,MyBusiness.class);
        intent.putExtra("business",DealSelected);
        intent.putExtra("rank",nrank);
        startActivity(intent);
    }
}
