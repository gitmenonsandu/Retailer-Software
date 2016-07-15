/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import static shoppingmall.Inventory.itemList;

/**
 *
 * @author sandukuttan
 */

//singleton
public class OfferTable {
    // offerID is key and Offer is value
    static HashMap< String , Offer> offerList;
    //itemID is key and offerID is value
    private OfferTable(){
        offerList=new HashMap<>();
    }
    static OfferTable offerTable=new OfferTable();
    
    public static OfferTable getOfferTable(){
        return offerTable;
    }
    
    public static void addOffer(Offer offer){
        offerList.put(offer.getOfferID(), offer);
    }
    
    public static Offer getOffer(String id){
        Offer offer=null;
        try{
            offer=offerList.get(id);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return offer;
    }
    public Offer removeOffer(String offerID){
        Offer removedOffer=offerList.remove(offerID);
        
        return removedOffer;
    }
    
    public void editOffer(Offer offer, String offerID){
        offerList.put(offerID,offer);
    }
    
    public ArrayList<Offer> sortByUses(){
        ArrayList<Offer> sortedByUses = null;
        
        for (Offer offer : offerList.values()) {
            sortedByUses.add(offer);
        }
        //flyweight design pattern can be used
        Collections.sort(sortedByUses, new Comparator<Offer>() {
            @Override
            public int compare(Offer f, Offer s) {
                return f.getOfferUses().compareTo(s.getOfferUses());
            }
        });
        
        return sortedByUses;
    }
    
}
