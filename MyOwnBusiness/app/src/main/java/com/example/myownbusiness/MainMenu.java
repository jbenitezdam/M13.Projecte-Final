package com.example.myownbusiness;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    //Contants.
    CircleMenu circleMenu;
    Usuarios_BBDD objectUser;
    threadBusiness chargeBusiness;
    dealClass business = null;
    ArrayList<dealClass> alist= null;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        getSupportActionBar().hide();
        Intent i = getIntent();
        handler = new Handler();
        //Inicialice arraylist
        alist = new ArrayList<>();
        //Get from the past activity the object with all data about the user.
        objectUser = (Usuarios_BBDD) i.getSerializableExtra("user");
        //Instance xml on function.
        instanceXML();
        //Creating circular menu via function --/External Library.
        createMenu();
        //Charge all business that BBDD contains.
        chargeBusiness = new threadBusiness(alist);
        //Inicialize database business charge.
        chargeBusiness.start();
    }

    //FUNCTIONS ----------------------------------------------------------------------------------->

    public void createMenu() {
        final String[] Menu = {"Ranking","My Business","Search"};
        //Circle menu via new activity.
        circleMenu.setMainMenu(Color.parseColor("#0993A5"), R.drawable.ic_add,R.drawable.ic_clear).
                addSubMenu(Color.parseColor("#1F9C3E"), R.drawable.ic_rank).
                addSubMenu(Color.parseColor("#1F5BA5"), R.drawable.ic_business).
                addSubMenu(Color.parseColor("#6127B5"), R.drawable.ic_search)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        //Main menu case clicking.
                        switch(i) {
                            case 0:
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Do something after 2s = 2000m
                                    }
                                }, 1000);
                                Toast.makeText(MainMenu.this, "Will come soon...", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Do something after 1s = 1000m
                                    }
                                }, 1000);
                                goCreateMyBusiness();
                                break;
                            case 2:
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Do something after 1s = 1000m
                                    }
                                }, 1000);
                                goSearchBusiness();
                                break;
                            default:
                        }
                    }
                });
    }
    public void instanceXML() {
        circleMenu = findViewById(R.id.circlemenu);
    }


    //Function that creates the option Menu via inflate function.
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.desplegable_menu);
        popup.show();
    }

    //This function set a method into every button of the popup.
    @Override
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
        Intent intent = new Intent(MainMenu.this,Profile.class);
        intent.putExtra("user",objectUser);
        startActivity(intent);

    }

    public void goMainMenu(){
        Intent intent = new Intent(MainMenu.this,MainMenu.class);
        startActivity(intent);
    }

    public void Leave() {
        Intent intent = new Intent(MainMenu.this,MainActivity.class);
        startActivity(intent);
    }

    public void goRanking(){
        Intent intent = new Intent(MainMenu.this,Rankings.class);
        startActivity(intent);
    }

    public void goSearchBusiness() {
        Intent intent = new Intent(MainMenu.this,SearchBusiness.class);
        intent.putExtra("alistbusiness",alist);
        startActivity(intent);
    }

    public void goCreateMyBusiness() {
        Intent intent = new Intent(MainMenu.this,CreateBusiness.class);
        startActivity(intent);
    }
}
