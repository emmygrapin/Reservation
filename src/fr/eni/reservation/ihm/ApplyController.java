package fr.eni.reservation.ihm;

import java.awt.Container;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import fr.eni.reservation.bll.ClientManager;
import fr.eni.reservation.bll.ReservationManager;
import fr.eni.reservation.bll.SpectacleManager;
import fr.eni.reservation.bo.Client;
import fr.eni.reservation.bo.Reservation;

import fr.eni.reservation.bo.Spectacle;
import fr.eni.reservation.dal.DALException;

public class ApplyController {
	
	public static ApplyController instance;
	
	private reservation ecr;
	private int panel = 0;
	private JButton annulerButton;
	
	
	private ApplyController() throws DALException
	{
		
		ecr = new reservation();
		//ecr.setContentPane(viewReservations());
		ecr.setContentPane(viewSpectacle());
		
		Container contain = ecr.getContentPane();
		
		JScrollPane scroll = new JScrollPane(contain);
		ecr.setContentPane(scroll);	
	}
	
	public static ApplyController getInstance() throws DALException
	{
		if( ApplyController.instance == null)
		{
			ApplyController.instance = new ApplyController();
		}
		return ApplyController.instance;
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
		int y = 0;
		gbc.gridx = 0;
	
		for(Client client:clients)
		{
			gbc.gridy = y;
			panelClients.add(viewUnClient(client), gbc);
			y++;
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
		gbc.gridy = 0;
		
		gbc.gridx = 0;
		panelClient.add(new JLabel(client.getNomClient()), gbc);
		gbc.gridx = 1;
		panelClient.add(new JLabel(client.getPrenomClient()), gbc);
		gbc.gridx = 2;
		panelClient.add(new JLabel(client.getEmailClient()), gbc);
		
		
		return panelClient;
	}
	

	//Panel d'affichage liste de r�servations
	public JPanel viewReservations() throws DALException{
		ReservationManager reservationManager = ReservationManager.getInstance();
		List<Reservation> listeReservations = reservationManager.getReservations();
		
		JPanel panelReservations = new JPanel();
		panelReservations.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);

		int y = 1;
		gbc.gridy = 0;
		panelReservations.add(new JLabel("R�servations")) ;
		gbc.gridx = 0;
		for(Reservation reservation : listeReservations)
		{
			gbc.gridy = y;
			panelReservations.add(viewUneReservation(reservation), gbc);
			y++;
		}
		
		return panelReservations;
		
	}
	
	public JPanel viewUneReservation(Reservation reservation){
		JPanel panelReservation = new JPanel();
		panelReservation.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		//Alignement � gauche
		gbc.anchor = GridBagConstraints.LINE_START;
		
		// Ligne 1
		gbc.gridy = 0;
		gbc.gridx = 0;
		panelReservation.add(new JLabel(reservation.getClient().getNomClient()
			+ " " 
			+ reservation.getClient().getPrenomClient()
			+ " / "
			+ reservation.getClient().getEmailClient())
			, gbc);
		//Ligne 2
		gbc.gridy = 2;
		panelReservation.add(new JLabel(reservation.getSpectacle().getTitre()
				+ " "
				+ reservation.getSpectacle().getArtiste()
				+ " / Date de r�servation: "
				+ reservation.getDateReservation()
				+ " nb de places r�serv�es : "
				+ String.valueOf(reservation.getNbPlacesReservation())), gbc);
		//Bouton Annuler
		gbc.gridx = 2;
		panelReservation.add(addAnnulerButton(reservation), gbc);
		
		return panelReservation;
	}
	
	public JButton addAnnulerButton(Reservation reservation){
		annulerButton = new JButton("Annuler");
		annulerButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ReservationManager.getInstance().removeReservation(reservation);
					Spectacle spectacleReservation = reservation.getSpectacle();
					spectacleReservation.setPlacesDispos(spectacleReservation.getPlacesDispos() + reservation.getNbPlacesReservation());
					SpectacleManager.getInstance().updateSpectacle(spectacleReservation);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return annulerButton;
	}

		
//Panel d'affichage des spectacles
	public JPanel viewSpectacle() throws DALException
	{
		
		SpectacleManager spectacleManager = SpectacleManager.getInstance();
		
		List<Spectacle> spectacles = spectacleManager.getSpectacles();
		
		JPanel panelSpectacles = new JPanel();
		panelSpectacles.setSize(this.ecr.getWidth(), this.ecr.getHeight());
		panelSpectacles.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridy = 0;
		panelSpectacles.add(new JLabel("Liste des Spectacles"), gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelSpectacles.add(new JLabel("Recherche par Artiste : "),gbc);
		gbc.gridx = 1;
		panelSpectacles.add(new JTextField(20), gbc);
		gbc.gridx = 2;
		panelSpectacles.add(new JButton("OK"), gbc);		
		int y = 2;
				
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 3;
		for(Spectacle spectacle : spectacles)
		{
			
			gbc.gridy = y;
			panelSpectacles.add(viewUnSpectacle(spectacle), gbc);
			y++;
		}
		
		return panelSpectacles;
		
	}
	
	public JPanel viewUnSpectacle(Spectacle spectacle) throws DALException
	{
		JPanel panelSpectacle = new JPanel();
		panelSpectacle.setLayout(new GridBagLayout());
		panelSpectacle.setSize(600, 600);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		panelSpectacle.add(new JLabel(spectacle.getArtiste() + ", " + spectacle.getTitre()), gbc);
		gbc.gridy = 1;
		panelSpectacle.add(new JLabel(spectacle.getLieu() + " / " + spectacle.getDate()), gbc);
		gbc.gridx = 1;
		gbc.anchor = GridBagConstraints.EAST;
		JButton dispo = new JButton("R�servations");
		dispo.setBackground(new Color(10, 200, 50));
		dispo.setForeground(new Color(255, 255, 255));
		panelSpectacle.add(dispo, gbc);
		panelSpectacle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
		
		return panelSpectacle;
	}
	
	

}

