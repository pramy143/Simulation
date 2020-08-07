package org.example.vendingmachine.model;

public class FinalCart<E1, E2> {
    private E1 item;
    private E2 change;

    public FinalCart(E1 item, E2 change) {
        this.item = item;
        this.change = change;
    }

    public E1 getItem() {
        return item;
    }

    public void setItem(E1 item) {
        this.item = item;
    }

    public E2 getChange() {
        return change;
    }

    public void setChange(E2 change) {
        this.change = change;
    }
}