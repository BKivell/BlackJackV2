
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Brad Kivell - 20115449
 * @author Shelvin Kumar - 17985924
 */
public class InformationPanel extends JPanel {

    //=============================[VARIABLES]=============================
    private View view;
    private String infoString;

    //=============================[PANEL COMPONENTS]=============================
    private JButton returnToMenu;
    private JLabel infoLabel;

    //=============================[CONSTRUCTOR]=============================
    public InformationPanel(View view) {
        this.view = view;
        this.setSize(700, 700); // Set panel size as frame size
        initComponents(); // Setup componenets
        this.setLayout(null);
        this.setBackground(Color.black);
    }

    private void initComponents() {
        // Return to menu Button Setup
        returnToMenu = new JButton();
        returnToMenu.setText("Return To Menu");
        returnToMenu.setSize(200, 30);
        returnToMenu.setBackground(view.buttonColor);
        returnToMenu.setLocation(20, view.getHeight() - 100);
        returnToMenu.setVisible(true);

        // Info Label Setup
        infoLabel = new JLabel();
        infoLabel.setText(infoString);
        infoLabel.setForeground(view.buttonColor);
        infoLabel.setSize(view.getWidth() - 100, view.getHeight() - 350);
        infoLabel.setLocation(20, 50);
        infoLabel.setVisible(true);

        // Adds components to panel
        this.add(returnToMenu);
        this.add(infoLabel);
    }

    // Returns returnToMenu Jbutton
    public JButton getReturnToMenuButton() {
        return returnToMenu;
    }

    // Sets the information text with new string
    public void setInfoText(String newInfo) {
        this.infoString = newInfo;
        infoLabel.setText(infoString);
    }

}
