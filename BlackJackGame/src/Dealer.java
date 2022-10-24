import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Brad Kivell
 * @author Shelvin Kumar
 */
public final class Dealer extends Person {

    private final Deck deck;
    private final Random rand = new Random();
    
    
    // DEFAULT CONSTRUCTOR
    public Dealer() {
        this.deck = new Deck();
    }

    // SHUFFLES DECK
    public void shuffleDeck() {
        for (int i = 0; i <= deck.getDeckCards().size() - 1; i++) {
            deck.switchCards(i, rand.nextInt(deck.getDeckCards().size()));
        }
    }

    // RETURNS Card FROM Dealer Deck & REMOVES FROM DECK
    public Card dealCard() {
        Card card;
        card = deck.getDeckCards().get(0);
        deck.getDeckCards().remove(0);
        return card;
    }

    // ADDS CARDS BACK TO DEALERS DECK
    public void returnCards(Person p) {
        ArrayList<Card> returned = p.getHand();
        int returnedIndex = 0;
        for (Card card : returned) {
            this.deck.getDeckCards().add(card);
            returnedIndex++;
        }
        p.clearCards();
    }

}
