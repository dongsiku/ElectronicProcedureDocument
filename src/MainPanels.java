package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class MainPanels {
    public JPanel mainPanels = new JPanel();
    private CardLayout layout = new CardLayout();
    // mainPanelsのに登録されたパネル数を表す変数である．
    private int mainPanelsSize = 0;
    // mainPanelsのに登録されたパネルの名前を表す変数である．
    private List<String> screenNameList = new ArrayList<>();
    // 現在の画面の番号を表す変数である．
    private int currentScreenNum = 0;
    // 実施操作名の選択画面が何番目か表す定数である．
    private final int SELECT_OPERATION_SCREEN_NUM = 2;
    // 操作１の結果表示の画面が何番目か表す定数である．
    private final int CONCLUSION_SCREEN_ONE_NUM = 7;
    // 先頭にある操作２の操作手順の画面が何番目か表す定数である．
    private final int PROCEDURE_SCREEN_TWO_NUM = 8;
    // 操作２の結果表示の画面が何番目か表す定数である．
    private final int CONCLUSION_SCREEN_TWO_NUM = 16;
    private ProcedureDocData procedureDocData;

    MainPanels(ProcedureDocData procedure_doc_data) {
        mainPanels.setLayout(layout);
        procedureDocData = procedure_doc_data;
    }

    public void add(JPanel mainPanel, String screenName) {
        mainPanels.add(mainPanel, screenName);
        screenNameList.add(screenName);
        mainPanelsSize += 1;
    }

    public void next() {
        if ((procedureDocData.data.get("operationNum") == 1) && (currentScreenNum == SELECT_OPERATION_SCREEN_NUM)) {
            // 実施操作名として操作２が選択（operationNum==1）された状態で，
            // 現在の画面が実施操作名の選択画面であるとき，
            // 次の画面は，先頭にある操作２の操作手順の画面とする．
            currentScreenNum = PROCEDURE_SCREEN_TWO_NUM;
        } else if ((currentScreenNum == CONCLUSION_SCREEN_ONE_NUM) || (currentScreenNum == CONCLUSION_SCREEN_TWO_NUM)) {
            // 現在の画面が結果表示の画面であるとき，
            // 「次へ >」ボタンを押すとプログラムは終了する．
            // ただし，この時は，Main.javaにより，「次へ >」ボタンのテキストは
            // 「終了」とされている．
            System.exit(0);
        } else if (mainPanelsSize - 1 > currentScreenNum) {
            // 上述した以外の場合で，一番最後の画面でない場合，
            // 次の画面に遷移する．
            currentScreenNum += 1;
        }
        // 現在の画面の名前を標準出力に出力する．
        System.out.printf("This screen is %s\n", screenNameList.get(currentScreenNum));
        // 画面を上で指定された画面に更新する．
        layout.show(mainPanels, screenNameList.get(currentScreenNum));
    }

    public void previous() {
        if (currentScreenNum == PROCEDURE_SCREEN_TWO_NUM) {
            // 現在の画面が先頭にある操作２の操作手順の画面であるとき，
            // 「< 前へ」ボタンを押すと実施操作名の選択画面へ遷移する．
            currentScreenNum = SELECT_OPERATION_SCREEN_NUM;
        } else if ((currentScreenNum == CONCLUSION_SCREEN_ONE_NUM) || (currentScreenNum == CONCLUSION_SCREEN_TWO_NUM)) {
            // 現在の画面が結果表示の画面であるとき，
            // 「< 前へ」ボタンを押すと初期画面へ遷移する．
            currentScreenNum = 0;
        } else if (currentScreenNum > 0) {
            // 上述した以外の場合で，初期画面でない時，
            // 前の画面に遷移する．
            currentScreenNum -= 1;
        }
        // 現在の画面の名前を標準出力に出力する．
        System.out.printf("This screen is %s\n", screenNameList.get(currentScreenNum));
        // 画面を上で指定された画面に更新する．
        layout.show(mainPanels, screenNameList.get(currentScreenNum));
    }

    // 現在の画面の名前を提供するメゾットである．
    public String getCurrentScreenName() {
        return screenNameList.get(currentScreenNum);
    }
}
