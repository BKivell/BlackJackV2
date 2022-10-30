import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bradk
 */
public class BlackJackDBTest {
    
    public BlackJackDBTest() {
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
     * Test of checkForUser method, of class BlackJackDB.
     */
    @Test
    public void testCheckForUser() {
        System.out.println("checkForUser");
        String userNameCheck = "test";
        BlackJackDB instance = new BlackJackDB();
        boolean expResult = true;
        boolean result = instance.checkForUser(userNameCheck);
        assertEquals(expResult, result);
    }

    /**
     * Test of getUserBalance method, of class BlackJackDB.
     */
    @Test
    public void testGetUserBalance() {
        System.out.println("getUserBalance");
        String userName = "unit_test";
        BlackJackDB instance = new BlackJackDB();
        int expResult = 1234;
        int result = instance.getUserBalance(userName);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateUserData method, of class BlackJackDB.
     */
    @Test
    public void testUpdateUserData() {
        System.out.println("updateUserData");
        String userName = "unit_test";
        int newBalance = 1234;
        BlackJackDB instance = new BlackJackDB();
        instance.updateUserData(userName, newBalance);
        // Test will pass as other tests rely on this test
    }

    /**
     * Test of getRules method, of class BlackJackDB.
     */
    @Test
    public void testGetRules() {
        System.out.println("getRules");
        BlackJackDB instance = new BlackJackDB();
        String result = instance.getRules();
        assertNotNull(result);
    }
    
}
