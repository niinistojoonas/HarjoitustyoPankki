package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class PankkiController extends AppCompatActivity {

    private Spinner spinner;

    ArrayList oliolista = new ArrayList();

    ArrayList spinnerLista= new ArrayList();

    ArrayList kayttajalista= new ArrayList();

    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //this is Bank controller activity where you can deside the user and do anything
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pankki_controller);

        spinner = findViewById(R.id.spinner7);

        User uusi = User.getInstance();

        oliolista =  uusi.getList();


        kayttajalista.add("Valitse käyttäjä");


        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            spinnerLista.add(kayttaja.getName());
        }





        ArrayAdapter<String> dataAdapter; // creates spinner of users
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerLista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Valitse käyttäjä")){
                }
                else{
                    item = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void tilitiedot(View v){ // goes to check payment and deposit history
        Intent intent = new Intent(this, TilitapahtumatActivity.class);
        intent.putExtra("kayttis", item);
        startActivity(intent);
    }

    public void poistaKayttaja(View v){ //deletes user
        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            if (item.equals(kayttaja.getName())){
                oliolista.remove(kayttaja);
                Intent intent = new Intent(this, PankkiController.class);
                startActivity(intent);
            }
        }
    }

    public void saldot(View v){ // goes check palances
        Intent intent = new Intent(this, Saldot.class);
        intent.putExtra("kayttis", item);
        intent.putExtra("kumpi", "pankki"); //with this activity knows that bank controller is using it and returns to this class
        startActivity(intent);
    }

    public void kortit(View v){ // goes to card activity
        Intent intent = new Intent(this, CardActivity.class);
        intent.putExtra("kayttis", item);
        intent.putExtra("kumpi", "pankki");
        startActivity(intent);
    }

    public void lisaatili(View v){ //adds accounts
         Intent intent = new Intent(this, LisaaTili.class);
         intent.putExtra("kayttis", item);
         intent.putExtra("kumpi", "pankki");
         startActivity(intent);
    }

    public void lisaaRahaa(View v){ //adds money
         Intent intent = new Intent(this, MoreMoney.class);
         intent.putExtra("kayttis", item);
         intent.putExtra("kumpi", "pankki");
         startActivity(intent);
    }


    public void kayttajaTiedot(View v){ //checks user info
         Intent intent = new Intent(this, UserInfo.class);
         intent.putExtra("kayttis", item);
         intent.putExtra("kumpi", "pankki");
         startActivity(intent);
    }


    public void ulos(View v){ //logsout
        Intent intent = new Intent(this, Kirjautuminen.class);
        startActivity(intent);
    }





}
