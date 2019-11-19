package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
		ProcedureScreen[] procedureScreen0 = new ProcedureScreen[4];
		for (int i = 0; i < 4; i++) {
			procedureScreen0[i] = new ProcedureScreen(mainPanels, procedureDocData, 0, i);
		}
		ProcedureScreen[] procedureScreen1 = new ProcedureScreen[3];
		for (int i = 0; i < 3; i++) {
			procedureScreen1[i] = new ProcedureScreen(mainPanels, procedureDocData, 1, i);
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
				for (int i = 0; i < 3; i++) {
					canMove *= procedureScreen0[i].update(currentScreenName);
				}
				for (int i = 0; i < 3; i++) {
					canMove *= procedureScreen1[i].update(currentScreenName);
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
