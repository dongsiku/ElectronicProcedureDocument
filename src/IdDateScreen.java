package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;

public class IdDateScreen {

    private JLabel idLabel = new JLabel();
    private String idNumStr = "";
    private ProcedureDocData procedureDocData;
    private int operationYear, operationMonth, operationDate;
    private String SCREEN_NAME = "IdDateScreen";
    private PrevNextButton prevNextButton;

    IdDateScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, PrevNextButton prev_next_button) {
        procedureDocData = procedure_doc_data;
        prevNextButton = prev_next_button;

        JPanel IdDateScreenPanel = new JPanel(new GridLayout(2, 1));
        IdDateScreenPanel.add(createDatePanel());
        IdDateScreenPanel.add(createIdPanel());

        JPanel IdDateScreenKeyboardPanel = new JPanel(new GridLayout(2, 1));
        IdDateScreenKeyboardPanel.add(IdDateScreenPanel);
        IdDateScreenKeyboardPanel.add(createKeyboardPanel());

        main_panels.add(IdDateScreenKeyboardPanel, "IdDateScreen");
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            if (idNumStr.length() == 4) {
                procedureDocData.data.put("operatorID", Integer.parseInt(idNumStr));
                procedureDocData.data.put("operationYear", operationYear);
                procedureDocData.data.put("operationMonth", operationMonth);
                procedureDocData.data.put("operationDate", operationDate);
                procedureDocData.printData();
            } else {
                return 0;
            }
        }
        return 1; // ok
    }

    private JPanel createIdPanel() {
        JPanel idPanel = new JPanel(new FlowLayout());
        setIdLabel();
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
                    if (idNumStr.length() < 4) {
                        idNumStr += tempNum;
                    }
                    setIdLabel();
                    if (idNumStr.length() == 4) {
                        prevNextButton.setNextButtonDefaultText();
                    } else {
                        prevNextButton.nextButton.setText("");
                    }
                }
            });
        }
        deleteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (idNumStr.length() > 0) {
                    idNumStr = idNumStr.substring(0, idNumStr.length() - 1);
                }
                setIdLabel();
                prevNextButton.nextButton.setText("");
            }

        });

        return keyboardPanel;
    }

    private void setIdLabel() {
        StringBuilder idBuf = new StringBuilder();
        idBuf.append("操作者ID: ");
        idBuf.append(idNumStr);
        idLabel.setText(idBuf.toString());
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
        operationMonth = initMonth + 1;
        operationDate = initDate;

        combo_year.setPreferredSize(dimension_combo);
        combo_month.setPreferredSize(dimension_combo);
        combo_date.setPreferredSize(dimension_combo);

        datePanel.add(new JLabel("日付: "));
        datePanel.add(combo_year);
        datePanel.add(combo_month);
        datePanel.add(combo_date);

        combo_year.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operationYear = combo_year.getSelectedIndex() + initYear - prevYear;
            }
        });
        combo_month.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operationMonth = combo_month.getSelectedIndex() + 1;
            }
        });
        combo_date.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operationDate = combo_date.getSelectedIndex();
            }
        });
        return datePanel;
    }
}
