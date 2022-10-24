
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Brad Kivell (20115449)
 */
public final class BlackJackDB {

    //=============================[VARIABLES]=============================
    private DBConnection dBConnection;
    private Connection conn;
    private Statement statement;

    //=============================[CONSTRUCTOR]=============================
    public BlackJackDB() {
        this.dBConnection = new DBConnection();
        this.conn = this.dBConnection.getConnection();
        setupTables();
        setUpRules();
    }

    //=============================[TABLE SETUP]=============================
    private void setupTables() {
        if (!ifTableExists("USERDATA")) {
            dBUpdate("CREATE TABLE USERDATA (username VARCHAR(12), balance INT)");
        } else {
            System.out.println("User Data Table Already Exists");
        }
        if (!ifTableExists("GAMEINFO")) {
            dBUpdate("CREATE TABLE GAMEINFO (messagekey INT, message VARCHAR(2000))");
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
            ResultSet rsDBMeta = dbmd.getTables(null, null, tableName, null);
            if (rsDBMeta != null) {
                if (rsDBMeta.next()) {
                    flag = true;
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
            if (rs != null) {
                while (rs.next()) {
                    String username = rs.getString("username");
                    int balance = rs.getInt("balance");
                    System.out.println("USER: " + username + " BAL: " + balance);
                }
            }
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
        } else { // If a user is found, update their data
            dBUpdate("UPDATE USERDATA SET balance = " + newBalance + " WHERE username='" + userName + "'");
        }
    }

    // Returns rules from game info
    public String getRules() {
        String newRules = "";
        ResultSet rs = dBQuery("SELECT * FROM GAMEINFO WHERE messagekey = 1");
        if (rs != null) {
            try {
                if (rs.next()) {
                    newRules = rs.getString("message");
                }
            } catch (SQLException ex) {
                System.out.println("Error retrieving game rules");
            }
        }
        return newRules;
    }

    private void setUpRules() {
        String rules = """
        <html>RULES:<br>
        THE GAME:<br>
        The point of the BlackJack is the get your card value as close to 21 with out getting BUST to win and get the bet value doubled.<br>
        <br>             
        CARD VALUES:<br>
        Each number card is the value of the card, Ace Cards value can <br>
        be 1 or 11, Face cards value equal 10.<br>
        <br>     
        BETTING:<br>
        Start of the game the player places a bet (must be between 0 and the players balance)<br>
        <br>              
        THE DEALER:<br>
        Once the player has placed their bet, the dealer then deal 2 card to the player facing up and<br>
        1 card facing up and one card hidden from player to themself. The Dealer then asks the player if they<br>
        Want to HIT or STAND, If Player picks HIT then dealer gives them one 1 card and asks again until player either<br>
        says STAND or if player gets BUST(Hand value over 21). The dealer then flips over the other card and will keep dealing to themself
        until a minimum value of 17 has been reached.<br></html>
        """;

        ResultSet rs = dBQuery("SELECT * FROM GAMEINFO WHERE messagekey = 1");
        if (rs != null) {
            try {
                if (!rs.next()) {
                    dBUpdate("INSERT INTO GAMEINFO VALUES(1, '" + rules + "')");
                }
            } catch (SQLException ex) {
                System.out.println("Error inserting rules: " + ex);
            }
        }

    }

}
