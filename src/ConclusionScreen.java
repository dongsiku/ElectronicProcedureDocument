package src;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class ConclusionScreen {

    private ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    private int operationNum;
    private JLabel idLabel = new JLabel();
    private JLabel dateLabel = new JLabel();
    private JLabel operataionNameLabel = new JLabel();
    private List<JLabel> conclusionLabelList = new ArrayList<>();

    ConclusionScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int operation_num) {
        procedureDocData = procedure_doc_data;
        operationNum = operation_num;

        StringBuilder screenNameBuf = new StringBuilder();
        screenNameBuf.append("ConclusionScreen");
        screenNameBuf.append(operation_num);
        SCREEN_NAME = screenNameBuf.toString();

        main_panels.add(createConclusionScreenPanel(), SCREEN_NAME);
    }

    // 「次へ >」ボタンがクリックされるたびに，
    // クリックされたときのchecklistのデータをもとに，この画面を更新する．
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

        // conclusionLabelListのそれぞれの要素に，
        // テキストとして実行された操作手順名を順に代入する．
        int k = 0;
        for (int i = 0; i < ProcedureList.PROCEDURE_LIST[operationNum].length; i++) {
            for (int j = 0; j < ProcedureList.PROCEDURE_LIST[operationNum][i].length; j++) {
                StringBuilder conclusionLabelBuf = new StringBuilder();
                conclusionLabelBuf.append(ProcedureList.PROCEDURE_LIST[operationNum][i][j]);
                if (ProcedureList.PROCEDURE_LIST_INFO[operationNum][i][1] == 1) {
                    // 記録を行う操作手順である場合，記録した値も出力する．
                    if (procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]) > -1.0) {
                        conclusionLabelBuf.append(": ");
                        conclusionLabelBuf.append(
                                procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]));
                        conclusionLabelList.get(k).setText(conclusionLabelBuf.toString());
                        k += 1;
                    }
                } else {
                    if (procedureDocData.checklist.get(ProcedureList.PROCEDURE_LIST[operationNum][i][j]) > 0.0) {
                        conclusionLabelList.get(k).setText(conclusionLabelBuf.toString());
                        k += 1;
                    }
                }
            }
        }
        // 次の画面に行ってはいけない場合が存在しないため，1（真）を返す．
        return 1; // ok
    }

    // 実行した操作手順を表示するパネルを生成するメゾットである
    private JPanel createConclusionScreenPanel() {
        JPanel idDateOperationNamePanel = new JPanel(new GridLayout(1, 3));
        idDateOperationNamePanel.add(idLabel);
        idDateOperationNamePanel.add(dateLabel);
        idDateOperationNamePanel.add(operataionNameLabel);

        JLabel conclusionNotationLabel = new JLabel("< 実行した操作 >");
        conclusionNotationLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel conclusionPanel = new JPanel(new GridLayout(ProcedureList.PROCEDURE_LIST_NUM[operationNum] + 1, 1));
        conclusionPanel.add(conclusionNotationLabel);
        // 空文字列のラベルを全操作手順数分生成する．
        int k = 0;
        for (int i = 0; i < ProcedureList.PROCEDURE_LIST[operationNum].length; i++) {
            for (int j = 0; j < ProcedureList.PROCEDURE_LIST[operationNum][i].length; j++) {
                conclusionLabelList.add(new JLabel());
                conclusionLabelList.get(k).setHorizontalAlignment(JLabel.CENTER);
                conclusionPanel.add(conclusionLabelList.get(k));
                k += 1;
            }
        }

        JPanel conclusionScreenPanel = new JPanel();
        conclusionScreenPanel.setLayout(new BorderLayout());
        conclusionScreenPanel.add(idDateOperationNamePanel, BorderLayout.NORTH);
        conclusionScreenPanel.add(conclusionPanel, BorderLayout.CENTER);
        return conclusionScreenPanel;
    }
}
