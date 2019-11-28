package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class ProcedureScreen {

    private ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    private int operationNum = -1;
    private int procedurePanelNum;
    private int procedureListLength = 0;
    private String valueStr = "";
    private JLabel operationNameLabel = new JLabel();
    private JLabel subOperationNameLabel = new JLabel();
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

        main_panels.add(createProcedureScreenPanel(), SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            procedureDocData.printChecklist();
        }
        return 1; // ok
    }

    private JPanel createProcedureScreenPanel() {
        if (operationNum == 0) {
            operationNameLabel.setText("操作１");
        } else {
            operationNameLabel.setText("操作２");
        }

        if (ProcedureList.PROCEDURE_LIST_INFO[operationNum][procedurePanelNum][0] == 0) {
            subOperationNameLabel.setText("操作前確認");
        } else if (ProcedureList.PROCEDURE_LIST_INFO[operationNum][procedurePanelNum][0] == 1) {
            subOperationNameLabel.setText("起動操作");
        } else {
            subOperationNameLabel.setText("操作後確認");
        }

        operationNameLabel.setHorizontalAlignment(JLabel.CENTER);
        subOperationNameLabel.setHorizontalAlignment(JLabel.CENTER);

        if (ProcedureList.PROCEDURE_LIST_INFO[operationNum][procedurePanelNum][1] == 1) {
            return createProcedureKeyboardPanel();
        }
        return createProcedureCheckboxPanel();
    }

    private JPanel createProcedureCheckboxPanel() {
        procedureListLength = ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum].length;
        JPanel procedureCheckboxPanel = new JPanel(new GridLayout(5, 2));

        procedureCheckboxPanel.add(operationNameLabel);
        procedureCheckboxPanel.add(subOperationNameLabel);
        for (int i = 0; i < procedureListLength; i++) {
            procedureCheckbox.add(new JCheckBox(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][i]));
            procedureCheckbox.get(i).setHorizontalAlignment(JLabel.CENTER);
            procedureCheckbox.get(i).setBorderPainted(true);
            procedureCheckboxPanel.add(procedureCheckbox.get(i));
        }

        // Add blank labels to fix design when (procedureListLength + 2) < 6
        // 2 means the number of operationNameLabel and subOperationNameLabel
        int blankLabelNum = 4 - procedureListLength;
        while (blankLabelNum > 0) {
            procedureCheckboxPanel.add(new JLabel());
            blankLabelNum -= 1;
        }

        for (int i = 0; i < procedureListLength; i++) {
            int this_box_num = i;
            procedureCheckbox.get(this_box_num).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (procedureCheckbox.get(this_box_num).isSelected()) {
                        procedureDocData.checklist
                                .put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][this_box_num], 1.0); // True
                    } else {
                        procedureDocData.checklist
                                .put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][this_box_num], -1.0); // False
                    }
                }
            });
        }
        return procedureCheckboxPanel;
    }

    private JPanel createProcedureKeyboardPanel() {
        JPanel operationNamePanel = new JPanel(new GridLayout(1, 2));
        operationNamePanel.add(operationNameLabel);
        operationNamePanel.add(subOperationNameLabel);

        JPanel procedureNameAndValuePanel = new JPanel(new GridLayout(2, 1));
        JLabel procedureNameLabel = new JLabel(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0]);
        procedureNameLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel procedureValueLabel = new JLabel("Value: ");
        procedureValueLabel.setHorizontalAlignment(JLabel.CENTER);
        procedureNameAndValuePanel.add(procedureNameLabel);
        procedureNameAndValuePanel.add(procedureValueLabel);

        JPanel operationNameAndProcedureNameAndValuePanel = new JPanel(new GridLayout(2, 1));
        operationNameAndProcedureNameAndValuePanel.add(operationNamePanel);
        operationNameAndProcedureNameAndValuePanel.add(procedureNameAndValuePanel);

        JPanel procedureKeyboardPanel = new JPanel(new GridLayout(2, 1));
        procedureKeyboardPanel.add(operationNameAndProcedureNameAndValuePanel);
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
                    valueStr += tempValue;
                    procedureValueLabel.setText("Value: " + valueStr);
                    updateChecklist();
                }
            });
        }
        pointButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!valueStr.contains(".")) {
                    valueStr += ".";
                    procedureValueLabel.setText("Value: " + valueStr);
                }
                updateChecklist();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (valueStr.length() > 0) {
                    valueStr = valueStr.substring(0, valueStr.length() - 1);
                    procedureValueLabel.setText("Value: " + valueStr);
                }
                updateChecklist();
            }
        });

        procedureKeyboardPanel.add(keyboardPanel);
        return procedureKeyboardPanel;

    }

    private void updateChecklist() {
        if (valueStr.equals(".")) {
            procedureDocData.checklist.put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0], 0.0);
        } else if (valueStr.equals("")) {
            procedureDocData.checklist.put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0], -1.0);
        } else {
            procedureDocData.checklist.put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0],
                    Double.parseDouble(valueStr));
        }
    }

}
