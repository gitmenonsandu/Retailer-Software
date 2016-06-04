/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static shoppingmall.SqlLogin.data;

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
            //System.out.println(name);
            if(true){
                page= new ImageView(new Image(ShoppingMall.class.getResourceAsStream("img/doofer")));
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
        String offerID,offerDesc,offerCategory;
        tile.setVgap(10);
        tile.setHgap(10);
        Integer offerUsers;
        boolean ItemOfferAvailability;
        try {
            
            SqlLogin.getTable("select * from OfferTable",null);
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<SqlLogin.data.size();++i){
            
            offerID=((String) SqlLogin.data.get(i).get(0));
                       
            offerDesc=((String) SqlLogin.data.get(i).get(1));
                       
            
            
            offerUsers=(Integer.parseInt((String) SqlLogin.data.get(i).get(3)));
               
            offerCategory=((String) SqlLogin.data.get(i).get(4));
            
                
            Offer offer=new Offer(offerID, offerDesc, offerCategory, offerUsers);
            OfferTable.addOffer(offer);
        }
        //System.out.println(inventory.viewAllItems());
        noOfOffers+=(SqlLogin.data.size());
        totalOffers.setText(noOfOffers);
        tileDisplay(SqlLogin.data);
        // TODO

    }
        
    
}
