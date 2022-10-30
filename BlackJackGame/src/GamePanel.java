
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class GamePanel extends JPanel {

    private View view;
    private JLabel centerText;
    private JLabel aceCardText;
    private JButton hitButton;
    private JButton standButton;
    private JButton changeAceCardButton;

    //=============================[CONSTRUCTOR]=============================
    public GamePanel(View view) {
        this.view = view;
        this.setSize(view.getWidth(), view.getHeight()); // Set panel size as frame size
        initComponents(); // Setup componenets
        this.setLayout(null);
        this.setBackground(Color.black);
    }

    //=============================[COMPONENT SETUP]=============================
    // Sets up all panel components
    private void initComponents() {
        // centerText Setup
        centerText = new JLabel();
        centerText.setText("[TABLE INFORMATION]");
        centerText.setForeground(view.buttonColor);
        centerText.setFont(new Font("Arial", Font.PLAIN, 18));
        centerText.setSize(view.getWidth() - 100, 400);
        centerText.setLocation(getCenterXAlign(centerText), getCenterYAlign(centerText) - 200);
        centerText.setVisible(true);

        aceCardText = new JLabel();
        aceCardText.setText("");
        aceCardText.setForeground(view.buttonColor);
        aceCardText.setSize(view.getWidth() - 200, 100);
        aceCardText.setLocation(getCenterXAlign(centerText), getCenterYAlign(centerText) + 100);
        aceCardText.setVisible(true);

        // Hit Button Setup
        hitButton = new JButton();
        hitButton.setText("Hit");
        hitButton.setBackground(view.buttonColor);
        hitButton.setSize(75, 30);
        hitButton.setLocation(20, view.getHeight() - hitButton.getHeight() - 50);
        hitButton.setVisible(true);

        // Stand Button Setup
        standButton = new JButton();
        standButton.setText("Stand");
        standButton.setBackground(view.buttonColor);
        standButton.setSize(75, 30);
        standButton.setLocation((view.getWidth() - standButton.getWidth()) - 30, view.getHeight() - standButton.getHeight() - 50);
        standButton.setVisible(true);

        // Stand Button Setup
        changeAceCardButton = new JButton();
        changeAceCardButton.setText("Change Ace Value");
        changeAceCardButton.setBackground(view.buttonColor);
        changeAceCardButton.setSize(150, 30);
        changeAceCardButton.setLocation((view.getWidth() / 2) - changeAceCardButton.getWidth() / 2, view.getHeight() - 80);
        changeAceCardButton.setVisible(true);

        // Add components to panel
        this.add(centerText);
        this.add(aceCardText);
        this.add(standButton);
        this.add(hitButton);
        this.add(changeAceCardButton);

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
    public JLabel getCenterText() {
        return centerText;
    }

    public JButton getStandButton() {
        return standButton;
    }

    public JButton getHitButtont() {
        return hitButton;
    }

    public JButton getAceChangeButton() {
        return changeAceCardButton;
    }

    public JLabel getAceCardText() {
        return aceCardText;
    }
}
