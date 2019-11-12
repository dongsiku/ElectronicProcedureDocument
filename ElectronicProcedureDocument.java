import javax.swing.*;
import java.awt.*;

class ElectronicProcedureDocument extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Electronic Procedure Document");

		Container container = frame.getContentPane();
		container.setBackground(Color.white);

		ProcedureDocData procedureDocData = new ProcedureDocData();
		// GridLayout gl = new GridLayout(3, 2, 5, 10);
		// container.setLayout(gl);

		InitialScreen initialScreen = new InitialScreen(container);
		IdDateScreen idDateSreen = new IdDateScreen(container, procedureDocData);
		// ProcedureScreen procedureScreen = new ProcedureScreen(contanier);
		// initialScreen.showScreen();
		idDateSreen.showScreen();
		// procedureScreen.showScreen();

		frame.setLocation(200, 100);
		frame.setSize(640, 480);
		frame.setVisible(true);
	}

}
