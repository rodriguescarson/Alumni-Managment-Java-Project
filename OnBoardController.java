/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 *
 * @author rodri
 */
public class OnBoardController implements Initializable {
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
  private ComboBox < String > gender;

  @FXML
  private TextField prno;

  @FXML
  private TextField collegeName;

  @FXML
  private TextField yearOfPassingOut;

  @FXML
  private TextField workingPlace;

  @FXML
  private TextField currentPosition;

  @FXML
  private TextField linkedin;

  @FXML
  private PasswordField password;

  @FXML
  private void handleSave(ActionEvent event) {
      
    Window owner = btnSave.getScene().getWindow();
    
    if (email.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter your email");
      return;
    }
    if (fname.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter your first name");
      return;
    }
    if (lname.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter a last name");
      return;
    }
    if (password.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter a Password");
      return;
    }
    if (prno.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter a prno");
      return;
    }
    if (collegeName.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter a college name");
      return;
    }
    if (workingPlace.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter a working place");
      return;
    }
    if (currentPosition.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter a current position");
      return;
    }
    if (yearOfPassingOut.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter a year of passing out");
      return;
    }
    if (linkedin.getText().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter a linedin");
      return;
    }
    if (dob.getValue() == null) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please enter date of birth");
      return;
    }
    if (gender.getItems().isEmpty()) {
      showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
        "Please select gender");
      return;
    }
    
    String emailCon = email.getText();
    String fnameCon = fname.getText();
    String lnameCon = lname.getText();
    String passwordCon = password.getText();
    String prnoCon = prno.getText();
    String collegeNameCon = collegeName.getText();
    String workingPlaceCon = workingPlace.getText();
    String currentPositionCon = currentPosition.getText();
    String yearOfPassingOutCon = yearOfPassingOut.getText();
    String linkedinCon = linkedin.getText();
    String dobCon = dob.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String genderCon = gender.getValue();
    
    RegisterDao registerDao = new RegisterDao();
    try {
      registerDao.insertRecord(emailCon,fnameCon,lnameCon,passwordCon,prnoCon,collegeNameCon,workingPlaceCon,currentPositionCon,yearOfPassingOutCon,linkedinCon,dobCon,genderCon);
    } catch (SQLException ex) {
      Logger.getLogger(OnBoardController.class.getName()).log(Level.SEVERE, null, ex);
    }

    showAlert(Alert.AlertType.CONFIRMATION, owner, "Created Successful!",
      " Welcome " + fname.getText());
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    // TODO
    gender.getItems().removeAll(gender.getItems());
    gender.getItems().addAll(" ","Male", "Female");
    gender.getSelectionModel().select(" ");
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