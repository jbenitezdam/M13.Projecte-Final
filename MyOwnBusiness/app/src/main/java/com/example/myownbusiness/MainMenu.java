package com.example.myownbusiness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MainMenu extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    CircleMenu circleMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //Instance xml on function.
        instanceXML();
        //Creating circular menu via function --/External Library.
        createMenu();

    }

    //FUNCTIONS ----------------------------------------------------------------------------------->

    public void createMenu() {
        final String[] Menu = {"Ranking","My Business","Search"};

        circleMenu.setMainMenu(Color.parseColor("#0993A5"), R.drawable.ic_add,R.drawable.ic_clear).
                addSubMenu(Color.parseColor("#1F9C3E"), R.drawable.ic_rank).
                addSubMenu(Color.parseColor("#1F5BA5"), R.drawable.ic_business).
                addSubMenu(Color.parseColor("#6127B5"), R.drawable.ic_search)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        //Main menu case clicking.
                        Toast.makeText(MainMenu.this, "You Click " + Menu[i], Toast.LENGTH_SHORT).show();
                        switch(i) {
                            case 0:
                                goRanking();
                                break;
                            case 1:
                                goCreateMyBusiness();
                                break;
                            case 2:
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
        startActivity(intent);
    }

    public void goCreateMyBusiness() {
        Intent intent = new Intent(MainMenu.this,CreateBusiness.class);
        startActivity(intent);
    }
}
