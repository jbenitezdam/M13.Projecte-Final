package com.example.myownbusiness;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
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
        Intent intent = new Intent(Profile.this,Profile.class);
        startActivity(intent);

    }

    public void goMainMenu(){
        Intent intent = new Intent(Profile.this,MainMenu.class);
        startActivity(intent);
    }

    public void Leave() {
        Intent intent = new Intent(Profile.this,MainActivity.class);
        startActivity(intent);
    }
}
