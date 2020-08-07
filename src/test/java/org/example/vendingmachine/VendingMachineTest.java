package org.example.vendingmachine;

import org.example.vendingmachine.model.Coin;
import org.example.vendingmachine.model.FinalCart;
import org.example.vendingmachine.model.Item;
import org.example.vendingmachine.repo.Inventory;
import org.example.vendingmachine.service.VendingMachine;
import org.example.vendingmachine.service.VendingMachineImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VendingMachineTest {
    private static VendingMachine vm;
    private static Inventory<Coin> cashInventory = new Inventory<>();
    private static Inventory<Item> itemInventory = new Inventory<>();

    @BeforeAll
    public static void setUp() {
        initialSetUp(); //initialize with 5 coins of each denomination and 5 cans of each Item

        vm = new VendingMachineImpl(cashInventory, itemInventory);
    }

    static void initialSetUp(){
        for(Coin c : Coin.values()){
            cashInventory.put(c, 5);
        }

        for(Item i : Item.values()){
            itemInventory.put(i, 5);
        }

    }

    @Test
    public void testBuyItemWithExactPrice() {
        long paidAmount = vm.insertCoin(Coin.DIME);
        FinalCart<Item, List<Coin>> bucket = vm.collectItemAndChange(Item.Chocolate, paidAmount);
        Item item = bucket.getItem();
        List<Coin> change = bucket.getChange();
        assertEquals(Item.Chocolate, item);
        assertTrue(change.isEmpty());
    }

    @Test
    public void testBuyItemWithMorePrice(){
        long paidAmount = vm.insertCoin(Coin.DIME, Coin.NICKLE, Coin.CENT, Coin.DIME, Coin.NICKLE, Coin.CENT, Coin.QUARTER);

        FinalCart<Item, List<Coin>> bucket = vm.collectItemAndChange(Item.Cold_Drink, paidAmount);
        Item item = bucket.getItem();
        List<Coin> change = bucket.getChange();

        //should be Coke
        assertEquals(Item.Cold_Drink, item);
        //there should not be any change
        assertTrue(!change.isEmpty());
        //comparing change
        assertEquals(paidAmount - Item.Cold_Drink.getPrice(), 32);

    }

}
