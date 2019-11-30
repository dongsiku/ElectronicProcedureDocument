package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Main extends JFrame {

	// serialVersionUIDを定義しないと，エラーが表示されるため，
	// これを定義する．
	private static final long serialVersionUID = 0;

	public static void main(String[] args) {
		Main ElectronicProcedureDocument = new Main();
		ElectronicProcedureDocument.setVisible(true);
	}

	Main() {
		setBackground(Color.white);

		// 閉じるボタンを押したときに，プログラムが終了するように設定する．
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// アイコンを設定する．
		ImageIcon icon = new ImageIcon("icon/icon.png");
		setIconImage(icon.getImage());

		// 操作に関する情報を管理するprocedureDocDataを宣言して生成する．
		ProcedureDocData procedureDocData = new ProcedureDocData();

		// 画面遷移専用のボタンを表示するprevnextButtonを宣言して生成する．
		// また，Frameに追加する．
		PrevNextButton prevnextButton = new PrevNextButton();
		add(prevnextButton.prevnextButtonPanel, BorderLayout.NORTH);

		// マインパネルを管理をするmainPanelsを宣言して生成する．
		MainPanels mainPanels = new MainPanels(procedureDocData);
		// 初期画面，操作者IDと日付の入力画面，実施操作名の選択画面をそれぞれ宣言して生成する．
		InitialScreen initialScreen = new InitialScreen(mainPanels);
		IdDateScreen idDateSreen = new IdDateScreen(mainPanels, procedureDocData, prevnextButton);
		SelectOperationScreen selectOperationScreen = new SelectOperationScreen(mainPanels, procedureDocData,
				prevnextButton);

		// 操作手順の画面を2次元リストで，結果表示の画面を1次元リストで宣言して生成する．
		ArrayList<ArrayList<ProcedureScreen>> procedureScreen = new ArrayList<ArrayList<ProcedureScreen>>();
		List<ConclusionScreen> conclusionScreen = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			procedureScreen.add(new ArrayList<ProcedureScreen>());
			for (int j = 0; j < ProcedureList.PROCEDURE_LIST[i].length; j++) {
				// iは実施操作名，jは何番目の操作手順の画面であるかを表す．
				procedureScreen.get(i).add(new ProcedureScreen(mainPanels, procedureDocData, i, j));
			}
			conclusionScreen.add(new ConclusionScreen(mainPanels, procedureDocData, i));
		}

		// 「次へ >」ボタンが押された場合の動作を示す．
		prevnextButton.nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// canMoveが1（真）であるとき，画面を遷移できる．
				// 画面のupdateメゾットの返り値が一つでも0（偽）であったとき，
				// 次の画面に遷移できない．
				int canMove = 1;
				String currentScreenName = mainPanels.getCurrentScreenName();
				canMove *= initialScreen.update();
				canMove *= idDateSreen.update(currentScreenName);
				canMove *= selectOperationScreen.update(currentScreenName);
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < ProcedureList.PROCEDURE_LIST[i].length; j++) {
						canMove *= procedureScreen.get(i).get(j).update(currentScreenName);
					}
					canMove *= conclusionScreen.get(i).update(currentScreenName);
				}
				if (canMove > 0) {
					mainPanels.next();
					updatePrevNextButtonText(mainPanels.getCurrentScreenName(), prevnextButton, idDateSreen.idNumStr);
				}

			}
		});
		// 「< 前へ」ボタンが押された場合の動作を示す．
		prevnextButton.prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanels.previous();
				updatePrevNextButtonText(mainPanels.getCurrentScreenName(), prevnextButton, idDateSreen.idNumStr);
			}
		});

		// mainPanelsに登録されたメインパネルをFrameに追加する．
		add(mainPanels.mainPanels);
		setLocation(200, 100);
		setSize(640, 480);
	}

	// 画面遷移専用のボタンのテキストの更新を行うメゾットである．
	private static void updatePrevNextButtonText(String currentScreenName, PrevNextButton prevnextButton,
			String idNumStr) {
		prevnextButton.setNextButtonStandardText();
		prevnextButton.setPrevButtonStandardText();
		if ((currentScreenName.equals("SelectOperationScreen"))
				|| (currentScreenName.equals("IdDateScreen") && idNumStr.length() < 4)) {
			prevnextButton.nextButton.setText("");
		} else if (currentScreenName.equals("ConclusionScreen0") || currentScreenName.equals("ConclusionScreen1")) {
			prevnextButton.nextButton.setText("終了");
			prevnextButton.prevButton.setText("< 初期画面へ");
		} else if (currentScreenName.equals("ProcedureScreen0_3") || currentScreenName.equals("ProcedureScreen1_7")) {
			prevnextButton.nextButton.setText("完了");
		} else if (currentScreenName.equals("InitialScreen")) {
			prevnextButton.setInitialText();
		}
	}
}
