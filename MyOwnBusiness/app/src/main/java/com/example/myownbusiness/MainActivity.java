package com.example.myownbusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {

    Button Conn, newUser;
    EditText txtUser,txtPassword;
    TextView txtFgtPasswd;
    ImageView Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Log In activity, getting user from Mysql.

        //Function to instance all xml.
        InstanceXML();

        //Get all users from BBDD.
        Conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Compare user and passwd from all users of BBDD.
                GoMenu();
                Toast.makeText(MainActivity.this, "Connecting...", Toast.LENGTH_SHORT).show();
            }
        });

        //OnClickListener For Create new User.
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewUser();
            }
        });

        //Button for recovering Passwd if missed.
        //Touch Listener because of EditText.
        txtFgtPasswd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "It Works!", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    //FUNCTIONS ----------------------------------------------------------------------------------->


    private void InstanceXML()  {
        Conn = findViewById(R.id.connect);
        newUser = findViewById(R.id.btnnewuser);
        txtFgtPasswd = findViewById(R.id.forgotyourpasswd);
        txtUser = findViewById(R.id.user);
        txtPassword = findViewById(R.id.passwd);
        Img = findViewById(R.id.img);

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
        Intent intent = new Intent(MainActivity.this,Profile.class);
        startActivity(intent);

    }

    public void goMainMenu(){
        Intent intent = new Intent(MainActivity.this,MainMenu.class);
        startActivity(intent);
    }

    public void Leave() {
        Intent intent = new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
    }

    private void GoMenu() {
        Intent intent = new Intent(MainActivity.this,MainMenu.class);
        startActivity(intent);

    }
    private void NewUser() {
        Intent intent = new Intent(MainActivity.this,NewUser.class);
        startActivity(intent);

    }
}
