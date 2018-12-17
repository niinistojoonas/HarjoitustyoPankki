package com.example.joonas.pankkisovelluharkka;

public class cardObject {
    private String maksuraja =  "";
    private String nostoraja = "";
    private String toimivuusalue = "Suomi";


    public void createCard(String m, String n, String t){ //card object
        maksuraja = m;
        nostoraja = n;
        toimivuusalue = t;
    }

    public String getMaksuraja(){
        return maksuraja;
    } //returns paying limit

    public String getNostoraja() {
        return nostoraja;
    } //returns lift limit

    public String getToimivuusalue(){
        return toimivuusalue;
    } // gets working zone of the card
}
