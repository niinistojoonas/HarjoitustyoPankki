package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class UserInfo extends AppCompatActivity {

    EditText nimi;
    EditText osoite;
    EditText puhelinnumero;

    String name;
    String addres;
    String phonenumber;

    ArrayList oliolista = new ArrayList();

    String kayttis;

    String who;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //here user can check and change the user information
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        kayttis = (String) getIntent().getSerializableExtra("kayttis");
        who = (String) getIntent().getSerializableExtra("kumpi");

        nimi = findViewById(R.id.editText3);
        osoite = findViewById(R.id.editText5);
        puhelinnumero = findViewById(R.id.editText4);


        User uusi = User.getInstance();


        oliolista =  uusi.getList();
        System.out.println(oliolista.size());

        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            if (kayttaja.getName().equals(kayttis)){
                name = kayttaja.getName();
                addres = kayttaja.getAddres();
                phonenumber = kayttaja.getPhone();
                nimi.setText(name);
                osoite.setText(addres);
                puhelinnumero.setText(phonenumber);
                }
            }
        }

        public void paivitys(View v){
            for (int i = 0; i<oliolista.size(); i++){
                objectUser kayttaja = (objectUser) oliolista.get(i);
                if (kayttaja.getName().equals(kayttis)){
                    kayttaja.changeInfo(nimi.getText().toString(), puhelinnumero.getText().toString(), osoite.getText().toString());
                    if (who.equals("pankki")) {
                        Intent intent = new Intent(this, PankkiController.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(this, Mainactivity.class);
                        System.out.println("MITÄ VITTUUA"+kayttis);
                        kayttis = kayttaja.getName();
                        intent.putExtra("kayttis", kayttis);
                        startActivity(intent);

                    }
                }
            }
        }


    }

