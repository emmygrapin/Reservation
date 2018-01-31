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
		this.setSize(640, 480);
		
		// Réglage de la position du conteneur
		this.setLocationRelativeTo(null);
		
		// Fermeture de l'application JAVA lorsque on clique sur la croix
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.basePanel();
		
		// J'affiche la fenêtre
		this.setVisible(true);
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
	
	
	//Panel de base;
	public JPanel basePanel() throws DALException
	{
		
		// Creation du panel
		panel = new JPanel();
		 
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
		panel.add(this.viewClient(), gbc);

		this.setContentPane(panel);
		
		return panel;
		
	}
	
	//Panel d'affichage des clients
	public JPanel viewClient() throws DALException
	{
		
		ClientManager clientManager = ClientManager.getInstance() ;
		
		List<Client> clients = clientManager.getClients();
	
		JPanel panelClients = new JPanel();
		panelClients.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
	
		gbc.gridx = 0;
		gbc.gridy = 0;
	
		for(Client client:clients)
		{
			panelClients.add(viewUnClient(client));
		}
		
		return panelClients;
	}
	
	

	//Panel d'affichage d'un client
	public JPanel viewUnClient(Client client)
	{
		JPanel panelClient = new JPanel();
		panelClient.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		
		// Ligne 1
		gbc.gridx = 0;
		
		gbc.gridy = 0;
		panelClient.add(new JLabel(client.getNomClient()), gbc);
		gbc.gridy = 1;
		panelClient.add(new JLabel(client.getPrenomClient()), gbc);
		gbc.gridy = 3;
		panelClient.add(new JLabel(client.getEmailClient()), gbc);
		
		
		return panelClient;
	}
	
}
