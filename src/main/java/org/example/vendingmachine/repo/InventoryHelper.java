package org.example.vendingmachine.repo;

import java.util.HashMap;
import java.util.Map;

public class InventoryHelper<T> {
    private Map<T, Integer> inventoryCart = new HashMap<>();

    public int getQuantity(T item){
        Integer value = inventoryCart.get(item);
        return value == null? 0 : value ;
    }

    public void add(T item){
        int count = inventoryCart.get(item);
        inventoryCart.put(item, count+1);
    }

    public void deduct(T item) {
        if (hasItem(item)) {
            int count = inventoryCart.get(item);
            inventoryCart.put(item, count - 1);
        }
    }

    public boolean hasItem(T item) {
        return getQuantity(item) > 0;
    }

    public void put(T item, int quantity) {
        inventoryCart.put(item, quantity);
    }
}