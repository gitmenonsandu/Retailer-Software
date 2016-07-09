/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;

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
            PieChart pieChart=(PieChart) newPane.getChildren().get(3);
            Label demand=(Label) newPane.getChildren().get(7);
            Label revenue=(Label) newPane.getChildren().get(8);
            Label profit=(Label) newPane.getChildren().get(9);
            
            ImageView[] im = new ImageView[4];
            im[0]=(ImageView) newPane.getChildren().get(11);
            im[1]=(ImageView) newPane.getChildren().get(12);
            im[2]=(ImageView) newPane.getChildren().get(13);
            im[3]=(ImageView) newPane.getChildren().get(14);
            
            int cnt=0;
            for(Item item : Inventory.itemList.values()){
                //
                if(item.getItemCategory().toLowerCase().contains(category.toLowerCase())){
                    try{
                    System.out.println(item.getItemImage());    
                    im[cnt].setImage(new Image(ShoppingMall.class.getResourceAsStream("img/"+item.getItemImage())));
                    cnt++;
                    
                    if(cnt>3)
                        break;
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                }
            }
            
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
