package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class kortillaMaksu extends AppCompatActivity {

    EditText summa;
    String kayttis;
    String tili2;

    ArrayList oliolista = new ArrayList();
    ArrayList tileja= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kortilla_maksu);
        summa =findViewById(R.id.editText16);
        kayttis = (String) getIntent().getSerializableExtra("kayttis");
        tili2 = (String) getIntent().getSerializableExtra("tili");
    }


    public void maksu(View v){ // pays with card

        User uusi = User.getInstance();
        oliolista =  uusi.getList();

        int maksettava = Integer.parseInt(summa.getText().toString().trim());

        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            if (kayttaja.getName().equals(kayttis)){
                tileja = kayttaja.returnList();
                for (int c = 0; c<tileja.size(); c++) {
                    account tili = (account) tileja.get(c);
                    System.out.println("KAALAJAJA");
                    System.out.println(tili2);
                    if (tili2.equals(tili.getAccountNumber())) {

                        if (tili.isFreezed()){ //checks if the account is freezed by the bank
                            Toast.makeText(getBaseContext(), "TILI JÄÄDYTETTY!", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            int nostoraja = Integer.parseInt(tili.getCardBuyLimit().trim());
                            if (nostoraja>maksettava){ //checks if the buing limit is not too small
                                tili.sendMoney(maksettava); //buys with card and deletes money
                                kayttaja.maksaKortilla(tili2, maksettava); // buys with card and saves it to list
                                Intent intent = new Intent(this, Mainactivity.class);
                                intent.putExtra("kayttis", kayttis);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getBaseContext(), "Maksuraja ylitetty! Ei voida maksaa", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }
        }
    }


}
