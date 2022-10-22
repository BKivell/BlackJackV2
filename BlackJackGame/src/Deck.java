import java.util.ArrayList;

/**
 * @author Shelvin Kumar
 * @author Brad Kivell
 */
public class Deck {

    // DECK ArrayList
    private final ArrayList<Card> cards;

    // CONSTRUCTOR
    public Deck() {
        cards = new ArrayList<>();

        // POPULATE DECK
        // Clubs
        for (int i = 2; i <= 10; i++) {
            cards.add(new Card(Integer.toString(i), "Clubs", i));
        }
        // Special Case Cards
        cards.add(new Card("Ace", "Clubs", 1));
        cards.add(new Card("Jack", "Clubs", 10));
        cards.add(new Card("Queen", "Clubs", 10));
        cards.add(new Card("King", "Clubs", 10));

        // Diamonds
        for (int i = 2; i <= 10; i++) {
            cards.add(new Card(Integer.toString(i), "Diamonds", i));
        }
        // Special Case Cards
        cards.add(new Card("Ace", "Diamonds", 1));
        cards.add(new Card("Jack", "Diamonds", 10));
        cards.add(new Card("Queen", "Diamonds", 10));
        cards.add(new Card("King", "Diamonds", 10));

        // Hearts
        for (int i = 2; i <= 10; i++) {
            cards.add(new Card(Integer.toString(i), "Hearts", i));
        }
        // Special Case Cards
        cards.add(new Card("Ace", "Hearts", 1));
        cards.add(new Card("Jack", "Hearts", 10));
        cards.add(new Card("Queen", "Hearts", 10));
        cards.add(new Card("King", "Hearts", 10));

        // Spades
        for (int i = 2; i <= 10; i++) {
            cards.add(new Card(Integer.toString(i), "Spades", i));
        }
        // Special Case Cards
        cards.add(new Card("Ace", "Spades", 1));
        cards.add(new Card("Jack", "Spades", 10));
        cards.add(new Card("Queen", "Spades", 10));
        cards.add(new Card("King", "Spades", 10));
    }

    // RETURNS deck AS ArrayList<Card>
    public ArrayList<Card> getDeckCards() {
        return cards;
    }

    // SWITCHES Card AT INDEX i & j
    public void switchCards(int i, int j) {
        Card c = cards.get(i);
        Card c2 = cards.get(j);
        cards.set(j, c);
        cards.set(i, c2);
    }

}
