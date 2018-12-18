package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity {

    Spinner spinner;

    ArrayList objectList = new ArrayList();

    ArrayList spinnerLista = new ArrayList();

    String nameOfUser;

    ArrayList accountList = new ArrayList();

    ArrayList zoneList = new ArrayList();

    String item;

    String item2;

    Spinner spinner2;

    TextView hasIt;


    TextView limit1;

    TextView limit2;

    String who;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);


        zoneList.add("Suomi");
        zoneList.add("Eurooppa");
        zoneList.add("Koko maailma");

        nameOfUser = (String) getIntent().getSerializableExtra("nameOfUser"); //tells the program who is the user

        who = (String) getIntent().getSerializableExtra("kumpi"); // tells the program if the user is bank controller (Pankinjohtaja)



        spinner = findViewById(R.id.spinner3);

        spinner2 = findViewById(R.id.spinner4);


        limit1 = findViewById(R.id.editText12);
        limit2 = findViewById(R.id.editText13);
        hasIt = findViewById(R.id.textView18);


        Bank newUser = Bank.getInstance();

        objectList =  newUser.getList();



        nameOfUser = (String) getIntent().getSerializableExtra("nameOfUser");
        System.out.println("TAMA ON"+ nameOfUser);


        for (int i = 0; i< objectList.size(); i++){
            objectUser useObject = (objectUser) objectList.get(i);
            System.out.println(useObject.getName());
            System.out.println(nameOfUser);
            if (useObject.getName().equals(nameOfUser)){
                System.out.println(nameOfUser);
                accountList = useObject.returnList();
                System.out.println(accountList.size());
                for (int c = 0; c< accountList.size(); c++){
                    account tili = (account) accountList.get(c);
                    System.out.println(tili.getAccountNumber());
                    spinnerLista.add(tili.getAccountNumber());
                    }
            }
        }

        ArrayAdapter<String> dataAdapter;
        dataAdapter  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, zoneList); // creates spinner for diffent working zones
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("")){

                }
                else{

                    item2 = parent.getItemAtPosition(position).toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ArrayAdapter<String> dataAdapter2;
        dataAdapter2  = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerLista); //creates spinner for different accounts
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("")){

                }
                else{

                    item = parent.getItemAtPosition(position).toString();
                    for (int i = 0; i< objectList.size(); i++){
                        objectUser kayttaja = (objectUser) objectList.get(i);
                        if (kayttaja.getName().equals(nameOfUser)){
                            System.out.println(nameOfUser);
                            accountList = kayttaja.returnList();
                            System.out.println(accountList.size());
                            for (int c = 0; c< accountList.size(); c++){
                                account tili = (account) accountList.get(c);
                                if (item.equals(tili.getAccountNumber())){
                                    if (tili.hasCard()) {
                                        limit2.setText(tili.getCardBuyLimit());
                                        limit1.setText(tili.getCardTakeLimit());
                                        hasIt.setText("ON KORTTI");
                                    }
                                    else{
                                        hasIt.setText("EI OLE KORTTIA");
                                        limit2.setText("");
                                        limit1.setText("");
                                    }
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
    public void saveThis(View v){ //saves the card if created
        String limitTake = limit1.getText().toString();
        String limitBuy = limit2.getText().toString();

        for (int i = 0; i< objectList.size(); i++){
            objectUser users = (objectUser) objectList.get(i);
            System.out.println(users.getName());
            System.out.println(nameOfUser);
            if (users.getName().equals(nameOfUser)){
                System.out.println(nameOfUser);
                accountList = users.returnList();
                System.out.println(accountList.size());
                for (int c = 0; c< accountList.size(); c++) {
                    account accounts1 = (account) accountList.get(c);
                    if (item.equals(accounts1.getAccountNumber())) {
                        if (!limitBuy.isEmpty() & !limitTake.isEmpty()){
                            accounts1.addCard(limitBuy, limitTake, item2); //sets limits for the card and creates it
                            accounts1.makeCard(); //makes the value of has card true
                            if (who.equals("pankki")) {
                                Intent intent = new Intent(this, BankControllerActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Intent intent = new Intent(this, Mainactivity.class);
                                intent.putExtra("nameOfUser", nameOfUser);
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

    }
    public void deleteCard(View v){ //deletes card if pressed

        for (int i = 0; i< objectList.size(); i++){
            objectUser users = (objectUser) objectList.get(i);
            System.out.println(users.getName());
            System.out.println(nameOfUser);
            if (users.getName().equals(nameOfUser)){
                accountList = users.returnList();
                System.out.println(accountList.size());
                for (int c = 0; c< accountList.size(); c++) {
                    account accounts1 = (account) accountList.get(c);
                    if (item.equals(accounts1.getAccountNumber())) {
                        accounts1.deleteCard();
                        if (who.equals("pankki")) {
                            Intent intent = new Intent(this, BankControllerActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Intent intent = new Intent(this, Mainactivity.class);
                            intent.putExtra("nameOfUser", nameOfUser);
                            startActivity(intent);
                        }
                    }
                }
            }
        }

    }


    public void buyWithCard(View v){ //goes to a activity where user can pay with card
        if (hasIt.getText().equals("ON KORTTI")){
            if (who.equals("pankki")) {
                Toast.makeText(getBaseContext(), "Ei mahdollista maksaa tällä käyttäjällä!", Toast.LENGTH_SHORT).show();
            }
            else{



                Intent intent = new Intent(this, PayCardActivity.class);
                intent.putExtra("nameOfUser", nameOfUser);
                intent.putExtra("account1", item);
                startActivity(intent);
            }



        }
    }

}
