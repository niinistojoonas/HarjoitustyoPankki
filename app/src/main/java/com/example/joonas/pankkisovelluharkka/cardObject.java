package com.example.joonas.pankkisovelluharkka;

public class cardObject {
    private String buyLimit =  "";
    private String liftLimit = "";
    private String zone = "Suomi";


    public void createCard(String m, String n, String t){ //card object
        buyLimit = m;
        liftLimit = n;
        zone = t;
    }

    public String getCardBuyinglimit(){
        return buyLimit;
    } //returns paying limit

    public String getCardLiftingLimit() {
        return liftLimit;
    } //returns lift limit

    public String getCardZone(){
        return zone;
    } // gets working zone of the card
}
