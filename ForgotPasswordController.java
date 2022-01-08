/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 *
 * @author rodri
 */
public class ForgotPasswordController  implements Initializable {
     Scene sceneStart, sceneRegister, sceneLogin, sceneReset, sceneHome;
     private Stage stage;
 private Scene scene;
 private Parent root;
    @FXML
    private TextField email;

    @FXML
    private PasswordField newpassword;

    @FXML
    private Button btnForgetPassword;

    @FXML
    private Label lblErrors;

    @FXML
    private TextField dob;
    
    @FXML
    private void goBack(ActionEvent eventsingin) throws IOException{
               FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
                root = loader.load();
                                
                stage = (Stage)((Node)eventsingin.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
    }

    
    @FXML
    private void handleForgetPassword(ActionEvent event)throws IOException {
        System.out.println("34");
        Window owner = btnForgetPassword.getScene().getWindow();        
            if (email.getText().isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
              return;
            }
            if (newpassword.getText().isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a new password");
              return;
            }

            String emailCon = email.getText();
            String newpasswordCon = newpassword.getText();
            
            System.out.println(emailCon+" "+newpasswordCon);
            
            LoginDao loginDao = new LoginDao();
            
            String id;
            String zero= new String("0");
            try {
              id = loginDao.getId(emailCon);
              if (id.equals(zero)) {
                infoBox("Please enter correct Email", null, "Failed");
              } else {
                System.out.println(id);
                infoBox("Login Successful!", null, "Passed");

                       UpdateDao updateDao = new UpdateDao();
                        try {
                         updateDao.updatePassword(emailCon, newpasswordCon, id);
                        } catch (SQLException ex) {
                        Logger.getLogger(Edit_detailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  
                  showAlert(Alert.AlertType.CONFIRMATION, owner, "Update Successful!",
                    " Updated");

                
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Home.fxml"));
                root = loader.load();
                

                HomeController homeController = loader.getController();
                
                homeController.getRecord(id);
                
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                              }
            } catch (SQLException ex) {
              Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(owner);
    alert.show();
  }

  public static void infoBox(String infoMessage, String headerText, String title) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setContentText(infoMessage);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.showAndWait();
  }
    
}
