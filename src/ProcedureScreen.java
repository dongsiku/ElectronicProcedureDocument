package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

public class ProcedureScreen {

    private ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    private int operationNum;
    private int procedurePanelNum;
    // ソフトキーボードの画面にて入力された値をvalueStrに代入する．
    // 初期状態では，何も入力されていないため，空文字列が代入されている．
    private String valueStr = "";
    private JLabel operationNameLabel = new JLabel();
    // subOperationNameは操作段階名を示す．
    private JLabel subOperationNameLabel = new JLabel();

    ProcedureScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int operation_num,
            int procedure_panel_num) {
        procedureDocData = procedure_doc_data;
        operationNum = operation_num;
        procedurePanelNum = procedure_panel_num;

        // 操作手順の画面は，operation_numやprocedure_panel_numを変更することにより，
        // 複数個生成されるため，それぞれの画面の名前を生成する．
        StringBuilder screenNameBuf = new StringBuilder();
        screenNameBuf.append("ProcedureScreen");
        screenNameBuf.append(operation_num);
        screenNameBuf.append("_");
        screenNameBuf.append(procedure_panel_num);
        SCREEN_NAME = screenNameBuf.toString();

        main_panels.add(createProcedureScreenPanel(), SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            // 現在表示されている画面がこの画面のとき，checklistを標準出力で出力する．
            procedureDocData.printChecklist();
        }
        return 1; // ok
    }

    private JPanel createProcedureScreenPanel() {
        // 実施操作名を生成する
        StringBuilder operationNameBuf = new StringBuilder();
        operationNameBuf.append("操作");
        if (operationNum == 0) {
            operationNameBuf.append("１");
        } else {
            operationNameBuf.append("２");
        }
        operationNameBuf.append(" (");
        operationNameBuf.append(procedurePanelNum + 1);
        operationNameBuf.append(" / ");
        operationNameBuf.append(ProcedureList.PROCEDURE_LIST_INFO[operationNum].length);
        operationNameBuf.append(")");
        operationNameLabel.setText(operationNameBuf.toString());

        // 操作段階名を生成する．
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
            // PROCEDURE_LIST_INFOを参照し，この操作手順が記録を行う手順である場合は，
            // ソフトキーボードの画面を生成する．
            return createProcedureKeyboardPanel();
        }
        // これ以外の場合は，チェックボックスの画面を生成する．
        return createProcedureCheckboxPanel();
    }

    // チェックボックスの画面を生成するメゾットである．
    private JPanel createProcedureCheckboxPanel() {
        JPanel procedureCheckboxPanel = new JPanel(new GridLayout(5, 2));
        procedureCheckboxPanel.add(operationNameLabel);
        procedureCheckboxPanel.add(subOperationNameLabel);

        int procedureListLength = ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum].length;
        List<JCheckBox> procedureCheckbox = new ArrayList<>();
        for (int i = 0; i < procedureListLength; i++) {
            procedureCheckbox.add(new JCheckBox(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][i]));
            procedureCheckbox.get(i).setHorizontalAlignment(JLabel.CENTER);
            procedureCheckbox.get(i).setBorderPainted(true);
            procedureCheckboxPanel.add(procedureCheckbox.get(i));
        }

        // パネルに追加するラベルやチェックボックスの数が
        // GridLayoutの行数より小さい場合，パネルのデザインが崩れてしまうため，
        // 空文字列のラベルを追加する．
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
                // チェックボックスにイベントが発生した際，
                // チェックされた手順について，checklistの値を1.0に更新する．
                // これは，操作手順が実行されたことを意味する．
                // また，チェックされていない手順について，checklistの値を-1.0に更新するが，
                // これは，操作手順が実行されなかったことを意味する．
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

    // ソフトキーボードの画面を生成するメゾットである．
    private JPanel createProcedureKeyboardPanel() {
        JPanel operationNamePanel = new JPanel(new GridLayout(1, 2));
        operationNamePanel.add(operationNameLabel);
        operationNamePanel.add(subOperationNameLabel);

        JLabel procedureNameLabel = new JLabel(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0]);
        procedureNameLabel.setHorizontalAlignment(JLabel.CENTER);
        JLabel procedureValueLabel = new JLabel("値: ");
        procedureValueLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel procedureNameAndValuePanel = new JPanel(new GridLayout(2, 1));
        procedureNameAndValuePanel.add(procedureNameLabel);
        procedureNameAndValuePanel.add(procedureValueLabel);

        JPanel operationNameAndProcedureNameAndValuePanel = new JPanel(new GridLayout(2, 1));
        operationNameAndProcedureNameAndValuePanel.add(operationNamePanel);
        operationNameAndProcedureNameAndValuePanel.add(procedureNameAndValuePanel);

        JPanel procedureKeyboardPanel = new JPanel(new GridLayout(2, 1));
        procedureKeyboardPanel.add(operationNameAndProcedureNameAndValuePanel);

        JButton numberButtons[] = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }
        JButton pointButton = new JButton(".");
        JButton deleteButton = new JButton("Delete");

        JPanel keyboardPanel = new JPanel(new GridLayout(4, 3));
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
                    procedureValueLabel.setText(createProcedureValueLabelText());
                    updateChecklist();
                }
            });
        }
        pointButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!valueStr.contains(".")) {
                    // valueStrに.（小数点）が含まれていない場合に限り，
                    // これに.を追加する．
                    valueStr += ".";
                    procedureValueLabel.setText(createProcedureValueLabelText());
                }
                updateChecklist();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (valueStr.length() > 0) {
                    valueStr = valueStr.substring(0, valueStr.length() - 1);
                    procedureValueLabel.setText(createProcedureValueLabelText());
                }
                updateChecklist();
            }
        });

        procedureKeyboardPanel.add(keyboardPanel);
        return procedureKeyboardPanel;
    }

    // procedureValueLabelのテキストを作成するメゾットである．
    private String createProcedureValueLabelText() {
        StringBuilder procedureValueBuf = new StringBuilder();
        procedureValueBuf.append("値: ");
        procedureValueBuf.append(valueStr);
        return procedureValueBuf.toString();
    }

    // valueStrの更新に伴い，該当するchecklistの値を更新するメゾットである．
    private void updateChecklist() {
        if (valueStr.equals(".")) {
            // valueStrが.のときは，該当するchecklistの値を0.0に更新する．
            // これは，この状態でdouble型への変換を行うと，
            // エラーが発生してしまうためである．
            procedureDocData.checklist.put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0], 0.0);
        } else if (valueStr.equals("")) {
            // valueStrが空文字のときは，記録が行われていないため，
            // 該当するchecklistの値を-1.0に更新する．
            procedureDocData.checklist.put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0], -1.0);
        } else {
            // これ以外の場合は，valueStrをdouble型に変換し，該当するchecklistの値を更新する．
            procedureDocData.checklist.put(ProcedureList.PROCEDURE_LIST[operationNum][procedurePanelNum][0],
                    Double.parseDouble(valueStr));
        }
    }
}
