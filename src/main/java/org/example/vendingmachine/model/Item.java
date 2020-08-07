package org.example.vendingmachine.model;

public enum Item{
    Chocolate("Chocolate", 10),
    Candy("Candy", 5),
    Cold_Drink("Cold_Drink", 25);

    private String name;
    private int price;

    private Item(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public long getPrice(){
        return price;
    }
}