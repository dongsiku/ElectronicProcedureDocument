package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProcedureScreen extends JFrame {

    public MainPanels mainPanels;
    public ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    private int operationNum = -1;
    private int procedureStepNum;

    private List<String> procedureNameList = new ArrayList<>();
    private List<String> hasNumberForm = new ArrayList<>();
    private List<JCheckBox> operationCheckbox = new ArrayList<>();
    private JPanel ProcedureScreenPanel;

    ProcedureScreen() {
        ;
    };

    ProcedureScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int operation_num,
            int procedure_step_num) {
        procedureDocData = procedure_doc_data;
        procedureStepNum = procedure_step_num;
        CreateProcedureNameList createProcedureNameList = new CreateProcedureNameList(operation_num,
                procedure_step_num);
        procedureNameList = createProcedureNameList.returnProcedureNameList();
        hasNumberForm = createProcedureNameList.returnHasNumberForm();
        SCREEN_NAME = "ProcedureScreen" + String.valueOf(procedure_step_num);
        ProcedureScreenPanel = createProcedureScreenPanel();
        mainPanels = main_panels;
        mainPanels.add(ProcedureScreenPanel, SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            ;
        }
        return 1; // ok
    }

    public void updateOperationNum() {
        int newoperationNum = procedureDocData.data.get("operationNum");
        if (operationNum != newoperationNum) {
            operationNum = newoperationNum;
            System.out.println("procedureStepNum: " + procedureStepNum);
            ProcedureScreenPanel = createProcedureScreenPanel();
        }
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
