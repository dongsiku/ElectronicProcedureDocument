package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProcedureScreen extends JFrame {

    public MainPanels mainPanels;
    public ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    // private int operationNum = -1;
    // private int procedureStepNum;

    private List<String> procedureNameList = new ArrayList<>();
    private List<String> hasNumberForm = new ArrayList<>();
    private List<JCheckBox> operationCheckbox = new ArrayList<>();
    // private JPanel ProcedureScreenPanel;

    ProcedureScreen() {
        ;
    };

    ProcedureScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int operation_num,
            int procedure_step_num) {
        // procedureDocData = procedure_doc_data;
        // procedureStepNum = procedure_step_num;
        CreateProcedureNameList createProcedureNameList = new CreateProcedureNameList(operation_num,
                procedure_step_num);
        procedureNameList = createProcedureNameList.returnProcedureNameList();
        hasNumberForm = createProcedureNameList.returnHasNumberForm();

        StringBuilder buf = new StringBuilder();
        buf.append("ProcedureScreen");
        buf.append(operation_num);
        buf.append("_step");
        buf.append(procedure_step_num);
        SCREEN_NAME = buf.toString();

        mainPanels = main_panels;
        mainPanels.add(createProcedureScreenPanel(), SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            ;
        }
        return 1; // ok
    }

    private JPanel createProcedureScreenPanel() {
        int procedureNameListSize = procedureNameList.size();
        JPanel procedureScreenPanel = new JPanel(new GridLayout(procedureNameListSize, 1));
        for (int i = 0; i < procedureNameListSize; i++) {
            operationCheckbox.add(new JCheckBox(procedureNameList.get(i)));
            operationCheckbox.get(i).setHorizontalAlignment(JLabel.CENTER);
            procedureScreenPanel.add(operationCheckbox.get(i));
        }
        return procedureScreenPanel;
    }

}
