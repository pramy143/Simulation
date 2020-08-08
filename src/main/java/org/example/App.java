package org.example;

import org.example.vendingmachine.model.Coin;
import org.example.vendingmachine.model.FinalCart;
import org.example.vendingmachine.model.Item;
import org.example.vendingmachine.repo.Inventory;
import org.example.vendingmachine.service.VendingMachineService;
import org.example.vendingmachine.service.VendingMachineServiceImpl;

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

        VendingMachineService vendingMachine = new VendingMachineServiceImpl(cashInventory, itemInventory);
        long paidAmount = vendingMachine.payAmount(Coin.DIME, Coin.NICKLE, Coin.CENT, Coin.DIME, Coin.NICKLE, Coin.CENT, Coin.QUARTER);

        FinalCart<Item, List<Coin>, String> buyItemWithChange = vendingMachine.buyItemAndCollectChange(Item.Cold_Drink, paidAmount);

        System.out.println("Item selected : " + buyItemWithChange.getItem() );
        System.out.println("Actual price is : " + buyItemWithChange.getItem().getPrice());
        System.out.println("Amount paid is : " + paidAmount);

        System.out.println("Change paid is ");
        buyItemWithChange.getChange().stream().forEach((changeD) -> System.out.println(changeD + " : " + changeD.getDenomination()));
        System.out.println(buyItemWithChange.getMessage());

    }

    static void initialSetUp(){
        for(Coin coin : Coin.values()){
            cashInventory.put(coin, 5);
        }

        for(Item item : Item.values()){
            itemInventory.put(item, 5);
        }

    }
}
