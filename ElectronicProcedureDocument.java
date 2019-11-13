import javax.swing.*;
import java.awt.*;

class ElectronicProcedureDocument extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Electronic Procedure Document");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = frame.getContentPane();
		container.setBackground(Color.white);
		// JPanel mainPanel = new JPanel();
		MainPanels mainPanels = new MainPanels(container);

		PrevNextButton prevnextButton = new PrevNextButton(container);
		prevnextButton.showPrevNextButton();

		ProcedureDocData procedureDocData = new ProcedureDocData();
		// GridLayout gl = new GridLayout(3, 2, 5, 10);
		// container.setLayout(gl);

		InitialScreen initialScreen = new InitialScreen(mainPanels, prevnextButton);
		IdDateScreen idDateSreen = new IdDateScreen(mainPanels, prevnextButton, procedureDocData);
		// ProcedureScreen procedureScreen = new ProcedureScreen(contanier);
		// initialScreen.showScreen();
		// procedureScreen.showScreen();
		initialScreen.listen();
		idDateSreen.listen();

		mainPanels.init();
		frame.setLocation(200, 100);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

}
