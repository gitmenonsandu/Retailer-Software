/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandukuttan
 */
public class updateModel {
    
    //function retireves data from MYSQL and stores in singleton class Inventory
    public static void updateInventoryModel(){
        String ItemID,ItemName,ItemCategory,ItemImage;
        Float ItemPrice,ItemDiscount;
        Integer ItemQuantity;
        boolean ItemOfferAvailability;
        try {
            
            SqlLogin.getTable("select * from itemtable");
        } catch (SQLException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<SqlLogin.data.size();++i){
            
            ItemID=((String) SqlLogin.data.get(i).get(0));
                       
            ItemName=((String) SqlLogin.data.get(i).get(1));
                       
            ItemPrice=(Float.valueOf((String) SqlLogin.data.get(i).get(2)));
            
            ItemQuantity=(Integer.parseInt((String) SqlLogin.data.get(i).get(3)));
               
            ItemCategory=((String) SqlLogin.data.get(i).get(4));
            
            ItemOfferAvailability=((Integer.valueOf((String) SqlLogin.data.get(i).get(5))==1));
            
            ItemImage=((String) SqlLogin.data.get(i).get(6));
            
            ItemDiscount=(Float.valueOf((String) SqlLogin.data.get(i).get(7)));
                
            Item item=new Item(ItemID, ItemName, ItemCategory, ItemImage, ItemPrice, ItemDiscount, ItemQuantity, ItemOfferAvailability);
            Inventory.addItem(item);
        }
        
    }
    
    //function retireves data from MYSQL and stores in singleton class OfferTable
    public static void updateOfferModel(){
        
        String offerID,offerDesc,offerCategory,offerImage;
        Date expiryDate,startDate;
        Integer minimumPurchase,onBuying,offerUsers;
        
        try {
            
            SqlLogin.getTable("select * from offertable");
        } catch (Exception ex) {
            //Logger.getLogger(OffersHomePageController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        for(int i=0;i<SqlLogin.data.size();++i){
            System.out.println(i);
            
            offerID=((String) SqlLogin.data.get(i).get(0));
                       
            offerDesc=((String) SqlLogin.data.get(i).get(1));
            try{     
                expiryDate=Date.valueOf(SqlLogin.data.get(i).get(2).toString());

                startDate=Date.valueOf(SqlLogin.data.get(i).get(8).toString());

            }
            catch(Exception e){
                expiryDate=startDate=null;
                System.out.println(e);
            }
            offerUsers=(Integer.parseInt((String) SqlLogin.data.get(i).get(3)));
               
            offerCategory=((String) SqlLogin.data.get(i).get(4));
            
            offerImage=((String) SqlLogin.data.get(i).get(5));
            
            minimumPurchase=Integer.parseInt(SqlLogin.data.get(i).get(6).toString());
            
            onBuying=Integer.parseInt(SqlLogin.data.get(i).get(7).toString());
            
            Offer offer=new Offer(offerID, offerDesc, offerCategory, offerImage, offerUsers, minimumPurchase, onBuying, expiryDate, startDate);
            OfferTable.addOffer(offer);
        }
    }
    
}
