package org.example;

import org.example.vendingmachine.model.Coin;
import org.example.vendingmachine.model.FinalCart;
import org.example.vendingmachine.model.Item;
import org.example.vendingmachine.repo.InventoryHelper;
import org.example.vendingmachine.service.VendingMachineService;
import org.example.vendingmachine.service.VendingMachineServiceImpl;

import java.util.List;

/**
 * Hello world!
 *
 */
public class AppVendingMachineSimulator {
    private static InventoryHelper<Coin> cashInventoryHelper = new InventoryHelper<>();
    private static InventoryHelper<Item> itemInventoryHelper = new InventoryHelper<>();

    public static void main( String[] args) {
        System.out.println( "Simulation for Vending Machine!" );
        initialSetUp();

        VendingMachineService vendingMachine = new VendingMachineServiceImpl(cashInventoryHelper, itemInventoryHelper);
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
            cashInventoryHelper.put(coin, 5);
        }

        for(Item item : Item.values()){
            itemInventoryHelper.put(item, 5);
        }

    }
}
