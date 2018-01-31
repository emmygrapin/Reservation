package fr.eni.reservation.ihm;

import javax.swing.SwingUtilities;

import fr.eni.papeterie.bll.BLLException;

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
