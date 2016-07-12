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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sandukuttan
 */
public class HomePageController implements Initializable {

    @FXML
    Label shopName;
    
    @FXML
    TabPane tabpane;
    
    @FXML
    Tab tab1;
    
    @FXML
    Tab tab2;
   
    @FXML
    Tab tab3;
    
    @FXML
    LineChart<String,Integer> offerIDvsUses;
    
    @FXML
    Button billing;
    
    @FXML
    Button offers;
    
    @FXML
    Button inventory;
    

    
    @FXML
    Button logout;
    
    @FXML
    Button adv;
    
    @FXML
    protected void handleKeyPress(KeyEvent event) throws IOException{
        switch (event.getCode()) {
            case B:
                {
                    Parent billPageParent = FXMLLoader.load(getClass().getResource("BillingPage.fxml"));
                    Scene billPageScene = new Scene(billPageParent);
                    Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(billPageScene);
                    appStage.show();
                    break;
                }
            case I:
                {
                    Parent invPageParent = FXMLLoader.load(getClass().getResource("InventoryHomePage.fxml"));
                    Scene invPageScene = new Scene(invPageParent);
                    Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(invPageScene);
                    appStage.show();
                    break;
                }
            case O:
                {
                    Parent offerPageParent = FXMLLoader.load(getClass().getResource("OfferPage.fxml"));
                    Scene offerPageScene = new Scene(offerPageParent);
                    Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                    appStage.setScene(offerPageScene);
                    appStage.show();
                    break;
                }
            case A:
                System.out.println("Go to adv page");
                break;
            default:
                break;
        }
        
    }
    
    @FXML
    protected void handleButtonAction(ActionEvent event) throws IOException{
        if(event.getSource()==billing){
            Parent billPageParent = FXMLLoader.load(getClass().getResource("BillingPage.fxml"));
            Scene billPageScene = new Scene(billPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(billPageScene);
            appStage.show();
        }
        else if(event.getSource()==offers){
            Parent offerPageParent = FXMLLoader.load(getClass().getResource("OfferPage.fxml"));
            Scene offerPageScene = new Scene(offerPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(offerPageScene);
            appStage.show();
                    
                    }
        else if(event.getSource()==inventory){
            Parent invPageParent = FXMLLoader.load(getClass().getResource("InventoryHomePage.fxml"));
            Scene invPageScene = new Scene(invPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(invPageScene);
            appStage.show();
            
            
            
        }
        else if(event.getSource()==adv)
            System.out.println("Go to advertisement page");
        else 
            System.out.println("Invalide Button");
    }
    
    @FXML
    protected void handleDrop(ActionEvent event) throws IOException{
        if(event.getSource()==logout){
            Parent loginPageParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Scene loginPageScene = new Scene(loginPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(loginPageScene);
            appStage.show();
        }
        else
            System.out.println("Invalid click");
        
    }
    
    @FXML
    protected void handleTabs(Event event) throws SQLException{
        try{
            if(tab2.isSelected()){

                SqlLogin.getTable("select offerID,offerUsers from OfferTable", null);
                offerIDvsUses.getXAxis().setAutoRanging(true);
                offerIDvsUses.getYAxis().setAutoRanging(true);
                offerIDvsUses.getXAxis().setAnimated(true);
                offerIDvsUses.getYAxis().setAnimated(true);
                offerIDvsUses.getXAxis().setLabel("OfferID");
                offerIDvsUses.getYAxis().setLabel("Uses");
                String offerID;
                Integer offerUses;
                XYChart.Series series = new XYChart.Series<>();
                for(int i=0;i<SqlLogin.data.size();++i){
                    offerID=(String) SqlLogin.data.get(i).get(0);
                    offerUses=Integer.parseInt((String) SqlLogin.data.get(i).get(1));

                    series.getData().add(new XYChart.Data<>(offerID,offerUses));
                    series.getData().add(new XYChart.Data(offerID,offerUses));
                }
                offerIDvsUses.getData().add(series);

            }
            else if(tab1.isSelected()){
                
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Login user=Login.getUser();
        shopName.setText(user.getShopName());
        
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
    }    
    
    
    
}
