package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TransferActivity extends AppCompatActivity {

    Spinner spinner;

    ArrayList objectList = new ArrayList();
    ArrayList spinnerList = new ArrayList();
    ArrayList accountList = new ArrayList();

    EditText transferSum;
    EditText account1;

    TextView type;


    String userOfthis;

    String item;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //here user can send money to diffent accounts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        spinner = findViewById(R.id.spinner5);

        transferSum = findViewById(R.id.editText15);
        account1 = findViewById(R.id.editText14);

        type = findViewById(R.id.textView22);


        Bank newUSer = Bank.getInstance();

        objectList = newUSer.getList();


        userOfthis = (String) getIntent().getSerializableExtra("nameOfUser");
        System.out.println("TAMA ON" + userOfthis);


        for (int i = 0; i < objectList.size(); i++) {
            objectUser users = (objectUser) objectList.get(i);
            System.out.println(users.getName());
            System.out.println(userOfthis);
            if (users.getName().equals(userOfthis)) {
                System.out.println(userOfthis);
                accountList = users.returnList();
                System.out.println(accountList.size());
                for (int c = 0; c < accountList.size(); c++) {
                    account newAccount1 = (account) accountList.get(c);
                    System.out.println(newAccount1.getAccountNumber());
                    spinnerList.add(newAccount1.getAccountNumber());
                }
            }
        }

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList); // spinner for the account of money source
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (parent.getItemAtPosition(position).equals("Valitse tili:")) {

                } else {

                    item = parent.getItemAtPosition(position).toString();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void find(View V) { // checks if the account where money goes is from the same bank (if it finds it somewhere from the list)
        String toFind = account1.getText().toString();
        for (int i = 0; i < spinnerList.size(); i++) {
            if (spinnerList.get(i).toString().equals(toFind)) {
                type.setText("OMA TILI");
                return;
            }
        }
        for (int i = 0; i < objectList.size(); i++) {
            objectUser users = (objectUser) objectList.get(i);
            System.out.println(userOfthis);
            accountList = users.returnList();
            System.out.println(accountList.size());
            for (int c = 0; c < accountList.size(); c++) {
                account newAccount1 = (account) accountList.get(c);
                if (newAccount1.getAccountNumber().equals(toFind)) {
                    type.setText("SAMAN PANKIN TILI");
                    return;
                }
            }
            type.setText("VIERAS TILI");

        }
    }

    public void sendMoney(View v) { //sends money to chosen account
        int MON = Integer.parseInt(transferSum.getText().toString().trim());
        int enough = 0;
        String source = item;
        String goal = account1.getText().toString();
        for (int i = 0; i < objectList.size(); i++) {
            objectUser users = (objectUser) objectList.get(i);
            System.out.println(userOfthis);
            accountList = users.returnList();
            System.out.println(accountList.size());
            for (int c = 0; c < accountList.size(); c++) {
                account newAccount1 = (account) accountList.get(c);

                if (newAccount1.getAccountNumber().equals(item)) {

                    if (newAccount1.isFreezed()) { //checks if the account is freezed
                        Toast.makeText(getBaseContext(), "TILI JÄÄDYTETTY!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (newAccount1.getMon() > MON){
                        if (newAccount1.getAccountNumber().equals(source)) {
                            newAccount1.sendMoney(MON);
                            users.moneyFromList(goal, source, MON);
                            enough = 1;
                        }
                        }
                        else{
                            Toast.makeText(getBaseContext(), "TILILLÄ EI OLE TARPEEKSI RAHAA!", Toast.LENGTH_SHORT).show();
                        }


                    }


                }
            }
        }
        for (int i = 0; i < objectList.size(); i++) {
            objectUser users = (objectUser) objectList.get(i);
            accountList = users.returnList();
            for (int c = 0; c < accountList.size(); c++) {
                account newAccount1 = (account) accountList.get(c);
                if (newAccount1.getAccountNumber().equals(goal)) {
                    if (enough == 1) {
                        newAccount1.getMoney(MON);
                        users.moneyToList(goal, source, MON);
                        Intent intent = new Intent(this, Mainactivity.class);
                        intent.putExtra("nameOfUser", userOfthis);
                        startActivity(intent);
                    }
                }
            }

        }
    }
}
