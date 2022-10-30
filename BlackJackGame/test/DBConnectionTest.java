import java.sql.Connection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class DBConnectionTest {
    
    public DBConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class DBConnection.
     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection");
        DBConnection testDBConnection = new DBConnection();
        Connection result = testDBConnection.getConnection();
        assertNotNull(result);
    }
    
}
