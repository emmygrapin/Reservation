package fr.eni.reservation.ihm;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import fr.eni.reservation.bll.ClientManager;
import fr.eni.reservation.bll.ReservationManager;
import fr.eni.reservation.bll.SpectacleManager;
import fr.eni.reservation.bo.Client;
import fr.eni.reservation.bo.Spectacle;
import fr.eni.reservation.dal.DALException;


public class reservation extends JFrame {

	private JPanel panel;
	private JLabel labelWelcome;
	private JMenuBar menuBar;
	
	
	
	
	public reservation() throws DALException
	{
		// Cr�ation du conteneur de plus haut niveau		
		this.setTitle("Reservation");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		// R�glage de la taille du conteneur
		this.setSize(640, 480);
		
		// R�glage de la position du conteneur
		this.setLocationRelativeTo(null);
		
		// Fermeture de l'application JAVA lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setJMenuBar(addMenu());
		
		// J'affiche la fen�tre
		this.setVisible(true);
	}
	
	
	
	//Menu
	private JMenuBar addMenu()
	{
		JMenuItem menu = new JMenuItem("Accueil");
		JMenuItem menu1 = new JMenuItem("Reservations");
		JMenuItem menu2 = new JMenuItem("Clients");
				
		menu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ApplyController.getInstance().move("listClient");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		menu1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ApplyController.getInstance().move("listResa");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		menu2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ApplyController.getInstance().move("listClient");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		this.menuBar= new JMenuBar();
		this.menuBar.add(menu);
		this.menuBar.add(menu1);
		this.menuBar.add(menu2);
		
		return this.menuBar;
	}
	
}
