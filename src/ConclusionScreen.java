package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class ConclusionScreen extends JFrame {

    private ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    private int operationNum = -1;
    private JLabel idLabel = new JLabel();
    private JLabel dateLabel = new JLabel();
    private JLabel operataionNameLabel = new JLabel();
    private List<JLabel> conclusionLabelList = new ArrayList<>();

    ConclusionScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int operation_num) {
        procedureDocData = procedure_doc_data;
        operationNum = operation_num;

        StringBuilder buf = new StringBuilder();
        buf.append("ConclusionScreen");
        buf.append(operation_num);
        SCREEN_NAME = buf.toString();

        main_panels.add(createConclusionScreenPanel(), SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            procedureDocData.printChecklist();
            procedureDocData.initializeChecklist();
        }
        StringBuilder idBuf = new StringBuilder();
        idBuf.append("操作者ID: ");
        idBuf.append(procedureDocData.data.get("operatorID"));
        idLabel.setText(idBuf.toString());
        idLabel.setHorizontalAlignment(JLabel.CENTER);

        StringBuilder dateBuf = new StringBuilder();
        dateBuf.append("日付: ");
        dateBuf.append(procedureDocData.data.get("operationYear"));
        dateBuf.append("/");
        dateBuf.append(procedureDocData.data.get("operationMonth"));
        dateBuf.append("/");
        dateBuf.append(procedureDocData.data.get("operationDate"));
        dateLabel.setText(dateBuf.toString());
        dateLabel.setHorizontalAlignment(JLabel.CENTER);

        StringBuilder operationNumBuf = new StringBuilder();
        operationNumBuf.append("実施操作名: ");
        if (procedureDocData.data.get("operationNum") == 0) {
            operationNumBuf.append("操作１");
        } else {
            operationNumBuf.append("操作２");
        }
        operataionNameLabel.setText(operationNumBuf.toString());
        operataionNameLabel.setHorizontalAlignment(JLabel.CENTER);

        int k = 1;
        for (int i = 0; i < ProcedureList.PROCEDURE_LIST[operationNum].length; i++) {
            for (int j = 0; j < ProcedureList.PROCEDURE_LIST[operationNum][i].length; j++) {
                StringBuilder buf = new StringBuilder();
                buf.append(ProcedureList.PROCEDURE_LIST[operationNum][i][j]);
                if (ProcedureList.PROCEDURE_LIST_INFO[operationNum][i][1] == 1) {
                    if (procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]) > -1.0) {
                        buf.append(": ");
                        buf.append(procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]));
                        conclusionLabelList.get(k).setText(buf.toString());
                        k += 1;
                    }
                } else {
                    if (procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]) > 0.0) {
                        conclusionLabelList.get(k).setText(buf.toString());
                        k += 1;
                    }
                }
            }
        }

        return 1; // ok
    }

    private JPanel createConclusionScreenPanel() {
        JPanel conclusionScreenPanel = new JPanel();
        conclusionScreenPanel.setLayout(new BorderLayout());
        JPanel idDateOperationNamePanel = new JPanel(new GridLayout(1, 3));
        idDateOperationNamePanel.add(idLabel);
        idDateOperationNamePanel.add(dateLabel);
        idDateOperationNamePanel.add(operataionNameLabel);
        conclusionScreenPanel.add(idDateOperationNamePanel, BorderLayout.NORTH);

        JPanel conclusionPanel = new JPanel(new GridLayout(ProcedureList.PROCEDURE_LIST_NUM[operationNum] + 1, 1));
        conclusionLabelList.add(new JLabel("< 実行した操作 >"));
        conclusionLabelList.get(0).setHorizontalAlignment(JLabel.CENTER);
        conclusionPanel.add(conclusionLabelList.get(0));

        int k = 1;
        for (int i = 0; i < ProcedureList.PROCEDURE_LIST[operationNum].length; i++) {
            for (int j = 0; j < ProcedureList.PROCEDURE_LIST[operationNum][i].length; j++) {
                conclusionLabelList.add(new JLabel());
                conclusionLabelList.get(k).setHorizontalAlignment(JLabel.CENTER);
                conclusionPanel.add(conclusionLabelList.get(k));
                k += 1;
            }
        }
        conclusionScreenPanel.add(conclusionPanel, BorderLayout.CENTER);
        return conclusionScreenPanel;
    }

}
