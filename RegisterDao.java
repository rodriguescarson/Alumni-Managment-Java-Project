package javafxapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterDao {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String INSERT_QUERY = "INSERT INTO alumni (fname, email, lname) VALUES (?, ?, ?)";

    public void insertRecord(String fname, String email, String lname) throws SQLException {
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, fname);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, lname);
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
