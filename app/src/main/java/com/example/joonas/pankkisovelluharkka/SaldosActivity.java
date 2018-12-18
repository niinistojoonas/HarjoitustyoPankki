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

public class SaldosActivity extends AppCompatActivity {

    private Spinner spinner;

    ArrayList objectList = new ArrayList();

    ArrayList accountList = new ArrayList();

    ArrayList spinnerList = new ArrayList();

    String userOfThis;

    TextView saldos1;

    TextView type;

    String item;

    String who;

    Button freeze;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //here user can check palances of the account ore delete accounts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldot);

        spinnerList.add("Valitse tili:");


        saldos1 = findViewById(R.id.rahasumma);

        type = findViewById(R.id.textView12);


        spinner = findViewById(R.id.spinner);


        Bank newUser = Bank.getInstance();

        objectList =  newUser.getList();

        freeze = findViewById(R.id.button15);



        userOfThis = (String) getIntent().getSerializableExtra("nameOfUser");
        who = (String) getIntent().getSerializableExtra("kumpi");
        System.out.println("TAMA ON"+ userOfThis);


        for (int i = 0; i< objectList.size(); i++){
            objectUser users = (objectUser) objectList.get(i);
            System.out.println(users.getName());
            System.out.println(userOfThis);
            if (users.getName().equals(userOfThis)){
                System.out.println(userOfThis);
                accountList = users.returnList();
                System.out.println(accountList.size());
                for (int c = 0; c< accountList.size(); c++){
                    account newAccount1 = (account) accountList.get(c);
                    System.out.println(newAccount1.getAccountNumber());
                    spinnerList.add(newAccount1.getAccountNumber());
                }
            }
            }

        ArrayAdapter<String> dataAdapter;
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Valitse tili:")){

                }
                else{

                    item = parent.getItemAtPosition(position).toString();
                    for (int i = 0; i< objectList.size(); i++){
                        objectUser kayttaja = (objectUser) objectList.get(i);
                        System.out.println(kayttaja.getName());
                        System.out.println(userOfThis);
                        if (kayttaja.getName().equals(userOfThis)){
                            System.out.println(userOfThis);
                            accountList = kayttaja.returnList();
                            System.out.println(accountList.size());
                            for (int c = 0; c< accountList.size(); c++){
                                account tili = (account) accountList.get(c);
                                if (tili.getAccountNumber().equals(item)){
                                    if (tili.isFreezed()){
                                        freeze.setText("Poista jäädytys");
                                    }
                                    String sumOfthis = (""+tili.getMon());
                                    saldos1.setText(sumOfthis+" €");
                                    type.setText(tili.getAccounType());

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

        public void deleteAccount(View v){ //deletes account
            for (int i = 0; i< objectList.size(); i++){
                objectUser users = (objectUser) objectList.get(i);
                System.out.println(users.getName());
                System.out.println(userOfThis);
                if (users.getName().equals(userOfThis)){
                    System.out.println(userOfThis);
                    accountList = users.returnList();
                    System.out.println(accountList.size());
                    for (int c = 0; c< accountList.size(); c++){
                        account newAccount1 = (account) accountList.get(c);
                        if (newAccount1.getAccountNumber().equals(item)){
                            users.deteleAccount(c);
                            if (who.equals("pankki")) {
                                Intent intent = new Intent(this, BankControllerActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(this, Mainactivity.class);
                                intent.putExtra("nameOfUser", userOfThis);
                                startActivity(intent);
                            }
                        }
                    }
                }
            }
        }


        public void freezeThis(View v){ //freezes account (only bank controller can do this)

            for (int i = 0; i< objectList.size(); i++){
                objectUser users = (objectUser) objectList.get(i);
                System.out.println(users.getName());
                System.out.println(userOfThis);
                if (users.getName().equals(userOfThis)){
                    System.out.println(userOfThis);
                    accountList = users.returnList();
                    System.out.println(accountList.size());
                    for (int c = 0; c< accountList.size(); c++){
                        account newAccount1 = (account) accountList.get(c);
                        if (newAccount1.getAccountNumber().equals(item)){
                            if (who.equals("pankki")) { // checks if the bank controller is used
                                if (newAccount1.isFreezed()){
                                    newAccount1.unFreeze();
                                }
                                else{
                                    newAccount1.freezeAccount();
                                }
                                Intent intent = new Intent(this, BankControllerActivity.class);
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

