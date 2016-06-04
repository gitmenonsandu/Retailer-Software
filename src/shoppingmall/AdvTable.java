/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.util.HashMap;

/**
 *
 * @author sandukuttan
 */
public class AdvTable {
    HashMap<String , Adv> advList;
    
    public void addAdv(Adv adv){
        advList.put(adv.getAdvID(),adv);
    }
    
    public Adv removeAdv(String advID){
        Adv removedAdv = advList.remove(advID);
        return removedAdv;
    }
    
}
