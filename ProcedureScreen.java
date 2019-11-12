import javax.swing.*;
import java.awt.*;

public class ProcedureScreen extends JFrame {

    Container screenContainer;
    private int procedureNum; // 0: 操作１, 1: 操作２
    private int subprocedureNum; // 0: 操作前確認, 1: 起動操作, 2:操作後確認

    ProcedureScreen(Container contanier, int procedure_num, int subProcedureNum) {
        creenContainer = contanier;
        procedureNum = procedure_num;
        subprocedureNum = subProcedureNum;
    }

    public void showScreen() {

    }

}
