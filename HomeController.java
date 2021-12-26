package javafxapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private ImageView imgUrl;

    @FXML
    private Label name;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnConnects;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnSignOut;

    @FXML
    private Pane pnlConnects;

    @FXML
    private Pane pnlEdit;

    @FXML
    private Pane pnlSignOut;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Label Prno;

    @FXML
    private Label yearOfPassingOut;

    @FXML
    private Label Department;

    @FXML
    private Label Cgpa;

    @FXML
    private VBox pnItems;

    @FXML
    private Label email;

    @FXML
    private Label working;

    @FXML
    private Label currentPosition;

    @FXML
    private Label linkedin;

    @FXML
    private Label college;

    @FXML
    private Label gender;

    @FXML
    private Label workinPlace;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Node[] nodes = new Node[10];
//         for (int i = 0; i < nodes.length; i++) {
//             try {
//
//                 final int j = i;
//                 nodes[i] = FXMLLoader.load(getClass().getResource("Item.fxml"));
//
//                 //give the items some effect
//
//                 nodes[i].setOnMouseEntered(event -> {
//                     nodes[j].setStyle("-fx-background-color : #0A0E3F");
//                 });
//                 nodes[i].setOnMouseExited(event -> {
//                     nodes[j].setStyle("-fx-background-color : #02030A");
//                 });
//                 pnItems.getChildren().add(nodes[i]);
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
    }


    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #02030A");
            pnlOverview.toFront();
        }
        
        if(actionEvent.getSource()==btnEdit)
        {
            pnlEdit.setStyle("-fx-background-color : #464F67");
            pnlEdit.toFront();
        }
        if (actionEvent.getSource() == btnConnects) {
            pnlConnects.setStyle("-fx-background-color : #1620A1");
            pnlConnects.toFront();
        }
        
        if (actionEvent.getSource() == btnSignOut) {
            pnlSignOut.setStyle("-fx-background-color : #53639F");
            pnlSignOut.toFront();
        }

    }
}
