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
public class DealerTest {
    
    public DealerTest() {
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
     * Test of dealCard method, of class Dealer.
     */
    @Test
    public void testDealCard() {
        System.out.println("dealCard");
        Dealer instance = new Dealer();
        Card result = instance.dealCard();
        assertNotNull(result);
    }
    
}
