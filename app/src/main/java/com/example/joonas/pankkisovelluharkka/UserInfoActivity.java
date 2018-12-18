package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class UserInfoActivity extends AppCompatActivity {

    EditText name1;
    EditText addres1;
    EditText number1;

    String name;
    String addres;
    String phonenumber;

    ArrayList objectList = new ArrayList();

    String userOfThis;

    String who;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //here user can check and change the user information
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        userOfThis = (String) getIntent().getSerializableExtra("nameOfUser");
        who = (String) getIntent().getSerializableExtra("kumpi");

        name1 = findViewById(R.id.editText3);
        addres1 = findViewById(R.id.editText5);
        number1 = findViewById(R.id.editText4);


        Bank newUser = Bank.getInstance();


        objectList =  newUser.getList();
        System.out.println(objectList.size());

        for (int i = 0; i< objectList.size(); i++){
            objectUser users = (objectUser) objectList.get(i);
            if (users.getName().equals(userOfThis)){
                name = users.getName();
                addres = users.getAddres();
                phonenumber = users.getPhone();
                name1.setText(name);
                addres1.setText(addres);
                number1.setText(phonenumber);
                }
            }
        }

        public void reFresh(View v){
            for (int i = 0; i< objectList.size(); i++){
                objectUser users = (objectUser) objectList.get(i);
                if (users.getName().equals(userOfThis)){
                    users.changeInfo(name1.getText().toString(), number1.getText().toString(), addres1.getText().toString());
                    if (who.equals("pankki")) {
                        Intent intent = new Intent(this, BankControllerActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(this, Mainactivity.class);
                        userOfThis = users.getName();
                        intent.putExtra("nameOfUser", userOfThis);
                        startActivity(intent);

                    }
                }
            }
        }


    }

