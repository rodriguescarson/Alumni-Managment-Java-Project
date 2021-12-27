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
public class Edit_detailsController implements Initializable {
     Scene sceneStart, sceneRegister, sceneLogin, sceneReset, sceneHome;
    
     @FXML
     private TextField fname;
 
     @FXML
     private TextField lname;
 
     @FXML
     private TextField email;
 
     @FXML
     private DatePicker dob;
 
     @FXML
     private Button btnSave;
 
     @FXML
     private ComboBox<?> gender;
 
     @FXML
     private TextField prno;
 
     @FXML
     private TextField college;
 
     @FXML
     private TextField yearOfPassingOut;
 
     @FXML
     private TextField workingplace;
 
     @FXML
     private TextField currentPosition;
 
     @FXML
     private TextField linkedin;
 
     @FXML
     private PasswordField password;
 
     @FXML
     private TextField cgpa;
 
     @FXML
     private TextField department;

    
    @FXML
    private void saveData(ActionEvent event) {
                    Window owner = btnSave.getScene().getWindow();
                  if (fname.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                      "Please enter your first name");
                    return;
                  }
                  if (email.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                      "Please enter your email");
                    return;
                  }
                  if (lname.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                      "Please enter a lastName");
                    return;
                  }
      
                  String fnameCon = fname.getText();
                  String emailCon = email.getText();
                  String lnameCon = lname.getText();
                  
                  UpdateDao updateDao = new UpdateDao();
                  try {
                    updateDao.updateRecord(fnameCon, emailCon, lnameCon);
                  } catch (SQLException ex) {
                    Logger.getLogger(Edit_detailsController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
                  showAlert(Alert.AlertType.CONFIRMATION, owner, "Update Successful!",
                    " Updated" + fname.getText());
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
