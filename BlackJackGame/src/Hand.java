
import java.util.ArrayList;

/**
 *
 * @author Brad Kivell
 * @author Shelvin Kumar
 */
public class Hand {

    private final ArrayList<Card> hand;
    private int handValue;

    // Default constructor
    public Hand() {
        this.hand = new ArrayList<>();
        this.handValue = 0;
    }
    
    // Returns array list of Card objects
    public ArrayList<Card> getHand()
    {
        return this.hand;
    }
    
    // Returns hand value as int
    public int getHandValue()
    {
        return this.handValue;
    }
    
    // Sets hand value
    public void setHandValue(int i)
    {
        this.handValue = i;
    }
}
