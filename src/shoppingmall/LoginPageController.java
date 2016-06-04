/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingmall;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sandukuttan
 */
public class LoginPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label shopName;
   
    @FXML
    private PasswordField pswd;
    
    @FXML
    private Label verify;
    
    @FXML
    Button exit;
    
    @FXML
    protected void login(Event event) throws IOException{
        
        verify.setText("");
        if(Login.verifyLogin(pswd.getText())){
            System.out.println("login successful");
            
            Parent invPageParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene invPageScene = new Scene(invPageParent);
            Stage appStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            appStage.setScene(invPageScene);
            appStage.show();
       }

    }
    
    @FXML
    protected void closeProgram(ActionEvent event){
        exit();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        Login user=Login.getUser();
        shopName.setText(user.getShopName());
    }    
    
}
