/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author sandukuttan
 */

//singleton class

public class Inventory {
    static Inventory inventory = new Inventory();
    
    static HashMap< String , Item> itemList;

    private Inventory() {
        Inventory.itemList = new HashMap<>() ;
    }
    
    public static Inventory getInventory(){
        return Inventory.inventory;
    }
    public static void addItem(Item item){
        // add suitable design pattern
        //System.out.println(item.getItemID());
        try{
        itemList.putIfAbsent(item.getItemID(), item);
        }
        catch(Exception e){
            //System.out.println(item);
        }
    }
    
    public static Item getItem(String id){
        Item item=null;
        try{
            item= itemList.get(id);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return item;
    }
    
    public ArrayList<Item> viewAllItems(){
        ArrayList<Item> result= new ArrayList<>();
        for(Item item : itemList.values())
            result.add(item);
        return result;
    }
    public Item removeItem(String itemID){
        
        Item deletedItem=itemList.remove(itemID);
        return deletedItem;
    }
    
    public ArrayList<Item> searchItem(String name){
        ArrayList<Item> searchResult = new ArrayList<>();
        for (Item item : itemList.values()) {
            if(item.getItemName().toLowerCase().contains(name.toLowerCase())){
                boolean add = searchResult.add(item);
            }
        }
        return searchResult;
    }
    
    public void editItemDetails(Item newItem, String key){
        Item put = itemList.put(key,newItem);
    }
    
    public ArrayList<Item> sortByDiscount(){
        ArrayList<Item> sortedByDiscount= new ArrayList<>();
        // flyweight design pattern can be used
        for(Item item : itemList.values())
            sortedByDiscount.add(item);
        
        Collections.sort(sortedByDiscount, new Comparator<Item>() {
            @Override
            public int compare(Item f, Item s) {
                return f.getItemDiscount().compareTo(s.getItemDiscount());
            }
        });
        
        return sortedByDiscount;
    }
    
    public ArrayList<Item> sortByPrice(){
        ArrayList<Item> sortedByPrice= new ArrayList<>();
        // flyweight design pattern can be used
        for(Item item : itemList.values())
            sortedByPrice.add(item);
        
        Collections.sort(sortedByPrice, new Comparator<Item>() {
            @Override
            public int compare(Item f, Item s) {
                return f.getItemPrice().compareTo(s.getItemPrice());
            }
        });
        return sortedByPrice;
    }
}
