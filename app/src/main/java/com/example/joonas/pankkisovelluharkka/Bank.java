package com.example.joonas.pankkisovelluharkka;


import java.util.ArrayList;

public class Bank { // this is the user class where every user is

    private static Bank users = new Bank();
    public static Bank getInstance(){
        return users;
    }

    ArrayList<objectUser> objectList = new ArrayList<>();


    String name1;
    String addres1;
    String password1;
    String phone1;

    public Bank(){

    }

    public void toList(String name, String password, String address, String phone){ //adds new user to a list
        name1 = name;
        addres1 = address;
        password1 = password;
        phone1 = phone;
        objectUser newUser1 = new objectUser(name1, password1, addres1, phone1);
        System.out.println(name1 + addres1 + password1);
        objectList.add(newUser1);


    }

    public ArrayList<objectUser> getList(){
        return objectList;


    }

}
