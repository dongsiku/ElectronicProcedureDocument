package src;

import javax.swing.*;
import java.awt.*;

public class PrevNextButton {

    public JPanel prevnextButtonPanel = new JPanel(new GridLayout(1, 2));
    public JButton prevButton = new JButton();
    public JButton nextButton = new JButton();

    PrevNextButton() {
        setPrevNextButtonPanel();
        setInitialText();
    }

    private void setPrevNextButtonPanel() {
        Dimension prevnextButtonDimension = new Dimension(320, 64);
        prevButton.setPreferredSize(prevnextButtonDimension);
        nextButton.setPreferredSize(prevnextButtonDimension);

        prevnextButtonPanel.add(prevButton);
        prevnextButtonPanel.add(nextButton);
    }

    public void setInitialText() {
        nextButton.setText("開始 >");
        prevButton.setText("");
    }

    public void setNextButtonStandardText() {
        nextButton.setText("次へ >");
    }

    public void setPrevButtonStandardText() {
        prevButton.setText("< 前へ");
    }
}
