/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.sql.Date;

/**
 *
 * @author sandukuttan
 */
public class Offer {
    private String offerID, offerDesc,offerCategory,image;
    private Integer offerUses,minimumPurchase,onBuying;
    private Date offerExpiry,startDate;

    public Offer(String offerID, String offerDesc, String offerCategory, String image, Integer offerUses, Integer minimumPurchase, Integer onBuying, Date offerExpiry, Date startDate) {
        this.offerID = offerID;
        this.offerDesc = offerDesc;
        this.offerCategory = offerCategory;
        this.image = image;
        this.offerUses = offerUses;
        this.minimumPurchase = minimumPurchase;
        this.onBuying = onBuying;
        this.offerExpiry = offerExpiry;
        this.startDate = startDate;
    }


    Offer() {
        
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOfferCategory() {
        return offerCategory;
    }

    public void setOfferCategory(String offerCategory) {
        this.offerCategory = offerCategory;
    }
    
    
    public String getOfferID() {
        return offerID;
    }

    public void setOfferID(String offerID) {
        this.offerID = offerID;
    }

    public String getOfferDesc() {
        return offerDesc;
    }

    public void setOfferDesc(String offerDesc) {
        this.offerDesc = offerDesc;
    }

    public Integer getOfferUses() {
        return offerUses;
    }

    public void setOfferUses(Integer offerUses) {
        this.offerUses = offerUses;
    }

    public Date getOfferExpiry() {
        return offerExpiry;
    }

    public void setOfferExpiry(Date offerExpiry) {
        this.offerExpiry = offerExpiry;
    }
    
        public Integer getMinimumPurchase() {
        return minimumPurchase;
    }

    public void setMinimumPurchase(Integer minimumPurchase) {
        this.minimumPurchase = minimumPurchase;
    }

    public Integer getOnBuying() {
        return onBuying;
    }

    public void setOnBuying(Integer onBuying) {
        this.onBuying = onBuying;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    
}
