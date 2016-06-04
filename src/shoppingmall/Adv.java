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
public class Adv {
    private String advID, adImage, adDesc;
    private Date adExpiryDate;
    private Float adRating;
    private Integer adCost;

    public String getAdvID() {
        return advID;
    }

    public void setAdvID(String advID) {
        this.advID = advID;
    }

    public String getAdImage() {
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public String getAdDesc() {
        return adDesc;
    }

    public void setAdDesc(String adDesc) {
        this.adDesc = adDesc;
    }

    public Date getAdExpiryDate() {
        return adExpiryDate;
    }

    public void setAdExpiryDate(Date adExpiryDate) {
        this.adExpiryDate = adExpiryDate;
    }

    public Float getAdRating() {
        return adRating;
    }

    public void setAdRating(Float adRating) {
        this.adRating = adRating;
    }

    public Integer getAdCost() {
        return adCost;
    }

    public void setAdCost(Integer adCost) {
        this.adCost = adCost;
    }
    
    
    
}
