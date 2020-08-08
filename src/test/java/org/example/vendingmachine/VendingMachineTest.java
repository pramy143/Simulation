package org.example.vendingmachine;

import org.example.vendingmachine.model.Coin;
import org.example.vendingmachine.model.FinalCart;
import org.example.vendingmachine.model.Item;
import org.example.vendingmachine.repo.Inventory;
import org.example.vendingmachine.service.VendingMachineService;
import org.example.vendingmachine.service.VendingMachineServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VendingMachineTest {
    private static VendingMachineService vendingMachine;
    private static Inventory<Coin> cashInventory = new Inventory<>();
    private static Inventory<Item> itemInventory = new Inventory<>();

    @BeforeAll
    public static void setUp() {
        initialSetUp();
        vendingMachine = new VendingMachineServiceImpl(cashInventory, itemInventory);
    }

    static void initialSetUp(){
        for(Coin coin : Coin.values()){
            cashInventory.put(coin, 5);
        }

        for(Item item : Item.values()){
            itemInventory.put(item, 5);
        }

    }

    @Test
    public void testBuyItemWithExactPrice() {
        long paidAmount = vendingMachine.payAmount(Coin.DIME);
        FinalCart<Item, List<Coin>, String> bucket = vendingMachine.buyItemAndCollectChange(Item.Chocolate, paidAmount);
        Item item = bucket.getItem();
        List<Coin> change = bucket.getChange();
        assertEquals(Item.Chocolate, item);
        assertTrue(change.isEmpty());
    }

    @Test
    public void testBuyItemWithMorePrice(){
        long paidAmount = vendingMachine.payAmount(Coin.DIME, Coin.NICKLE, Coin.CENT, Coin.DIME, Coin.NICKLE, Coin.CENT, Coin.QUARTER);

        FinalCart<Item, List<Coin>, String> bucket = vendingMachine.buyItemAndCollectChange(Item.Cold_Drink, paidAmount);
        Item item = bucket.getItem();
        List<Coin> change = bucket.getChange();

        assertEquals(Item.Cold_Drink, item);
        assertTrue(!change.isEmpty());
        assertEquals(paidAmount - Item.Cold_Drink.getPrice(), 32);

    }

}
