package org.example;

import org.example.vendingmachine.model.Coin;
import org.example.vendingmachine.model.Item;
import org.example.vendingmachine.repo.Inventory;
import org.example.vendingmachine.service.VendingMachine;
import org.example.vendingmachine.service.VendingMachineImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    private static Inventory<Coin> cashInventory = new Inventory<>();
    private static Inventory<Item> itemInventory = new Inventory<>();

    public static void main( String[] args) {
        System.out.println( "Simulation for Vending Machine!" );
        initialSetUp();

        VendingMachine vm = new VendingMachineImpl(cashInventory, itemInventory);
        long paidAmount = vm.insertCoin(Coin.DIME, Coin.NICKLE, Coin.CENT, Coin.DIME, Coin.NICKLE, Coin.CENT, Coin.QUARTER);
        List<Coin> change = vm.collectItemAndChange(Item.Cold_Drink, paidAmount).getChange();
        Item item = vm.collectItemAndChange(Item.Cold_Drink, paidAmount).getItem();

        System.out.println("Item selected : " + item );
        System.out.println("Actual price is : " + item.getPrice());
        System.out.println("Amount paid is : " + paidAmount);

        System.out.println("Change paid is ");
        change.stream().forEach((changeD) -> System.out.println(changeD + " : " + changeD.getDenomination()));

    }

    static void initialSetUp(){
        for(Coin c : Coin.values()){
            cashInventory.put(c, 5);
        }

        for(Item i : Item.values()){
            itemInventory.put(i, 5);
        }

    }
}
