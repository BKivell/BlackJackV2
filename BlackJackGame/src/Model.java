
import java.util.Observable;

/**
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class Model extends Observable {

    //=============================[VARIABLES]=============================
    private BlackJackDB database;
    private Player player;
    private Dealer dealer;

    private int gameBet;

    // State of game outcome
    public enum WinState {
        PLAYER_WIN,
        DEALER_WIN,
        NO_WINNER
    }

    private WinState gameResult;

    //=============================[CONSTRUCTOR]=============================
    public Model() {
        database = new BlackJackDB();
        dealer = new Dealer();
        gameBet = 0;
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
    public void saveUserData() {
        database.updateUserData(player.getName(), player.getBalance()); // Comit user data to database
        System.out.println("USER: " + player.getName() + " BALANCE: " + player.getBalance() + " LOGGED OUT");
    }

    //=============================[GAME CONTROLS]=============================
    // Sets game up
    public void startGame() {
        // Clears current cards held and shuffles deck
        player.clearCards();
        dealer.clearCards();
        dealer.shuffleDeck();

        dealToPerson(player, 2); // Deals 2 cards to player

        dealToPerson(dealer, 1); // Deals 1 card to dealer
        updateView(); // Updates view
    }

    // Adds money to player balance
    public void depositMoney(int depositAmount) {
        player.setBalance(player.getBalance() + depositAmount);
        updateView();
    }

    // Player calls hit
    public void playerHit() {
        dealToPerson(player, 1);
        updateView();
    }

    // Player calls stand
    public void playerStand() {
        while (dealer.getHandValue() < 17) { // While the dealer doesn't have a value of 17 they must keep hitting
            dealToPerson(dealer, 1);
        }
    }

    // Updates players hand value (use when ace card values are changed)
    public void updatePlayerHand() {
        player.updateHandValue();
    }

    // Deals num cards to person
    private void dealToPerson(Person person, int num) {
        for (int i = 0; i < num; i++) {
            Card deltCard = dealer.dealCard();
            person.giveCard(deltCard);
        }
    }

    // Calculates winner
    public void gameOver() {
        if (getGameResult() == WinState.PLAYER_WIN) { // Player WIn
            player.increaseBalance();// Increase players balance by bet
        } else if (getGameResult() == WinState.DEALER_WIN) { // Player Loss
            player.decreaseBalance();
        }
        saveUserData(); // Saves data after game
        updateView();
    }

    // Returns player & dealer cards back to deck
    public void returnCardsToDeck() {
        dealer.returnCards(player);
        dealer.returnCards(dealer);
    }

    // Returns true if players cards beat dealers
    private void checkPlayerWins() {
        if (player.getHandValue() <= 21) { // Player not bust
            if (player.getHandValue() == 21) { // Player has blackjack
                gameResult = WinState.PLAYER_WIN;
            } else if (player.getHandValue() > dealer.getHandValue()) { // Player greater than dealer
                gameResult = WinState.PLAYER_WIN;
            } else if (player.getHandValue() < dealer.getHandValue() && dealer.getHandValue() <= 21) { // Dealer wins
                gameResult = WinState.DEALER_WIN;
            } else if (player.getHandValue() < dealer.getHandValue() && dealer.getHandValue() > 21) { // Player wins, dealer bust
                gameResult = WinState.PLAYER_WIN;
            } else { // No winner
                gameResult = WinState.NO_WINNER;
            }
        } else { // Bust, dealer wins
            if (dealer.getHandValue() <= 21) {
                gameResult = WinState.DEALER_WIN;
            } else {
                gameResult = WinState.NO_WINNER;
            }

        }
    }

    //=============================[OTHER FUNCTIONS]=============================
    // Returns play
    public Player getPlayer() {
        return player;
    }

    // Returns dealer
    public Dealer getDealer() {
        return dealer;
    }

    // Returns calculated game state
    public WinState getGameResult() {
        checkPlayerWins();
        return this.gameResult;
    }

    // Sets game bet to new bet, will return false if bet is invalid
    public boolean setBet(int newBet) {
        if (player.getBalance() >= newBet) {
            this.gameBet = newBet;
            player.setMoneyInGame(newBet); // Set player bet
            return true;
        }
        return false;
    }

    // Notifies view to update based on model state
    public void updateView() {
        this.setChanged();
        this.notifyObservers(this);
    }

    // Gets the game rules from database
    public String getRules() {
        System.out.println("Getting Game Rules: " + database.getRules());
        return database.getRules();
    }

    // Gets the players ace cards as a string
    public String getPlayerAceCardString() {
        String newString = "";
        if (!player.getAceCardArrayList().isEmpty()) {
            newString += "<html>Players Ace Cards:<br>";
            for (Card card : player.getAceCardArrayList()) {
                newString += card.toString() + "       -    Value: " + card.getValue() + "<br>";
            }
            newString += "</html>";
        }
        return newString;
    }
    
    // Returns an HTML string of player & dealer cards and their total value
    public String gameStatusString() {
        String output = "<html>Players Cards: ";
        for (Card c : player.getHand()) { // Loops through player cards
            output += c.toString() + ",";
        }
        output += "<br/>Players Hand Value: " + player.getHandValue();
        output += "<br><br>";
        output += "<br/>Dealer Cards: ";
        for (Card c : dealer.getHand()) { // Loops through dealer cards
            output += c.toString() + ",";
        }
        output += "<br/>Dealers Hand Value: " + dealer.getHandValue();
        output += "</html>";
        return output;
    }

}
