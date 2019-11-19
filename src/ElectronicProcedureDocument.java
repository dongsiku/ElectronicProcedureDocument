package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class ElectronicProcedureDocument extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Electronic Procedure Document");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = frame.getContentPane();
		container.setBackground(Color.white);
		// JPanel mainPanel = new JPanel();
		ProcedureDocData procedureDocData = new ProcedureDocData();
		MainPanels mainPanels = new MainPanels(procedureDocData);

		PrevNextButton prevnextButton = new PrevNextButton(container);
		prevnextButton.showPrevNextButton();

		// GridLayout gl = new GridLayout(3, 2, 5, 10);
		// container.setLayout(gl);
		InitialScreen initialScreen = new InitialScreen(mainPanels);
		IdDateScreen idDateSreen = new IdDateScreen(mainPanels, procedureDocData);
		SelectOperationScreen selectOperationScreen = new SelectOperationScreen(mainPanels, procedureDocData);

		ArrayList<ArrayList<ProcedureScreen>> procedureScreen = new ArrayList<ArrayList<ProcedureScreen>>();
		for (int i = 0; i < 2; i++) {
			procedureScreen.add(new ArrayList<ProcedureScreen>());
			for (int j = 0; j < CreateProcedureNameList.procedureList[i].length; j++) {
				procedureScreen.get(i).add(new ProcedureScreen(mainPanels, procedureDocData, i, j));
			}
		}

		// ProcedureScreen procedureScreen = new ProcedureScreen(contanier);
		// initialScreen.showScreen();
		// procedureScreen.showScreen();
		prevnextButton.nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int canMove = 1;
				String currentScreenName = mainPanels.currentScreenName();
				canMove *= idDateSreen.update(currentScreenName);
				canMove *= selectOperationScreen.update(currentScreenName);
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < CreateProcedureNameList.procedureList[i].length; j++) {
						canMove *= procedureScreen.get(i).get(j).update(currentScreenName);
					}
				}
				if (canMove > 0) {
					mainPanels.next();
				}
			}
		});
		prevnextButton.prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanels.previous();
			}
		});
		container.add(mainPanels.mainPanels);
		frame.setLocation(200, 100);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

}
