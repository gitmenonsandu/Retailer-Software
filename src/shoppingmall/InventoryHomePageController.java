/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class InventoryHomePageController implements Initializable {

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
    @FXML
    private Button prev;
    @FXML
    private Button next;
    @FXML
    private Button addCategory;
    @FXML
    private Button addFolder;
    @FXML
    private VBox vbox;

    /**
     * Initializes the controller class.
     */
    
    void setAllCategories() throws IOException{
        for(String category : Inventory.categories.values()){
            Pane newPane=(Pane)FXMLLoader.load(getClass().getResource("InventoryCategory.fxml"));
            Label name=(Label)newPane.getChildren().get(0);
            name.setText(category);
            vbox.getChildren().add(newPane);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        vbox.setSpacing(10);            
        try{
           setAllCategories();
        } catch (IOException ex) {
            Logger.getLogger(InventoryHomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
}
