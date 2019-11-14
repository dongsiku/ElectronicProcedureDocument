import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ProcedureScreen extends JFrame {

    public MainPanels mainPanels;
    public ProcedureDocData procedureDocData;
    private static String SCREEN_NAME;
    private int operation_num = -1;
    private static int procedureNum;
    private static int procedureStepNum;
    // private static
    // private static

    ProcedureScreen() {
        ;
    };

    ProcedureScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int procedure_step_num) {
        procedureDocData = procedure_doc_data;
        procedureStepNum = procedure_step_num;
        SCREEN_NAME = "ProcedureScreen" + String.valueOf(procedure_step_num);

        JPanel ProcedureScreenPanel = createProcedureScreenPanel();

        mainPanels = main_panels;
        mainPanels.add(ProcedureScreenPanel, SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            ;
        }
        return 1; // ok
    }

    private JPanel createProcedureScreenPanel() {
        JPanel procedureScreenPanel = new JPanel();

        return procedureScreenPanel;
    }

    public int updateOperatorNum() {
        // if (currentScreenName.equals(SCREEN_NAME)) {
        procedureNum = Integer.parseInt(procedureDocData.data.get("operatorNum"));
        System.out.println("foo"); // debug
        if (procedureNum > 0) {
            System.out.println("foo2"); // debug
            readProcedureList();
            System.out.println("foo3"); // debug
        }
        return procedureStepNum; // debug
        // }
    }

    private static void readProcedureList() {
        ProcedureLists procedureLists = new ProcedureLists();
        List<String> procedureNameList = new ArrayList<>(); // debug
        List<Integer> hasNumberForm = new ArrayList<>(); // debug
        int procedureListLength = procedureLists.procedureLists[procedureNum - 1][procedureStepNum - 1].length;
        for (int i = 0; i < procedureListLength; i++) {
            StringTokenizer std = new StringTokenizer(
                    procedureLists.procedureLists[procedureNum - 1][procedureStepNum - 1][i], ",");
            while (std.hasMoreTokens()) {
                procedureNameList.add(std.nextToken());
                hasNumberForm.add(Integer.parseInt(std.nextToken().trim()));
            }
        }
        for (int i = 0; i < procedureNameList.size(); i++) {
            System.out.println(procedureNameList.get(i) + ": " + hasNumberForm.get(i));
        }
    }

}
