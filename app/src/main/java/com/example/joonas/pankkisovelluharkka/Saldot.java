package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Saldot extends AppCompatActivity {

    private Spinner spinner;

    ArrayList oliolista = new ArrayList();

    ArrayList tileja= new ArrayList();

    ArrayList spinnerLista= new ArrayList();

    String kayttis;

    TextView saldot1;

    TextView tyyppi;

    String item;

    String who;

    Button freeze;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //here user can check palances of the account ore delete accounts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldot);

        spinnerLista.add("Valitse tili");


        saldot1 = findViewById(R.id.rahasumma);

        tyyppi = findViewById(R.id.textView12);


        spinner = findViewById(R.id.spinner);


        User uusi = User.getInstance();

        oliolista =  uusi.getList();

        freeze = findViewById(R.id.button15);



        kayttis = (String) getIntent().getSerializableExtra("kayttis");
        who = (String) getIntent().getSerializableExtra("kumpi");
        System.out.println("TAMA ON"+kayttis);


        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            System.out.println(kayttaja.getName());
            System.out.println(kayttis);
            if (kayttaja.getName().equals(kayttis)){
                System.out.println(kayttis);
                tileja = kayttaja.returnList();
                System.out.println(tileja.size());
                for (int c = 0; c<tileja.size(); c++){
                    account tili = (account) tileja.get(c);
                    System.out.println(tili.getAccountNumber());
                    spinnerLista.add(tili.getAccountNumber());
                }
            }
            }

        ArrayAdapter<String> dataAdapter;
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerLista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Valitse tili")){

                }
                else{

                    item = parent.getItemAtPosition(position).toString();
                    for (int i = 0; i<oliolista.size(); i++){
                        objectUser kayttaja = (objectUser) oliolista.get(i);
                        System.out.println(kayttaja.getName());
                        System.out.println(kayttis);
                        if (kayttaja.getName().equals(kayttis)){
                            System.out.println(kayttis);
                            tileja = kayttaja.returnList();
                            System.out.println(tileja.size());
                            for (int c = 0; c<tileja.size(); c++){
                                account tili = (account) tileja.get(c);
                                if (tili.getAccountNumber().equals(item)){
                                    if (tili.isFreezed()){
                                        freeze.setText("Poista jäädytys");
                                    }
                                    String summa = (""+tili.getMon());
                                    saldot1.setText(summa+" €");
                                    tyyppi.setText(tili.getAccounType());

                                }
                            }
                        }
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        }

        public void poistatili(View v){ //deletes account
            for (int i = 0; i<oliolista.size(); i++){
                objectUser kayttaja = (objectUser) oliolista.get(i);
                System.out.println(kayttaja.getName());
                System.out.println(kayttis);
                if (kayttaja.getName().equals(kayttis)){
                    System.out.println(kayttis);
                    tileja = kayttaja.returnList();
                    System.out.println(tileja.size());
                    for (int c = 0; c<tileja.size(); c++){
                        account tili = (account) tileja.get(c);
                        if (tili.getAccountNumber().equals(item)){
                            kayttaja.poistaTili(c);
                            if (who.equals("pankki")) {
                                Intent intent = new Intent(this, PankkiController.class);
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(this, Mainactivity.class);
                                intent.putExtra("kayttis", kayttis);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        }


        public void kuoletatili(View v){ //freezes account (only bank controller can do this)

            for (int i = 0; i<oliolista.size(); i++){
                objectUser kayttaja = (objectUser) oliolista.get(i);
                System.out.println(kayttaja.getName());
                System.out.println(kayttis);
                if (kayttaja.getName().equals(kayttis)){
                    System.out.println(kayttis);
                    tileja = kayttaja.returnList();
                    System.out.println(tileja.size());
                    for (int c = 0; c<tileja.size(); c++){
                        account tili = (account) tileja.get(c);
                        if (tili.getAccountNumber().equals(item)){
                            if (who.equals("pankki")) { // checks if the bank controller is used
                                if (tili.isFreezed()){
                                    tili.unFreeze();
                                }
                                else{
                                    tili.freezeAccount();
                                }
                                Intent intent = new Intent(this, PankkiController.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getBaseContext(), "Ei oikeuksia tällä käyttäjällä!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

        }


}

