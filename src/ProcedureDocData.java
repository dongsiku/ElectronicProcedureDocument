package src;

import java.util.HashMap;
import java.util.Map;

class ProcedureDocData {
    public Map<String, Integer> data = new HashMap<>();
    public Map<String, Double> checklist = new HashMap<>();
    // TODO: fix data and checklist to static

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
        for (int i = 0; i < ProcedureList.PROCEDURE_LIST.length; i++) {
            for (int j = 0; j < ProcedureList.PROCEDURE_LIST[i].length; j++) {
                for (int k = 0; k < ProcedureList.PROCEDURE_LIST[i][j].length; k++) {
                    checklist.put(ProcedureList.PROCEDURE_LIST[i][j][k], -1.0);
                }
            }
        }
    }

}
