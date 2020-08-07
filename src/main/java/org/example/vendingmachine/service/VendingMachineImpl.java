package org.example.vendingmachine.service;

import org.example.vendingmachine.model.Coin;
import org.example.vendingmachine.model.FinalCart;
import org.example.vendingmachine.model.Item;
import org.example.vendingmachine.repo.Inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {
    private Inventory<Coin> cashInventory;
    private Inventory<Item> itemInventory;
    private long currentBalance;
   
    public VendingMachineImpl(Inventory<Coin> cashInventory, Inventory<Item> itemInventory){
        this.cashInventory = cashInventory;
        this.itemInventory = itemInventory;
    }

   @Override
    public long selectItemAndGetPrice(Item item) {
        if(itemInventory.hasItem(item)){
            return item.getPrice();
        }
        return 0;
    }

    @Override
    public long insertCoin(Coin ...coins) {
        for(Coin coin : coins){
            currentBalance = currentBalance + coin.getDenomination();
            cashInventory.add(coin);
        }
        return currentBalance;
    }

    @Override
    public FinalCart<Item, List<Coin>> collectItemAndChange(Item item, long paidAmount) {
        List<Coin> change = collectChange(item);
       
        return new FinalCart<>(item, change);
    }

    private List<Coin> collectChange(Item item) {
        long changeAmount = currentBalance - item.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventory(change);
        currentBalance = 0;
        return change;
    }
   
    private List<Coin> getChange(long amount) {
        List<Coin> changes = Collections.EMPTY_LIST;
       
        if(amount > 0){
            changes = new ArrayList<Coin>();
            long balance = amount;
            while(balance > 0){
                if(balance >= Coin.QUARTER.getDenomination() 
                            && cashInventory.hasItem(Coin.QUARTER)){
                    changes.add(Coin.QUARTER);
                    balance = balance - Coin.QUARTER.getDenomination();
                    continue;
                   
                }
                else if(balance >= Coin.DIME.getDenomination()
                                 && cashInventory.hasItem(Coin.DIME)) {
                    changes.add(Coin.DIME);
                    balance = balance - Coin.DIME.getDenomination();
                    continue;
                   
                }
                else if(balance >= Coin.NICKLE.getDenomination()
                                 && cashInventory.hasItem(Coin.NICKLE)) {
                    changes.add(Coin.NICKLE);
                    balance = balance - Coin.NICKLE.getDenomination();
                    continue;
                   
                }
                else if(balance >= Coin.CENT.getDenomination()
                                 && cashInventory.hasItem(Coin.CENT)) {
                    changes.add(Coin.CENT);
                    balance = balance - Coin.CENT.getDenomination();
                    continue;
                   
                }
                else{
                    System.out.println("Please try to buy another product");
                }
            }
        }
       
        return changes;
    }
   
    private void updateCashInventory(List<Coin> change) {
        for(Coin c : change){
            cashInventory.deduct(c);
        }
    }

}