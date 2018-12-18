package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MoreMoney extends AppCompatActivity {


    private Spinner spinner;

    ArrayList objectList = new ArrayList();

    ArrayList accountList = new ArrayList();

    ArrayList spinnerList = new ArrayList();

    String userOfThis;

    EditText toAdd;

    String item;

    String who;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_money);

        spinnerList.add("Valitse tili:");



        toAdd = findViewById(R.id.editText17);


        spinner = findViewById(R.id.spinner6);


        Bank newUSer = Bank.getInstance();

        objectList =  newUSer.getList();



        userOfThis = (String) getIntent().getSerializableExtra("nameOfUser");
        who = (String) getIntent().getSerializableExtra("kumpi");


        for (int i = 0; i< objectList.size(); i++){
            objectUser users = (objectUser) objectList.get(i);
            System.out.println(users.getName());
            System.out.println(userOfThis);
            if (users.getName().equals(userOfThis)){
                System.out.println(userOfThis);
                accountList = users.returnList();
                System.out.println(accountList.size());
                for (int c = 0; c< accountList.size(); c++){
                    account tili = (account) accountList.get(c);
                    System.out.println(tili.getAccountNumber());
                    spinnerList.add(tili.getAccountNumber());
                }
            }
        }

        ArrayAdapter<String> dataAdapter;
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList); //creates spinner for acounts
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Valitse tili:")){

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

    public void takeMoney(View v){ // adds money to the account
        for (int i = 0; i< objectList.size(); i++){
            objectUser users = (objectUser) objectList.get(i);
            System.out.println(users.getName());
            System.out.println(userOfThis);
            if (users.getName().equals(userOfThis)){
                System.out.println(userOfThis);
                accountList = users.returnList();
                for (int c = 0; c< accountList.size(); c++){
                    account accounts1 = (account) accountList.get(c);
                    System.out.println(accounts1.getAccountNumber());
                    System.out.println(item);
                    if (accounts1.getAccountNumber().equals(item)){
                        int toGet = Integer.parseInt(toAdd.getText().toString().trim());
                        accounts1.getMoney(toGet);
                        users.moreMoneyList(item, toGet);
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

}
