package fr.eni.reservation.ihm;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class reservation extends JFrame {

	private JPanel panel;
	private JLabel labelWelcome;
	private JMenuBar menuBar;
	
	
	
	
	public reservation()
	{
		// Création du conteneur de plus haut niveau		
		this.setTitle("Reservation");
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
		// Réglage de la taille du conteneur
		this.setSize(640, 480);
		
		// Réglage de la position du conteneur
		this.setLocationRelativeTo(null);
		
		// Fermeture de l'application JAVA lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setupIHM();
		
		// J'affiche la fenêtre
		this.setVisible(true);
	}
	
	private void setupIHM()
	{
		// Creation du panel
		panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panel);
		 
		panel.setSize(this.getWidth(), this.getHeight());
		panel.setOpaque(true);
		
		// Mise en place Layout
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		
		//Création du menu
		this.setJMenuBar(addMenu());
		
		// Colonne 1
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(this.addLabelWelcome(), gbc);
		gbc.gridy = 1;
		panel.add(this.afficherSpectacle(), gbc);
		gbc.gridy = 2;
		panel.add(this.afficherSpectacle(), gbc);
		gbc.gridy = 3;
		panel.add(this.afficherSpectacle(), gbc);

		
		panel.setPreferredSize(new Dimension(300 ,400));
		  
		this.setContentPane(panel);
		
		this.add(scrollPane);
	}
	
	
	
	
	//Labels	
	private JLabel addLabelWelcome(){
		this.labelWelcome = new JLabel("Bienvenue");
		return this.labelWelcome;
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
	
	
	//Spectacle	
	private JPanel afficherSpectacle(){
		
		JPanel panelSpe = new JPanel();
		panelSpe.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		
		// Colonne 1
		gbc.gridy = 0;
		
		gbc.gridx = 0;
		panelSpe.add(new JLabel("Artiste"), gbc);
		gbc.gridx = 1;
		panelSpe.add(new JLabel("Lieu"), gbc);
		
		// Colonne 2
		gbc.gridy = 1;
		
		gbc.gridx = 0;
		panelSpe.add(new JLabel("Spectacle"), gbc);
		gbc.gridx = 1;
		panelSpe.add(new JLabel("Date"), gbc);
		
		
		return panelSpe;
	}
		
	
}
