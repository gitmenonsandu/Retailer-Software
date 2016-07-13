/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
