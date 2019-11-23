package src;

import javax.swing.*;
import java.awt.*;

public class ConclusionScreen extends JFrame {

    public MainPanels mainPanels;
    public ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    private int operationNum = -1;

    ConclusionScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int operation_num) {
        procedureDocData = procedure_doc_data;
        operationNum = operation_num;

        StringBuilder buf = new StringBuilder();
        buf.append("ConclusionScreen");
        buf.append(operation_num);
        SCREEN_NAME = buf.toString();

        mainPanels = main_panels;
        mainPanels.add(createConclusionScreenPanel(), SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            procedureDocData.printChecklist();
        }
        return 1; // ok
    }

    private JPanel createConclusionScreenPanel() {
        int columnNum = (int) Math.ceil(ProcedureList.PROCEDURE_LIST_NUM[operationNum] / 2.0);

        JPanel conclusionScreenPanel = new JPanel(new GridLayout(columnNum, 2));
        for (int i = 0; i < ProcedureList.PROCEDURE_LIST[operationNum].length; i++) {
            for (int j = 0; j < ProcedureList.PROCEDURE_LIST[operationNum][i].length; j++) {
                StringBuilder buf = new StringBuilder();
                buf.append(ProcedureList.PROCEDURE_LIST[operationNum][i][j]);
                buf.append(": ");
                if (ProcedureList.PROCEDURE_LIST_INFO[operationNum][i][1] == 1) {
                    buf.append(procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]));
                } else {
                    if (procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]) > 0.0) {
                        buf.append("OK");
                    } else {
                        buf.append("NG");
                    }
                }
                conclusionScreenPanel.add(new JLabel(buf.toString()));

            }
        }
        return conclusionScreenPanel;
    }

}
