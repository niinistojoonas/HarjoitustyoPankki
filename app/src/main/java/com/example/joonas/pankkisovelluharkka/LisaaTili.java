package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class LisaaTili extends AppCompatActivity {

    EditText tilinumero;
    EditText rahaa;

    ArrayList oliolista = new ArrayList();

    String kayttis;

    ArrayList spinnerLista = new ArrayList();

    Spinner spinner;

    String item;

    String who;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa_tili);


        spinnerLista.add("Normaalitili");
        spinnerLista.add("Säästötili");

        spinner = findViewById(R.id.spinner2);


        tilinumero = findViewById(R.id.editText10);
        rahaa = findViewById(R.id.editText11);

        User uusi = User.getInstance();
        oliolista = uusi.getList();

        kayttis = (String) getIntent().getSerializableExtra("kayttis");
        who = (String) getIntent().getSerializableExtra("kumpi");




        ArrayAdapter<String> dataAdapter;
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerLista); //creates spinner of different types of accounts
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("")){

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

    public void newAccount(View v) { // makes new account


        for (int i = 0; i < oliolista.size(); i++) {
            objectUser kayttaja = (objectUser) oliolista.get(i);
            if (kayttaja.getName().equals(kayttis)) {
                if (!tilinumero.getText().toString().isEmpty() & !rahaa.getText().toString().isEmpty()){ //checks that all the places are filled
                    int MON = Integer.parseInt(rahaa.getText().toString().trim());
                    System.out.println(MON);
                    kayttaja.addAccount(tilinumero.getText().toString(), MON, item);
                    if (who.equals("pankki")) { //goes back to bankCotroller
                        Intent intent = new Intent(this, PankkiController.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(this, Mainactivity.class); //goes back to user mainactivity
                        intent.putExtra("kayttis", kayttis);
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(getBaseContext(), "Täytä kaikki kentät!", Toast.LENGTH_SHORT).show();
                }

            }

        }

    }
}