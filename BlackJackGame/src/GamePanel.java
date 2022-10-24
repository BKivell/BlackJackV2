
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brad Kivell
 */
public class GamePanel extends JPanel {

    private View view;
    private JLabel centerText;
    private JButton hitButton;
    private JButton standButton;

    //=============================[CONSTRUCTOR]=============================
    public GamePanel(View view) {
        this.view = view;
        this.setSize(view.getWidth(), view.getHeight()); // Set panel size as frame size
        initComponents(); // Setup componenets
        this.setLayout(null);
        this.setBackground(Color.black);
    }

    //=============================[COMPONENT SETUP]=============================
    private void initComponents() {
        // centerText Setup
        centerText = new JLabel();
        centerText.setText("[TABLE INFORMATION]");
        centerText.setSize(view.getWidth() - 200, 200);
        centerText.setLocation(getCenterXAlign(centerText), getCenterYAlign(centerText));
        centerText.setVisible(true);

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

        this.add(centerText);
        this.add(standButton);
        this.add(hitButton);

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
}
