package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class ProcedureScreen extends JFrame {

    public MainPanels mainPanels;
    public ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    private int operationNum = -1;
    private int procedurePanelNum;
    private int procedureListLength = 0;
    private String value_str = "";

    private List<JCheckBox> procedureCheckbox = new ArrayList<>();

    ProcedureScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int operation_num,
            int procedure_panel_num) {
        procedureDocData = procedure_doc_data;
        operationNum = operation_num;
        procedurePanelNum = procedure_panel_num;

        StringBuilder buf = new StringBuilder();
        buf.append("ProcedureScreen");
        buf.append(operation_num);
        buf.append("_");
        buf.append(procedure_panel_num);
        SCREEN_NAME = buf.toString();

        mainPanels = main_panels;
        mainPanels.add(createProcedureScreenPanel(), SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            procedureDocData.printChecklist();
        }
        return 1; // ok
    }

    private JPanel createProcedureScreenPanel() {
        if (ProcedureList.PROCEDURE_LIST_INFO[operationNum][procedurePanelNum][1] == 1) {
            return createProcedureKeyboardPanel();
        }
        return createProcedureCheckboxPanel();
    }

    private JPanel createProcedureCheckboxPanel() {
        procedureListLength = ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum].length;
        JPanel procedureCheckboxPanel = new JPanel(new GridLayout(procedureListLength, 1));
        for (int i = 0; i < procedureListLength; i++) {
            procedureCheckbox.add(new JCheckBox(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][i]));
            procedureCheckbox.get(i).setHorizontalAlignment(JLabel.CENTER);
            procedureCheckboxPanel.add(procedureCheckbox.get(i));
        }
        for (int i = 0; i < procedureListLength; i++) {
            int this_box_num = i;
            procedureCheckbox.get(this_box_num).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    procedureDocData.checklist
                            .put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][this_box_num], 1.0);
                }
            });
        }
        return procedureCheckboxPanel;
    }

    private JPanel createProcedureKeyboardPanel() {
        JPanel procedureKeyboardPanel = new JPanel(new GridLayout(2, 1));
        JPanel procedureNameAndValuePanel = new JPanel(new GridLayout(2, 1));
        JLabel procedureValueLabel = new JLabel(value_str);
        procedureNameAndValuePanel.add(new JLabel(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0]));
        procedureNameAndValuePanel.add(procedureValueLabel);
        procedureKeyboardPanel.add(procedureNameAndValuePanel);
        JPanel keyboardPanel = new JPanel(new GridLayout(4, 3));
        JButton numberButtons[] = new JButton[10];
        JButton deleteButton = new JButton("Delete");
        JButton pointButton = new JButton(".");
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        for (int i = 1; i < 10; i++) {
            keyboardPanel.add(numberButtons[i]);
        }
        keyboardPanel.add(pointButton);
        keyboardPanel.add(numberButtons[0]);
        keyboardPanel.add(deleteButton);

        for (int i = 0; i < 10; i++) {
            String tempValue = String.valueOf(i);
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    value_str += tempValue;
                    procedureValueLabel.setText("Value: " + value_str);
                    procedureDocData.checklist.put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0],
                            Double.parseDouble(value_str));
                }
            });
        }
        pointButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!value_str.contains(".")) {
                    value_str += ".";
                    procedureValueLabel.setText("Value: " + value_str);
                    procedureDocData.checklist.put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0],
                            Double.parseDouble(value_str));
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (value_str.length() > 0) {
                    value_str = value_str.substring(0, value_str.length() - 1);
                }
                procedureValueLabel.setText("Value: " + value_str);
                procedureDocData.checklist.put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0],
                        Double.parseDouble(value_str));
            }
        });

        procedureKeyboardPanel.add(keyboardPanel);
        return procedureKeyboardPanel;

    }

}
