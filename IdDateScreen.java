import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class IdDateScreen extends JFrame {

    Container initialscreenContainer;
    JLabel idLabel = new JLabel("ID: ");;
    String id_num_str = "";

    IdDateScreen(Container initial_screen_contanier) {
        initialscreenContainer = initial_screen_contanier;
    }

    public void showScreen() {
        PrevNextButton prevnextButton = new PrevNextButton(initialscreenContainer);
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));

        mainPanel.add(createDatePanel());
        mainPanel.add(createIdPanel());
        mainPanel.add(createKeyboardPanel());

        initialscreenContainer.add(mainPanel);
        prevnextButton.showPrevNextButton();

    }

    private JPanel createIdPanel() {
        JPanel idPanel = new JPanel(new FlowLayout());
        idPanel.add(idLabel);
        return idPanel;
    }

    private JPanel createKeyboardPanel() {
        JPanel keyboardPanel = new JPanel(new GridLayout(4, 3));
        JButton numberButtons[] = new JButton[10];
        JButton deleteButton = new JButton("Delete");
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        for (int i = 1; i < 10; i++) {
            keyboardPanel.add(numberButtons[i]);
        }
        keyboardPanel.add(new JLabel(""));
        keyboardPanel.add(numberButtons[0]);
        keyboardPanel.add(deleteButton);

        for (int i = 0; i < 10; i++) {
            String tempNum = String.valueOf(i);
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (id_num_str.length() < 4) {
                        id_num_str += tempNum;
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

        return keyboardPanel;

    }

    private JPanel createDatePanel() {
        JPanel datePanel = new JPanel(new FlowLayout());
        Calendar cal = Calendar.getInstance();
        String[] combodata_year = new String[10];
        String[] combodata_month = new String[12];
        String[] combodata_date = new String[31];
        int prev_init_year = 5;
        int init_year = cal.get(Calendar.YEAR) - prev_init_year;
        for (int i = 0; i < 10; i++) {
            combodata_year[i] = String.valueOf(i + init_year);
        }
        for (int i = 0; i < 12; i++) {
            combodata_month[i] = String.valueOf(i + 1);
        }
        for (int i = 0; i < 31; i++) {
            combodata_date[i] = String.valueOf(i + 1);
        }
        JComboBox<String> combo_year = new JComboBox<>(combodata_year);
        JComboBox<String> combo_month = new JComboBox<>(combodata_month);
        JComboBox<String> combo_date = new JComboBox<>(combodata_date);
        Dimension dimension_combo = new Dimension(80, 30);

        combo_year.setSelectedIndex(prev_init_year);
        combo_month.setSelectedIndex(cal.get(Calendar.MONTH));
        combo_date.setSelectedIndex(cal.get(Calendar.DATE) - 1);

        combo_year.setPreferredSize(dimension_combo);
        combo_month.setPreferredSize(dimension_combo);
        combo_date.setPreferredSize(dimension_combo);

        datePanel.add(new JLabel("Date: "));
        datePanel.add(combo_year);
        datePanel.add(combo_month);
        datePanel.add(combo_date);
        return datePanel;
    }

}
