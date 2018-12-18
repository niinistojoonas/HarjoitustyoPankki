package com.example.joonas.pankkisovelluharkka;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class AccountEventActivity extends AppCompatActivity {

    TextView text;

    ArrayList objectList = new ArrayList();

    ArrayList accountList = new ArrayList();

    String userOfThis;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //shows money transfers
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilitapahtumat);

        userOfThis = (String) getIntent().getSerializableExtra("nameOfUser");

        text = findViewById(R.id.textView24);



        Bank newUser = Bank.getInstance();

        objectList =  newUser.getList();

        for (int i = 0; i< objectList.size(); i++){
            objectUser users = (objectUser) objectList.get(i);
            System.out.println(users.getName());
            System.out.println(userOfThis);
            if (users.getName().equals(userOfThis)){
                System.out.println(userOfThis);
                accountList = users.returnList2();
            }
        }


        for(int i = 0; i < accountList.size(); i++) {
            text.append((i+1)+". "+ accountList.get(i));
            text.append(System.getProperty("line.separator"));
            text.append(System.getProperty("line.separator"));
        }

    }
}
