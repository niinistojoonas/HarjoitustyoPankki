package com.example.joonas.pankkisovelluharkka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class TilitapahtumatActivity extends AppCompatActivity {

    TextView text;

    ArrayList oliolista = new ArrayList();

    ArrayList tileja= new ArrayList();

    String kayttis;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //shows money transfers
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilitapahtumat);

        kayttis = (String) getIntent().getSerializableExtra("kayttis");

        text = findViewById(R.id.textView24);



        User uusi = User.getInstance();

        oliolista =  uusi.getList();

        for (int i = 0; i<oliolista.size(); i++){
            objectUser kayttaja = (objectUser) oliolista.get(i);
            System.out.println(kayttaja.getName());
            System.out.println(kayttis);
            if (kayttaja.getName().equals(kayttis)){
                System.out.println(kayttis);
                tileja = kayttaja.returnList2();
            }
        }


        for(int i = 0; i < tileja.size(); i++) {
            text.append((i+1)+". "+tileja.get(i));
            text.append(System.getProperty("line.separator"));
            text.append(System.getProperty("line.separator"));
        }

    }
}
