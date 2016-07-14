/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sandukuttan
 */
public class AddItemPageController implements Initializable {

    
    @FXML
    private Button back;
    
    @FXML
    private Button addToInventory;
    
    @FXML
    private Button upload;
    
    @FXML
    private TextField name;
    
    @FXML
    private TextField price;
    
    @FXML
    private TextField category;
    
    @FXML
    private TextField quantity;
    
    @FXML
    private TextField discount;
    
    @FXML
    private TextField imageDir;
    
    @FXML
    private CheckBox isDiscount;
    
    @FXML
    private ImageView itemImage;
    
    
    private void goBack(ActionEvent event) throws IOException{
        Parent invPageParent = FXMLLoader.load(getClass().getResource("InventoryPage.fxml"));
            Scene invPageScene = new Scene(invPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(invPageScene);
            appStage.show();
    }

    private void uploadImage(ActionEvent event){
        FileChooser fc = new FileChooser();
        File selectedFile  = fc.showOpenDialog(null);
        fc.setTitle("Choose Item Image");
       
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        
       if(selectedFile!=null){
           String dir=selectedFile.getName();
           imageDir.setText(dir);
           Image image=new Image(ShoppingMall.class.getResourceAsStream("img/"+dir));
           itemImage.setImage(image);
       }else{
           
       }
    }
    
    @FXML
    protected void handleButtonAction(ActionEvent event) throws IOException{
        if(event.getSource()==back){
            goBack(event);
        }
        else if(event.getSource()==addToInventory){
           Item item=new Item();
           try{
                item.setItemName(name.getText());
                item.setItemCategory(category.getText());
                item.setItemQuantity(Integer.parseInt(quantity.getText()));
                item.setItemPrice(Float.parseFloat(price.getText()));
                if(discount.isEditable())
                     item.setItemDiscount(Float.parseFloat(discount.getText()));
                else
                    item.setItemDiscount(Float.parseFloat("0.0"));
                item.setItemImage(imageDir.getText());
                item.setItemOfferAvailability(false);
           }
           catch(Exception e){
               System.out.println(e);
               return;
           }
           Inventory.addItem(item);
            try {
                SqlLogin.insertItem(item,"local");
            } catch (SQLException ex) {
                Logger.getLogger(AddItemPageController.class.getName()).log(Level.SEVERE, null, ex);
                return ;
            }
            goBack(event);
           
        }
        else if(event.getSource()==isDiscount){

            if(isDiscount.isSelected())
                discount.setEditable(true);
            else{
                discount.clear();
                discount.setEditable(false);
            }
            
        }
        else if(event.getSource()==upload)
                uploadImage(event);
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imageDir.setEditable(false);
        discount.setEditable(false);
        isDiscount.setSelected(false);
        
    }    
    
}
