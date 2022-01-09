package javafxapplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;  
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ConnectController implements Initializable {

     private Stage stage;
 private Scene scene;
 private Parent root;
    String idForEdit;
    
    
    @FXML
    private Pane pnlOverview;
    
    @FXML
    private VBox pnItems;
    
    @FXML
    private Button btnSignout;

    @FXML
    private Label totalStudent;
    
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnConnects;

    @FXML
    private Button btnEdit;

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    
    //change id down here to update specific user
    private static final String INSERT_QUERY = "SELECT * FROM alumni";

    public void getRecord(String id) throws SQLException {
        idForEdit=id;
        ResultSet rs;
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            
                       
            rs=preparedStatement.executeQuery();
            
            while (rs.next()) {              
                       
            }
            rs.close();                         
            preparedStatement.close(); 
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    
    @Override
    public void initialize(URL location, ResourceBundle resources){
   
                ResultSet rs;

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            
            rs=preparedStatement.executeQuery();
            
            int i=0;
            while (rs.next()) {              
                         try {

                 final int j = i;
                 

                FXMLLoader node=new FXMLLoader(getClass().getResource("ItemAlumni.fxml"));
                 
                
                node.getNamespace().put("name",(String) rs.getString(2)+" "+(String)rs.getString(3));
                node.getNamespace().put("collegeName", (String)rs.getString(5));
                node.getNamespace().put("linkedin",(String) rs.getString(6));
                node.getNamespace().put("department", (String)rs.getString(13));                

                 pnItems.getChildren().add(node.load());
                 i++;
             } catch (IOException e) {
             }
            }
            String s=Integer.toString(i);
            totalStudent.setText(s);
            rs.close();                         
            preparedStatement.close(); 
        } catch (SQLException e) {
            printSQLException(e);
        }

    }


    public void handleClicks(ActionEvent actionEvent) throws IOException{
                System.out.println(idForEdit);
        if (actionEvent.getSource() == btnOverview) {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Home.fxml"));
                root = loader.load();
                
               
//                ConnectController.setData(idForEdit);

                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
        
        if(actionEvent.getSource()==btnEdit)
        {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Edit_Details.fxml"));
                root = loader.load();
                
                Edit_detailsController Edit_detailsController = loader.getController();
                
                Edit_detailsController.setData(idForEdit);

                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
        if (actionEvent.getSource() == btnConnects) {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Connect.fxml"));
                root = loader.load();
                
                ConnectController ConnectController = loader.getController();
                
//                ConnectController.setData(idForEdit);

                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
        
        if (actionEvent.getSource() == btnSignout) {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
                root = loader.load();
                                
                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
    }
}
