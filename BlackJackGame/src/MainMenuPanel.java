
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brad and Shelvin Kumar
 */
public class MainMenuPanel extends JPanel {

    //=============================[VARIABLES]=============================
    private View view;

    //=============================[PANEL COMPONENTS]=============================
    private JButton playButton;
    private JButton infoButton;
    private JButton depositButton;
    private JButton exitButton;
    private JButton logOutButton;

    private JLabel balanceLabel;

    //=============================[CONSTRUCTOR]=============================
    public MainMenuPanel(View view) {
        this.view = view;
        this.setSize(700, 700); // Set panel size as frame size
        initComponents(); // Setup componenets
        this.setLayout(null);
        this.setBackground(Color.black);
    }

    //=============================[COMPONENT SETUP]=============================
    private void initComponents() {
        // Play Button Setup
        playButton = new JButton();
        playButton.setText("Play");
        playButton.setSize(175, 30);
        playButton.setBackground(view.buttonColor);
        playButton.setLocation(getCenterXAlign(playButton), getCenterYAlign(playButton) + 100);
        playButton.setVisible(true);

        // infoButton Button Setup
        infoButton = new JButton();
        infoButton.setText("Help");
        infoButton.setBackground(view.buttonColor);
        infoButton.setSize(150, 30);
        infoButton.setLocation(getCenterXAlign(infoButton), getCenterYAlign(infoButton) + 140);
        infoButton.setVisible(true);

        // exitButton Button Setup
        exitButton = new JButton();
        exitButton.setText("Exit");
        exitButton.setBackground(view.buttonColor);
        exitButton.setSize(75, 30);
        exitButton.setLocation((view.getWidth() - exitButton.getWidth()) - 30, view.getHeight() - exitButton.getHeight() - 50);
        exitButton.setVisible(true);

        // log Out Button Setup
        logOutButton = new JButton();
        logOutButton.setText("Log Out");
        logOutButton.setBackground(view.buttonColor);
        logOutButton.setSize(100, 30);
        logOutButton.setLocation((view.getWidth() - logOutButton.getWidth()) - 30, 20);
        logOutButton.setVisible(true);

        // balance Label  Setup
        balanceLabel = new JLabel();
        balanceLabel.setText("Balance: 0");
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setSize(100, 20);
        balanceLabel.setLocation(20, 20);
        balanceLabel.setVisible(true);

        // deposit Button Button Setup
        depositButton = new JButton();
        depositButton.setText("Deposit Funds");
        depositButton.setBackground(view.buttonColor);
        depositButton.setSize(130, 20);
        depositButton.setLocation(balanceLabel.getWidth() + 50, 20);
        depositButton.setVisible(true);

        this.add(playButton);
        this.add(infoButton);
        this.add(depositButton);
        this.add(exitButton);
        this.add(logOutButton);
        this.add(balanceLabel);
    }

    //=============================[FUNCTIONS]=============================
    // Returns x center position accounting for component width
    private int getCenterXAlign(JComponent comp) {
        return getWidth() / 2 - comp.getSize().width / 2;
    }

    // Returns y center position accounting for component height
    private int getCenterYAlign(JComponent comp) {
        return getHeight() / 2 - comp.getSize().height / 2;
    }

    //=============================[GET COMPONENTS]=============================
    // Return play button
    public JButton getPlayButton() {
        return playButton;
    }

    // Return infoButton
    public JButton getInfoButton() {
        return infoButton;
    }

    // Return deposit button
    public JButton getDepositButton() {
        return depositButton;
    }

    // Return exit button
    public JButton getExitButton() {
        return exitButton;
    }

    // Return log out button
    public JButton getLogOutButton() {
        return logOutButton;
    }

    // Return balance Label
    public JLabel getBalanceLabel() {
        return balanceLabel;
    }

}
