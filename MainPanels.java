import javax.swing.*;
import java.awt.*;

class MainPanels {
    public JPanel mainPanels = new JPanel();
    public CardLayout layout = new CardLayout();
    public Container container;
    public int mainPanelsSize = 0;
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
        mainPanelsSize += 1;
        System.out.printf("mainPanelsSize is %d\n", mainPanelsSize);
    }

    public void show() {
        container.add(mainPanels);
    }

    public void next() {
        layout.next(mainPanels);
    }

    public void previous() {
        layout.previous(mainPanels);
    }
}
