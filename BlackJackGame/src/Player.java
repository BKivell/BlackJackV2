
/**
 *
 * @author Brad Kivell
 * @author Shelvin Kumar
 */
public final class Player extends Person {

    private int balance;
    private String name;
    private int moneyInGame;

    public Player(String name, int balance) {
        super();
        this.setBalance(balance);
        this.setName(name);
        this.setMoneyInGame(0);
    }

    // RETURN balance AS int
    public int getBalance() {
        return balance;
    }

    // SETS balance TO PARAM
    public void setBalance(int balance) {
        this.balance = balance;
    }

    // RETURNS name AS String
    public String getName() {
        return name;
    }

    // SETS name TO PARAM
    public void setName(String name) {
        this.name = name;
    }

    // RETURNS moneyInGame AS String
    public int getMoneyInGame() {
        return moneyInGame;
    }

    // SET moneyInGame TO PARAM
    public boolean setMoneyInGame(int i) {
        if (i <= this.getBalance() && i >= 0) {
            this.moneyInGame = i;
            return true;
        } else {
            System.out.println("Insufficient Funds: Enter a valid bet");
        }
        return false;
    }

    // INCREASES balance BY moneyInGame
    public void increaseBalance() {
        this.setBalance(balance + moneyInGame);
    }

    // DECREASES balance BY moneyInGame
    public void decreaseBalance() {
        this.setBalance(balance - moneyInGame);
    }

}
