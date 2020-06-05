package com.example.myownbusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Spinner;
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

public class CreateBusiness extends AppCompatActivity {

    String URL,etname,etlocation,etfirstcat,etsecondcat,etthirdcat,etprice,etdescription;
    ImageButton searchLocation;
    Spinner firstcat,secondcat,thirdcat;
    EditText Name,Location,Price,Description;
    dealClass Deal;
    Button CreateDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business);
        getSupportActionBar().hide();
        instanceXML();
        URL = getString(R.string.URL_GET_SERVICIOS);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.categoryspinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstcat.setAdapter(adapter);
        secondcat.setAdapter(adapter);
        thirdcat.setAdapter(adapter);
        searchLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goMAP();
            }
        });

        CreateDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    etname = Name.getText().toString();
                    etlocation = Location.getText().toString();
                    etfirstcat = firstcat.getSelectedItem().toString();
                    etsecondcat = secondcat.getSelectedItem().toString();
                    etthirdcat = thirdcat.getSelectedItem().toString();
                    etprice = Price.getText().toString();
                    etdescription = Description.getText().toString();

                    insertUsuario(URL);
                    Toast.makeText(CreateBusiness.this, "Business Created Correctly.", Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e) {
                    Toast.makeText(CreateBusiness.this, "Some field is void.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //FUNCTIONS------------------------------------------------------------------------------------>
    public void instanceXML() {
        searchLocation = findViewById(R.id.searchLocation);
        Name = findViewById(R.id.name);
        Location = findViewById(R.id.location);
        firstcat = findViewById(R.id.firstCat);
        secondcat = findViewById(R.id.secondCat);
        thirdcat = findViewById(R.id.thirdCat);
        Price = findViewById(R.id.price);
        Description = findViewById(R.id.description);
        CreateDeal = findViewById(R.id.create);
    }

    //Getting all the string from EditText onClick
    //Method to put data from new business into database.
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
                parametros.put("codigo_usuario",etname);
                parametros.put("nombre", etname);
                parametros.put("categoria1", etfirstcat);
                parametros.put("categoria2", etsecondcat);
                parametros.put("categoria3", etthirdcat);
                parametros.put("localizacion", etlocation);
                parametros.put("precio", etprice);
                parametros.put("descripcion", etdescription);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    public void goMAP() {
        Intent intent = new Intent(CreateBusiness.this, MapsActivity.class);
        startActivity(intent);
    }

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

    public void goProfile() {
        Intent intent = new Intent(CreateBusiness.this,Profile.class);
        startActivity(intent);
    }
    public void Leave() {
        Intent intent = new Intent(CreateBusiness.this,MainActivity.class);
        startActivity(intent);

    }
    public void goMainMenu() {
        Intent intent = new Intent(CreateBusiness.this,MainMenu.class);
        startActivity(intent);
    }
}
