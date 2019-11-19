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
        for (int i = 0; i < CreateProcedureNameList.procedureList.length; i++) {
            for (int j = 0; j < CreateProcedureNameList.procedureList[i].length; j++) {
                for (int k = 0; k < CreateProcedureNameList.procedureList[i][j].length; k++) {
                    checklist.put(CreateProcedureNameList.procedureList[i][j][k], -1);
                }
            }
        }
    }

}
