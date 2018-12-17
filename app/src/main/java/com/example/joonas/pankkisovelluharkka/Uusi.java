package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Uusi extends AppCompatActivity { //here user can create new user

    EditText nimi;
    EditText salasana;
    EditText osoite;
    EditText puhelinnumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uusi);

        nimi = findViewById(R.id.editText6);
        salasana = findViewById(R.id.editText7);
        osoite = findViewById(R.id.editText8);
        puhelinnumero = findViewById(R.id.editText9);
    }

    public void lisaa(View v){ // adds new user
        String addname = nimi.getText().toString();
        String addpassword = salasana.getText().toString().trim();
        String addaddres = osoite.getText().toString().trim();
        String addphone = puhelinnumero.getText().toString().trim();

        if (!addname.isEmpty() & !addpassword.isEmpty()){
            User uusi = User.getInstance();
            uusi.listaan(addname, addpassword, addaddres, addphone);
            Intent intent = new Intent(this, Kirjautuminen.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getBaseContext(), "Täytä kaikki kentät!", Toast.LENGTH_SHORT).show();
        }




    }
}
