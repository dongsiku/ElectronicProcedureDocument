import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputIdDate extends JFrame {

    Container initialscreenContainer;
    String id_num_str = "";

    InputIdDate(Container initial_screen_contanier) {
        initialscreenContainer = initial_screen_contanier;
    }

    public void showScreen() {
        PrevNextButton prevnextButton = new PrevNextButton(initialscreenContainer);
        JPanel panel = new JPanel(new GridLayout(3, 1));
        JPanel id_date_panels[] = { new JPanel(new GridLayout(1, 2)), new JPanel(new GridLayout(1, 2)) };
        JPanel software_keyboard = new JPanel(new GridLayout(4, 3));
        JButton software_buttons[] = new JButton[10];

        for (int i = 0; i < 10; i++) {
            software_buttons[i] = new JButton(String.valueOf(i));
        }

        JLabel idLabel = new JLabel("ID: " + id_num_str);
        id_date_panels[0].add(idLabel);
        id_date_panels[1].add(new JLabel("DATE: "));
        for (int i = 1; i < 10; i++) {
            software_keyboard.add(software_buttons[i]);
        }
        software_keyboard.add(new JLabel(""));
        software_keyboard.add(software_buttons[0]);
        JButton deleteButton = new JButton("Delete");
        software_keyboard.add(deleteButton);
        for (

        JPanel id_date_panel : id_date_panels) {
            panel.add(id_date_panel);
        }

        panel.add(software_keyboard);

        for (int i = 0; i < 10; i++) {
            String temp_id_num = String.valueOf(i);
            software_buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (id_num_str.length() < 4) {
                        id_num_str += temp_id_num;
                    }
                    idLabel.setText("ID: " + id_num_str);
                }
            });
        }
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id_num_str.length() > 0) {
                    id_num_str = id_num_str.substring(0, id_num_str.length() - 1);
                }
                idLabel.setText("ID: " + id_num_str);
            }
        });
        /*
         * String[] combodata = { "Swing", "Java2D", "Java3D", "JavaMail" }; JComboBox
         * combo = new JComboBox(combodata);
         * 
         * JPanel p = new JPanel(); p.add(combo);
         * 
         * getContentPane().add(p, BorderLayout.CENTER);
         * 
         * initialscreenContainer.add(panel); prevnextButton.showPrevNextButton();
         */
    }

}
