/**
 *
 * @author Brad Kivell
 * @author Shelvin Kumar
 */
public class Card {

    private final String face; // E.g 3, 5, Jack, King
    private final String suit;  // E.g Clubs, Diamonds
    private int value;

    // CONSTRUCTOR
    public Card(String face, String suit, int value) {
        this.face = face;
        this.suit = suit;
        this.value = value;
    }

    // RETURN suit AS String
    public String getSuit() {
        return suit;
    }

    // RETURN face AS String
    public String getFace() {
        return face;
    }

    // RETURN value AS int
    public int getValue() {
        return value;
    }

    // SET value AS PARAM
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return (this.getFace() + " " + this.getSuit());
    }
}
