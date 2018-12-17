package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MoreMoney extends AppCompatActivity {


    private Spinner spinner;

    ArrayList oliolista = new ArrayList();

    ArrayList tileja= new ArrayList();

    ArrayList spinnerLista= new ArrayList();

    String kayttis;

    EditText lisattava;

    String item;

    String who;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_money);

        spinnerLista.add("Valitse tili");



        lisattava = findViewById(R.id.editText17);


        spinner = findViewById(R.id.spinner6);


        User uusi = User.getInstance();

        oliolista =  uusi.getList();



        kayttis = (String) getIntent().getSerializableExtra("kayttis");
        who = (String) getIntent().getSerializableExtra("kumpi");


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
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerLista); //creates spinner for acounts
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Valitse tili")){

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

    public void maksa(View v){ // adds money to the account
        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            System.out.println(kayttaja.getName());
            System.out.println(kayttis);
            if (kayttaja.getName().equals(kayttis)){
                System.out.println(kayttis);
                tileja = kayttaja.returnList();
                for (int c = 0; c<tileja.size(); c++){
                    account tili = (account) tileja.get(c);
                    System.out.println(tili.getAccountNumber());
                    System.out.println(item);
                    if (tili.getAccountNumber().equals(item)){
                        int saatava = Integer.parseInt(lisattava.getText().toString().trim());
                        tili.getMoney(saatava);
                        kayttaja.talletaLista(item, saatava);
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

}
