package org.example.vendingmachine.model;

public class FinalCart<E1, E2, E3> {
    private E1 item;
    private E2 change;
    private E3 message;

    public FinalCart(E1 item, E2 change, E3 message) {
        this.item = item;
        this.change = change;
        this.message = message;
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

    public E3 getMessage() {
        return message;
    }

    public void setMessage(E3 message) {
        this.message = message;
    }
}