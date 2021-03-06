package javafxapplication;
import java.sql.SQLException;
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
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class HomeController implements Initializable {

     private Stage stage;
 private Scene scene;
 private Parent root;
    
    @FXML
    private ImageView imgUrl;

    @FXML
    private Label name;
    
    @FXML
    private Label avtarName;
        
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
    private Label prno;

    @FXML
    private Label yearOfPassingOut;

    @FXML
    private Label department;

    @FXML
    private Label cgpa;

    @FXML
    private VBox pnItems;

    @FXML
    private Label email;

    @FXML
    private Label workingPlace;

    @FXML
    private Label currentPosition;

    @FXML
    private Label linkedin;

    @FXML
    private Label collegeName;

    @FXML
    private Label dob;
    
    @FXML
    private Label gender;
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    
    //change id down here to update specific user
    private static final String INSERT_QUERY = "SELECT * FROM alumni where id=?";
    String idForEdit;
    
    public void getRecord(String id) throws SQLException {
        ResultSet rs;
        idForEdit=id;

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, id);
            rs=preparedStatement.executeQuery();
            while (rs.next()) {              
            
            name.setText( rs.getString(2)+" "+rs.getString(3));      
            prno.setText( rs.getString(4));                  
            
            collegeName.setText((String) rs.getString(5));                          
        linkedin.setText( rs.getString(6));                          
            email.setText( rs.getString(7));                                                         
         dob.setText( rs.getString(9));                          
          gender.setText( rs.getString(10));                          
           workingPlace.setText( rs.getString(11));                          
           currentPosition.setText( rs.getString(12));                          
            department.setText( rs.getString(13));                          
            cgpa.setText( rs.getString(14));                          
          yearOfPassingOut.setText( rs.getString(15));                          
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
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void handleClicks(ActionEvent actionEvent) throws IOException,SQLException{
                System.out.println(idForEdit);
        if (actionEvent.getSource() == btnOverview) {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("Home.fxml"));
                root = loader.load();
                 HomeController homeController = loader.getController();
                
                homeController.getRecord(idForEdit);
                
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
        
        if (actionEvent.getSource() == btnSignOut) {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("Login.fxml"));
                root = loader.load();
                                
                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
    }
}
