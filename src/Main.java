package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Main extends JFrame {

	private static final long serialVersionUID = 0;

	public static void main(String[] args) {
		Main ElectronicProcedureDocument = new Main();
		ElectronicProcedureDocument.setVisible(true);
	}

	Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon icon = new ImageIcon("icon/icon.png");
		setIconImage(icon.getImage());
		setBackground(Color.white);

		ProcedureDocData procedureDocData = new ProcedureDocData();

		PrevNextButton prevnextButton = new PrevNextButton();
		add(prevnextButton.prevnextButtonPanel, BorderLayout.NORTH);

		MainPanels mainPanels = new MainPanels(procedureDocData);
		InitialScreen initialScreen = new InitialScreen(mainPanels);
		IdDateScreen idDateSreen = new IdDateScreen(mainPanels, procedureDocData, prevnextButton);
		SelectOperationScreen selectOperationScreen = new SelectOperationScreen(mainPanels, procedureDocData,
				prevnextButton);

		ArrayList<ArrayList<ProcedureScreen>> procedureScreen = new ArrayList<ArrayList<ProcedureScreen>>();
		List<ConclusionScreen> conclusionScreen = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			procedureScreen.add(new ArrayList<ProcedureScreen>());
			for (int j = 0; j < ProcedureList.PROCEDURE_LIST[i].length; j++) {
				procedureScreen.get(i).add(new ProcedureScreen(mainPanels, procedureDocData, i, j));
			}
			conclusionScreen.add(new ConclusionScreen(mainPanels, procedureDocData, i));
		}

		prevnextButton.nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int canMove = 1;
				String currentScreenName = mainPanels.currentScreenName();
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
					prevnextCommon(mainPanels.currentScreenName(), prevnextButton, idDateSreen.idNumStr);
				}

			}
		});
		prevnextButton.prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanels.previous();
				prevnextCommon(mainPanels.currentScreenName(), prevnextButton, idDateSreen.idNumStr);
			}
		});

		add(mainPanels.mainPanels);
		setLocation(200, 100);
		setSize(640, 480);
	}

	private static void prevnextCommon(String currentScreenName, PrevNextButton prevnextButton, String idNumStr) {
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
