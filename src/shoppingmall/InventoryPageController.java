/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.smartcardio.Card;

/**
 * FXML Controller class
 *
 * @author sandukuttan
 */
public class InventoryPageController implements Initializable {

    @FXML
    private Label shopName;

    @FXML
    private TilePane tile;
   
    @FXML
    private TextField search;
    
    @FXML
    private Label totalProducts;
    
    @FXML
    private Button addItem;
    
    @FXML
    private Button back;
    
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
            Parent addPageParent = FXMLLoader.load(getClass().getResource("AddItemPage.fxml"));
            Scene addPageScene = new Scene(addPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(addPageScene);
            appStage.show();
        
    }
    @FXML
    protected void displaySearch(){
        ArrayList<Item> result=null;
        String noOfProducts="Total Products : ";
        if(search.getText().isEmpty()){
            tile.getChildren().remove(0, tile.getChildren().size());
               noOfProducts+=(SqlLogin.data.size());
        totalProducts.setText(noOfProducts);
            tileDisplay(SqlLogin.data);
        }
        else{
            
            
            ObservableList<ObservableList> data = FXCollections.observableArrayList();
            try{
                result= inventory.searchItem(search.getText());
                
                for (Item item : result) {
                    ObservableList o = FXCollections.observableArrayList();
                    o.addAll(item.getItemID(),item.getItemName(),item.getItemPrice().toString(),item.getItemQuantity().toString(),item.getItemCategory(),(item.isItemOfferAvailability())?"1":"0",item.getItemImage(),item.getItemDiscount().toString());
                    //System.out.println(o);

                    data.add(o);
                }
            }
            catch(Exception e){
                System.out.println(e);
            }
            noOfProducts+=(data.size());
            totalProducts.setText(noOfProducts);
            tile.getChildren().remove(0, tile.getChildren().size());
            tileDisplay(data);
        }

        
    }
    
    
    Inventory inventory = Inventory.getInventory();
    public void tileDisplay(ObservableList<ObservableList> data){
        ImageView page = new ImageView();
        
        String name;
        try{
        for(int i=0;i<data.size();++i){
            VBox itemTile=new VBox();
            Label itemName=new Label();
            Label itemPrice=new Label();
            Label itemQuantity=new Label();
            
            name=(String)data.get(i).get(2);
            itemPrice.setText(name);
            itemPrice.setScaleX(1.3);
            itemPrice.setScaleY(1.3);
            name=(String)data.get(i).get(3);
            itemQuantity.setText(name);
            name=(String) data.get(i).get(6);
            itemName.setText(name);
            if(!name.equals("0")){
                page= new ImageView(new Image(ShoppingMall.class.getResourceAsStream("img/"+name)));
                page.setFitHeight(200);
                page.setFitWidth(200);
                page.setId((String) data.get(i).get(1));
                itemTile.getChildren().add(page);
                itemTile.getChildren().add(itemName);
                itemTile.getChildren().add(itemPrice);
                itemTile.getChildren().add(itemQuantity);
                itemTile.applyCss();
                tile.getChildren().add(itemTile);
                
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
        String noOfProducts="Total Products : ";
        String ItemID,ItemName,ItemCategory,ItemImage;
        Float ItemPrice,ItemDiscount;
        Integer ItemQuantity;
        boolean ItemOfferAvailability;
        try {
            
            SqlLogin.getTable("select * from ItemTable",null);
        } catch (SQLException ex) {
            Logger.getLogger(InventoryPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<SqlLogin.data.size();++i){
            
            ItemID=((String) SqlLogin.data.get(i).get(0));
                       
            ItemName=((String) SqlLogin.data.get(i).get(1));
                       
            ItemPrice=(Float.valueOf((String) SqlLogin.data.get(i).get(2)));
            
            ItemQuantity=(Integer.parseInt((String) SqlLogin.data.get(i).get(3)));
               
            ItemCategory=((String) SqlLogin.data.get(i).get(4));
            
            ItemOfferAvailability=((Integer.valueOf((String) SqlLogin.data.get(i).get(5))==1));
            
            ItemImage=((String) SqlLogin.data.get(i).get(6));
            
            ItemDiscount=(Float.valueOf((String) SqlLogin.data.get(i).get(7)));
                
            Item item=new Item(ItemID, ItemName, ItemCategory, ItemImage, ItemPrice, ItemDiscount, ItemQuantity, ItemOfferAvailability);
            inventory.addItem(item);
        }
        //System.out.println(inventory.viewAllItems());
        noOfProducts+=(SqlLogin.data.size());
        totalProducts.setText(noOfProducts);
       tileDisplay(SqlLogin.data);
    }        
    
}
