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

public class NewAccountActivity extends AppCompatActivity {

    EditText accountsNumber;
    EditText money1;

    ArrayList objectList = new ArrayList();

    String userOfThis;

    ArrayList spinnerList = new ArrayList();

    Spinner spinner;

    String item;

    String who;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisaa_tili);


        spinnerList.add("Normaalitili");
        spinnerList.add("Säästötili");

        spinner = findViewById(R.id.spinner2);


        accountsNumber = findViewById(R.id.editText10);
        money1 = findViewById(R.id.editText11);

        Bank newUser = Bank.getInstance();
        objectList = newUser.getList();

        userOfThis = (String) getIntent().getSerializableExtra("nameOfUser");
        who = (String) getIntent().getSerializableExtra("kumpi");




        ArrayAdapter<String> dataAdapter;
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList); //creates spinner of different types of accounts
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


        for (int i = 0; i < objectList.size(); i++) {
            objectUser users = (objectUser) objectList.get(i);
            if (users.getName().equals(userOfThis)) {
                if (!accountsNumber.getText().toString().isEmpty() & !money1.getText().toString().isEmpty()){ //checks that all the places are filled
                    int MON = Integer.parseInt(money1.getText().toString().trim());
                    System.out.println(MON);
                    users.addAccount(accountsNumber.getText().toString(), MON, item);
                    if (who.equals("pankki")) { //goes back to bankCotroller
                        Intent intent = new Intent(this, BankControllerActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(this, Mainactivity.class); //goes back to user mainactivity
                        intent.putExtra("nameOfUser", userOfThis);
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