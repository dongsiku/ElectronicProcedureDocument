import javax.swing.*;
import java.awt.*;

public class InputIdDate extends JFrame {

    Container initialscreenContainer;

    InputIdDate(Container initial_screen_contanier) {
        initialscreenContainer = initial_screen_contanier;
    }

    public void showScreen() {
        PrevNextButton prevnextButton = new PrevNextButton(initialscreenContainer);
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JPanel id_date_panels[] = { new JPanel(new GridLayout(1, 2)), new JPanel(new GridLayout(1, 2)) };
        JPanel software_keyboard = new JPanel(new GridLayout(4, 3));
        JButton software_button[] = new JButton[10];
        for (int i = 0; i < 10; i++) {
            software_button[i] = new JButton(String.valueOf(i));
        }

        id_date_panels[0].add(new JLabel("ID: "));
        id_date_panels[1].add(new JLabel("DATE: "));
        for (int i = 1; i < 10; i++) {
            software_keyboard.add(software_button[i]);
        }
        software_keyboard.add(software_button[0]);
        for (

        JPanel id_date_panel : id_date_panels) {
            panel.add(id_date_panel);
        }

        panel.add(software_keyboard);

        initialscreenContainer.add(panel);
        prevnextButton.showPrevNextButton();
    }

}
