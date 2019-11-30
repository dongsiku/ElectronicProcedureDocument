package src;

import javax.swing.*;
import java.awt.*;

public class InitialScreen {

    InitialScreen(MainPanels main_panels) {
        // 初期画面をInitialScreenという名前で登録する．
        main_panels.add(createInitialScreenPanel(), "InitialScreen");
    }

    public int update() {
        // 特に確認することはないため，常時1（真）を返す
        return 1; // ok
    }

    private JPanel createInitialScreenPanel() {
        JLabel startLabel = new JLabel("電子化手順書");
        startLabel.setFont(new Font("Arial", Font.BOLD, 32));
        startLabel.setHorizontalAlignment(JLabel.CENTER);
        startLabel.setVerticalAlignment(JLabel.CENTER);

        JPanel initialscreenPanel = new JPanel();
        initialscreenPanel.setLayout(new BorderLayout());
        initialscreenPanel.add(startLabel);
        return initialscreenPanel;
    }
}
