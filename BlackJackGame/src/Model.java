
import java.util.Observable;

/**
 *
 * @author Bradk
 */
public class Model extends Observable {
    //=============================[VARIABLES]=============================

    private BlackJackDB database;
    private Player player;
    private Dealer dealer;

    public int gameBet;

    //=============================[CONSTRUCTOR]=============================
    public Model() {
        database = new BlackJackDB();
        dealer = new Dealer();
    }

    //=============================[DB FUNCTIONS]=============================
    public void userLogin(String userName) {
        player = new Player(userName, 0);
        if (database.checkForUser(userName)) { // If user is found
            player.setBalance(database.getUserBalance(userName)); // Retreives player balance
        }
        database.updateUserData(userName, player.getBalance());
        System.out.println("USER: " + userName + " BALANCE: " + player.getBalance() + " LOGGED IN");
        updateView();
    }

    // Prints all user data to console
    public void printDataBase() {
        database.printUserData();
    }

    // Saves user data to database
    public void userLogOut() {
        database.updateUserData(player.getName(), player.getBalance()); // Comit user data to database
        System.out.println("USER: " + player.getName() + " BALANCE: " + player.getBalance() + " LOGGED OUT");
    }

    //=============================[GAME CONTROLS]=============================
    // Sets game up
    public void startGame() {
        // Clears current cards held
        player.clearCards();
        dealer.clearCards();
        dealer.shuffleDeck();

        dealToPerson(player, 1);
        dealToPerson(player, 1);

        dealToPerson(dealer, 1);
        updateView();
    }

// Adds money to player balance
    public void depositMoney(int depositAmount) {
        player.setBalance(player.getBalance() + depositAmount);
        updateView();
    }

    // Returns cards
    public String gameStatusString() {
        String output = "<html>Players Cards: ";
        for (Card c : player.getHand()) {
            output += c.toString() + ",";
        }
        output += "<br/>Dealer Cards: ";
        for (Card c : dealer.getHand()) {
            output += c.toString() + ",";
        }
        output += "</html>";
        return output;
    }

    // Player calls hit
    public void playerHit() {
        // Keep hitting until player wants to end turn or goes bust
        dealToPerson(player, 1);
        updateView();
    }

    // Player calls stand
    public void playerStand() {
        dealToPerson(dealer, 1);
        gameOver();
        updateView();
    }

    // Deal card to person
    private void dealToPerson(Person person, int num) {
        for (int i = 0; i < num; i++) {
            Card deltCard = dealer.dealCard();
            person.giveCard(deltCard);
        }
    }

    // Calculates winner
    public void gameOver() {
        if (playerWins()) {

        }

        // Return cards to the dealers deck
        dealer.returnCards(player);
        dealer.returnCards(dealer);
        // Save Data
        // Display popup menu with game results

    }

    // Returns true if players cards beat dealers
    private boolean playerWins() {
        boolean flag = false;
        if (player.getHandValue() <= 21) { // Player not bust
            if (player.getHandValue() == 21) { // Player has blackjack
                flag = true;
            } else if (player.getHandValue() > dealer.getHandValue()) { // Player greater than dealer
                flag = true;
            } else if (player.getHandValue() < dealer.getHandValue() && dealer.getHandValue() <= 21) { // Dealer wins
                flag = false;
            } else if (player.getHandValue() < dealer.getHandValue() && dealer.getHandValue() > 21) { // Player wins, dealer bust
                flag = true;
            }
        } else { // Bust, dealer wins
            flag = false;
        }
        return flag;
    }

    //=============================[OTHER FUNCTIONS]=============================
    // Returns play
    public Player getPlayer() {
        return player;
    }

    // Notifies view to update based on model
    private void updateView() {
        this.setChanged();
        this.notifyObservers(this);
    }

}
