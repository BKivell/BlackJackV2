import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class View extends JFrame implements Observer {

    //=============================[PANELS]=============================
    private LoginPanel loginPanel;
    private MainMenuPanel mainMenuPanel;
    private InformationPanel infoPanel;
    private GamePanel gamePanel;

    private boolean loadedDatabaseInfo;
    private String gameRules;

    public Color buttonColor = Color.WHITE;

    //=============================[CONSTRUCTOR]=============================
    public View() {
        loadedDatabaseInfo = false;
        this.setSize(720, 720);
        initComponents();
        panelStartUp();
    }

    //=============================[FRAME SETUP]=============================S
    private void initComponents() {
        this.loginPanel = new LoginPanel(this);
        this.mainMenuPanel = new MainMenuPanel(this);
        this.infoPanel = new InformationPanel(this);
        this.gamePanel = new GamePanel(this);
        this.add(loginPanel); // Add panel to frame
        this.add(mainMenuPanel);
        this.add(infoPanel);
        this.add(gamePanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    private void panelStartUp() {
        this.loginPanel.setVisible(true);
        this.mainMenuPanel.setVisible(false);
        this.infoPanel.setVisible(false);
        this.gamePanel.setVisible(false);
    }

    //=============================[FUNCTIONS]=============================
    public void addControllers(Controller controller) {
        // LogIn Menu
        loginPanel.getLogInButton().addActionListener(controller);
        // Main Menu
        mainMenuPanel.getPlayButton().addActionListener(controller);
        mainMenuPanel.getExitButton().addActionListener(controller);
        mainMenuPanel.getInfoButton().addActionListener(controller);
        mainMenuPanel.getLogOutButton().addActionListener(controller);
        mainMenuPanel.getDepositButton().addActionListener(controller);
        // Info Menu
        infoPanel.getReturnToMenuButton().addActionListener(controller);
        // Game Menu
        gamePanel.getHitButtont().addActionListener(controller);
        gamePanel.getStandButton().addActionListener(controller);
        gamePanel.getAceChangeButton().addActionListener(controller);

    }

    // Displays panel to frame
    public void displayPanel(JPanel panel) {
        panel.setVisible(true);
    }

    // Hides panel from frame
    public void hidePanel(JPanel panel) {
        panel.setVisible(false);
    }

    // Returns login panel
    public LoginPanel getLogInPanel() {
        return loginPanel;
    }

    // Returns main menu panel
    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    // Returns info panel
    public InformationPanel getInfoPanel() {
        return infoPanel;
    }

    // Return game panel
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    //=============================[UPDATE]=============================
    @Override
    public void update(Observable o, Object arg) {
        Model model = (Model) arg;

        if (gamePanel.isVisible()) { // In game
            gamePanel.getCenterText().setText(model.gameStatusString());
            gamePanel.getAceCardText().setText(model.getPlayerAceCardString());
            if (model.getPlayer().hasAce()) { // Player has ace
                gamePanel.getAceChangeButton().setVisible(true);
            } else { // Player doesnt have ace
                gamePanel.getAceChangeButton().setVisible(false);
            }
        } else if (infoPanel.isVisible()) {
            infoPanel.setInfoText(model.getRules());
        }
        mainMenuPanel.getBalanceLabel().setText("Balance: " + model.getPlayer().getBalance());
    }

    //=============================[POPUP WINDOWS]=============================
// Returns integer from popup window - checks for valid input
    public int getPopUpInt(String displayMessage, String errorMessage, boolean hasMax, int maxAmount) {
        int newNum = 0;
        while (newNum <= 0) { // While input number is too low
            try {
                String inputString = JOptionPane.showInputDialog(displayMessage); // Display input window
                if (inputString == null) { // Check for cancel option
                    break;
                }
                int stringAsInt = Integer.parseInt(inputString);
                if (hasMax && stringAsInt <= maxAmount) {
                    newNum = stringAsInt;
                } else if (hasMax && stringAsInt > maxAmount) {
                    newNum = 0; // Dont allow larger than maximum values
                } else {
                    newNum = stringAsInt; // hasMax is false, allow any value
                }
            } catch (NumberFormatException e) {
                newNum = 0; // Set newNum to 0
            }
            if (newNum <= 0) { // Check for bad input and display error popup
                JOptionPane.showMessageDialog(this, errorMessage); // Display error message popup
            }
        }
        return newNum;
    }

    public void displayGameResultPopUp(Player player, Dealer dealer, Model.WinState winState) {
        String gameString = "<html>";
        switch (winState) { 
            case PLAYER_WIN -> gameString += "Player Wins!      New Balance: $" + player.getBalance(); // Player wins
            case DEALER_WIN -> gameString += "Dealer Wins.      New Balance: $" + player.getBalance(); // Dealer wins
            default -> gameString += "No One Wins.      New Balance: $" + player.getBalance(); // No winner
        }
        // Gets final cards and values based on game over & after dealer has dealed their final cards
        gameString += "<br/>Players Cards: ";
        for (Card c : player.getHand()) {
            gameString += c.toString() + ",";
        }
        gameString += "<br/>Players Hand Value: " + player.getHandValue();
        gameString += "<br/>Dealer Cards: ";
        for (Card c : dealer.getHand()) {
            gameString += c.toString() + ",";
        }
        gameString += "<br/>Dealers Hand Value: " + dealer.getHandValue();
        gameString += "</html>";
        JOptionPane.showMessageDialog(this, gameString); // Show popup with game results
    }
}
