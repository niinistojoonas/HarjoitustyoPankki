package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class BankControllerActivity extends AppCompatActivity {

    private Spinner spinner;

    ArrayList objectList = new ArrayList();

    ArrayList spinnerList = new ArrayList();

    ArrayList usersList = new ArrayList();

    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //this is Bank controller activity where you can deside the user and do anything
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pankki_controller);

        spinner = findViewById(R.id.spinner7);

        Bank newUser = Bank.getInstance();

        objectList =  newUser.getList();


        usersList.add("Valitse käyttäjä");


        for (int i = 0; i< objectList.size(); i++){
            objectUser users = (objectUser) objectList.get(i);
            spinnerList.add(users.getName());
        }





        ArrayAdapter<String> dataAdapter; // creates spinner of users
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList);
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

    public void accountInfo(View v){ // goes to check payment and deposit history
        Intent intent = new Intent(this, AccountEventActivity.class);
        intent.putExtra("nameOfUser", item);
        startActivity(intent);
    }

    public void deleteUser(View v){ //deletes user
        for (int i = 0; i< objectList.size(); i++){
            objectUser kayttaja = (objectUser) objectList.get(i);
            if (item.equals(kayttaja.getName())){
                objectList.remove(kayttaja);
                Intent intent = new Intent(this, BankControllerActivity.class);
                startActivity(intent);
            }
        }
    }

    public void saldos(View v){ // goes check palances
        Intent intent = new Intent(this, SaldosActivity.class);
        intent.putExtra("nameOfUser", item);
        intent.putExtra("kumpi", "pankki"); //with this activity knows that bank controller is using it and returns to this class
        startActivity(intent);
    }

    public void cards(View v){ // goes to card activity
        Intent intent = new Intent(this, CardActivity.class);
        intent.putExtra("nameOfUser", item);
        intent.putExtra("kumpi", "pankki");
        startActivity(intent);
    }

    public void addAccount(View v){ //adds accounts
         Intent intent = new Intent(this, NewAccountActivity.class);
         intent.putExtra("nameOfUser", item);
         intent.putExtra("kumpi", "pankki");
         startActivity(intent);
    }

    public void moreMoney(View v){ //adds money
         Intent intent = new Intent(this, MoreMoney.class);
         intent.putExtra("nameOfUser", item);
         intent.putExtra("kumpi", "pankki");
         startActivity(intent);
    }


    public void usersInformation(View v){ //checks user info
         Intent intent = new Intent(this, UserInfoActivity.class);
         intent.putExtra("nameOfUser", item);
         intent.putExtra("kumpi", "pankki");
         startActivity(intent);
    }


    public void outTo(View v){ //logsout
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }





}
