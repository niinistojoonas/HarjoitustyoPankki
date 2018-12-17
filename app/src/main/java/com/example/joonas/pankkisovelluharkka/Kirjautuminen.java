package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class Kirjautuminen extends AppCompatActivity {

    EditText password;

    EditText username;

    ArrayList oliolista = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirjautuminen);

        password = findViewById(R.id.editText2);
        username = findViewById(R.id.editText);



    }



    public void kirjaudu(View v){ //compares if the password and username are on correct and if yes let's user to the bank
        User uusi = User.getInstance();
        String avainNimi = username.getText().toString();
        String avainSalis= password.getText().toString();


        if (avainNimi.equals("Pankinjohtaja") & avainSalis.equals("jonttuli123")){
            Intent intent = new Intent(this, PankkiController.class);
            startActivity(intent);
        }


        oliolista =  uusi.getList();
        System.out.println(oliolista.size());

        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            if (kayttaja.getName().equals(avainNimi)){
                if (kayttaja.getPassword().equals(avainSalis)){
                    Intent intent = new Intent(this, Mainactivity.class);
                    intent.putExtra("kayttis", avainNimi);
                    startActivity(intent);

                }
            }
        }
    }

    public void uusi(View v){
        Intent intent = new Intent(this, Uusi.class);
        startActivity(intent);
    }


}
