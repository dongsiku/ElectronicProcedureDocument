import java.util.HashMap;
import java.util.Map;

class ProcedureDocData {
    public Map<String, String> data = new HashMap<>();

    ProcedureDocData() {
        data.put("operatorNum", "-1");
    }

    /*
     * operatorID, operationDate, operationName
     */
    public void print() {
        for (String key : data.keySet()) {
            System.out.println(key + ": " + data.get(key));
        }
    }

    public void put(String key, String value) {
        data.put(key, value);
    }

}
