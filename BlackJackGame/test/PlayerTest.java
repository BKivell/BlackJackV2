import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shelvin kumar
 */
public class PlayerTest {

    public PlayerTest() {
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
     * Test of getBalance method, of class Player.
     */
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        Player testPlayer = new Player("test", 512);
        int expResult = 512;
        int result = testPlayer.getBalance();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBalance method, of class Player.
     */
    @Test
    public void testSetBalance() {
        System.out.println("setBalance");
        int newBalance = 500;
        Player testPlayer = new Player("test", 512);
        testPlayer.setBalance(newBalance);
        assertEquals(testPlayer.getBalance(), newBalance);
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player testPlayer = new Player("test", 512);
        String expResult = "test";
        String result = testPlayer.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "testname";
        Player testPlayer = new Player("notmyname", 512);
        testPlayer.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(name, testPlayer.getName());
    }

    /**
     * Test of getMoneyInGame method, of class Player.
     */
    @Test
    public void testGetMoneyInGame() {
        System.out.println("getMoneyInGame");
        Player testPlayer = new Player("test", 100);
        int expResult = 50;
        testPlayer.setMoneyInGame(expResult);
        int result = testPlayer.getMoneyInGame();
        assertEquals(expResult, result);
    }

}