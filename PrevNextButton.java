import javax.swing.*;
import java.awt.*;

public class PrevNextButton extends JFrame {

    Container screenContainer;
    private JButton prevButton, nextButton;

    PrevNextButton(Container screen_contanier) {
        screenContainer = screen_contanier;
    }

    public void showScreen() {
        prevButton = new JButton("Previous");
        prevButton.setPreferredSize(new Dimension(10, 4));
        nextButton = new JButton("Previous");
        nextButton.setPreferredSize(new Dimension(10, 4));
        screenContainer.add(prevButton);
        screenContainer.add(nextButton);

    }

}
