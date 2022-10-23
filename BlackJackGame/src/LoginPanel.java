
import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Brad Kivell
 */
public class LoginPanel extends JPanel {

    //=============================[VARIABLES]=============================
    private View view;
    private String title = "<html> Welcome to BlackJack<br/><br/><br/><br/><br/><br/>Please enter Username</html>";

    //=============================[PANEL COMPONENTS]=============================
    private JButton logInButton;
    private JTextField userNameTextField;
    private JLabel logInText;

    //=============================[CONSTRUCTOR]=============================
    public LoginPanel(View view) {
        this.view = view;
        this.setSize(view.getWidth(), view.getHeight()); // Set panel size as frame size
        initComponents(); // Setup componenets
        this.setLayout(null);
        //view.getGraphics().drawImage(view.bgImage1, 0, 0, view);
        this.setBackground(Color.black);
    }

    //=============================[COMPONENT SETUP]=============================
    private void initComponents() {
        // Login Button Setup
        logInButton = new JButton();
        logInButton.setText("Login");
        logInButton.setSize(75, 30);
        logInButton.setLocation(getCenterXAlign(logInButton), getCenterYAlign(logInButton) + 50);
        logInButton.setVisible(true);

        // userNameTextField Setup
        userNameTextField = new JTextField();
        userNameTextField.setSize(300, 30);
        userNameTextField.setLocation(getCenterXAlign(userNameTextField), getCenterYAlign(userNameTextField));
        userNameTextField.setVisible(true);

        // Login Page Text Setup
        logInText = new JLabel("", SwingConstants.CENTER);
        logInText.setSize(300, 120);
        logInText.setLocation(getCenterXAlign(logInText), getCenterYAlign(logInText) - 100);
        logInText.setText(title);
        logInText.setForeground(Color.WHITE);
        logInText.setVisible(true);

        // Add comps to panel 
        this.add(logInButton);
        this.add(userNameTextField);
        this.add(logInText);
    }

    //=============================[FUNCTIONS]=============================
    // return the logInButton
    public JButton getLogInButton() {
        return logInButton;
    }

    // return the userNameTextField
    public JTextField getUserNameTextField() {
        return userNameTextField;
    }

    // Returns x center position accounting for component width
    private int getCenterXAlign(JComponent comp) {
        return getWidth() / 2 - comp.getSize().width / 2;
    }

    // Returns y center position accounting for component height
    private int getCenterYAlign(Component comp) {
        return getHeight() / 2 - comp.getSize().height / 2;
    }
}
