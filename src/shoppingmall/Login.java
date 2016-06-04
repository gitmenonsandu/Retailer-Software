/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.sql.SQLException;


/**
 *
 * @author sandukuttan
 */

//Singelton set
public class Login {
    private static String shopID,shopPassword, shopName, shopPhone;
    static Integer attempts;
    
    static Login userLogin = new Login();
    
    private Login(){
        try{
           
            shopName=SqlLogin.getShopName();
            shopPassword=SqlLogin.getShopPassword();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static Login getUser(){
        return userLogin;
    }
    public String getShopPhone() {
        return shopPhone;
    }

    public String getShopName() {
        return shopName;
    }
    
    public void setShopPhone(String shopPhone) {
        Login.shopPhone = shopPhone;
    }
    
    public static boolean verifyLogin(String password){
        return Login.shopPassword.equals(password);
    }
    
    public void changePassword(){
        
    }
    
    public void forgotPassword(){
        
    }
}
