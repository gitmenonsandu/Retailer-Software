/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sandukuttan
 */
public class AddNewOfferPageController implements Initializable {

    @FXML
    private Button upload;
    @FXML
    private ComboBox categories;
    @FXML
    private TextField picLocation;
    @FXML
    private TextArea offerDescription;
    @FXML
    private Button add;
    @FXML
    private Button clear;
    @FXML
    private DatePicker offerFrom;
    @FXML
    private DatePicker offerTill;
    @FXML
    private CheckBox isAll;
    @FXML
    private CheckBox isOlderThan;
    @FXML
    private TextField olderThan;
    @FXML
    private TextField addedLast;
    @FXML
    private CheckBox isAddedLast;
    @FXML
    private CheckBox isAddedBetween;
    @FXML
    private DatePicker addedFrom;
    @FXML
    private DatePicker addedTill;
    @FXML
    private CheckBox OnMinimumPurchase;
    @FXML
    private TextField minPurcahse;
    @FXML
    private TextField buyingXProducts;
    @FXML
    private CheckBox OnBuying;
    @FXML
    private TextField discountOff;
    @FXML
    private TextField getXProducts;
    @FXML
    private CheckBox isofferGetProducts;
    @FXML
    private ImageView offerImage;
    @FXML
    private Label offerDescMobileVIew;
    @FXML
    private ComboBox categoriesProduct;
    @FXML
    private CheckBox isofferDiscount;
    @FXML
    private Button dashboard;
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
        
        ObservableList<String> category=FXCollections.observableArrayList();
        for(String cat : Inventory.categories.values())
            category.add(cat);
        categories.setItems(category);
        categoriesProduct.setItems(category);
        offerDescMobileVIew.setText("");
        
        
        addedFrom.setDisable(true);
        addedTill.setDisable(true);
        
        
        
        
    }    

    @FXML
    private void handleUpload(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir")+"/src/shoppingmall/img/offers"));
        File selectedFile  = fc.showOpenDialog(null);
        fc.setTitle("Choose Offer Image");
       
        
        System.out.print(fc.getInitialDirectory().getPath());
        if(selectedFile!=null){
            picLocation.setText(selectedFile.getName());
            String dir=selectedFile.getName();
            offerImage.setImage(new Image(ShoppingMall.class.getResourceAsStream("img/offers/"+dir)));
            
            
        }
        
        
    }

    @FXML
    private void handleAdd(ActionEvent event){
        Offer offer=new Offer();
        offer.setOfferDesc(offerDescription.getText());
        offer.setOfferExpiry(Date.valueOf(offerTill.getValue()));
        offer.setOfferUses(0);
        offer.setOfferCategory(categories.getValue().toString());
        offer.setImage((picLocation.getText().isEmpty())?"doofer":picLocation.getText());
        offer.setMinimumPurchase(Integer.parseInt((minPurcahse.getText().isEmpty())?"-1":minPurcahse.getText()));
        offer.setOnBuying(Integer.parseInt((buyingXProducts.getText().isEmpty())?"-1":buyingXProducts.getText()));
        
        try{
            SqlLogin.insertOffer(offer);
            Parent offerPageParent = FXMLLoader.load(getClass().getResource("OffersHomePage.fxml"));
            Scene offerPageScene = new Scene(offerPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setMaximized(true);
            appStage.setScene(offerPageScene);
            appStage.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void handleClear(ActionEvent event) {
        offerDescMobileVIew.setText(null);
        offerDescription.clear();
        offerFrom.getEditor().clear();
        offerTill.getEditor().clear();
        offerImage.setImage(null);
        picLocation.clear();
        
        isofferDiscount.setSelected(false);
        isAddedBetween.setSelected(false);
        isAddedLast.setSelected(false);
        isAll.setSelected(false);
        isOlderThan.setSelected(false);
        isofferGetProducts.setSelected(false);
        OnBuying.setSelected(false);
        OnMinimumPurchase.setSelected(false);
        
        discountOff.clear();
        discountOff.setEditable(false);
        getXProducts.clear();
        getXProducts.setEditable(false);
        categories.getEditor().clear();
        minPurcahse.clear();
        minPurcahse.setEditable(false);
        buyingXProducts.clear();
        buyingXProducts.setEditable(false);
        olderThan.clear();
        olderThan.setEditable(false);
        addedLast.clear();
        addedLast.setEditable(false);
        addedFrom.getEditor().clear();
        addedTill.getEditor().clear();
        
        categories.setValue(null);
        categoriesProduct.setValue(null);
        
    }

    @FXML
    private void handleCheckBox(ActionEvent event) {
        if(event.getSource().equals(isofferDiscount)){
            if(isofferDiscount.isSelected())
                discountOff.setEditable(true);
            else if(!isofferDiscount.isSelected()){
                discountOff.setEditable(false);
                categories.setValue(null);
            }
        }
        else if(event.getSource().equals(isofferGetProducts)){
            if(isofferGetProducts.isSelected())
                getXProducts.setEditable(true);
            else if(!isofferGetProducts.isSelected())
                getXProducts.setEditable(false);
        }
        else if(event.getSource().equals(OnMinimumPurchase)){
            if(OnMinimumPurchase.isSelected())
                minPurcahse.setEditable(true);
            else if(!OnMinimumPurchase.isSelected())
                minPurcahse.setEditable(false);
        }
        else if(event.getSource().equals(OnBuying)){
            if(OnBuying.isSelected())
                buyingXProducts.setEditable(true);
            else if(!OnBuying.isSelected())
                buyingXProducts.setEditable(false);
        }
        else if(event.getSource().equals(isOlderThan)){
            if(isOlderThan.isSelected())
                olderThan.setEditable(true);
            else if(!isOlderThan.isSelected())
                olderThan.setEditable(false);
        }
        else if(event.getSource().equals(isAddedLast)){
            if(isAddedLast.isSelected())
                addedLast.setEditable(true);
            else if(!isAddedLast.isSelected())
                addedLast.setEditable(false);
        }
        else if(event.getSource().equals(isAddedBetween)){
            if(isAddedBetween.isSelected()){
                addedFrom.setDisable(false);
                addedTill.setDisable(false);
            }
            else if(!isAddedBetween.isSelected()){
                addedFrom.setDisable(true);
                addedTill.setDisable(true);
            }
        }
    }

    @FXML
    private void updateMobileView(KeyEvent event) {
        offerDescMobileVIew.setText(offerDescription.getText());
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
    
}
