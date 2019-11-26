package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class ConclusionScreen extends JFrame {

    public MainPanels mainPanels;
    public ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    private int operationNum = -1;
    private JLabel idLabel = new JLabel();
    private JLabel dateLabel = new JLabel();
    private JLabel operataionNameLabel = new JLabel();
    private List<JLabel> conclusionLabel = new ArrayList<>();

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
            procedureDocData.initializeChecklist();
        }
        StringBuilder idBuf = new StringBuilder();
        idBuf.append("ID: ");
        idBuf.append(procedureDocData.data.get("operatorID"));
        idLabel.setText(idBuf.toString());

        StringBuilder dateBuf = new StringBuilder();
        dateBuf.append("DATE: ");
        dateBuf.append(procedureDocData.data.get("operationDate"));
        dateLabel.setText(dateBuf.toString());

        StringBuilder operationNumBuf = new StringBuilder();
        operationNumBuf.append("operationNum: ");
        if (procedureDocData.data.get("operationNum") == 0) {
            operationNumBuf.append("操作１");
        } else {
            operationNumBuf.append("操作２");
        }
        operataionNameLabel.setText(operationNumBuf.toString());

        int k = 0;
        for (int i = 0; i < ProcedureList.PROCEDURE_LIST[operationNum].length; i++) {
            for (int j = 0; j < ProcedureList.PROCEDURE_LIST[operationNum][i].length; j++) {
                StringBuilder buf = new StringBuilder();
                buf.append(ProcedureList.PROCEDURE_LIST[operationNum][i][j]);
                buf.append(": ");
                if (ProcedureList.PROCEDURE_LIST_INFO[operationNum][i][1] == 1) {
                    if (procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]) < 0.0) {
                        buf.append("NG");
                    } else {
                        buf.append(procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]));
                    }
                } else {
                    if (procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]) > 0.0) {
                        buf.append("OK");
                    } else {
                        buf.append("NG");
                    }
                }
                conclusionLabel.get(k).setText(buf.toString());
                k += 1;
            }
        }

        return 1; // ok
    }

    private JPanel createConclusionScreenPanel() {
        int columnNum = (int) Math.ceil(ProcedureList.PROCEDURE_LIST_NUM[operationNum] / 2.0) + 1;
        JPanel conclusionScreenPanel = new JPanel(new GridLayout(columnNum, 2));
        JPanel idDatePanel = new JPanel(new GridLayout(2, 1));
        idDatePanel.add(idLabel);
        idDatePanel.add(dateLabel);
        conclusionScreenPanel.add(idDatePanel);
        conclusionScreenPanel.add(operataionNameLabel);

        int k = 0;
        for (int i = 0; i < ProcedureList.PROCEDURE_LIST[operationNum].length; i++) {
            for (int j = 0; j < ProcedureList.PROCEDURE_LIST[operationNum][i].length; j++) {
                conclusionLabel.add(new JLabel());
                conclusionScreenPanel.add(conclusionLabel.get(k));
                k += 1;
            }
        }
        return conclusionScreenPanel;
    }

}
