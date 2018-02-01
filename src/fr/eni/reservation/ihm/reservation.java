package fr.eni.reservation.ihm;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import fr.eni.reservation.bll.ClientManager;
import fr.eni.reservation.bo.Client;
import fr.eni.reservation.dal.DALException;


public class reservation extends JFrame {

	private JPanel panel;
	private JLabel labelWelcome;
	private JMenuBar menuBar;
	
	
	
	
	public reservation() throws DALException
	{
		// Création du conteneur de plus haut niveau		
		this.setTitle("Reservation");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		// Réglage de la taille du conteneur
		this.setSize(600, 600);
		
		// Réglage de la position du conteneur
		this.setLocationRelativeTo(null);
		
		// Fermeture de l'application JAVA lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setJMenuBar(addMenu());
		
		// J'affiche la fenêtre
		this.setVisible(true);
	}
	
	
	
	//Menu
	private JMenuBar addMenu()
	{
		JMenu menu = new JMenu("Accueil");
		JMenu menu1 = new JMenu("Reservations");
		JMenu menu2 = new JMenu("Clients");
		
		this.menuBar= new JMenuBar();
		this.menuBar.add(menu);
		this.menuBar.add(menu1);
		this.menuBar.add(menu2);
		
		return this.menuBar;
	}
	
}
