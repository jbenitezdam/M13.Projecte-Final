package com.example.myownbusiness;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  implements PopupMenu.OnMenuItemClickListener {
    BufferedInputStream is;
    String line=null;
    String result=null;
    //--------------------------------------------------------------------------------------------->
    Button Conn, newUser;
    EditText txtUser,txtPassword;
    TextView txtFgtPasswd,txtError;
    ImageView Img;
    RequestQueue RQ;
    String[] json_userCode,json_Birthdate,json_userName,json_userPassword,json_emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler();
        getSupportActionBar().hide();
        RQ = Volley.newRequestQueue(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //Conn with database users.
        //Log In activity, getting user from Mysql.
        //Function to instance all xml.
        InstanceXML();
        //Error text stays invisible since error.
        txtError.setVisibility(View.INVISIBLE);
        //Get all users from BBDD.
        getusers();
        Conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Compare the user and password to any user that has the same ID_user, via select.
                int json_count = 0;
                boolean  conn = true;
                try {
                    //If searched user is equ8al to writen, then change activity.
                    while (json_count < json_userName.length && conn) {

                        if (txtUser.getText().toString().equals(json_userName[json_count]) && txtPassword.getText().toString().equals(json_userPassword[json_count])) {
                            //If user and password equals array position then enter.

                            conn = false;
                            GoMenu();
                            Toast.makeText(MainActivity.this, "Connecting...", Toast.LENGTH_SHORT).show();
                        }
                        json_count++;
                    }

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 2s = 2000ms
                            txtError.setVisibility(View.VISIBLE);
                        }
                    }, 2000);
                    //Put invisible again.
                    txtError.setVisibility(View.INVISIBLE);
                }catch (NullPointerException e) {
                    e.printStackTrace();
                }
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

    public void getusers() {
        try {
            //getting URL from string xml.
            URL url = new URL(getString(R.string.URL_GET_USUARIOS));
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
            json_userCode = new String[ja.length()];
            json_Birthdate = new String[ja.length()];
            json_userName= new String[ja.length()];
            json_userPassword= new String[ja.length()];
            json_emailAddress= new String[ja.length()];

            //Insert data into every array position.
            for(int i = 0; i <= ja.length(); i++) {
                jo=ja.getJSONObject(i);
                json_userCode[i] = jo.getString("codigo_usuario");
                json_userName[i] = jo.getString("nombre_cuenta");
                json_userPassword[i] = jo.getString("contraseña_cuenta");
                json_emailAddress[i] = jo.getString("correo_elec");
                json_Birthdate[i] = jo.getString("fecha_naci");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void InstanceXML()  {
        Conn = findViewById(R.id.connect);
        newUser = findViewById(R.id.btnnewuser);
        txtFgtPasswd = findViewById(R.id.forgotyourpasswd);
        txtError = findViewById(R.id.Error);
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

    public void splitArrayList(ArrayList<String> list) {

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
