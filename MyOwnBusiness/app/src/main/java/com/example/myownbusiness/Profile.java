package com.example.myownbusiness;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    Usuarios_BBDD objectUser;
    TextView email,number,direction,name;
    ImageView profilePicture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        //Getting intent from MainMenu.
        Intent i = getIntent();
        //method for instance all xml object.
        instanceXML();
        objectUser = (Usuarios_BBDD) i.getSerializableExtra("user");
        //After getting object user, insert all data into his respective xml object.
        setTexts();
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
        intent.putExtra("user",objectUser);
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

    public void instanceXML() {
        email = findViewById(R.id.txtemail);
        number = findViewById(R.id.txtnumber);
        direction = findViewById(R.id.txtdirection);
        name = findViewById(R.id.txtname);
        profilePicture = findViewById(R.id.profileImage);
    }

    public void setTexts() {
        name.setText(objectUser.getAccName());
        email.setText(objectUser.getEmailAddress());
        number.setText(objectUser.getPhoneNumber());
        direction.setText(objectUser.getDirection());
    }
}
