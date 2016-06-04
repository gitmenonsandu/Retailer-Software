/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;




/**
 *
 * @author sandukuttan
 */


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button getItems;
    
    @FXML
    private Button getOffers;
    
    @FXML
    private Button getAdds;
    
    @FXML
    private Button getReview;

    @FXML
    private TableView table;
    @FXML
    private Button getEvent;
    

    
    @FXML
    private void handleItems(ActionEvent event) throws SQLException{
       
      table.setEditable(true);
      
      if(!table.getColumns().isEmpty())
        table.getColumns().clear();
      SqlLogin.getTable("select * from ItemTable",table);      
          
      table.setItems(SqlLogin.data);
               
   }
      
    
    @FXML
    private void handleOffers(ActionEvent event) throws SQLException{
       
      table.setEditable(true);
      
      if(!table.getColumns().isEmpty())
        table.getColumns().clear();
      SqlLogin.getTable("select * from OfferTable",table);      
          
      table.setItems(SqlLogin.data);
               
   }
    @FXML
    private void handleAdv(ActionEvent event) throws SQLException{
       
      table.setEditable(true);
      
      if(!table.getColumns().isEmpty())
        table.getColumns().clear();
      SqlLogin.getTable("select * from AdTable",table);      
          
      table.setItems(SqlLogin.data);
               
   }
    
    @FXML 
     private void handleReview(ActionEvent even) throws SQLException{
        table.setEditable(true);
        if(!table.getColumns().isEmpty())
            table.getColumns().clear();
        SqlLogin.getTable("select * from ReviewTable",table);
        
        table.setItems(SqlLogin.data);
        
    }
     
         
    @FXML 
     private void handleEvent(ActionEvent even) throws SQLException{
        table.setEditable(true);
        if(!table.getColumns().isEmpty())
            table.getColumns().clear();
        SqlLogin.getTable("select * from EventTable",table);
        
        table.setItems(SqlLogin.data);
        
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
