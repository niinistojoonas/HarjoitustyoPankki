package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class PayCardActivity extends AppCompatActivity {

    EditText sum;
    String userOfThis;
    String account2;

    ArrayList objectList = new ArrayList();
    ArrayList accountList1 = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kortilla_maksu);
        sum =findViewById(R.id.editText16);
        userOfThis = (String) getIntent().getSerializableExtra("nameOfUser");
        account2 = (String) getIntent().getSerializableExtra("account1");
    }


    public void buy(View v){ // pays with card

        Bank newUser = Bank.getInstance();
        objectList =  newUser.getList();

        int toBuy = Integer.parseInt(sum.getText().toString().trim());

        for (int i = 0; i< objectList.size(); i++){
            objectUser users = (objectUser) objectList.get(i);
            if (users.getName().equals(userOfThis)){
                accountList1 = users.returnList();
                for (int c = 0; c< accountList1.size(); c++) {
                    account newAccount = (account) accountList1.get(c);
                    System.out.println("KAALAJAJA");
                    System.out.println(account2);
                    if (account2.equals(newAccount.getAccountNumber())) {

                        if (newAccount.isFreezed()){ //checks if the account is freezed by the bank
                            Toast.makeText(getBaseContext(), "TILI JÄÄDYTETTY!", Toast.LENGTH_SHORT).show();
                        }
                        else{

                            int limits = Integer.parseInt(newAccount.getCardBuyLimit().trim());
                            if (limits>toBuy){ //checks if the buing limit is not too small
                                newAccount.sendMoney(toBuy); //buys with card and deletes money
                                users.BuywithCardList(account2, toBuy); // buys with card and saves it to list
                                Intent intent = new Intent(this, Mainactivity.class);
                                intent.putExtra("nameOfUser", userOfThis);
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
