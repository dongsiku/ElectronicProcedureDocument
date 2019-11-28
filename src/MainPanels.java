package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class MainPanels {
    public JPanel mainPanels = new JPanel();
    private CardLayout layout = new CardLayout();
    private int mainPanelsSize = 0;
    private List<String> screenNameList = new ArrayList<>();
    private int currentScreenNum = 0;
    private int PROCEDURE_SCREEN_TWO_NUM = 8;
    private int SELECT_OPERATION_SCREEN = 2;
    private int CONCLUSION_SCREEN_ONE_NUM = 7;
    private int CONCLUSION_SCREEN_TWO_NUM = 16;
    private ProcedureDocData procedureDocData = new ProcedureDocData();

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
        if ((procedureDocData.data.get("operationNum") == 1) && (currentScreenNum == SELECT_OPERATION_SCREEN)) {
            currentScreenNum = PROCEDURE_SCREEN_TWO_NUM;
        } else if ((currentScreenNum == CONCLUSION_SCREEN_ONE_NUM) || (currentScreenNum == CONCLUSION_SCREEN_TWO_NUM)) {
            currentScreenNum = 0;
        } else if (mainPanelsSize - 1 > currentScreenNum) {
            currentScreenNum += 1;
        }
        System.out.printf("This screen is %s\n", screenNameList.get(currentScreenNum));
        layout.show(mainPanels, screenNameList.get(currentScreenNum));
    }

    public void previous() {
        if (currentScreenNum == PROCEDURE_SCREEN_TWO_NUM) {
            currentScreenNum = SELECT_OPERATION_SCREEN;
        } else if (currentScreenNum > 0) {
            currentScreenNum -= 1;
        }
        layout.show(mainPanels, screenNameList.get(currentScreenNum));
    }

    public String currentScreenName() {
        return screenNameList.get(currentScreenNum);
    }
}
