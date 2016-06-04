/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sandukuttan
 */
public class ReviewTable {
    HashMap<String , Review> reviewList;
    
    public ArrayList<Review> getAllReviews(){
        ArrayList<Review> allReviews=null;
        
        for(Review review : reviewList.values())
            allReviews.add(review);
        
        return allReviews;
    }
    
}
