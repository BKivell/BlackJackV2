
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class DeckTest {

    public DeckTest() {
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
     * Test of getDeckCards method, of class Deck.
     */
    @Test
    public void testGetDeckCards() {
        System.out.println("getDeckCards");
        Deck testDeck = new Deck();
        ArrayList<Card> result = testDeck.getDeckCards();
        assertTrue(result.size() == 52); // Check for all 52 cards
    }

    /**
     * Test of switchCards method, of class Deck.
     */
    @Test
    public void testSwitchCards() {
        System.out.println("switchCards");
        int i = 1;
        int j = 11;
        Deck testDeck = new Deck();
        Card card1 = testDeck.getDeckCards().get(i);
        testDeck.switchCards(i, j);
        Card card2 = testDeck.getDeckCards().get(j);
        assertSame(card1, card2);
    }

}
