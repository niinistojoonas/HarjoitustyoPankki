package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class newUserActivity extends AppCompatActivity { //here user can create new user

    EditText nameOf;
    EditText passwordOf;
    EditText addresOf;
    EditText numberOf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uusi);

        nameOf = findViewById(R.id.editText6);
        passwordOf = findViewById(R.id.editText7);
        addresOf = findViewById(R.id.editText8);
        numberOf = findViewById(R.id.editText9);
    }

    public void add(View v){ // adds new user
        String addname = nameOf.getText().toString();
        String addpassword = passwordOf.getText().toString().trim();
        String addaddres = addresOf.getText().toString().trim();
        String addphone = numberOf.getText().toString().trim();

        if (!addname.isEmpty() & !addpassword.isEmpty()){
            Bank users = Bank.getInstance();
            users.toList(addname, addpassword, addaddres, addphone);
            Intent intent = new Intent(this, LogInActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getBaseContext(), "Täytä kaikki kentät!", Toast.LENGTH_SHORT).show();
        }




    }
}
