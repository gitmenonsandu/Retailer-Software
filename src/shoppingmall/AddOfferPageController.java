/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import com.sun.webkit.dom.KeyboardEventImpl;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sandukuttan
 */
public class AddOfferPageController implements Initializable {

    
    @FXML
    private Button back;
    
    @FXML
    private Button uploadImage;
    
    @FXML
    private Button addOffer;
    
    @FXML
    private ComboBox category;
    
    @FXML
    private TextField desc;
    
    @FXML
    private ImageView offerImage;
    
    @FXML
    private TextField offerDir;
    
    private void goBack(ActionEvent event) throws IOException{
        Parent invPageParent = FXMLLoader.load(getClass().getResource("OfferPage.fxml"));
            Scene invPageScene = new Scene(invPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(invPageScene);
            appStage.show();
    }
    
    @FXML
    private void BackSpace(KeyEvent event) throws IOException{
        if(event.getCode().equals(KeyCode.BACK_SPACE)){
                Parent homePageParent = FXMLLoader.load(getClass().getResource("OfferPage.fxml"));
                Scene homePageScene = new Scene(homePageParent);
                Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
                appStage.setMaximized(true);
                appStage.setScene(homePageScene);
                appStage.show();
        }
    }
    
    
    private void uploadOfferImage(ActionEvent event){
        FileChooser fc = new FileChooser();
        File selectedFile  = fc.showOpenDialog(null);
        fc.setTitle("Choose Offer Image");
       
        fc.setInitialDirectory(new File(System.getProperty("user.dir")+"/src/shoppingmall/img"));
        
       if(selectedFile!=null){
           String dir=selectedFile.getName();
           offerDir.setText(dir);
           Image image=new Image(ShoppingMall.class.getResourceAsStream("img/"+dir));
           offerImage.setImage(image);
       }else{
           
       }
    }
    @FXML
    protected void handleButtonAction(ActionEvent event) throws IOException{
        if(event.getSource()==back)
            goBack(event);
        else if(event.getSource()==addOffer){
            Offer offer=new Offer();
           try{
               if(desc.getText().isEmpty() || category.getValue() == null){
                   if(desc.getText().isEmpty())
                        desc.setStyle("-fx-background-color:red;");
               
                    if(category.getValue() == null)
                         category.setStyle("-fx-background-color:red");
                           
                    throw null;
                }
                offer.setOfferDesc(desc.getText());
                offer.setOfferCategory((String) category.getValue());
                offer.setOfferUses(0);
                if(offerDir.getText().isEmpty())
                    offer.setImage("doofer");
                else
                    offer.setImage(offerDir.getText());
                
           }
           catch(Exception e){
               System.out.println(e);
               return;
           }
           OfferTable.addOffer(offer);
            try {
                SqlLogin.insertOffer(offer);
            } catch (SQLException ex) {
                Logger.getLogger(AddItemPageController.class.getName()).log(Level.SEVERE, null, ex);
                return ;
            }
            goBack(event);
            
        }
        else if(event.getSource()==uploadImage){
            uploadOfferImage(event);
        }
        
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            offerDir.setEditable(false);
            category.getItems().clear();
            category.getItems().addAll("Combo","Discount");
            category.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                    category.setStyle("-fx-background-color:gray");
                }
                
            });
            desc.addEventHandler(KeyEvent.ANY, new EventHandler<KeyEvent>(){
                @Override
                public void handle(KeyEvent event) {
                    desc.setStyle("-fx-background-color:white");
                }
                
            });
        }
        catch(Exception e){
            System.out.println(e);
        }
    }    
    
}
