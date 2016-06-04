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
    private String offerID, offerDesc,offerCategory;
    private Integer offerUses;
    private Date offerExpiry;

    public Offer(String offerID, String offerDesc, String offerCategory, Integer offerUses) {
        this.offerID = offerID;
        this.offerDesc = offerDesc;
        this.offerCategory = offerCategory;
        this.offerUses = offerUses;
    }

    Offer() {
        
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
    
    
}
