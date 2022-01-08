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
import static javafxapplication.ItemController.printSQLException;


public class AdminHomeController implements Initializable {

     private Stage stage;
 private Scene scene;
 private Parent root;
    
    @FXML
    private VBox pnItems;
    
    @FXML
    private Button btnSignout;

    @FXML
    private Label totalStudent;
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    
    //change id down here to update specific user
    private static final String INSERT_QUERY = "SELECT * FROM alumni";

    public void getRecord(String id) throws SQLException {
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
    //pass a string down , id of the loggedIn person

                ResultSet rs;

        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            
            rs=preparedStatement.executeQuery();
            
            int i=0;
            while (rs.next()) {              
                         try {

                 final int j = i;
                 

                 FXMLLoader node=new FXMLLoader(getClass().getResource("Item.fxml"));
                 
//                 getRecord(id);                 
                
                node.getNamespace().put("name",(String) rs.getString(2)+" "+(String)rs.getString(3));
                node.getNamespace().put("prno", (String)rs.getString(4));                 
                node.getNamespace().put("collegeName", (String)rs.getString(5));
                node.getNamespace().put("linkedin",(String) rs.getString(6));
                node.getNamespace().put("email",(String) rs.getString(7));
                node.getNamespace().put("dob",(String) rs.getString(9));
                node.getNamespace().put("gender", (String)rs.getString(10));
                node.getNamespace().put("workingPlace",(String) rs.getString(11));
                node.getNamespace().put("currentPosition", (String)rs.getString(12));
                node.getNamespace().put("department", (String)rs.getString(13));
                node.getNamespace().put("cgpa", (String)rs.getString(14));
                node.getNamespace().put("yearOfPassingOut", (String)rs.getString(15));
                

                 //            name.setText( rs.getString(2)+" "+rs.getString(3));      
//            prno.setText( rs.getString(4));                  
//            
//            collegeName.setText((String) rs.getString(5));                          
//            linkedin.setText( rs.getString(6));                          
//            email.setText( rs.getString(7));                                                         
//            dob.setText( rs.getString(9));                          
//            gender.setText( rs.getString(10));                          
//            workingPlace.setText( rs.getString(11));                          
//            currentPosition.setText( rs.getString(12));                          
//            department.setText( rs.getString(13));                          
//            cgpa.setText( rs.getString(14));                          
//            yearOfPassingOut.setText( rs.getString(15));                          

                 
//               try{  
//                 controller.getRecord(id);
//               }catch(SQLException e ){
//               
//               }
               //give the items some effect

                 pnItems.getChildren().add(node.load());
                 i++;
             } catch (IOException e) {
             }
//            name.setText( rs.getString(2)+" "+rs.getString(3));      
//            prno.setText( rs.getString(4));                  
//            collegeName.setText((String) rs.getString(5));                          
//            linkedin.setText( rs.getString(6));                          
//            email.setText( rs.getString(7));                                                         
//            dob.setText( rs.getString(9));                          
//            gender.setText( rs.getString(10));                          
//            workingPlace.setText( rs.getString(11));                          
//            currentPosition.setText( rs.getString(12));                          
//            department.setText( rs.getString(13));                          
//            cgpa.setText( rs.getString(14));                          
//            yearOfPassingOut.setText( rs.getString(15));                          
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
 
        
//        if(actionEvent.getSource()==btnEdit)
//        {
//                FXMLLoader loader=new FXMLLoader(getClass().getResource("Edit_Details.fxml"));
//                root = loader.load();
//                                
//                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//        }
//        if (actionEvent.getSource() == btnConnects) {
//            pnlConnects.setStyle("-fx-background-color : #1620A1");
//            pnlConnects.toFront();
//        }
//        
        if (actionEvent.getSource() == btnSignout) {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
                root = loader.load();
                                
                stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
        }
    }
}
