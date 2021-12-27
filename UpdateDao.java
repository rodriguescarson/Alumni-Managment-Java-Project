package javafxapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDao {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    
    //change id down here to update specific user
    private static final String INSERT_QUERY = "UPDATE alumni SET  email= '?', fname = '?' , lname='?' , password= '?', prno= '?', collegeName= '?', workingPlace= '?', currentPosition= '?', yearOfPassingOut= '?', linkedin= '?', dob= '?', gender= '?',cgpa= '?',department= '?' WHERE id = 1;";

    public void updateRecord(String email,String fname, String lname,String password,String prno,String collegeName,String workingPlace,String currentPosition,String yearOfPassingOut,String linkedin,String dob,String gender,String cgpa,String department) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, fname);
            preparedStatement.setString(3, lname);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, prno);
            preparedStatement.setString(6, collegeName);            
            preparedStatement.setString(7, workingPlace);
            preparedStatement.setString(8, currentPosition);
            preparedStatement.setString(9, yearOfPassingOut);
            preparedStatement.setString(10, linkedin);
            preparedStatement.setString(11, dob);
            preparedStatement.setString(12, gender);
            preparedStatement.setString(13, cgpa);
            preparedStatement.setString(14, department);
//            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
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
}
