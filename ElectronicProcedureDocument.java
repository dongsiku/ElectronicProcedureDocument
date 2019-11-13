import javax.swing.*;
import java.awt.*;

class ElectronicProcedureDocument extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Electronic Procedure Document");
		Container container = frame.getContentPane();
		container.setBackground(Color.white);
		JPanel mainPanel = new JPanel();

		PrevNextButton prevnextButton = new PrevNextButton(container);
		prevnextButton.showPrevNextButton();

		ProcedureDocData procedureDocData = new ProcedureDocData();
		// GridLayout gl = new GridLayout(3, 2, 5, 10);
		// container.setLayout(gl);

		InitialScreen initialScreen = new InitialScreen(mainPanel, prevnextButton);
		IdDateScreen idDateSreen = new IdDateScreen(mainPanel, prevnextButton, procedureDocData);
		// ProcedureScreen procedureScreen = new ProcedureScreen(contanier);
		// initialScreen.showScreen();
		idDateSreen.showScreen();
		// procedureScreen.showScreen();
		container.add(mainPanel);
		frame.setLocation(200, 100);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

}
