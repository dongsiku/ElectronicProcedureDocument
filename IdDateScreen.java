import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class IdDateScreen extends JFrame {

    public MainPanels mainPanels;

    private JLabel idLabel = new JLabel("ID: ");;
    private String id_num_str = "";
    public ProcedureDocData procedureDocData;
    private int operationYear, operationMonth, operationDate;
    private int operationYMD;
    private String SCREEN_NAME = "IdDateScreen";

    IdDateScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data) {
        procedureDocData = procedure_doc_data;

        JPanel IdDateScreenPanel = new JPanel(new GridLayout(3, 1));
        IdDateScreenPanel.add(createDatePanel());
        IdDateScreenPanel.add(createIdPanel());
        IdDateScreenPanel.add(createKeyboardPanel());

        mainPanels = main_panels;
        mainPanels.add(IdDateScreenPanel, "IdDateScreen");
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {

            if (id_num_str.equals("")) {
                return 0;
            }
            if (id_num_str.length() == 4) {
                createOperationYMD();
                procedureDocData.put("operatorID", Integer.parseInt(id_num_str));
                procedureDocData.put("operationDate", operationYMD);
                procedureDocData.print();
            }
        }
        return 1; // ok
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
        int prevYear = 5;
        int initYear = cal.get(Calendar.YEAR);
        int initMonth = cal.get(Calendar.MONTH);
        int initDate = cal.get(Calendar.DATE);
        for (int i = 0; i < 10; i++) {
            combodata_year[i] = String.valueOf(i + initYear - prevYear);
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

        combo_year.setSelectedIndex(prevYear);
        combo_month.setSelectedIndex(initMonth);
        combo_date.setSelectedIndex(initDate - 1);

        operationYear = initYear;
        operationMonth = initMonth;
        operationDate = initDate;

        combo_year.setPreferredSize(dimension_combo);
        combo_month.setPreferredSize(dimension_combo);
        combo_date.setPreferredSize(dimension_combo);

        datePanel.add(new JLabel("Date: "));
        datePanel.add(combo_year);
        datePanel.add(combo_month);
        datePanel.add(combo_date);
        combo_year.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                operationDate = combo_year.getSelectedIndex() + initYear - prevYear;
                createOperationYMD();
            }

        });
        combo_month.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                operationDate = combo_month.getSelectedIndex();
                createOperationYMD();
            }

        });
        combo_date.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                operationDate = combo_date.getSelectedIndex();
                createOperationYMD();
            }

        });
        return datePanel;
    }

    private void createOperationYMD() {
        operationYMD = (operationYear % 100) * 10000 + operationMonth * 100 + operationDate;
    }
}
