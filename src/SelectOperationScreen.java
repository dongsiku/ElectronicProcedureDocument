package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectOperationScreen {

    private ProcedureDocData procedureDocData;
    private String SCREEN_NAME = "SelectOperationScreen";
    private JCheckBox[] operationCheckbox = { new JCheckBox("操作１"), new JCheckBox("操作２") };
    private int operation_num = -1;

    SelectOperationScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data) {
        procedureDocData = procedure_doc_data;

        JPanel SelectOperationScreenPanel = createSelectOperationScreenPanel();
        listenOperationCheckbox();

        main_panels.add(SelectOperationScreenPanel, SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            if (operation_num < 0) {
                return 0; // NG
            }
            procedureDocData.data.put("operationNum", operation_num);
            procedureDocData.printData();
        }
        return 1; // ok
    }

    private JPanel createSelectOperationScreenPanel() {
        JPanel selectOperationScreenPanel = new JPanel(new GridLayout(2, 1));
        JPanel selectOperationPanel = new JPanel(new GridLayout(1, 2));
        JLabel notationLabel = new JLabel("実施操作名");
        notationLabel.setHorizontalAlignment(JLabel.CENTER);
        selectOperationScreenPanel.add(notationLabel);
        for (int i = 0; i < 2; i++) {
            operationCheckbox[i].setHorizontalAlignment(JLabel.CENTER);
            operationCheckbox[i].setBorderPainted(true);
            selectOperationPanel.add(operationCheckbox[i]);
        }
        selectOperationScreenPanel.add(selectOperationPanel);
        return selectOperationScreenPanel;
    }

    private void listenOperationCheckbox() {
        for (int i = 0; i < 2; i++) {
            int this_box_num = i;
            int another_box_num = 1 - i;
            operationCheckbox[this_box_num].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    operationCheckbox[another_box_num].setSelected(false);
                    operation_num = this_box_num;
                }
            });
        }
    }
}
