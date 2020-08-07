package org.example.vendingmachine.service;

import org.example.vendingmachine.model.Coin;
import org.example.vendingmachine.model.FinalCart;
import org.example.vendingmachine.model.Item;

import java.util.List;

public interface VendingMachine {
    public long selectItemAndGetPrice(Item item);
    public long insertCoin(Coin ...coins);
    public FinalCart<Item, List<Coin>> collectItemAndChange(Item item, long paidAmount);
}

