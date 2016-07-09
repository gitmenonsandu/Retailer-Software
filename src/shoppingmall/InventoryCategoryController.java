/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class InventoryCategoryController implements Initializable {

    @FXML
    private Button addItem;
    @FXML
    private PieChart pieChart;
    @FXML
    private Label demand;
    @FXML
    private Label revenue;
    @FXML
    private Label profit;
    @FXML
    private ImageView im1;
    @FXML
    private ImageView im2;
    @FXML
    private ImageView im4;
    @FXML
    private ImageView im3;
    @FXML
    private Label categoryName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void handleAdd(ActionEvent event) {
        
    }
    
}
