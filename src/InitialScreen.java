package src;

import javax.swing.*;
import java.awt.*;

public class InitialScreen extends JFrame {

    public MainPanels mainPanels;

    InitialScreen(MainPanels main_panels) {
        mainPanels = main_panels;
        mainPanels.add(createInitialScreenPanel(), "InitialScreen");
    }

    public int update(String currentScreenName) {
        return 1; // ok
    }

    private JPanel createInitialScreenPanel() {
        JPanel initialscreenPanel = new JPanel();
        initialscreenPanel.setLayout(new BorderLayout());

        JLabel startLabel = new JLabel("電子化手順書");
        startLabel.setFont(new Font("Arial", Font.BOLD, 32));
        // startLabel.setSize(startLabel.getPreferredSize());
        startLabel.setHorizontalAlignment(JLabel.CENTER);
        startLabel.setVerticalAlignment(JLabel.CENTER);

        initialscreenPanel.add(startLabel);
        return initialscreenPanel;
    }

}
