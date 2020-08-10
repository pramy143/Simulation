package org.example.vendingmachine.service;

import org.example.vendingmachine.model.Coin;
import org.example.vendingmachine.model.FinalCart;
import org.example.vendingmachine.model.Item;

import java.util.List;

public interface VendingMachineService {
    public long payAmount(Coin ...coins);
    public FinalCart<Item, List<Coin>, String> buyItemAndCollectChange(Item item, long paidAmount);
}

