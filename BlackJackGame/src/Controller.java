
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class Controller implements ActionListener {

    //=============================[VARIABLES]=============================
    private View view;
    private Model model;

    //=============================[CONSTRUCTOR]=============================
    public Controller() {
        // Model & View Setup
        this.view = new View();
        this.model = new Model();

        // Operations
        view.addControllers(this);
        model.addObserver(view);
    }

    //=============================[ACTION EVENTS]=============================
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == view.getLogInPanel().getLogInButton()) { // LOGIN MENU - Login Button
            EventHandleLogInButton();
        } else if (source == view.getMainMenuPanel().getPlayButton()) { // MAIN MENU - Play Button
            EventHandlePlayButton();
        } else if (source == view.getMainMenuPanel().getInfoButton()) { // MAIN MENU - Info Button
            EventHandleInfoButton();
        } else if (source == view.getMainMenuPanel().getDepositButton()) { // MAIN MENU - Deposit Button
            EventHandleDepositButton();
        } else if (source == view.getMainMenuPanel().getLogOutButton()) {// MAIN MENU - Log Out Button
            EventHandleLogOutButton();
        } else if (source == view.getMainMenuPanel().getExitButton()) {// MAIN MENU - Exit Button
            EventHandleExitButton();
        } else if (source == view.getInfoPanel().getReturnToMenuButton()) {// INFO MENU - Return Button
            EventHandleReturnToMenu();
        } else if (source == view.getGamePanel().getHitButtont()) {// GAME PANEL - Hit Button
            EventHandleHit();
        } else if (source == view.getGamePanel().getStandButton()) {// GAME PANEL - Stand Button
            EventHandleStand();
        } else if (source == view.getGamePanel().getAceChangeButton()) {
            EventHandleChangeAces();
        }

    }

    //=============================[BUTTON EVENTS]=============================
    // LogIn Button Events
    private void EventHandleLogInButton() {
        System.out.println("LogIn");
        String logInString = view.getLogInPanel().getUserNameTextField().getText().trim(); // Gets login text box string
        // check not empty
        if (!"".equals(logInString)) {
            view.hidePanel(view.getLogInPanel()); // Hides login page
            view.displayPanel(view.getMainMenuPanel()); // Shows menu page
            model.userLogin(logInString); // Logs in user
        } else {
            JOptionPane.showMessageDialog(view, "Invalid Username");
        }
    }

    // Info Button Events
    private void EventHandleInfoButton() {
        System.out.println("Info Pressed");
        view.displayPanel(view.getInfoPanel()); // Shows info page
        view.hidePanel(view.getMainMenuPanel()); // Hides menu page
        model.updateView(); // Manually update view to avoid endless looping of collecting rules
    }

    // Play Button Events
    private void EventHandlePlayButton() {
        System.out.println("Play Pressed");
        int newBet = view.getPopUpInt("Bet Amount", "Invalid Bet: Please ensure bet is valid and less than player balance", true, model.getPlayer().getBalance());
        if (newBet != 0) // Not Invalid Bet
        {
            model.setBet(newBet);
            // Panel Configuration
            view.hidePanel(view.getMainMenuPanel()); // Hides menu page
            view.displayPanel(view.getGamePanel()); // Shows game page
            // Start game
            model.startGame();
        }
    }

// Log Out Button Events
    private void EventHandleLogOutButton() {
        System.out.println("Log Out Pressed");
        view.hidePanel(view.getMainMenuPanel()); // Hides menu page
        view.displayPanel(view.getLogInPanel()); // Shows login page
        model.saveUserData(); // Logout user
    }

    // Exit Button Events
    private void EventHandleExitButton() {
        System.out.println("Exit Pressed");
        model.saveUserData(); // Logout User
        System.exit(0);
    }

    // Deposit Button Events
    private void EventHandleDepositButton() {
        System.out.println("Deposit Pressed");
        model.depositMoney(view.getPopUpInt("Deposit Amount", "Invalid Deposit: Please enter valid amount", false, 0));
    }

    // Return To Menu Button
    private void EventHandleReturnToMenu() {
        view.hidePanel(view.getInfoPanel()); // Hides panel
        view.displayPanel(view.getMainMenuPanel()); // Shows panel
    }

    // Player Hit
    private void EventHandleHit() {
        model.playerHit();
        if (model.getPlayer().getHandValue() > 21) {
            gameOverGUI();
        }
    }

    // Player Stand
    private void EventHandleStand() {
        model.playerStand();
        gameOverGUI();
    }

    private void EventHandleChangeAces() {
        Card cardToChange = (Card) JOptionPane.showInputDialog(null, "Select Card to Change",
                "The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null,
                model.getPlayer().getAceCardArrayList().toArray(), // Array of cards
                model.getPlayer().getAceCardArrayList().get(0)); // Initial choice

        if (cardToChange != null) {
            if (cardToChange.getValue() == 1) {
                cardToChange.setValue(11);
            } else {
                cardToChange.setValue(1);
            }
            model.updatePlayerHand();
        }
        model.updateView();
    }

    //=============================[FUNCTIONS]=============================
    private void gameOverGUI() { // Performs gameover sequence in correct order, switches to main menu
        model.gameOver();
        view.displayGameResultPopUp(model.getPlayer(), model.getDealer(), model.getGameResult());
        model.returnCardsToDeck();
        view.hidePanel(view.getGamePanel()); // Hides panel
        view.displayPanel(view.getMainMenuPanel()); // Shows panel
    }

}
