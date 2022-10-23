
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Brad Kivell
 */
public class Controller implements ActionListener {

    //=============================[VARIABLES]=============================
    private View view;
    private BlackJackModel blackJackModel;
    private BlackJackDB database;

    //=============================[CONSTRUCTOR]=============================
    public Controller() {
        // Model & View Setup
        this.view = new View();
        this.blackJackModel = new BlackJackModel();
        
        // Operations
        view.addControllers(this);
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
        }

    }

    //=============================[BUTTON EVENTS]=============================
    // LogIn Button Events
    private void EventHandleLogInButton() {
        view.hidePanel(view.getLogInPanel()); // Hides login page
        view.displayPanel(view.getMainMenuPanel()); // Shows menu page
    }

    // Info Button Events
    private void EventHandleInfoButton() {
        System.out.println("Info Pressed");
        view.hidePanel(view.getMainMenuPanel()); // Hides menu page
    }

    // Play Button Events
    private void EventHandlePlayButton() {
        System.out.println("Play Pressed");
        view.hidePanel(view.getMainMenuPanel()); // Hides menu page
    }

    // Log Out Button Events
    private void EventHandleLogOutButton() {
        System.out.println("Log Out Pressed");
        view.hidePanel(view.getMainMenuPanel()); // Hides menu page
    }

    // Exit Button Events
    private void EventHandleExitButton() {
        System.out.println("Exit Pressed");
        System.exit(0);
    }

    // Deposit Button Events
    private void EventHandleDepositButton() {
        System.out.println("Deposit Pressed");
    }

    //=============================[TEST]=============================
    public static void main(String[] args) {
        Controller cont = new Controller();
    }

}

///TO DO LIST
//1.) Setup Database Drivers (At least 3 read + 3 write)
//3.) Hit & Stand
//4.) Rules & How to play - commit to database
//5.) Deposit Money to account (Check 0 values)
//6.) Exit button
//7.) Change ace card value
//8.) Set amount for placing bet (Check 0 values)
//9.) Ensure version control
//10.) Test cases (At least 5)
