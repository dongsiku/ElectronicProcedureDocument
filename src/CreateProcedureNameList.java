package src;

import java.util.ArrayList;
import java.util.List;

class CreateProcedureNameList {
    int operationNum, procedureStepNum;
    List<String> procedureNameList = new ArrayList<>();

    CreateProcedureNameList(int operation_num, int procedure_step_num) {
        operationNum = operation_num;
        procedureStepNum = procedure_step_num;
        createProcedureNameList();
    }

    public List<String> returnProcedureNameList() {
        return procedureNameList;
    }

    private void createProcedureNameList() {
        switch (operationNum) {
        case 0:
            switch (procedureStepNum) {
            case 0:
                procedureNameList.add("1. ポンプAP1の不作動を確認．"); // boolean
                procedureNameList.add("2. バルブAV1の閉を確認．"); // boolean
                procedureNameList.add("3. バルブAV2の閉を確認．"); // boolean
                procedureNameList.add("4. ヒータAH1の不作動を確認．, ");
                break;
            case 1:
                procedureNameList.add("5. ポンプAP1を起動．"); // boolean
                procedureNameList.add("6. ポンプAP1の作動を確認．"); // boolean
                procedureNameList.add("7. バルブAV1の開操作．"); // boolean
                procedureNameList.add("8. バルブAV1の開を確認．"); // boolean
                procedureNameList.add("9. バルブAV2の開操作．"); // boolean
                procedureNameList.add("10. バルブAV2の開を確認．"); // boolean
                procedureNameList.add("11. ヒータAH1を起動．"); // boolean
                procedureNameList.add("12. ヒータAH1の起動を確認．, ");
                break;
            case 2:
                procedureNameList.add("13. AV1の質量流量FAV1を記録．（単位はkg / s）"); // double
                break;
            case 3:
                procedureNameList.add("14. AV2の質量流量FAV2を記録．（定常的には， FAV1に一致）, ");
                break;
            }
            break;
        case 1:
            switch (procedureStepNum) {
            case 0:
                procedureNameList.add("1. 装置XのポンプXP1の不作動を確認．"); // boolean
                procedureNameList.add("2. 装置XのポンプXP2の不作動を確認．"); // boolean
                procedureNameList.add("3. 装置XのバルブXV1の閉を確認．"); // boolean
                procedureNameList.add("4. 装置XのバルブXV2の閉を確認．"); // boolean
                procedureNameList.add("5. 装置XのバルブXV3の閉を確認．"); // boolean
                break;
            case 1:
                procedureNameList.add("6. ポンプXP1を起動．"); // boolean
                procedureNameList.add("7. ポンプXP1の作動を確認．"); // boolean
                procedureNameList.add("8. ポンプXP2を起動．"); // boolean
                procedureNameList.add("9. ポンプXP2の作動を確認．"); // boolean
                procedureNameList.add("10. バルブXV1の開操作．"); // boolean
                procedureNameList.add("11. バルブXV1の開を確認．"); // boolean
                break;
            case 2:
                procedureNameList.add("12. 原料1の装置Xへの質量流量F1を記録．（単位はkg / s）"); // double
                break;
            case 3:
                procedureNameList.add("13. バルブXV2の開操作．"); // boolean
                procedureNameList.add("14. バルブXV2の開を確認．"); // boolean
                break;
            case 4:
                procedureNameList.add("15. 原料2の装置Xへの質量流量F2を記録．（単位はkg / s）"); // double
                break;
            case 5:
                procedureNameList.add("16. バルブXV3の開操作．"); // boolean
                procedureNameList.add("17. バルブXV3の開を確認．"); // boolean
                break;
            case 6:
                procedureNameList.add("18. 装置Xからの製品の質量流量を記録．"); // double
                procedureNameList.add("19. 装置Xの温度が制限以下であることを確認．"); // boolean
                break;
            }
            break;
        }
    }
}
