package fr.eni.reservation.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.reservation.bll.ClientManager;
import fr.eni.reservation.bo.Client;
import fr.eni.reservation.dal.DALException;

public class ApplyController {
	
	public static ApplyController instance;
	
	private reservation ecr;
	private int panel = 0;
	
	
	
	private ApplyController() throws DALException
	{
		
		ReservationController controller = null;
		controller = ReservationController.getinstance();
		
		ecr = new reservation();
		
		ecr.setContentPane(controller.NewReservation());
		
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
	
	
	

}

