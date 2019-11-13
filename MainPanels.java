import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

class MainPanels {
    public static List<JPanel> mainPanels = new ArrayList<JPanel>();
    public int currentMainPanelNum = 0;
    Container container;
    public int mainPanelsSize;

    MainPanels(Container root_container) {
        container = root_container;
    }

    public void add(JPanel mainPanel) {
        mainPanels.add(mainPanel);
        mainPanelsSize = mainPanels.size();
        System.out.printf("mainPanelsSize is %d\n", mainPanelsSize);
    }

    private void updateContainer() {
        for (JPanel mainPanel : mainPanels) {
            container.add(mainPanel);
        }
        System.out.printf("currentMainPanelNum is %d\n", currentMainPanelNum);
    }

    public void initializeContainer() {
        updateContainer();
    }

    public void next() {
        if (mainPanelsSize > currentMainPanelNum) {
            currentMainPanelNum += 1;
            previousnextCommon();
        }
    }

    public void previous() {
        if (0 < currentMainPanelNum) {
            currentMainPanelNum -= 1;
            previousnextCommon();
        }
    }

    private void previousnextCommon() {
        int mainPanelsSize = mainPanels.size();
        for (int i = 0; i < mainPanelsSize + 1; i++) {
            if (i == currentMainPanelNum) {
                mainPanels.get(i).setVisible(true);
            } else {
                mainPanels.get(i).setVisible(false);
            }
        }
        updateContainer();
    }

}
