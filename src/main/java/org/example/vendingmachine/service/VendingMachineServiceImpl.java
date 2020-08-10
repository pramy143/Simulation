package org.example.vendingmachine.service;

import org.example.vendingmachine.model.Coin;
import org.example.vendingmachine.model.FinalCart;
import org.example.vendingmachine.model.Item;
import org.example.vendingmachine.repo.InventoryHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VendingMachineServiceImpl implements VendingMachineService {
    private InventoryHelper<Coin> cashInventoryHelper;
    private InventoryHelper<Item> itemInventoryHelper;
    private long amountPaid;
   
    public VendingMachineServiceImpl(InventoryHelper<Coin> cashInventoryHelper, InventoryHelper<Item> itemInventoryHelper){
        this.cashInventoryHelper = cashInventoryHelper;
        this.itemInventoryHelper = itemInventoryHelper;
    }

    @Override
    public long payAmount(Coin ...coins) {
        for(Coin coin : coins){
            amountPaid = amountPaid + coin.getDenomination();
            cashInventoryHelper.add(coin);
        }

        return amountPaid;
    }

    @Override
    public FinalCart<Item, List<Coin>, String> buyItemAndCollectChange(Item item, long paidAmount) {
        List<Coin> change;
        String message = "Thanks for carting visit again !";
        if(itemInventoryHelper.hasItem(item)){
            change = collectChangeAndUpdateStock(item, paidAmount);
            return new FinalCart<>(item, change, message);
        }

        change = getChange(paidAmount);
        message = "We don't have stock please collect paid amount: " + paidAmount;
        return new FinalCart<>(item, change, message);
    }

    private List<Coin> collectChangeAndUpdateStock(Item item, long paidAmount) {
        long changeAmount = paidAmount - item.getPrice();
        List<Coin> change = getChange(changeAmount);
        updateCashInventoryHelper(change);

        return change;
    }
   
    private List<Coin> getChange(long amount) {
        List<Coin> changes = Collections.EMPTY_LIST;
       
        if(amount > 0){
            changes = new ArrayList<Coin>();
            long balance = amount;
            while(balance > 0){
                if(balance >= Coin.QUARTER.getDenomination() 
                            && cashInventoryHelper.hasItem(Coin.QUARTER)){
                    changes.add(Coin.QUARTER);
                    balance = balance - Coin.QUARTER.getDenomination();
                    continue;
                   
                }
                else if(balance >= Coin.DIME.getDenomination()
                                 && cashInventoryHelper.hasItem(Coin.DIME)) {
                    changes.add(Coin.DIME);
                    balance = balance - Coin.DIME.getDenomination();
                    continue;
                   
                }
                else if(balance >= Coin.NICKLE.getDenomination()
                                 && cashInventoryHelper.hasItem(Coin.NICKLE)) {
                    changes.add(Coin.NICKLE);
                    balance = balance - Coin.NICKLE.getDenomination();
                    continue;
                   
                }
                else if(balance >= Coin.CENT.getDenomination()
                                 && cashInventoryHelper.hasItem(Coin.CENT)) {
                    changes.add(Coin.CENT);
                    balance = balance - Coin.CENT.getDenomination();
                    continue;
                   
                }
                else{
                    System.out.println("Don't have enough change, Please try to buy another product");
                }
            }
        }
       
        return changes;
    }
   
    private void updateCashInventoryHelper(List<Coin> change) {
        for(Coin c : change){
            cashInventoryHelper.deduct(c);
        }
    }

}