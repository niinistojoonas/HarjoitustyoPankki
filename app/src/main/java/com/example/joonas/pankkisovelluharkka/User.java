package com.example.joonas.pankkisovelluharkka;


import java.util.ArrayList;

public class User { // this is the user class where every user is (basically this is the bank class the name is just different)

    private static User kayttis = new User();
    public static User getInstance(){
        return kayttis;
    }

    ArrayList<objectUser> oliolista = new ArrayList<>();


    String nimi;
    String osoite;
    String salasana;
    String puhelin;

    public User(){

    }

    public void listaan(String name, String password, String address, String phone){ //adds new user to a list
        nimi = name;
        osoite = address;
        salasana = password;
        puhelin = phone;
        objectUser uusi = new objectUser(nimi, salasana, osoite, puhelin);
        System.out.println(nimi+osoite+salasana);
        oliolista.add(uusi);


    }

    public ArrayList<objectUser> getList(){
        return oliolista;


    }

}
