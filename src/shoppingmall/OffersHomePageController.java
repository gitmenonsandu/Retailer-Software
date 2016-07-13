/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sandukuttan
 */
public class OffersHomePageController implements Initializable {

    @FXML
    private Button dashboard;
    @FXML
    private Button addNewOffer;
    @FXML
    private Button invoice;
    @FXML
    private Button ads;
    @FXML
    private Button offers;
    @FXML
    private Button inventory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String offerID,offerDesc,offerCategory,offerImage;
        Date expiryDate,startDate;
        Integer minimumPurchase,onBuying,offerUsers;
            
        try {
            
            SqlLogin.getTable("select * from offertable",null);
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

    @FXML
    private void handleTopBar(ActionEvent event) throws IOException {
        if(event.getSource().equals(dashboard)){
                Parent offerPageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene offerPageScene = new Scene(offerPageParent);
                Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                appStage.setMaximized(true);
                appStage.setScene(offerPageScene);
                appStage.show();
        }
        else if(event.getSource().equals(invoice)){
                Parent offerPageParent = FXMLLoader.load(getClass().getResource("BillingPage.fxml"));
                Scene offerPageScene = new Scene(offerPageParent);
                Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                appStage.setMaximized(true);
                appStage.setScene(offerPageScene);
                appStage.show();
        }
        else if(event.getSource().equals(inventory)){
                Parent offerPageParent = FXMLLoader.load(getClass().getResource("InventoryHomePage.fxml"));
                Scene offerPageScene = new Scene(offerPageParent);
                Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                appStage.setMaximized(true);
                appStage.setScene(offerPageScene);
                appStage.show();
        }
        else if(event.getSource().equals(offers)){
                Parent offerPageParent = FXMLLoader.load(getClass().getResource("OffersHomePage.fxml"));
                Scene offerPageScene = new Scene(offerPageParent);
                Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                appStage.setMaximized(true);
                appStage.setScene(offerPageScene);
                appStage.show();
        }
        else if(event.getSource().equals(ads)){
                Parent offerPageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene offerPageScene = new Scene(offerPageParent);
                Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                appStage.setMaximized(true);
                appStage.setScene(offerPageScene);
                appStage.show();
        }
    }
    
    void setAllOffers(){
        
    }

    @FXML
    private void goToAddNewOffer(ActionEvent event) throws IOException {
                Parent offerPageParent = FXMLLoader.load(getClass().getResource("AddNewOfferPage.fxml"));
                Scene offerPageScene = new Scene(offerPageParent);
                Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                appStage.setMaximized(true);
                appStage.setScene(offerPageScene);
                appStage.show();
    }
    
}
