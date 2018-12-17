package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    Spinner spinner;

    ArrayList oliolista = new ArrayList();

    ArrayList spinnerLista = new ArrayList();

    String kayttis;

    ArrayList tileja= new ArrayList();

    ArrayList toimialue = new ArrayList();

    String item;

    String item2;

    Spinner spinner2;

    TextView onko;


    TextView nostoraja;

    TextView maksuraja;

    String who;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);


        toimialue.add("Suomi");
        toimialue.add("Eurooppa");
        toimialue.add("Koko maailma");

        kayttis = (String) getIntent().getSerializableExtra("kayttis"); //tells the program who is the user

        who = (String) getIntent().getSerializableExtra("kumpi"); // tells the program if the user is bank controller (Pankinjohtaja)



        spinner = findViewById(R.id.spinner3);

        spinner2 = findViewById(R.id.spinner4);


        nostoraja = findViewById(R.id.editText12);
        maksuraja = findViewById(R.id.editText13);
        onko = findViewById(R.id.textView18);


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
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, toimialue); // creates spinner for diffent working zones
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("")){

                }
                else{

                    item2 = parent.getItemAtPosition(position).toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<String> dataAdapter2;
        dataAdapter2  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerLista); //creates spinner for different accounts
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("")){

                }
                else{

                    item = parent.getItemAtPosition(position).toString();
                    for (int i = 0; i<oliolista.size(); i++){
                        objectUser kayttaja = (objectUser) oliolista.get(i);
                        if (kayttaja.getName().equals(kayttis)){
                            System.out.println(kayttis);
                            tileja = kayttaja.returnList();
                            System.out.println(tileja.size());
                            for (int c = 0; c<tileja.size(); c++){
                                account tili = (account) tileja.get(c);
                                if (item.equals(tili.getAccountNumber())){
                                    if (tili.hasCard()) {
                                        maksuraja.setText(tili.getCardBuyLimit());
                                        nostoraja.setText(tili.getCardTakeLimit());
                                        onko.setText("ON KORTTI");
                                    }
                                    else{
                                        onko.setText("EI OLE KORTTIA");
                                        maksuraja.setText("");
                                        nostoraja.setText("");
                                    }
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
    public void tallennus(View v){ //saves the card if created
        String nRaja = nostoraja.getText().toString();
        String mRaja = maksuraja.getText().toString();

        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            System.out.println(kayttaja.getName());
            System.out.println(kayttis);
            if (kayttaja.getName().equals(kayttis)){
                System.out.println(kayttis);
                tileja = kayttaja.returnList();
                System.out.println(tileja.size());
                for (int c = 0; c<tileja.size(); c++) {
                    account tili = (account) tileja.get(c);
                    if (item.equals(tili.getAccountNumber())) {
                        if (!mRaja.isEmpty() & !nRaja.isEmpty()){
                            tili.addCard(mRaja, nRaja, item2); //sets limits for the card and creates it
                            tili.makeCard(); //makes the value of has card true
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
                        else{
                            Toast.makeText(getBaseContext(), "Täytä kaikki kentät!", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        }

    }
    public void poista(View v){ //deletes card if pressed

        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            System.out.println(kayttaja.getName());
            System.out.println(kayttis);
            if (kayttaja.getName().equals(kayttis)){
                tileja = kayttaja.returnList();
                System.out.println(tileja.size());
                for (int c = 0; c<tileja.size(); c++) {
                    account tili = (account) tileja.get(c);
                    if (item.equals(tili.getAccountNumber())) {
                        tili.deleteCard();
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


    public void maksaKortilla(View v){ //goes to a activity where user can pay with card
        if (onko.getText().equals("ON KORTTI")){
            if (who.equals("pankki")) {
                Toast.makeText(getBaseContext(), "Ei mahdollista maksaa tällä käyttäjällä!", Toast.LENGTH_SHORT).show();
            }
            else{



                Intent intent = new Intent(this, kortillaMaksu.class);
                intent.putExtra("kayttis", kayttis);
                intent.putExtra("tili", item);
                startActivity(intent);
            }



        }
    }

}
