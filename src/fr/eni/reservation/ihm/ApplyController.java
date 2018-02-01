package fr.eni.reservation.ihm;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

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
		ecr.setContentPane(viewReservations());
		
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
	
	//Panel d'affichage liste de réservations
	public JPanel viewReservations() throws DALException{
		ReservationManager reservationManager = ReservationManager.getInstance();
		List<Reservation> listeReservations = reservationManager.getReservations();
		
		JPanel panelReservations = new JPanel();
		panelReservations.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		int y = 1;
		gbc.gridy = 0;
		panelReservations.add(new JLabel("Réservations")) ;
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
		//Alignement à gauche
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
				+ " / Date de réservation: "
				+ reservation.getDateReservation()
				+ " nb de places réservées : "
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
}

