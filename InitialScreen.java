import javax.swing.*;
import java.awt.*;

public class InitialScreen extends JFrame {

    Container initialscreenContainer;
    private JLabel startLabel;

    InitialScreen(Container initial_screen_contanier) {
        initialscreenContainer = initial_screen_contanier;
    }

    public void showScreen() {
        PrevNextButton prevnextButton = new PrevNextButton(initialscreenContainer);

        startLabel = new JLabel("電子化手順書");
        // initialscreenContainer.add(startLabel);
        startLabel.setFont(new Font("Arial", Font.BOLD, 32));
        startLabel.setSize(startLabel.getPreferredSize());
        startLabel.setHorizontalAlignment(JLabel.CENTER);

        initialscreenContainer.add(startLabel);
        prevnextButton.showPrevNextButton();
    }

}
