package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// タブレット端末の日付を取得するために，
// java.util.Calendarをインポートする．
import java.util.Calendar;

public class IdDateScreen {

    private JLabel idLabel = new JLabel();
    // 入力された操作者IDを一時的に補完する変数である．
    // 0から始まる操作者IDを考慮し，String型を用いた．
    public String idNumStr = "";
    private ProcedureDocData procedureDocData;
    private int operationYear, operationMonth, operationDate;
    private String SCREEN_NAME = "IdDateScreen";
    private PrevNextButton prevNextButton;

    IdDateScreen(MainPanels main_panels, ProcedureDocData procedure_doc_data, PrevNextButton prev_next_button) {
        procedureDocData = procedure_doc_data;
        prevNextButton = prev_next_button;

        JPanel IdDateScreenPanel = new JPanel(new GridLayout(2, 1));
        IdDateScreenPanel.add(createDatePanel());
        IdDateScreenPanel.add(createIdPanel());

        JPanel IdDateScreenKeyboardPanel = new JPanel(new GridLayout(2, 1));
        IdDateScreenKeyboardPanel.add(IdDateScreenPanel);
        IdDateScreenKeyboardPanel.add(createKeyboardPanel());

        main_panels.add(IdDateScreenKeyboardPanel, SCREEN_NAME);
    }

    public int update(String currentScreenName) {
        if (currentScreenName.equals(SCREEN_NAME)) {
            if (idNumStr.length() == 4) {
                // 4桁の操作者IDが入力されたとき，操作者IDと日付をdataに追加する．
                // なお，Mapo型において，既に登録されているキーについて追加が行われた場合，
                // 値が更新される．
                procedureDocData.data.put("operatorID", Integer.parseInt(idNumStr));
                procedureDocData.data.put("operationYear", operationYear);
                procedureDocData.data.put("operationMonth", operationMonth);
                procedureDocData.data.put("operationDate", operationDate);
                procedureDocData.printData();
            } else {
                // 操作者IDが4桁でないときは，次の画面への遷移を禁止する．
                return 0;
            }
        }
        return 1; // ok
    }

    private JPanel createIdPanel() {
        JPanel idPanel = new JPanel(new FlowLayout());
        setIdLabel();
        idPanel.add(idLabel);
        return idPanel;
    }

    // 操作者IDを入力するためのソフトキーボードを生成する．
    private JPanel createKeyboardPanel() {
        JPanel keyboardPanel = new JPanel(new GridLayout(4, 3));
        JButton numberButtons[] = new JButton[10];
        JButton deleteButton = new JButton("Delete");
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        for (int i = 1; i < 10; i++) {
            keyboardPanel.add(numberButtons[i]);
        }
        keyboardPanel.add(new JLabel(""));
        keyboardPanel.add(numberButtons[0]);
        keyboardPanel.add(deleteButton);

        for (int i = 0; i < 10; i++) {
            String tempNum = String.valueOf(i);
            numberButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (idNumStr.length() < 4) {
                        // 入力された操作者IDが4桁以下であるとき，
                        // クリックされた数字をidNumStrに追加する．
                        idNumStr += tempNum;
                    }
                    setIdLabel();
                    if (idNumStr.length() == 4) {
                        // 入力された操作者IDが4桁になった時，
                        // 「次へ >」ボタンに標準のテキストを表示する．
                        prevNextButton.setNextButtonStandardText();
                    } else {
                        // 入力された操作者IDが4桁になってない時，
                        // 「次へ >」ボタンのテキストを削除する．
                        prevNextButton.nextButton.setText("");
                    }
                }
            });
        }
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (idNumStr.length() > 0) {
                    // 入力された操作者IDが1桁以上であるとき，一番最後の文字を削除する．
                    idNumStr = idNumStr.substring(0, idNumStr.length() - 1);
                }
                setIdLabel();
                // 操作者IDが必ず4桁以下になるため，「次へ >」ボタンのテキストを削除する．
                prevNextButton.nextButton.setText("");
            }
        });

        return keyboardPanel;
    }

    // 変数idNumStrを画面に表示するため，idLabelを設定する．
    private void setIdLabel() {
        StringBuilder idBuf = new StringBuilder();
        idBuf.append("操作者ID: ");
        idBuf.append(idNumStr);
        idLabel.setText(idBuf.toString());
    }

    // 日付を入力するためのパネルを生成する．
    private JPanel createDatePanel() {
        JPanel datePanel = new JPanel(new FlowLayout());

        // タブレット端末の日付を取得する．
        Calendar cal = Calendar.getInstance();
        // 取得した日付を初期値とする．
        final int INIT_YEAR = cal.get(Calendar.YEAR);
        final int INIT_MONTH = cal.get(Calendar.MONTH);
        final int INIT_DATE = cal.get(Calendar.DATE);

        // コンボボックスに表示する年について，
        // 何年分の範囲の指定を可能にするか指定する．
        // 例えば，タブレット端末から取得した年が2019年ならば，
        // (2019 - BEFORE_AFTER_YEAR)年から
        // (2019 + BEFORE_AFTER_YEAR)年までがコンボボックスに表示される．
        final int BEFORE_AFTER_YEAR = 5;

        // コンボボックスを生成する．
        String[] combodata_year = new String[BEFORE_AFTER_YEAR * 2 + 1];
        String[] combodata_month = new String[12];
        String[] combodata_date = new String[31];
        for (int i = 0; i < BEFORE_AFTER_YEAR * 2 + 1; i++) {
            combodata_year[i] = String.valueOf(i + INIT_YEAR - BEFORE_AFTER_YEAR);
        }
        for (int i = 0; i < 12; i++) {
            combodata_month[i] = String.valueOf(i + 1);
        }
        for (int i = 0; i < 31; i++) {
            combodata_date[i] = String.valueOf(i + 1);
        }
        JComboBox<String> combo_year = new JComboBox<>(combodata_year);
        JComboBox<String> combo_month = new JComboBox<>(combodata_month);
        JComboBox<String> combo_date = new JComboBox<>(combodata_date);

        // タブレット端末から取得した日付をコンボボックスの初期値とする．
        combo_year.setSelectedIndex(BEFORE_AFTER_YEAR);
        combo_month.setSelectedIndex(INIT_MONTH);
        combo_date.setSelectedIndex(INIT_DATE - 1);

        // コンボボックスの初期値を操作した日付として代入する．
        operationYear = INIT_YEAR;
        operationMonth = INIT_MONTH + 1;
        operationDate = INIT_DATE;

        Dimension dimension_combo = new Dimension(80, 30);
        combo_year.setPreferredSize(dimension_combo);
        combo_month.setPreferredSize(dimension_combo);
        combo_date.setPreferredSize(dimension_combo);

        datePanel.add(new JLabel("日付: "));
        datePanel.add(combo_year);
        datePanel.add(combo_month);
        datePanel.add(combo_date);

        // コンボボックスにアクションが発生した場合，
        // それぞれのコンボボックスに対応する日付（年，月，日）を更新する．
        combo_year.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operationYear = combo_year.getSelectedIndex() + INIT_YEAR - BEFORE_AFTER_YEAR;
            }
        });
        combo_month.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operationMonth = combo_month.getSelectedIndex() + 1;
            }
        });
        combo_date.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                operationDate = combo_date.getSelectedIndex();
            }
        });
        return datePanel;
    }
}
