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

    // 画面遷移のためのボタンを設定する．
    private void setPrevNextButtonPanel() {
        Dimension prevnextButtonDimension = new Dimension(320, 64);
        prevButton.setPreferredSize(prevnextButtonDimension);
        nextButton.setPreferredSize(prevnextButtonDimension);

        prevnextButtonPanel.add(prevButton);
        prevnextButtonPanel.add(nextButton);
    }

    // 初期画面でのそれぞれのボタンのテキストを設定する．
    public void setInitialText() {
        nextButton.setText("開始 >");
        prevButton.setText("");
    }

    // 「次へ >」ボタンを標準のテキストである「次へ >」に設定する．
    public void setNextButtonStandardText() {
        nextButton.setText("次へ >");
    }

    // 「< 前へ」ボタンを標準のテキストである「< 前へ」に設定する．
    public void setPrevButtonStandardText() {
        prevButton.setText("< 前へ");
    }
}
