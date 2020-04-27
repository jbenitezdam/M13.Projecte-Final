package com.example.myownbusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateBusiness extends AppCompatActivity {

    ImageButton searchLocation;
    Spinner firstcat,secondcat,thirdcat;
    EditText Name,Location,Price,Description;
    dealClass Deal;
    Button CreateDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business);
        instanceXML();
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
                createDeal();
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

    //get String from edittext and converting then into object dealClass.
    public void createDeal() {
        try {
            //In case any Field missing
            if (Name.getText().toString().equals("") || Name.getText().toString().equals(null)
                    || Location.getText().toString().equals("") || Location.getText().toString().equals(null)
                    || Price.getText().toString().equals("") || Price.getText().toString().equals(null)) {
                Toast.makeText(this, "Error, Some values have not been introduced.", Toast.LENGTH_SHORT).show();
            }
            //Case to do not miss any Field.
            else {
                //Create obj deal that we will use to introduce info into BBDD.
                short short_price = Short.parseShort(Price.getText().toString());
                Deal = new dealClass(Name.getText().toString(),Description.getText().toString(),Location.getText().toString(),firstcat.getSelectedItem().toString(),
                        secondcat.getSelectedItem().toString(),thirdcat.getSelectedItem().toString(),short_price);
            }


        }catch(Exception e) {
            System.out.println("Error al introducir ");
            Toast.makeText(this, "Error, Some values have not been introduced.", Toast.LENGTH_SHORT).show();
        }



    }


    public void goMAP() {
        Intent intent = new Intent(CreateBusiness.this, MapsActivity.class);
        startActivity(intent);
    }
}
