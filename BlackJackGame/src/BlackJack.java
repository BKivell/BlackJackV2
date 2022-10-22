
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Brad Kivell
 * @author Shelvin Kumar
 */
public class BlackJack {

    // INSTANCE VARIABLES
    private final Scanner scan = new Scanner(System.in);
    public String stringInput;
    public Dealer dealer;
    DataTracker dataTracker;
    Player player;

    // CONSTRUCTOR
    public BlackJack() {
        System.out.println("Welcome to BlackJack!\nPlease enter your username: ");
        stringInput = scan.nextLine();
        dataTracker = new DataTracker(stringInput);
        dataTracker.loadSequence();
        dealer = new Dealer();
        player = new Player(dataTracker.getUserName(), dataTracker.getBalance());
    }

    // Deal card to person
    public void dealToPerson(Person p, int num, boolean display) {
        for (int i = 0; i < num; i++) {
            Card c = dealer.dealCard();
            p.giveCard(c);
            if (display) {
                System.out.println("Dealt: " + c.toString());
            }
        }
    }

    // Sets game up
    public void startGame() {
        // Clears current cards held
        player.clearCards();
        dealer.clearCards();
        dealer.shuffleDeck();
        gameLoop();
    }

    // Called when no more moves are to be made
    public void endGame(boolean gameEnded) {
        System.out.println("\nGAME OVER");
        if (gameEnded) {
            System.out.println("Players Hand Value: " + player.getHandValue());
            System.out.println("Dealers Hand Value: " + dealer.getHandValue());

            // Possible game outcomes
            if (player.getHandValue() <= 21) {
                if (player.getHandValue() == 21) {
                    System.out.println("BLACKJACK - Player Wins");
                    player.increaseBalance();
                } else if (player.getHandValue() > dealer.getHandValue()) {
                    System.out.println("Player Wins");
                    player.increaseBalance();
                } else if (player.getHandValue() < dealer.getHandValue() && dealer.getHandValue() <= 21) {
                    System.out.println("Dealer Wins");
                    player.decreaseBalance();
                } else if (player.getHandValue() < dealer.getHandValue() && dealer.getHandValue() > 21) {
                    System.out.println("Player Wins - Dealer Bust");
                    player.increaseBalance();
                } else {
                    System.out.println("No Winner");
                }
            } else {
                System.out.println("BUST - Dealer Wins");
                player.decreaseBalance();
            }

            // Return cards to the dealers deck
            dealer.returnCards(player);
            dealer.returnCards(dealer);
            // Save Data
            dataTracker.setBalance(player.getBalance());
            try {
                dataTracker.saveSequence();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found: Data has been lost for this session");
            }
        }
        System.out.println("New player balance: " + dataTracker.getBalance());
    }

    // Returns input as integer w/ exception handling
    public int getIntInput() {
        String input = "";
        int i = 0;
        boolean askingInput = true;
        while (askingInput) {
            try {
                input = scan.nextLine().trim();
                try {
                    i = Integer.valueOf(input);
                    askingInput = false;
                } catch (NumberFormatException e) {
                    if (input.equalsIgnoreCase("x")) {
                        endGame(false);
                        System.exit(0);
                    } else {
                        System.out.println("Invalid Input: Please enter a valid number");
                    }
                }
            } catch (InputMismatchException e) {
                scan.next();
                System.out.println("Invalid Input: Please enter a valid number");
            }
        }
        return i;
    }

    //-----------------------------------------[SHOW GAME OPTIONS]-----------------------------------------
    public void showGameOptions() {
        // Method Variables
        boolean showingOptions = true;
        int optionNum;

        while (showingOptions) {
            // Print hand values
            System.out.println("\nPlayers hand value: " + player.getHandValue());
            System.out.println("Dealers hand value: " + dealer.getHandValue() + " + Unknown Card Value");
            // Loop while in users turn
            System.out.println("\n1. Hit - Deal more cards");
            System.out.println("2. Stand - End turn with current cards ");
            // Only display option if player holds ace
            if (player.checkForAce() > 0) {
                System.out.println("3. Change value of Ace Cards");
            }
            System.out.println("\nPlease enter your option number (E.g '1'): ");

            optionNum = getIntInput();
            switch (optionNum) {
                case 1 -> {
                    // Keep hitting until player wants to end turn or goes bust
                    dealToPerson(player, 1, true);
                    if (player.getHandValue() > 21) {
                        showingOptions = false;
                    }
                }
                case 2 ->
                    showingOptions = false;
                case 3 -> {
                    // Check for ace cards
                    if (player.checkForAce() > 0) {
                        // Check for multiple ace cards
                        for (int i = 1; i <= player.checkForAce(); i++) {
                            System.out.println(player.getAceCard(i).toString() + " Current Value: " + player.getAceCard(i).getValue());
                            System.out.println("Enter 1 to value card at 1, or enter 2 to value card at 11");
                            optionNum = getIntInput();
                            switch (optionNum) {
                                case 1:
                                    player.getAceCard(i).setValue(1);
                                    break;
                                case 2:
                                    player.getAceCard(i).setValue(11);
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                            player.updateHandValue();
                        }
                    } else {
                        System.out.println("Invalid Option: No aces are in hand");
                    }
                }
                default ->
                    System.out.println("Invalid Option");
            }
        }
    }

    //-----------------------------------------[MAIN LOOP]-----------------------------------------
    public void gameLoop() {
        // Start Game Loop to place bet
        System.out.println("New Game Starting\nEnter 'x' to exit");
        System.out.println("Current Balance: " + player.getBalance());
        System.out.println("Enter an amount to bet (Must be a whole number)");
        boolean setUp = true;
        while (setUp) {// Get bet amount and set players money in game
            if (player.setMoneyInGame(getIntInput())) {
                setUp = false;
            }
        }

        // Deal cards to player & dealer
        System.out.println("\nPlayers hand:");
        dealToPerson(player, 2, true);
        System.out.println("\nDealers shown card:");
        dealToPerson(dealer, 1, true);

        // Show options (Deal another card, Sit at current cards, Change ace value)
        showGameOptions();

        // Deal final cards to dealer until they have at least 17
        System.out.println("\nDealers Final Hand:");
        while (dealer.getHandValue() < 17) {
            dealToPerson(dealer, 1, false);
        }
        // Display dealers cards
        dealer.displayHand();

        // Ends game, checks for winner & saves data
        endGame(true);

        // Check for replay, if replay is wanted, call startGame();
        System.out.println("Type 1 to restart game or 'x' to exit");
        int optionNum = 0;
        while (optionNum != 1) {
            optionNum = getIntInput();
            if (optionNum == 1) {
                startGame();
            }
            else{
                System.out.println("Invalid Option");
            }

        }
    }
}
