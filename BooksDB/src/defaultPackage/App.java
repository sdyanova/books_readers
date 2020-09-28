package defaultPackage;

import javax.swing.SwingUtilities;

import gui.MainFrame;

public class App {

	public static void main(String[] args) {
		//Causes doRun.run() to be executed asynchronously on theAWT event dispatching thread.
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainFrame();
			}
		});

	}

}
