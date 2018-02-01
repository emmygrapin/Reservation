package fr.eni.reservation.ihm;

import javax.swing.SwingUtilities;

import fr.eni.reservation.dal.DALException;

public class LauncherGraph {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run()
			{
				try {
					ApplyController.getInstance();
				} catch (DALException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
