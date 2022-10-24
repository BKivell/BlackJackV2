
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Brad Kivell (20115449)
 */
public class BlackJackDB {

    //=============================[VARIABLES]=============================
    private DBConnection dBConnection;
    private Connection conn;
    private Statement statement;

    //=============================[CONSTRUCTOR]=============================
    public BlackJackDB() {
        this.dBConnection = new DBConnection();
        this.conn = this.dBConnection.getConnection();
        //setupTables();
    }

    //=============================[TABLE SETUP]=============================
    private void setupTables() {
        if (!ifTableExists("USERDATA")) {
            dBUpdate("CREATE TABLE USERDATA (username VARCHAR(12), balance INT)");
        } else {
            System.out.println("User Data Table Already Exists");
        }
        if (!ifTableExists("GAMEINFO")) {
            dBUpdate("CREATE TABLE GAMEINFO (key INT, message VARCHAR(2000))");
        } else {
            System.out.println("Game Info Table Already Exists");
        }
    }

    //=============================[DATABASE FUNCTIONS]=============================
    public void closeConnection() {
        this.dBConnection.closeConnections();
    }

    // Returns true if the table already exists, false if not found
    private boolean ifTableExists(String tableName) {
        boolean flag = false;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, "TABLE_NAME", null);
            if (rsDBMeta != null) {
                while (rsDBMeta.next()) {
                    String currentTableName = rsDBMeta.getString("TABLE_NAME");
                    if (tableName.compareToIgnoreCase(tableName) == 0) {
                        flag = true;
                    }
                }
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error checking for table");
        }
        return flag;
    }

    // Returns result set based on query
    private ResultSet dBQuery(String statementString) {
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery(statementString);
        } catch (SQLException ex) {
            System.out.println("DB Query Failed" + ex.getMessage());
        }
        return rs;
    }

    // Updates database based on input statement
    private void dBUpdate(String statementString) {
        try {
            statement = conn.createStatement();
            statement.addBatch(statementString);
            statement.executeBatch();
        } catch (SQLException ex) {
            System.out.println("DB Update Failed" + ex.getMessage());
        }
    }

    public void printUserData() {
        try {
            ResultSet rs = dBQuery("SELECT * FROM USERDATA");
            System.out.println("1");
            if (rs != null) {
                System.out.println("2");
                while (rs.next()) {
                    String username = rs.getString("username");
                    int balance = rs.getInt("balance");
                    System.out.println("USER: " + username + " BAL: " + balance);
                }
                System.out.println("3");
            }
            System.out.println("4");
        } catch (SQLException ex) {
            System.out.println("Error searching for user");
        }
    }

    //=============================[DATABASE IO]=============================
    // Returns true if user found within database
    public boolean checkForUser(String userNameCheck) {
        boolean userCheck = false;
        try {
            ResultSet rs = dBQuery("SELECT * FROM USERDATA WHERE username = '" + userNameCheck + "'");
            if (rs != null) {
                if (rs.next()) {
                    userCheck = true;
                    System.out.println("found user");

                } else {
                    System.out.println("No User Found");

                }
            }

        } catch (SQLException ex) {
            System.out.println("Error searching for user");
        }
        return userCheck;
    }

    // Returns true if user found within database
    public int getUserBalance(String userName) {
        int balance = 0;
        if (checkForUser(userName)) // Check for user
        {
            try {
                ResultSet rs = dBQuery("SELECT * FROM USERDATA"
                        + " WHERE username = '" + userName + "'");
                if (rs != null) {
                    if (rs.next()) {
                        balance = rs.getInt("balance");
                        System.out.println("Balance Found: " + balance);
                    }
                }

            } catch (SQLException ex) {
                System.out.println("Error getting player balance");
            }
        }
        return balance;
    }

    // Updates user data
    public void updateUserData(String userName, int newBalance) {
        if (!checkForUser(userName)) { // If no user found, add new user
            dBUpdate("INSERT INTO USERDATA VALUES('" + userName + "'," + newBalance + ")");
            System.out.println("Opt1");
        } else { // If a user is found, update their data
            dBUpdate("UPDATE USERDATA SET balance = " + newBalance + " WHERE username='" + userName + "'");
            System.out.println("Opt2");
        }
    }

    public void getRules() {

    }

}
