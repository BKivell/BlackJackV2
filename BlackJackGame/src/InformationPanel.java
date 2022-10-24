
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brad Kivell
 */
public class InformationPanel extends JPanel {

    //=============================[VARIABLES]=============================
    private View view;
    private String infoString;

    //=============================[PANEL COMPONENTS]=============================
    private JButton returnToMenu;
    private JLabel infoLabel;

    //=============================[CONSTRUCTOR]=============================
    public InformationPanel(View view, String infoString) {
        this.view = view;
        this.infoString = infoString;
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
        infoLabel.setSize(view.getWidth()-100, view.getHeight()-350);
        infoLabel.setLocation(20, 50);
        infoLabel.setVisible(true);
        
        this.add(returnToMenu);
        this.add(infoLabel);
    }

    public JButton getReturnToMenuButton() {
        return returnToMenu;
    }

}
