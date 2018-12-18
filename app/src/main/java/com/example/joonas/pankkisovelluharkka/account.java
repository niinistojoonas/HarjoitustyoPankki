package com.example.joonas.pankkisovelluharkka;

public class account {                //This class is a class for one account and it returns values depending on what the user wants
    private String accountNumber;
    private int money;
    private String accountType;
    cardObject newCard = new cardObject();

    boolean x = false; //this is true if this account has a card

    boolean y = false; // this is true if this account is freezed by the bank

    public account(String aN, int m, String t){
        accountNumber = aN;
        money = m;
        accountType = t;
    }


    public String getAccountNumber(){
        return accountNumber;
    }

    public int getMon() {
        return money;
    }

    public String getAccounType(){
        return accountType;
    }

    public void addCard(String m, String n, String t){
        newCard.createCard(m,n,t);
    }

    public String getCardBuyLimit(){
        return newCard.getCardBuyinglimit();
    }

    public String getCardTakeLimit(){
        return newCard.getCardLiftingLimit();
    }

    public String getCardArea(){
        return newCard.getCardZone();
    }

    public boolean hasCard(){
        return x;
    } //tells the program if the account has a card

    public void makeCard(){
        x = true;
    }

    public void deleteCard(){
        x = false;
    }


    public void sendMoney(int m){
        money = (money-m);
    }

    public void getMoney(int m){
        money = (money+m);
    }

    public void freezeAccount(){
        y = true;
    }

    public void unFreeze(){
        y = false;
    }


    public boolean isFreezed(){
        return y;
    } //tells the program if the account is freezed
}



