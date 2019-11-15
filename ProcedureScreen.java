import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProcedureScreen extends JFrame {

    public MainPanels mainPanels;
    public ProcedureDocData procedureDocData;
    private String SCREEN_NAME;
    private int operationNum = -1;
    private int procedureStepNum;

    private List<String> procedureNameList = new ArrayList<>();
    private List<String> hasNumberForm = new ArrayList<>();
    private List<JCheckBox> operationCheckbox = new ArrayList<>();
    private JPanel ProcedureScreenPanel;

    ProcedureScreen() {
        ;
    };

    ProcedureScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int procedure_step_num) {
        procedureDocData = procedure_doc_data;
        procedureStepNum = procedure_step_num;
        SCREEN_NAME = "ProcedureScreen" + String.valueOf(procedure_step_num);
        ProcedureScreenPanel = createProcedureScreenPanel();
        mainPanels = main_panels;
        mainPanels.add(ProcedureScreenPanel, SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            ;
        }
        return 1; // ok
    }

    public void updateOperatorNum() {
        operationNum = procedureDocData.data.get("operationNum");
        if (operationNum > -1) {
            System.out.println("procedureStepNum: " + procedureStepNum);
            createProcedureNameList();
            ProcedureScreenPanel = createProcedureScreenPanel();
        }
    }

    private JPanel createProcedureScreenPanel() {
        int procedureNameListSize = procedureNameList.size();
        JPanel procedureScreenPanel = new JPanel(new GridLayout(procedureNameListSize, 1));
        for (int i = 0; i < procedureNameListSize; i++) {
            operationCheckbox.add(new JCheckBox(procedureNameList.get(i)));
            operationCheckbox.get(i).setHorizontalAlignment(JLabel.CENTER);
            procedureScreenPanel.add(operationCheckbox.get(i));
        }
        return procedureScreenPanel;
    }

    private void createProcedureNameList() {
        List<String> procedureList = new ArrayList<>();
        switch (operationNum) {
        case 0:
            switch (procedureStepNum) {
            case 0:
                procedureList.add("1. ポンプAP1の不作動を確認．, 0");
                procedureList.add("2. バルブAV1の閉を確認．, 0");
                procedureList.add("3. バルブAV2の閉を確認．, 0");
                procedureList.add("4. ヒータAH1の不作動を確認．, ");
                break;
            case 1:
                procedureList.add("5. ポンプAP1を起動．, 0");
                procedureList.add("6. ポンプAP1の作動を確認．, 0");
                procedureList.add("7. バルブAV1の開操作．, 0");
                procedureList.add("8. バルブAV1の開を確認．, 0");
                procedureList.add("9. バルブAV2の開操作．, 0");
                procedureList.add("10. バルブAV2の開を確認．, 0");
                procedureList.add("11. ヒータAH1を起動．, 0");
                procedureList.add("12. ヒータAH1の起動を確認．, ");
                break;
            case 2:
                procedureList.add("13. AV1の質量流量FAV1を記録．（単位はkg / s）, 1");
                procedureList.add("14. AV2の質量流量FAV2を記録．（定常的には， FAV1に一致）, ");
                break;
            }
            break;
        case 1:
            switch (procedureStepNum) {
            case 0:
                procedureList.add("1. 装置XのポンプXP1の不作動を確認．, 0");
                procedureList.add("2. 装置XのポンプXP2の不作動を確認．, 0");
                procedureList.add("3. 装置XのバルブXV1の閉を確認．, 0");
                procedureList.add("4. 装置XのバルブXV2の閉を確認．, 0");
                procedureList.add("5. 装置XのバルブXV3の閉を確認．, ");
                break;
            case 1:
                procedureList.add("6. ポンプXP1を起動．, 0");
                procedureList.add("7. ポンプXP1の作動を確認．, 0");
                procedureList.add("8. ポンプXP2を起動．, 0");
                procedureList.add("9. ポンプXP2の作動を確認．, 0");
                procedureList.add("10. バルブXV1の開操作．, 0");
                procedureList.add("11. バルブXV1の開を確認．, 0");
                procedureList.add("12. 原料1の装置Xへの質量流量F1を記録．（単位はkg / s）, 1");
                procedureList.add("13. バルブXV2の開操作．, 0");
                procedureList.add("14. バルブXV2の開を確認．, 0");
                procedureList.add("15. 原料2の装置Xへの質量流量F2を記録．（単位はkg / s）, 1");
                procedureList.add("16. バルブXV3の開操作．, 0");
                procedureList.add("17. バルブXV3の開を確認．, ");
                break;
            case 2:
                procedureList.add("18. 装置Xからの製品の質量流量を記録．, 1");
                procedureList.add("19. 装置Xの温度が制限以下であることを確認．, ");
                break;
            }
            break;
        }
        int procedureListLength = procedureList.size();
        for (int i = 0; i < procedureListLength; i++) {
            StringTokenizer std = new StringTokenizer(procedureList.get(i), ",");
            while (std.hasMoreTokens()) {
                procedureNameList.add(std.nextToken());
                hasNumberForm.add(std.nextToken());
            }
        }
        for (int i = 0; i < procedureNameList.size(); i++) {
            System.out.println(procedureNameList.get(i) + ": " + hasNumberForm.get(i));
        }
    }
}
