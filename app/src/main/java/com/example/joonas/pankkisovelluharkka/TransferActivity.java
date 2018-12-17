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
import android.widget.Toast;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity {

    Spinner spinner;

    ArrayList oliolista = new ArrayList();
    ArrayList spinnerLista= new ArrayList();
    ArrayList tileja= new ArrayList();
    ArrayList tileja2= new ArrayList();

    EditText siirrettavaSumma;
    EditText tili;

    TextView tyyppi;

    String kayttis;

    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //here user can send money to diffent accounts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        spinner = findViewById(R.id.spinner5);

        siirrettavaSumma = findViewById(R.id.editText15);
        tili = findViewById(R.id.editText14);

        tyyppi = findViewById(R.id.textView22);



        User uusi = User.getInstance();

        oliolista =  uusi.getList();




        kayttis = (String) getIntent().getSerializableExtra("kayttis");
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
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerLista); // spinner for the account of money source
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

    public void etsiTili(View V){ // checks if the account where money goes is from the same bank (if it finds it somewhere from the list)
        String etsittava = tili.getText().toString();
        for (int i = 0; i<spinnerLista.size(); i++){
            if (spinnerLista.get(i).toString().equals(etsittava)){
                tyyppi.setText("OMA TILI");
                return;
            }
        }
        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            System.out.println(kayttis);
            tileja = kayttaja.returnList();
            System.out.println(tileja.size());
            for (int c = 0; c<tileja.size(); c++){
                account tili = (account) tileja.get(c);
                if (tili.getAccountNumber().equals(etsittava)){
                    tyyppi.setText("SAMAN PANKIN TILI");
                    return;
                }
            }
            tyyppi.setText("VIERAS TILI");

        }
    }

    public void lahetaRahaa(View v){ //sends money to chosen account
        int MON = Integer.parseInt(siirrettavaSumma.getText().toString().trim());
        String lahde = item;
        String kohde = tili.getText().toString();
        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            System.out.println(kayttis);
            tileja = kayttaja.returnList();
            System.out.println(tileja.size());
            for (int c = 0; c<tileja.size(); c++){
                account tili = (account) tileja.get(c);

                if (tili.isFreezed()){ //checks if the account is freezed
                    Toast.makeText(getBaseContext(), "TILI JÄÄDYTETTY!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (tili.getAccountNumber().equals(lahde)){
                        tili.sendMoney(MON);
                        kayttaja.tililtarahaaLista(kohde, lahde, MON);
                    }
                    if (tili.getAccountNumber().equals(kohde)) {
                        tili.getMoney(MON);
                        kayttaja.tilillerahaaLista(kohde, lahde, MON);
                    }
                    Intent intent = new Intent(this, Mainactivity.class);
                    intent.putExtra("kayttis", kayttis);
                    startActivity(intent);
                }



                }
            }
        }



}
