package src;

import javax.swing.*;
import java.awt.*;

public class PrevNextButton extends JFrame {

    Container screenContainer;
    public JButton prevButton, nextButton;
    private JPanel prevnextPanel;

    PrevNextButton(Container screen_contanier) {
        screenContainer = screen_contanier;
    }

    public void showPrevNextButton() {
        prevnextPanel = new JPanel(new GridLayout(1, 2));
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");

        Dimension prevnextButtonDimension = new Dimension(320, 64);
        prevButton.setPreferredSize(prevnextButtonDimension);
        nextButton.setPreferredSize(prevnextButtonDimension);

        prevnextPanel.add(prevButton);
        prevnextPanel.add(nextButton);

        screenContainer.add(prevnextPanel, BorderLayout.NORTH);

    }
}
