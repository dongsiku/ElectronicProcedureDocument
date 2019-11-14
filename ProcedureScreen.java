import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    private static List<String> procedureNameList = new ArrayList<>();
    private static List<Integer> hasNumberForm = new ArrayList<>();

    ProcedureScreen() {
        ;
    };

    ProcedureScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, int procedure_step_num)
            throws IOException {
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

    public void updateOperatorNum(String currentScreenName) throws IOException {
        if (currentScreenName.equals(SCREEN_NAME)) {
            procedureNum = Integer.parseInt(procedureDocData.data.get("operatorNum"));
            if (procedureNum > 0) {
                readProcedureList();
            }
        }
    }

    private static void readProcedureList() throws IOException {
        StringBuilder procedureFilename = new StringBuilder();
        procedureFilename.append("procedure_lists/");
        procedureFilename.append("procedure");
        procedureFilename.append(String.valueOf(procedureNum));
        procedureFilename.append("_step");
        procedureFilename.append(String.valueOf(procedureStepNum));
        procedureFilename.append(".txt");

        BufferedReader br = new BufferedReader(new FileReader(procedureFilename.toString()));
        System.out.println(procedureFilename.toString());

        String line;

        while ((line = br.readLine()) != null) {
            StringTokenizer std = new StringTokenizer(line, ",");

            while (std.hasMoreTokens()) {
                procedureNameList.add(std.nextToken());
                hasNumberForm.add(Integer.parseInt(std.nextToken().trim()));
            }

        }
        br.close();
        for (int i = 0; i < procedureNameList.size(); i++) {
            System.out.println(SCREEN_NAME);
            System.out.println(procedureNameList.get(i) + ": " + hasNumberForm.get(i));
        }
    }

}
