package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class LogInActivity extends AppCompatActivity {

    EditText password;

    EditText username;

    ArrayList objectList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirjautuminen);

        password = findViewById(R.id.editText2);
        username = findViewById(R.id.editText);



    }



    public void goIn(View v){ //compares if the password and username are on correct and if yes let's user to the bank
        Bank newUser = Bank.getInstance();
        String keyName = username.getText().toString();
        String keyPassword= password.getText().toString();


        if (keyName.equals("Pankinjohtaja") & keyPassword.equals("jonttuli123")){
            Intent intent = new Intent(this, BankControllerActivity.class);
            startActivity(intent);
        }


        objectList =  newUser.getList();
        System.out.println(objectList.size());

        for (int i = 0; i< objectList.size(); i++){
            objectUser kayttaja = (objectUser) objectList.get(i);
            if (kayttaja.getName().equals(keyName)){
                if (kayttaja.getPassword().equals(keyPassword)){
                    Intent intent = new Intent(this, Mainactivity.class);
                    intent.putExtra("nameOfUser", keyName);
                    startActivity(intent);

                }
            }
        }
    }

    public void newUsers(View v){
        Intent intent = new Intent(this, newUserActivity.class);
        startActivity(intent);
    }


}
