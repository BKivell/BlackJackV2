
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Brad Kivell
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
        }

    }

    //=============================[BUTTON EVENTS]=============================
    // LogIn Button Events
    private void EventHandleLogInButton() {

        System.out.println("LogIn");
        String logInString = view.getLogInPanel().getUserNameTextField().getText().trim();
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

    }

    // Play Button Events
    private void EventHandlePlayButton() {
        System.out.println("Play Pressed");
        view.hidePanel(view.getMainMenuPanel()); // Hides menu page
        view.displayPanel(view.getGamePanel()); // Shows info page
        String betAmount = JOptionPane.showInputDialog("Bet Amount"); // -------------------------------------------- CHECK INPUT INT
        model.gameBet = (Integer.parseInt(betAmount));
        model.startGame();

    }

    // Log Out Button Events
    private void EventHandleLogOutButton() {
        System.out.println("Log Out Pressed");
        view.hidePanel(view.getMainMenuPanel()); // Hides menu page
        view.displayPanel(view.getLogInPanel()); // Shows login page
        model.userLogOut();
    }

    // Exit Button Events
    private void EventHandleExitButton() {
        System.out.println("Exit Pressed");
        System.exit(0);
    }

    // Deposit Button Events
    private void EventHandleDepositButton() {
        System.out.println("Deposit Pressed");
        String depositAmountString = JOptionPane.showInputDialog("Deposit Amount");// -------------------------------------------- CHECK INPUT INT
        model.depositMoney(Integer.parseInt(depositAmountString));
    }

    // Return To Menu Button
    private void EventHandleReturnToMenu() {
        view.hidePanel(view.getInfoPanel()); // Hides panel
        view.displayPanel(view.getMainMenuPanel()); // Shows panel
    }

    // Player Hit
    private void EventHandleHit() {
        model.playerHit();
    }

    // Player Stand
    private void EventHandleStand() {
        model.playerStand();
    }

    //=============================[TEST]=============================
    public static void main(String[] args) {
        Controller cont = new Controller();
    }

}

///TO DO LIST
//1.) Setup Database Drivers (At least 3 read + 3 write)
//3.) Hit & Stand
//4.) Rules & How to play - commit to database?
//5.) Deposit Money to account (Check 0 values)
//6.) Exit button
//7.) Change ace card value
//8.) Set amount for placing bet (Check 0 values)
//9.) Ensure version control
//10.) Test cases (At least 5)
