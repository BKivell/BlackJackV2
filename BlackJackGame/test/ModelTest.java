
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
public class ModelTest {

    public ModelTest() {
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
     * Test of userLogin method, of class Model.
     */
    @Test
    public void testUserLogin() {
        System.out.println("userLogin");
        String userName = "testPlayer";
        Model instance = new Model();
        instance.userLogin(userName);
        assertNotNull(instance.getPlayer());
    }

    /**
     * Test of startGame method, of class Model.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        Model instance = new Model();
        instance.userLogin("test");
        instance.startGame();
        assertTrue(instance.getPlayer().getHand().size() == 2 && instance.getDealer().getHand().size() == 1); // Checks player has been delt 2 cards & dealer 1
    }

    /**
     * Test of depositMoney method, of class Model.
     */
    @Test
    public void testDepositMoney() {
        System.out.println("depositMoney");
        int depositAmount = 1234;
        Model instance = new Model();
        instance.userLogin("depostTestPlayer");
        instance.depositMoney(depositAmount);
        assertTrue(instance.getPlayer().getBalance() == depositAmount);
    }

    /**
     * Test of playerHit method, of class Model.
     */
    @Test
    public void testPlayerHit() {
        System.out.println("playerHit");
        Model instance = new Model();
        instance.userLogin("testPlayerHit");
        int currentCards = instance.getPlayer().getHand().size();
        instance.playerHit();
        assertEquals(currentCards + 1, instance.getPlayer().getHand().size());
    }

    /**
     * Test of returnCardsToDeck method, of class Model.
     */
    @Test
    public void testReturnCardsToDeck() {
        System.out.println("returnCardsToDeck");
        Model instance = new Model();
        instance.userLogin("testReturnCards");
        instance.playerHit();
        instance.returnCardsToDeck();

        assertEquals(0, instance.getPlayer().getHand().size());
    }

    /**
     * Test of getPlayer method, of class Model.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        Model instance = new Model();
        instance.userLogin("testGetPlayer");
        Player result = instance.getPlayer();
        assertEquals("testGetPlayer", result.getName());
    }

    /**
     * Test of getDealer method, of class Model.
     */
    @Test
    public void testGetDealer() {
        System.out.println("getDealer");
        Model instance = new Model();
        Dealer result = instance.getDealer();
        assertNotNull(result);
    }

    /**
     * Test of setBet method, of class Model.
     */
    @Test
    public void testSetBet() {
        System.out.println("setBet");
        int newBet = 35;
        Model instance = new Model();
        instance.userLogin("betTestPlayer");
        instance.depositMoney(1000);
        boolean result = instance.setBet(newBet);
        assertTrue(result);
    }
}
