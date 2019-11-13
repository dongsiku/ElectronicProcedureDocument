import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class MainPanels {
    public JPanel mainPanels = new JPanel();
    public CardLayout layout = new CardLayout();
    public Container container;
    public int mainPanelsSize = 0;
    public List<String> screenNameList = new ArrayList<>();
    private int currentScreenNum = 0;
    /*
     * layout = new CardLayout(); cardPanel.setLayout(layout);
     * 
     * cardPanel.add(card1, "button");
     */

    MainPanels(Container root_container) {
        container = root_container;
        mainPanels.setLayout(layout);
    }

    public void add(JPanel mainPanel, String screenName) {
        mainPanels.add(mainPanel, screenName);
        screenNameList.add(screenName);
        mainPanelsSize += 1;
        System.out.printf("mainPanelsSize is %d\n", mainPanelsSize);
    }

    public void init() {
        container.add(mainPanels);
    }

    public void next() {
        if (mainPanelsSize - 1 > currentScreenNum) {
            currentScreenNum += 1;
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
