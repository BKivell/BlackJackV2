import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Brad Kivell (20115449)
 */
public final class DBConnection {

    //=============================[VARIABLES]=============================
    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:BlackJack_EDB; create=true";
    private Connection conn;

    //=============================[CONSTRUCTOR]=============================
    // Establishes database connection on object creation
    public DBConnection() {
        establishConnection();
    }

    //=============================[DATABASE FUNCTIONS]=============================
    // Returns connection
    public Connection getConnection() {
        return this.conn;
    }

    // Establishes connection to embedded database, will print error on fail
    private void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                System.out.println("Database Connected");
            } catch (SQLException ex) {
                System.out.println("Failed to establish connection: " + ex.getMessage());
            }
        }
    }

    // Closes connection to embedded databse
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database Connection Closed");
            } catch (SQLException ex) {
                System.out.println("Failed to close connection: " + ex.getMessage());
            }
        }
    }

}
