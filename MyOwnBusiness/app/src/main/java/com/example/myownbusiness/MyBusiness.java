package com.example.myownbusiness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

public class MyBusiness extends AppCompatActivity {
    Boolean firstBusiness;
    dealClass Business;
    ImageView imgrank;
    int nRank;
    TextView txtname, txtemail,txtphone,txtlocation,txtdesc,txtprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_business);
        getSupportActionBar().hide();
        //Get intent from past activity
        Intent i = getIntent();
        nRank = i.getIntExtra("rank",4);
        Business = (dealClass) i.getSerializableExtra("business");
        instanceXML();
        //Set texts into all TextView Objects.
        setObjectTexts();
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

    private void instanceXML() {
        imgrank = findViewById(R.id.imgrank);
        txtname = findViewById(R.id.name);
        txtemail = findViewById(R.id.email);
        txtlocation = findViewById(R.id.location);
        txtphone = findViewById(R.id.phonenumber);
        txtdesc = findViewById(R.id.description);
        txtprice = findViewById(R.id.price);
    }

    private void setObjectTexts(){
        txtname.setText(Business.nombre.toString());
        txtdesc.setText(Business.descripcion.toString());
        txtprice.setText(Business.precio.toString()+ " € ");
        txtlocation.setText(Business.localizacion.toString());

        switch (nRank) {
            case 0: imgrank.setImageResource(R.mipmap.rangouno_foreground);
                break;
            case 1: imgrank.setImageResource(R.mipmap.rangodos_foreground);
                break;
            case 2: imgrank.setImageResource(R.mipmap.rangotres_foreground);
                break;
            default:imgrank.setImageResource(R.mipmap.sinrango_foreground);
                break;
        }
    }


    //Functions with intents.
    public void goProfile(){
        Intent intent = new Intent(MyBusiness.this,Profile.class);
        startActivity(intent);

    }

    public void goMainMenu(){
        Intent intent = new Intent(MyBusiness.this,MainMenu.class);
        startActivity(intent);
    }

    public void Leave() {
        Intent intent = new Intent(MyBusiness.this,MainActivity.class);
        startActivity(intent);
    }

}
