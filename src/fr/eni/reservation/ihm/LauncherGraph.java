package fr.eni.reservation.ihm;

import javax.swing.SwingUtilities;

public class LauncherGraph {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run()
			{
				ApplyController.getInstance();
			}
		});
	}

}
