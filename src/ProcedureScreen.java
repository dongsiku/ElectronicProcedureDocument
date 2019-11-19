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

    // private List<String> procedureNameList = new ArrayList<>();
    private List<JCheckBox> procedureCheckbox = new ArrayList<>();
    // private JPanel ProcedureScreenPanel;
    CreateProcedureNameList createProcedureNameList;

    ProcedureScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int operation_num,
            int procedure_panel_num) {
        procedureDocData = procedure_doc_data;
        operationNum = operation_num;
        procedurePanelNum = procedure_panel_num;
        createProcedureNameList = new CreateProcedureNameList();

        StringBuilder buf = new StringBuilder();
        buf.append("ProcedureScreen");
        buf.append(operation_num);
        buf.append("_");
        buf.append(procedure_panel_num);
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
        procedureListLength = CreateProcedureNameList.procedureList[operationNum][procedurePanelNum].length;
        JPanel procedureScreenPanel = new JPanel(new GridLayout(procedureListLength, 1));
        for (int i = 0; i < procedureListLength; i++) {
            procedureCheckbox
                    .add(new JCheckBox(CreateProcedureNameList.procedureList[operationNum][procedurePanelNum][i]));
            procedureCheckbox.get(i).setHorizontalAlignment(JLabel.CENTER);
            procedureScreenPanel.add(procedureCheckbox.get(i));
        }
        return procedureScreenPanel;
    }

    private void listenProcedureCheckbox() {
        for (int i = 0; i < procedureListLength; i++) {
            int this_box_num = i;
            procedureCheckbox.get(this_box_num).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    procedureDocData.checklist.put(
                            CreateProcedureNameList.procedureList[operationNum][procedurePanelNum][this_box_num], 1);
                }
            });
        }
    }

}
