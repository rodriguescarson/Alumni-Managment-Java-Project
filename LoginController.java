/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;
import java.io.IOException;

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
import javafx.scene.Node;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author rodri
 */
public class LoginController implements Initializable {
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
    private Button btnSignup;

    @FXML
    private Label lblErrors;

    @FXML
    private Button btnForgetPassword;

    @FXML
    void handleForgetPassword(ActionEvent event) {

    }

    @FXML
    void handleSignUp(ActionEvent event) {

    }

    @FXML
    private void handleSignIn(ActionEvent event)throws IOException {
            Window owner = btnSignin.getScene().getWindow();        
            if (email.getText().isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
              return;
            }
            if (password.getText().isEmpty()) {
              showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
              return;
            }

            String emailCon = email.getText();
            String passwordCon = password.getText();
            LoginDao loginDao = new LoginDao();
            
            String flag;
            String zero= new String("0");
            try {
              flag = loginDao.validate(emailCon, passwordCon);
              if (!flag.equals(zero)) {
                infoBox("Please enter correct Email and Password", null, "Failed");
              } else {
                System.out.println(flag);
                infoBox("Login Successful!", null, "Passed");
                
                root = FXMLLoader.load(getClass().getResource("Home.fxml"));
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
