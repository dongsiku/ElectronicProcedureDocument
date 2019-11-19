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
    // private int operationNum = -1;
    // private int procedureStepNum;
    private int procedureNameListSize = 0;

    private List<String> procedureNameList = new ArrayList<>();
    private List<JCheckBox> procedureCheckbox = new ArrayList<>();
    // private JPanel ProcedureScreenPanel;

    ProcedureScreen() {
        ;
    };

    ProcedureScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int operation_num,
            int procedure_step_num) {
        procedureDocData = procedure_doc_data;
        // procedureStepNum = procedure_step_num;
        CreateProcedureNameList createProcedureNameList = new CreateProcedureNameList(operation_num,
                procedure_step_num);
        procedureNameList = createProcedureNameList.returnProcedureNameList();

        StringBuilder buf = new StringBuilder();
        buf.append("ProcedureScreen");
        buf.append(operation_num);
        buf.append("_step");
        buf.append(procedure_step_num);
        SCREEN_NAME = buf.toString();

        mainPanels = main_panels;
        mainPanels.add(createProcedureScreenPanel(), SCREEN_NAME);
        listenProcedureCheckbox();
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            procedureDocData.printChecklist();
        }
        return 1; // ok
    }

    private JPanel createProcedureScreenPanel() {
        procedureNameListSize = procedureNameList.size();
        JPanel procedureScreenPanel = new JPanel(new GridLayout(procedureNameListSize, 1));
        for (int i = 0; i < procedureNameListSize; i++) {
            procedureCheckbox.add(new JCheckBox(procedureNameList.get(i)));
            procedureCheckbox.get(i).setHorizontalAlignment(JLabel.CENTER);
            procedureScreenPanel.add(procedureCheckbox.get(i));
        }
        return procedureScreenPanel;
    }

    private void listenProcedureCheckbox() {
        for (int i = 0; i < procedureNameListSize; i++) {
            int this_box_num = i;
            procedureCheckbox.get(this_box_num).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    procedureDocData.checklist.put(procedureNameList.get(this_box_num), 1);
                }
            });
        }
    }

}
