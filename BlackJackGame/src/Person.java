
import java.util.ArrayList;

/**
 *
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class Person {

    // Stores players cards on hand
    private Hand hand;

    // DEFAULT CONSTRUCTOR
    public Person() {
        hand = new Hand();
    }

    // ADDS card PARAM  TO PLAYER hand
    public void giveCard(Card card) {
        this.getHand().add(card);
        this.updateHandValue();
    }

    // CLEARS PLAYERS hand
    public void clearCards() {
        this.getHand().clear();
        this.updateHandValue();
    }

    // RETURNS hand AS ArrayList<Card>;
    public ArrayList<Card> getHand() {
        return hand.getHand();
    }

    // Prints players hand to system.out
    public void displayHand() {
        for (Card c : getHand()) {
            System.out.println(c.toString());
        }
    }

    // RETURNS handValue AS int
    public int getHandValue() {
        return this.hand.getHandValue();
    }

    // SETS handValue TO PARAM
    public void updateHandValue() {
        int i = 0;
        for (Card c : this.getHand()) {
            i += c.getValue();
        }
        this.hand.setHandValue(i);
    }

    // Checks player hand for Ace RETURNS number of aces
    public int aceCount() {
        int i = 0;
        for (Card c : this.getHand()) {
            if (c.getFace().equals("Ace")) {
                i++;
            }
        }
        return i;
    }

    // Returns the ith ace card
    public Card getAceCard(int i) {
        Card ace = null;
        int count = 1;
        for (Card c : this.hand.getHand()) {
            if (c.getFace().equals("Ace")) {
                if (count == i) {
                    ace = c;
                }
                count++;
            }
        }
        return ace;
    }
    // Returns all ace cards
    public ArrayList<Card> getAceCardArrayList() {
        ArrayList<Card> cardList = new ArrayList<>();
        for (Card card : this.hand.getHand()) {
            if (card.getFace().equals("Ace")) {
                cardList.add(card);
            }
        }
        return cardList;
    }

    // Returns true if holds ace card
    public boolean hasAce() {
        for (Card card : this.hand.getHand()) {
            if (card.getFace().equals("Ace")) {
                return true;
            }
        }
        return false;
    }

}
