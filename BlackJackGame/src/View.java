
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Brad Kivell
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

}
