package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SelectOperationScreen {

    private ProcedureDocData procedureDocData;
    private String SCREEN_NAME = "SelectOperationScreen";
    private JCheckBox[] operationCheckbox = { new JCheckBox("操作１"), new JCheckBox("操作２") };
    // 実施操作名が選択されていないことを示すため，-1を代入する．
    private int operationNum = -1;
    private PrevNextButton prevNextButton;

    SelectOperationScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data,
            PrevNextButton prev_next_button) {
        procedureDocData = procedure_doc_data;
        prevNextButton = prev_next_button;

        JPanel SelectOperationScreenPanel = createSelectOperationScreenPanel();
        listenOperationCheckbox();

        main_panels.add(SelectOperationScreenPanel, SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            if (operationNum < 0) {
                // 実施操作名が選択されていないとき，画面の遷移を禁止する．
                return 0; // NG
            }
            // 実施操作名の情報（operationNum）をdataに追加する．
            procedureDocData.data.put("operationNum", operationNum);
            procedureDocData.printData();
            // 先の画面において「< 前へ」ボタンが押され，この画面に戻った時と，
            // 全ての作業が終了し，初期画面に戻った時のために，チェックボックスの選択を解除する．
            resetCheckbox();
        }
        return 1; // ok
    }

    private JPanel createSelectOperationScreenPanel() {
        JLabel notationLabel = new JLabel("実施操作名");
        notationLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel selectOperationPanel = new JPanel(new GridLayout(1, 2));
        for (int i = 0; i < 2; i++) {
            operationCheckbox[i].setHorizontalAlignment(JLabel.CENTER);
            // チェックボックスの枠線を描画する．
            operationCheckbox[i].setBorderPainted(true);
            selectOperationPanel.add(operationCheckbox[i]);
        }

        JPanel selectOperationScreenPanel = new JPanel(new GridLayout(2, 1));
        selectOperationScreenPanel.add(notationLabel);
        selectOperationScreenPanel.add(selectOperationPanel);
        return selectOperationScreenPanel;
    }

    // チェックボックスが押されたか監視するメゾットである．
    private void listenOperationCheckbox() {
        for (int i = 0; i < 2; i++) {
            int this_box_num = i;
            int another_box_num = 1 - i;
            operationCheckbox[this_box_num].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (operationCheckbox[this_box_num].isSelected()) {
                        // 片方のチェックボックスが選択されたとき，もう片方のチェックボックスを解除し，
                        // 実施操作名の情報を選択されたものに更新し，「次へ >」ボタンに標準のテキストを表示する．
                        operationCheckbox[another_box_num].setSelected(false);
                        operationNum = this_box_num;
                        prevNextButton.setNextButtonStandardText();
                    } else {
                        // 両方のチェックボックスが解除されたとき，実施操作名は選択されてないものとし，
                        // 「次へ >」ボタンのテキストを削除する．
                        operationNum = -1;
                        prevNextButton.nextButton.setText("");
                    }
                }
            });
        }
    }

    // チェックボックスの選択を解除するメゾットである．
    private void resetCheckbox() {
        operationNum = -1;
        for (int i = 0; i < 2; i++) {
            operationCheckbox[i].setSelected(false);
        }
    }
}
