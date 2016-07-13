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
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import static shoppingmall.Inventory.inventory;

/**
 * FXML Controller class
 *
 * @author sandukuttan
 */
public class BillingPageController implements Initializable {
    
    @FXML
    private Label shopName;
    
    @FXML
    private Label lpoints;
    
    @FXML
    private Label subTotal;
    
    @FXML
    private Label saved;
    
    @FXML
    private Label total;
    
    @FXML
    private Button print;
    
    @FXML
    private TextField search;
    
    @FXML
    private TextField custName;
    
    @FXML
    private TextField custID;
    
    @FXML
    private TilePane tile;      //displays tiles of inventory items
    
    @FXML
    private HBox cart;          // displays tiles of selected items
    
    @FXML
    private Button back;
    
    @FXML
    private TableView table;    //displays selected items in a table
    
    @FXML
    private void goBack(ActionEvent event) throws IOException{
        Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene homePageScene = new Scene(homePageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setMaximized(true);
            appStage.setScene(homePageScene);
            appStage.show();
    }
    
    @FXML
    private void BackSpace(KeyEvent event) throws IOException{
        if(event.getCode().equals(KeyCode.BACK_SPACE)){
                Parent homePageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene homePageScene = new Scene(homePageParent);
                Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                appStage.setMaximized(true);
                appStage.setScene(homePageScene);
                appStage.show();
        }
    }
    
    //function to search item by name
    @FXML
    protected void displaySearch(){
        ArrayList<Item> result=null;
        String noOfProducts="Total Products : ";
        if(search.getText().isEmpty()){
            tile.getChildren().remove(0, tile.getChildren().size());
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
            tile.getChildren().remove(0, tile.getChildren().size());
            tileDisplay(data);
        }

        
    }
    private Float totalCost,subTotalCost;
    ObservableList<ObservableList> billData;    //2d observable list where the contents to be displayed on the table is stored
    HashMap<String,ObservableList> billItem;    //hashing itemID with with a row in the table to avoid duplicates and increase quantity
    HashMap<String,VBox> cartIndex;             //hashing itemID with VBox(tile) to avoid duplicates in the cart
    
    //function to display items in tiles
    public void tileDisplay(ObservableList<ObservableList> data){
        ImageView page = new ImageView();
        

        String name;
        
        try{
        for(int i=0;i<data.size();++i){
            VBox itemTile=new VBox();       //tile to display inventory items
            Label itemName=new Label();
            Label itemPrice=new Label();
            itemTile.setStyle("-fx-border-width:2px;-fx-border-color:black;-fx-padding:5");
   
            name=(String)data.get(i).get(2);    //retrieving item price
            itemPrice.setText(name);
            itemPrice.setScaleX(1.3);
            itemPrice.setScaleY(1.3);
   
            name=(String) data.get(i).get(1);
            itemName.setText(name);
            
            name=(String) data.get(i).get(6);   //retrieving item image name
            
            
            itemTile.setId((String) data.get(i).get(0));    //setting itemID as the tile ID to retrieve data on clicking the tile
            
            
            if(!name.equals("0")){      // condition to check if image is present or not
                page= new ImageView(new Image(ShoppingMall.class.getResourceAsStream("img/items/"+name)));
                page.setFitHeight(190);
                page.setFitWidth(190);
                
                itemTile.getChildren().add(page);
                itemTile.getChildren().add(itemName);
                itemTile.getChildren().add(itemPrice);
                
                itemTile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String itemID=itemTile.getId();
                        Item item = item=Inventory.getItem(itemID);
                        ObservableList<String> row=FXCollections.observableArrayList();
                        //Label quantity=new Label("x1");
                        //VBox cartItem=new VBox();
                        
                        
                        Integer removeQ = null; 
                        if(!billItem.containsKey(itemID)){
                            //cartItem.getChildren().add(itemTile.getChildren().get(0));
                            //cartItem.getChildren().add(quantity);
                            row.addAll(item.getItemID(),item.getItemName(),"1",(item.isItemOfferAvailability())?"Yes":"No",item.getItemPrice().toString(),"x");
                            
                            //cartIndex.put(itemID, cartItem);
                            billItem.put(itemID, row);
                            billData.add(row);
                            
                            cartTileDisplay(0, itemID, itemTile);
                         }
                         else{
                             
                             row=billItem.get(itemID);
                             billData.remove(row);
                             removeQ=Integer.parseInt((String) row.get(2))+1;
                             row.set(2,removeQ.toString() );
                             billData.add(row);
                             
                             
                             cartTileDisplay(removeQ, itemID, itemTile);
                         }
                     
                         
                         
                         //cart.getChildren().add(cartItem);

                         table.setItems(billData);
                         table.refresh();
                         tile.getChildren().removeAll(tile.getChildren());
                         tileDisplay(SqlLogin.data);
                         subTotalCost+=item.getItemPrice();
                         totalCost=subTotalCost*Float.parseFloat("0.87");
                         total.setText("Total : "+totalCost);
                         subTotal.setText("Rs "+subTotalCost+"\\-");
                         
                    }
                });
                //itemTile.getChildren().add(itemQuantity);
                tile.getChildren().add(itemTile);
                
            }
            
            }

            //tile.getChildren().retainAll(tile.getChildren());
        }
        catch(Exception e){
                System.out.println(e);
            }
     
    }
    
 
    public void cartTileDisplay(int quantity,String itemID,VBox itemTile){
        VBox cartItem=new VBox();
        Label num= new Label();
        if(quantity==0){
            cartItem.getChildren().add(itemTile.getChildren().get(0));
            num.setText("x1");
            cartItem.getChildren().add(num);
            cartIndex.put(itemID, cartItem);
            cart.getChildren().add(cartItem);
            
        }
        else if(quantity>0){
            
            cart.getChildren().remove(cartIndex.get(itemID));
            cartItem=cartIndex.get(itemID);
            cartItem.getChildren().remove(1);
            num.setText("x"+quantity);
            cartItem.getChildren().add(num);
            cartIndex.put(itemID,cartItem);
            cart.getChildren().add(cartItem);
        }
        else{
            cart.getChildren().remove(cartIndex.get(itemID));
            cartIndex.remove(itemID);
            billItem.remove(itemID);

        }
        
    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        totalCost=subTotalCost=Float.parseFloat("0");
        billItem = new HashMap<>();
        cartIndex = new HashMap<>();
        billData=FXCollections.observableArrayList();
        Login user=Login.getUser();
        table.setEditable(true);
        tile.setHgap(10);
        tile.setVgap(10);
        shopName.setText(user.getShopName());
        String noOfProducts="Total Products : ";
        String ItemID,ItemName,ItemCategory,ItemImage;
        Float ItemPrice,ItemDiscount;
        Integer ItemQuantity;
        boolean ItemOfferAvailability;
        try {
            
            SqlLogin.getTable("select * from itemtable",null);
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
            Inventory.addItem(item);
        }
        String colName = null;
        
        for(int i=0;i<6;++i){
                    final int j=i;
                    if(i==0)
                        colName="Item ID";
                    else if(i==1)
                        colName="Name";
                    else if(i==2)
                        colName="Quantity";
                    else if(i==3)
                        colName="Offer Availability";
                    else if(i==4)
                        colName="Price";
                    else
                        colName="Remove";
                    TableColumn col=new TableColumn(colName);

                    
                    if(i==3 || i==1)
                         col.setMinWidth(200);
                    col.setEditable(true);
                    col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {                                                                                              
                            return new SimpleStringProperty(param.getValue().get(j).toString());                        
                        }                    
                    });
                           
                    if(colName.equals("Remove")){
                       col.setCellFactory(new Callback<TableColumn<ObservableList,String>,TableCell<ObservableList,String>>(){
                           @Override
                           public TableCell<ObservableList, String> call(TableColumn<ObservableList, String> param) {
                               final TableCell<ObservableList,String> cell=new TableCell<>();
                              
                               cell.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                                   @Override
                                   public void handle(MouseEvent event) {
                                       
                                       try{
                                            ObservableList row = cell.getTableView().getItems().get(cell.getIndex());
                                            int ind= cell.getTableView().getItems().indexOf(row);
                                            String itemID = (String) row.get(0);
                                            Integer quantity=Integer.parseInt((String) row.get(2));
                                            Float cost=Float.parseFloat((String) row.get(4));
                                            subTotalCost-=cost;
                                            totalCost=subTotalCost*Float.parseFloat("0.87");
                                            total.setText("Total : "+totalCost);
                                            subTotal.setText("Rs "+subTotalCost+"\\-");
                                            if(quantity>1){
                                                quantity--;
                                                row.set(2, quantity.toString());
                                                cell.getTableView().getItems().set(ind, row);
                                               
                                                cartTileDisplay(quantity, itemID, null);
                                            }
                                            else{
                                                cell.getTableView().getItems().remove(row);
                                                
                                                cartTileDisplay(-1, itemID, null);
                                            }
                                       }
                                       catch(Exception e){
                                           System.out.println(e);
                                       }
                                    }
                                   
                               });
                               
                               cell.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>(){
                                   @Override
                                   public void handle(MouseEvent event) {
                                       try{
                                        if(!cell.getTableView().getItems().get(cell.getIndex()).isEmpty())
                                             cell.setText("x");
                                        else
                                            cell.setText("-");
                                   
                                       }
                                       catch(Exception e){
                                           System.out.println(e);
                                       }
                                    }
                                   
                               });
                               cell.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>(){
                                   @Override
                                   public void handle(MouseEvent event) {
                                       cell.setText("");
                                   }
                                   
                               });
                               return cell;
                           }
                           
                           
                       });
                                
                    }
                    
                    table.getColumns().addAll(col);
                }
        
    
        tileDisplay(SqlLogin.data);
        
        
    }    
    
}
