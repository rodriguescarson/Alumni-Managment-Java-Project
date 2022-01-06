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
    private PasswordField password;

    @FXML
    private Button btnSignin;

    @FXML
    private Label lblErrors;

    @FXML
    private void handleSignIn(ActionEvent event)throws IOException {
            Window owner = btnSignin.getScene().getWindow();        
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

            if (dob.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                  "Please enter a date of birth");
                return;
              }

            String emailCon = email.getText();
            String newpasswordCon = neewpassword.getText();
            String dobCon = dob.getText();
            LoginDao AdminLoginDao = new LoginDao();
            
            String id;
            String zero= new String("0");
            try {
              id = AdminLoginDao.validate(emailCon, dobCon);
              if (id.equals(zero)) {
                infoBox("Please enter correct Email and Date Of Birth", null, "Failed");
              } else {
                System.out.println(id);
                infoBox("Login Successful!", null, "Passed");

                       UpdateDao updateDao = new UpdateDao();
                        try {
                         updateDao.updateRecord(emailCon, newpasswordCon, dobCon, id);
                        } catch (SQLException ex) {
                        Logger.getLogger(Edit_detailsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                  
                  showAlert(Alert.AlertType.CONFIRMATION, owner, "Update Successful!",
                    " Updated" + fname.getText());

                
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
