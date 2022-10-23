
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Brad Kivell
 */
public class View extends JFrame {

    //=============================[PANELS]=============================
    private LoginPanel loginPanel;
    private MainMenuPanel mainMenuPanel;
    //public Image bgImage1;
    //public Image bgImage2;

    //=============================[CONSTRUCTOR]=============================
    public View() {
        this.setSize(720, 720);
        //bgImage1 = new ImageIcon("PokerIcon.png").getImage();
        //bgImage2 = new ImageIcon("BlackJackImage.png").getImage();
        initComponents();
        panelStartUp();
    }

    //=============================[FRAME SETUP]=============================S
    private void initComponents() {
        this.loginPanel = new LoginPanel(this);
        this.mainMenuPanel = new MainMenuPanel(this);
        this.add(loginPanel); // Add panel to frame
        this.add(mainMenuPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    private void panelStartUp() {
        this.loginPanel.setVisible(true);
        this.mainMenuPanel.setVisible(false);
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

}
