import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrevNextButton extends JFrame {

    Container screenContainer;
    private JButton prevButton, nextButton;
    private JPanel prevnextPanel;

    PrevNextButton(Container screen_contanier) {
        screenContainer = screen_contanier;
    }

    public void showPrevNextButton() {
        prevnextPanel = new JPanel();
        // public void setBounds(int x, int y, int width, int height);
        // int button_x = 300;
        // int button_y = 100;
        // prevButton.setBounds(button_x, button_y, 100, 50);
        // nextButton.setBounds(button_x, button_y, 100, 50);
        // prevnextPanel.setBounds(620, 600, 200, 50);
        prevnextPanel.setLayout(new GridLayout(1, 2, 20, 20));
        prevButton = new JButton("Previous");
        nextButton = new JButton("Next");

        Dimension prevnextButtonDimension = new Dimension(320, 80);
        prevButton.setPreferredSize(prevnextButtonDimension);
        nextButton.setPreferredSize(prevnextButtonDimension);

        prevnextPanel.add(prevButton);
        prevnextPanel.add(nextButton);
        prevnextPanel.setBackground(Color.ORANGE);

        screenContainer.add(prevnextPanel, BorderLayout.NORTH);

    }

    public void listenPrevNextButton(JPanel cardPanel) {
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ((CardLayout) cardPanel.getLayout()).next(cardPanel);
            }
        });
    }

}
