package com.example.joonas.pankkisovelluharkka;

import java.util.ArrayList;

public class objectUser { //this is object of one user
    private String name;
    private String password;
    private String addres;
    private String number;

    ArrayList<account> tilit = new ArrayList<>();

    ArrayList<String> tilitiedot = new ArrayList<>();


    public objectUser(String n, String p, String a, String x) {
        name = n;
        password = p;
        addres = a;
        number = x;

    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getAddres() {
        return addres;
    }

    public String getPhone(){
        return number;
    }


    public void changeInfo(String n, String p, String a) {
        name = n;
        number = p;
        addres = a;
    }

    public void changePasword(String n){
        password = n;
    }

    public void addAccount(String An, int m, String t){
        account uusi = new account(An, m, t);
        tilit.add(uusi);
    }

    public ArrayList<account> returnList(){
        return tilit;
    } // returns list of this users accounts


    public ArrayList<String> returnList2(){
        return tilitiedot;
    } // returns list of this users payment and deposit history


    public void tililtarahaaLista(String minne, String mista, int p){
        tilitiedot.add("Siirrettiin "+p+"€ rahaa  tililtä "+mista+" tilille "+minne);
    }
    public void tilillerahaaLista(String minne, String mista, int p){
        tilitiedot.add("Saatiin "+p+"€ rahaa tilille "+minne+" tililtä "+mista);
    }

    public void maksaKortilla(String mista, int p){
        tilitiedot.add("Maksettiin tilin "+mista+" maksukortilla "+p+"€");
    }

    public void talletaLista(String minne, int p){
        tilitiedot.add("Talletettiin tilille "+minne+" rahaa "+p+"€");
    }

    public void poistaTili(int m){
        tilit.remove(m);
    }
}

