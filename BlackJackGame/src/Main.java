
/**
 * Console based BlackJack game for PDC Assignment 1
 *
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class Main {

    public static void main(String[] args) {
        String Rule = """
        RULES:
        THE GAME:
        The point of the BlackJack is the get your card value as close to 21 with out getting BUST to win and get the bet value doubled.        
                      
        CARD VALUES:
        Each number card is the value of the card, Ace Cards value can 
        be 1 or 11, Face cards value equal 10.B
                      
        BETTING:
        Start of the game the player places a bet (must be between 0 and the players balance)
                      
        THE DEALER:
        Once the player has placed their bet, the dealer then deal 2 card to the player facing up and
        1 card facing up and one card hidden from player to themself. The Dealer then asks the player if they
        Want to HIT or STAND, If Player picks HIT then dealer gives them one 1 card and asks again until player either
        says STAND or if player gets BUST(Hand value over 21). The dealer then flips over the other card and will keep dealing to themself
        until a minimum value of 17 has been reached.

        """;

        // Prints game rules
        System.out.println(Rule);
        // Creates & Starts game
        BlackJack game = new BlackJack();
        game.startGame();
    }
}