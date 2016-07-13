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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goToDashBoard(ActionEvent event) throws IOException {
        Parent invPageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene invPageScene = new Scene(invPageParent);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(invPageScene);
        appStage.show();
    }

    @FXML
    private void goToAddNewOffer(ActionEvent event) throws IOException {
        Parent invPageParent = FXMLLoader.load(getClass().getResource("AddNewOfferPage.fxml"));
        Scene invPageScene = new Scene(invPageParent);
        Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(invPageScene);
        appStage.show();
    }
    
    void setAllOffers(){
        
    }
    
}
