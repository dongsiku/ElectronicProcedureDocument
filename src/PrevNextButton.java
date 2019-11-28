package src;

import javax.swing.*;
import java.awt.*;

public class PrevNextButton {

    Container screenContainer;
    public JButton prevButton = new JButton("");
    public JButton nextButton = new JButton("");

    PrevNextButton(Container screen_contanier) {
        screenContainer = screen_contanier;
    }

    public void showPrevNextButton() {
        JPanel prevnextPanel = new JPanel(new GridLayout(1, 2));

        Dimension prevnextButtonDimension = new Dimension(320, 64);
        prevButton.setPreferredSize(prevnextButtonDimension);
        nextButton.setPreferredSize(prevnextButtonDimension);

        prevnextPanel.add(prevButton);
        prevnextPanel.add(nextButton);

        screenContainer.add(prevnextPanel, BorderLayout.NORTH);

    }

    public void setNextButtonDefaultText() {
        nextButton.setText("次へ >");
    }

    public void setPrevButtonDefaultText() {
        prevButton.setText("< 前へ");
    }
}
