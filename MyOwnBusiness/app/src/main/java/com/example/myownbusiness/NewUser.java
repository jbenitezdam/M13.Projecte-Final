package com.example.myownbusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class NewUser extends AppCompatActivity {
    String URL,st_Name,st_Password,st_emailAdddres,st_Birthdate,st_AccName, st_codigo_usuario,st_direction,st_phonenumber;
    Button create;
    EditText name,password,emailaddress,accname,birthdate,direction,phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_user);
        //instance all xml objects.
        URL = getString(R.string.URL_INSERT_USUARIOS);
        instanceXML();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //Save all data into new var to set petition.
                    st_Name = name.getText().toString();
                    st_Password = password.getText().toString();
                    st_emailAdddres = emailaddress.getText().toString();
                    st_Birthdate = birthdate.getText().toString();
                    st_AccName = accname.getText().toString();
                    st_direction = direction.getText().toString();
                    st_phonenumber = phonenumber.getText().toString();
                    createUserCode(st_Name,st_Password,st_emailAdddres);
                    //Execute query and insert into database.
                    insertUsuario(URL);
                    Toast.makeText(NewUser.this, "User has been created correctly.", Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e) {
                    Toast.makeText(NewUser.this, "Some field is void.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void goMainActivity() {
        Intent intent = new Intent(NewUser.this,MainActivity.class);
        startActivity(intent);
    }

    //Instance XML
    private void instanceXML() {
        create = findViewById(R.id.create);
        name = findViewById(R.id.user);
        password = findViewById(R.id.passwd);
        emailaddress = findViewById(R.id.email);
        accname = findViewById(R.id.accountname);
        birthdate = findViewById(R.id.birthdate);
        direction = findViewById(R.id.direction);
        phonenumber = findViewById(R.id.phonenumber);

    }

    //Method that creats user code from 3 first characters of user_name, User_password, and Email.
    public void createUserCode(String string1, String string2, String string3) {
        st_codigo_usuario = "";
        char[] char_user = st_Name.toCharArray();
        char[] char_passwd = st_Password.toCharArray();
        char[] char_email = st_emailAdddres.toCharArray();
        st_codigo_usuario += char_user[0];
        st_codigo_usuario += char_user[1];
        st_codigo_usuario += char_user[2];
        st_codigo_usuario += char_passwd[0];
        st_codigo_usuario += char_passwd[1];
        st_codigo_usuario += char_passwd[2];
        st_codigo_usuario += char_email[0];
        st_codigo_usuario += char_email[1];
        st_codigo_usuario += char_email[2];
    }

    //Insert into Usuarios de information about the new user via volley.
    //Getting all the string from EditText onClick
    //Method to put data from new user into database.
    private void insertUsuario(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<String, String>();
                    parametros.put("codigo_usuario",st_codigo_usuario);
                    parametros.put("nombre_cuenta",st_Name);
                    parametros.put("contraseña_cuenta", st_Password);
                    parametros.put("correo_elec", st_emailAdddres);
                    parametros.put("fecha_naci", st_Birthdate);
                    parametros.put("nombre_visual", st_AccName);
                    parametros.put("direction", st_direction);
                    parametros.put("phonenumber", st_phonenumber);
                    return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
