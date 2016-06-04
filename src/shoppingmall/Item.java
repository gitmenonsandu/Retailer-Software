/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

/**
 *
 * @author sandukuttan
 */
public class Item {
    private String itemID, itemName, itemCategory, itemImage;
    private Float itemPrice, itemDiscount;
    private Integer itemQuantity;
    private boolean itemOfferAvailability;

    public Item(String itemID, String itemName, String itemCategory, String itemImage, Float itemPrice, Float itemDiscount, Integer itemQuantity, boolean itemOfferAvailability) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemImage = itemImage;
        this.itemPrice = itemPrice;
        this.itemDiscount = itemDiscount;
        this.itemQuantity = itemQuantity;
        this.itemOfferAvailability = itemOfferAvailability;
    }

    Item() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public Float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Float getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(Float itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    /**
     *
     * @return
     */
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public boolean isItemOfferAvailability() {
        return itemOfferAvailability;
    }

    public void setItemOfferAvailability(boolean itemOfferAvailability) {
        this.itemOfferAvailability = itemOfferAvailability;
    }
    
    
    
}
