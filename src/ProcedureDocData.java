package src;

import java.util.HashMap;
import java.util.Map;

class ProcedureDocData {
    // 操作者ID，日付，実施操作名を保存するdata
    public Map<String, Integer> data = new HashMap<>();
    // チェックした操作手順を記録するchecklist
    // これの値の型がdouble型であるのは，操作手順の画面で
    // 小数の入力を可能にするためである．
    public Map<String, Double> checklist = new HashMap<>();

    ProcedureDocData() {
        // data.get("operationNum")を-1に初期化する．
        // これは，operationNumがまだ選択されていないことを表すものである．
        data.put("operationNum", -1);
        initializeChecklist();
    }

    public void printData() {
        for (String key : data.keySet()) {
            // dataに登録されているものを標準出力として出力する．
            System.out.println(key + ": " + data.get(key));
        }
    }

    public void printChecklist() {
        for (String key : checklist.keySet()) {
            // checklistのうち，操作手順が実行されたものだけを標準出力として出力する．
            if (checklist.get(key) > -1) {
                System.out.println(key + ": " + checklist.get(key));
            }
        }
    }

    public void initializeChecklist() {
        // checklistの全ての項目を-1.0に初期化する．
        // これは，操作手順がまだ実行されていないことを表すものである．
        // 操作手順が実行された場合，checklistの値は0.0以上になる．
        for (int i = 0; i < ProcedureList.PROCEDURE_LIST.length; i++) {
            for (int j = 0; j < ProcedureList.PROCEDURE_LIST[i].length; j++) {
                for (int k = 0; k < ProcedureList.PROCEDURE_LIST[i][j].length; k++) {
                    checklist.put(ProcedureList.PROCEDURE_LIST[i][j][k], -1.0);
                }
            }
        }
    }
}
