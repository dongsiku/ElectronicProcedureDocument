import javax.swing.*;
import java.awt.*;

public class InitialScreen extends JFrame {

    private JLabel startLabel;
    private PrevNextButton prevnextButton;
    private JPanel mainPanel = new JPanel();

    InitialScreen(JPanel main_panel, PrevNextButton prev_next_button) {
        mainPanel = main_panel;
        prevnextButton = prev_next_button;
        mainPanel.add("init", createInitialScreenPanel());
    }

    private JPanel createInitialScreenPanel() {
        JPanel initialscreenPanel = new JPanel();
        startLabel = new JLabel("電子化手順書");
        // initialscreenContainer.add(startLabel);
        startLabel.setFont(new Font("Arial", Font.BOLD, 32));
        startLabel.setSize(startLabel.getPreferredSize());
        startLabel.setHorizontalAlignment(JLabel.CENTER);

        initialscreenPanel.add(startLabel);
        return initialscreenPanel;
    }

}
