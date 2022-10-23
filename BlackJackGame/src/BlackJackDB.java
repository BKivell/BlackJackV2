import java.sql.Connection;
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
    public BlackJackDB()
    {
        this.dBConnection = new DBConnection();
        this.conn = this.dBConnection.getConnection();
    }
    
    //=============================[DATABASE FUNCTIONS]=============================
    public void closeConnection()
    {
        this.dBConnection.closeConnections();
    }
    
    //=============================[DATABASE IO]=============================
    
    
    
}
