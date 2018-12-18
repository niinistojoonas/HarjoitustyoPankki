package com.example.joonas.pankkisovelluharkka;

import java.util.ArrayList;

public class objectUser { //this is object of one user
    private String name;
    private String password;
    private String addres;
    private String number;

    ArrayList<account> accountsList = new ArrayList<>();

    ArrayList<String> historyList = new ArrayList<>();


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
        accountsList.add(uusi);
    }

    public ArrayList<account> returnList(){
        return accountsList;
    } // returns list of this users accounts


    public ArrayList<String> returnList2(){
        return historyList;
    } // returns list of this users payment and deposit history


    public void moneyFromList(String minne, String mista, int p){
        historyList.add("Siirrettiin "+p+"€ rahaa  tililtä "+mista+" tilille "+minne);
    }
    public void moneyToList(String minne, String mista, int p){
        historyList.add("Saatiin "+p+"€ rahaa tilille "+minne+" tililtä "+mista);
    }

    public void BuywithCardList(String mista, int p){
        historyList.add("Maksettiin tilin "+mista+" maksukortilla "+p+"€");
    }

    public void moreMoneyList(String minne, int p){
        historyList.add("Talletettiin tilille "+minne+" money1 "+p+"€");
    }

    public void deteleAccount(int m){
        accountsList.remove(m);
    }
}

