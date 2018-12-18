package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ChangePasswordActivity extends AppCompatActivity {


    EditText old1;

    EditText new2;

    EditText new1;

    String nameOfUser;

    ArrayList oliolista = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);


        old1 = findViewById(R.id.editText18);
        new2 = findViewById(R.id.editText19);
        new1 = findViewById(R.id.editText20);


        nameOfUser = (String) getIntent().getSerializableExtra("nameOfUser");

        Bank uusi = Bank.getInstance();

        oliolista =  uusi.getList();


    }



    public void changePassword(View v){ //changes password
        String newPas1 = new1.getText().toString();
        String newPas2 = new2.getText().toString();
        if (!newPas1.isEmpty()){ //checks if the new password is empty
            if (newPas1.equals(newPas2)){
                String oldPas = old1.getText().toString();
                for (int i = 0; i<oliolista.size(); i++){
                    objectUser kayttaja = (objectUser) oliolista.get(i);
                    if (kayttaja.getName().equals(nameOfUser)){
                        if (kayttaja.getPassword().equals(oldPas)){ // checks if the old password is correct
                            kayttaja.changePasword(newPas1); //changes password
                            Intent intent = new Intent(this, Mainactivity.class);
                            nameOfUser = kayttaja.getName();
                            intent.putExtra("nameOfUser", nameOfUser);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getBaseContext(), "Vanha salasana meni väärin.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
            else{
                Toast.makeText(getBaseContext(), "Uudet salasanat eivät täsmää.", Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast.makeText(getBaseContext(), "Uusi salasana ei kelpaa", Toast.LENGTH_SHORT).show();
        }




        }


}
