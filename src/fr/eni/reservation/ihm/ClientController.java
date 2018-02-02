package fr.eni.reservation.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.eni.reservation.bll.ClientManager;
import fr.eni.reservation.bll.ReservationManager;
import fr.eni.reservation.bll.SpectacleManager;
import fr.eni.reservation.bo.Client;
import fr.eni.reservation.bo.Reservation;
import fr.eni.reservation.bo.Spectacle;
import fr.eni.reservation.dal.DALException;

public class ClientController {
	

private static ClientController _instance;
	
	private ClientController()
	{
		
	}
	
	public static ClientController getinstance()
	{
		if(_instance ==  null)
		{
			ClientController._instance = new ClientController();
		}
		
		return _instance;
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
		//Alignement à gauche
		gbc.anchor = GridBagConstraints.LINE_START;
		
		
		
		// Ligne 1
		gbc.gridy = 0;
		
		gbc.gridx = 0;
		panelClient.add(new JLabel(client.getNomClient()), gbc);
		gbc.gridx = 1;
		panelClient.add(new JLabel(client.getPrenomClient()), gbc);
		gbc.gridx = 2;
		panelClient.add(new JLabel(client.getEmailClient()), gbc);
		gbc.gridx = 3;
		panelClient.add(addSupprimerButton(client), gbc);
		
		
		return panelClient;
	}
	
	
	public JButton addSupprimerButton(Client client){
		JButton supprimerButton = new JButton("Supprimer");
		supprimerButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ReservationManager.getInstance().removeClientReservation(client);
					ClientManager.getInstance().removeClient(client);
					ApplyController.getInstance().move("listClient", new ArrayList<>());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return supprimerButton;
	}
	
}
