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

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sandukuttan
 */
public class OfferPageController implements Initializable {

    OfferTable offerTable=OfferTable.getOfferTable();
    
    @FXML
    private Button back;
    
    @FXML
    private Button add;
    
    @FXML
    private Label totalOffers;
    
    @FXML
    private Label shopName;
    
    @FXML
    private TilePane tile;
    
    @FXML
    protected void goBack(ActionEvent event) throws IOException{
            Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene homePageScene = new Scene(homePageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(homePageScene);
            appStage.show();
    }
    
    @FXML
    protected void BackSpace(KeyEvent event) throws IOException{
        if(event.getCode().equals(KeyCode.BACK_SPACE)){
                Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene homePageScene = new Scene(homePageParent);
                Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                appStage.setMaximized(true);
                appStage.setScene(homePageScene);
                appStage.show();
        }
    }
    @FXML
    protected void goToAdding(ActionEvent event) throws IOException{
            Parent offerPageParent = FXMLLoader.load(getClass().getResource("AddOfferPage.fxml"));
            Scene offerPageScene = new Scene(offerPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(offerPageScene);
            appStage.show();
        
    }
    
    void tileDisplay(ObservableList<ObservableList> data){
        ImageView page = new ImageView();
        
        String name;
        try{
        for(int i=0;i<data.size();++i){
            VBox offerTile=new VBox();
            Label offerCategory=new Label();
            Label offerDesc=new Label();
            Label offerUses=new Label();
            
            name="Category : ";
            name+=(String)data.get(i).get(4);
            offerCategory.setText(name);
            
            name="Description : ";
            name+=(String)data.get(i).get(1);
            offerDesc.setMaxWidth(300);
            offerDesc.setWrapText(true);
            offerDesc.setText(name);
            
            name="Uses : ";
            name+=(String) data.get(i).get(3);
            
            offerUses.setText(name);
            
            name=(String) data.get(i).get(5);
            
            if(name==null)
                name="doofer";
            //System.out.println(name);
            if(true){
                page= new ImageView(new Image(ShoppingMall.class.getResourceAsStream("img/offers/"+name)));
                page.setFitHeight(200);
                page.setFitWidth(300);
                page.setId((String) data.get(i).get(1));
                offerTile.getChildren().add(page);
                offerTile.getChildren().add(offerCategory);
                offerTile.getChildren().add(offerDesc);
                offerTile.getChildren().add(offerUses);
                offerTile.applyCss();
                tile.getChildren().add(offerTile);
                
            }
            }
            
        }
        catch(Exception e){
                System.out.println(e);
            }
        
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Login user=Login.getUser();
        
        shopName.setText(user.getShopName());
        String noOfOffers="Total Offers : ";
        String offerID,offerDesc,offerCategory,offerImage;
        Date expiryDate,startDate;
        Integer minimumPurchase,onBuying;
        tile.setVgap(10);
        tile.setHgap(10);
        Integer offerUsers;
        boolean ItemOfferAvailability;
        try {
            
            SqlLogin.getTable("select * from offertable",null);
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<SqlLogin.data.size();++i){
            
            offerID=((String) SqlLogin.data.get(i).get(0));
                       
            offerDesc=((String) SqlLogin.data.get(i).get(1));
                       
            expiryDate=Date.valueOf(SqlLogin.data.get(i).get(2).toString());
            
            offerUsers=(Integer.parseInt((String) SqlLogin.data.get(i).get(3)));
               
            offerCategory=((String) SqlLogin.data.get(i).get(4));
            
            offerImage=((String) SqlLogin.data.get(i).get(5));
            
            minimumPurchase=Integer.parseInt(SqlLogin.data.get(i).get(6).toString());
            
            onBuying=Integer.parseInt(SqlLogin.data.get(i).get(7).toString());
            
            startDate=Date.valueOf(SqlLogin.data.get(i).get(8).toString());
            
            Offer offer=new Offer(offerID, offerDesc, offerCategory, offerImage, offerUsers, minimumPurchase, onBuying, expiryDate, startDate);
            OfferTable.addOffer(offer);
        }
        //System.out.println(inventory.viewAllItems());
        noOfOffers+=(SqlLogin.data.size());
        totalOffers.setText(noOfOffers);
        tileDisplay(SqlLogin.data);
        // TODO

    }
        
    
}
