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
public class Review {
    String reviewID, reviewContent, reviewUserID;
    Float reviewRating;

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getReviewUserID() {
        return reviewUserID;
    }

    public void setReviewUserID(String reviewUserID) {
        this.reviewUserID = reviewUserID;
    }

    public Float getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Float reviewRating) {
        this.reviewRating = reviewRating;
    }
    
    
    
}
