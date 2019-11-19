package src;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CreateProcedureNameList {
    int operationNum, procedureStepNum;
    List<String> procedureNameList = new ArrayList<>();
    List<String> hasNumberForm = new ArrayList<>();;

    CreateProcedureNameList(int operation_num, int procedure_step_num) {
        operationNum = operation_num;
        procedureStepNum = procedure_step_num;
        createProcedureNameList();
    }

    public List<String> returnProcedureNameList() {
        return procedureNameList;
    }

    public List<String> returnHasNumberForm() {
        return hasNumberForm;
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
                break;
            case 2:
                procedureList.add("12. 原料1の装置Xへの質量流量F1を記録．（単位はkg / s）, 1");
                break;
            case 3:
                procedureList.add("13. バルブXV2の開操作．, 0");
                procedureList.add("14. バルブXV2の開を確認．, 0");
                break;
            case 4:
                procedureList.add("15. 原料2の装置Xへの質量流量F2を記録．（単位はkg / s）, 1");
                break;
            case 5:
                procedureList.add("16. バルブXV3の開操作．, 0");
                procedureList.add("17. バルブXV3の開を確認．, ");
                break;
            case 6:
                procedureList.add("18. 装置Xからの製品の質量流量を記録．, 1");
                procedureList.add("19. 装置Xの温度が制限以下であることを確認．, ");
                break;
            }
            break;
        }
        int procedureListLength = procedureList.size();
        procedureNameList = new ArrayList<>();
        hasNumberForm = new ArrayList<>();
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
