/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Window;


/**
 *
 * @author rodri
 */
public class OnBoardController implements Initializable {
     Scene sceneStart, sceneRegister, sceneLogin, sceneReset, sceneHome;
    
    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label label1;

    @FXML
    private Label label;

    
    @FXML
    private void handleLogin(ActionEvent event) {
        System.out.println("You clicked me!");
        label1.setText("Hello World!");
            Window owner = btnLogin.getScene().getWindow();        
                    if (txtEmail.getText().isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
              return;
            }
            if (txtPassword.getText().isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
              return;
            }

            String emailId = txtEmail.getText();
            String password = txtPassword.getText();
            LoginDao loginDao = new LoginDao();
            boolean flag;
            try {
              flag = loginDao.validate(emailId, password);
              if (!flag) {
                infoBox("Please enter correct Email and Password", null, "Failed");
              } else {
                infoBox("Login Successful!", null, "Passed");
                
              }
            } catch (SQLException ex) {
              Logger.getLogger(Alumni_LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
