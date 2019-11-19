package src;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class MainPanels {
    public JPanel mainPanels = new JPanel();
    public CardLayout layout = new CardLayout();
    public int mainPanelsSize = 0;
    public List<String> screenNameList = new ArrayList<>();
    private int currentScreenNum = 0;
    private int PROCEDURE_SCREEN_ONE_NUM = 3;
    private int PROCEDURE_SCREEN_TWO_NUM = 6;
    private static ProcedureDocData procedureDocData = new ProcedureDocData();
    /*
     * layout = new CardLayout(); cardPanel.setLayout(layout);
     * 
     * cardPanel.add(card1, "button");
     */

    MainPanels(ProcedureDocData procedure_doc_data) {
        mainPanels.setLayout(layout);
        procedureDocData = procedure_doc_data;
    }

    public void add(JPanel mainPanel, String screenName) {
        mainPanels.add(mainPanel, screenName);
        screenNameList.add(screenName);
        mainPanelsSize += 1;
        System.out.printf("mainPanelsSize is %d\n", mainPanelsSize);
    }

    public void next() {
        if (mainPanelsSize - 1 > currentScreenNum) {
            if ((procedureDocData.data.get("operationNum") == 1) && (currentScreenNum == PROCEDURE_SCREEN_ONE_NUM)) {
                currentScreenNum = PROCEDURE_SCREEN_TWO_NUM;
            } else {
                currentScreenNum += 1;
            }
        } else {
            currentScreenNum = 0;
        }
        layout.show(mainPanels, screenNameList.get(currentScreenNum));
    }

    public void previous() {
        if (currentScreenNum > 0) {
            currentScreenNum -= 1;
        }
        layout.show(mainPanels, screenNameList.get(currentScreenNum));
    }

    public String currentScreenName() {
        return screenNameList.get(currentScreenNum);
    }
}
