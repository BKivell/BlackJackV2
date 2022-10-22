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
    private int deckIndex;
    
    
    // DEFAULT CONSTRUCTOR
    public Dealer() {
        this.deck = new Deck();
        deckIndex = 0;
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
        card = deck.getDeckCards().get(deckIndex);
        deck.getDeckCards().remove(deckIndex);
        deckIndex++;
        return card;
    }

    // ADDS CARDS BACK TO DEALERS DECK
    public void returnCards(Person p) {
        ArrayList<Card> returned = p.getHand();
        int returnedIndex = 0;
        for (int i = 0; i <= returned.size() - 1; i++) {
            this.deck.getDeckCards().add(returned.get(returnedIndex));
            returnedIndex++;
            deckIndex--;
        }
        p.clearCards();
    }

}
