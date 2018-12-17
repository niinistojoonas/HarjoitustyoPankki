package com.example.joonas.pankkisovelluharkka;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class Mainactivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String kayttis;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // this is the main activity. Here user can deside what he wants to do
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        kayttis = (String) getIntent().getSerializableExtra("kayttis");



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ChangePassword.class);
            intent.putExtra("kayttis", kayttis);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add) { //goes to add new account
            Intent intent = new Intent(this, LisaaTili.class);
            intent.putExtra("kayttis", kayttis);
            intent.putExtra("kumpi", "normal");
            startActivity(intent);
        } else if (id == R.id.saldo) { //goes to check account balances
            Intent intent = new Intent(this, Saldot.class);
            intent.putExtra("kayttis", kayttis);
            intent.putExtra("kumpi", "normal");
            startActivity(intent);

        } else if (id == R.id.transfer) { //goes to transfer money
            Intent intent = new Intent(this, TransferActivity.class);
            intent.putExtra("kayttis", kayttis);
            startActivity(intent);
        }

        else if (id == R.id.cards){ //goes to teh card activity
            Intent intent = new Intent(this, CardActivity.class);
            intent.putExtra("kayttis", kayttis);
            intent.putExtra("kumpi", "normal");
            startActivity(intent);
        }

        else if (id == R.id.addMoney){ //goes to add money
            Intent intent = new Intent(this, MoreMoney.class);
            intent.putExtra("kayttis", kayttis);
            intent.putExtra("kumpi", "normal");
            startActivity(intent);
        }


        else if (id == R.id.user) { //goes to user info
            Intent intent = new Intent(this, UserInfo.class);
            intent.putExtra("kayttis", kayttis);
            intent.putExtra("kumpi", "normal");
            startActivity(intent);

        } else if (id == R.id.out) { // logs out
            Intent intent = new Intent(this, Kirjautuminen.class);
            startActivity(intent);

        } else if (id == R.id.setting) { //goes to check history of payments and deposits
            Intent intent = new Intent(this, TilitapahtumatActivity.class);
            intent.putExtra("kayttis", kayttis);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
