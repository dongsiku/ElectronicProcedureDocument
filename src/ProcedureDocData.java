package src;

import java.util.HashMap;
import java.util.Map;

class ProcedureDocData {
    public Map<String, Integer> data = new HashMap<>();
    public Map<String, Integer> checklist = new HashMap<>();

    ProcedureDocData() {
        data.put("operationNum", -1);
        initializeChecklist();
    }

    /*
     * operatorID, operationDate, operationName
     */
    public void print() {
        for (String key : data.keySet()) {
            System.out.println(key + ": " + data.get(key));
        }
    }

    public void printChecklist() {
        for (String key : checklist.keySet()) {
            System.out.println(key + ": " + checklist.get(key));
        }
    }
    /*
     * public void put(String key, int value) { data.put(key, value); }
     */

    private void initializeChecklist() {
        for (int i = 0; i < 4; i++) {
            CreateProcedureNameList createProcedureNameList0 = new CreateProcedureNameList(0, i);
            for (String procedureNameList : createProcedureNameList0.returnProcedureNameList()) {
                checklist.put(procedureNameList, -1);
            }
        }
        for (int j = 0; j < 8; j++) {
            CreateProcedureNameList createProcedureNameList1 = new CreateProcedureNameList(1, j);
            for (String procedureNameList : createProcedureNameList1.returnProcedureNameList()) {
                checklist.put(procedureNameList, -1);
            }
        }
    }

}
