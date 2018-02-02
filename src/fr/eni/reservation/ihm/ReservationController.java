package fr.eni.reservation.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.reservation.bll.ClientManager;
import fr.eni.reservation.bll.ReservationManager;
import fr.eni.reservation.bll.SpectacleManager;
import fr.eni.reservation.bo.Client;
import fr.eni.reservation.bo.Reservation;
import fr.eni.reservation.bo.Spectacle;
import fr.eni.reservation.dal.DALException;

public class ReservationController {
	
	private JTextField txtNom, txtPrenom, txtAdresse, txtEmail, txtVille, txtCP;
	private JButton btnValider, btnValider2;
	private JComboBox cbxPlaces2, cbxPlaces, cbxClients;
	
	private static ReservationController _instance;
	
	private ReservationController()
	{
		
	}
	
	public static ReservationController getinstance()
	{
		if(_instance ==  null)
		{
			ReservationController._instance = new ReservationController();
		}
		
		return _instance;
	}
	
	

	//Panel de reservation
	public JPanel NewReservation(Spectacle spectacle) throws DALException
	{
	
		JPanel panelReservation = new JPanel();
		panelReservation.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Espace entre les cases
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.LINE_START;
		
		//Colonne 1
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		panelReservation.add(new JLabel(spectacle.getArtiste()+", "+spectacle.getTitre()), gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		panelReservation.add(new JLabel("Places Restantes : "+spectacle.getPlacesDispos()));
		
		
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		panelReservation.add(new JLabel(spectacle.getLieu()+", "+spectacle.getDate()), gbc);
		gbc.gridwidth = 1;
		gbc.gridy = 2;
		panelReservation.add(new JLabel("Nom : "), gbc);
		gbc.gridy = 3;
		panelReservation.add(new JLabel("Prenom : "), gbc);
		gbc.gridy = 4;
		panelReservation.add(new JLabel("Email : "), gbc);
		gbc.gridy = 5;
		panelReservation.add(new JLabel("Adresse : "), gbc);
		gbc.gridy = 6;
		panelReservation.add(new JLabel("Code Postale : "), gbc);
		gbc.gridy = 7;
		panelReservation.add(new JLabel("Ville : "), gbc);
		gbc.gridy = 8;
		panelReservation.add(new JLabel("Places : "), gbc);
		
		//Colonne 2
		gbc.gridx = 1;
		gbc.gridy = 2;
		panelReservation.add(addFieldNom(), gbc);
		gbc.gridy = 3;
		panelReservation.add(addFieldPrenom(), gbc);
		gbc.gridy = 4;
		panelReservation.add(addFieldEmail(), gbc);
		gbc.gridy = 5;
		panelReservation.add(addFieldAdresse(), gbc);
		gbc.gridy = 6;
		panelReservation.add(addFieldCP(), gbc);
		gbc.gridy = 7;
		panelReservation.add(addFieldVille(), gbc);
		gbc.gridy = 8;
		panelReservation.add(addCbxNBPlaces(0), gbc);
		
		//Buton Save New Client
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		panelReservation.add(getBtnSave(spectacle), gbc);
		
		//Buton Save Old Client
		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		panelReservation.add(getBtnSaveClient(spectacle), gbc);
		
		//Client existant
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		
		panelReservation.add(new JLabel("Client existant : "), gbc);
		gbc.gridx = 3;
		gbc.gridy = 3;
		panelReservation.add(addCbxClients(), gbc);
		gbc.gridy = 4;
		panelReservation.add(addCbxNBPlaces(1), gbc);
		
		return panelReservation;
	}
	
	
	//TextField
	private JTextField addFieldNom()
	{
		if (this.txtNom == null) {
			this.txtNom = new JTextField(20);
		}
		return this.txtNom;
	}
	
	private JTextField addFieldPrenom()
	{
		if (this.txtPrenom == null) {
			this.txtPrenom = new JTextField(20);
		}
		return this.txtPrenom;
	}
	
	private JTextField addFieldEmail()
	{
		if (this.txtEmail == null) {
			this.txtEmail = new JTextField(20);
		}
		return this.txtEmail;
	}
	
	private JTextField addFieldAdresse()
	{
		if (this.txtAdresse == null) {
			this.txtAdresse = new JTextField(20);
		}
		return this.txtAdresse;
	}
	
	private JTextField addFieldCP()
	{
		if (this.txtCP == null) {
			this.txtCP = new JTextField(20);
		}
		return this.txtCP;
	}
	
	private JTextField addFieldVille()
	{
		if (this.txtVille == null) {
			this.txtVille = new JTextField(20);
		}
		return this.txtVille;
	}
	
	
	//ComboBox
	private JComboBox addCbxNBPlaces(int box)
	{
		Vector<Integer> places = new Vector<Integer>();
		places.addElement(new Integer(1));
		places.addElement(new Integer(2));
		places.addElement(new Integer(3));
		places.addElement(new Integer(4));
		places.addElement(new Integer(5));
		if(box == 0)
		{
			this.cbxPlaces = new JComboBox(places);	
			return this.cbxPlaces;
		}
		else
		{
			this.cbxPlaces2 = new JComboBox(places);	
			return this.cbxPlaces2;
		}

	}

	private JComboBox addCbxClients() throws DALException
	{
		Vector<Client> clients = new Vector<Client>();
		List<Client> lesclients = ClientManager.getInstance().getClients();
		
		
		
		for(Client client : lesclients)
		{
			
			clients.addElement(client);
		}
		this.cbxClients = new JComboBox(clients);
		return this.cbxClients;
	}
	
	//Button
	public JButton getBtnSave(Spectacle spectacle)
	{
		if(btnValider == null)
		{
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try {
						Client client = newClient();
						newReservation(client, spectacle, 0);
						ApplyController.getInstance().move("listResa", new ArrayList());
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}
			});
		}
		return btnValider;
	}
	
	
	public JButton getBtnSaveClient(Spectacle spectacle)
	{
		if(btnValider2 == null)
		{
			btnValider2 = new JButton("Valider");
			btnValider2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e)
				{
					try {
						Client client = (Client)cbxClients.getSelectedItem();
						System.out.println("nom"+ client.getNomClient());
						newReservation(client, spectacle, 1);
						ApplyController.getInstance().move("listResa", new ArrayList());
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
				}
			});
		}
		return btnValider2;
	}
	
	
	
	public Client newClient() throws DALException
	{
		ClientManager clientManager = ClientManager.getInstance();
		Client client = new Client(txtNom.getText(), txtPrenom.getText(), txtEmail.getText(), txtAdresse.getText(), txtCP.getText(), txtVille.getText());
		clientManager.addClient(client);
		return client;
	}
	
	
	public void newReservation(Client client, Spectacle spectacle, int box) throws Exception
	{
		 Timestamp timestamp = new Timestamp(System.currentTimeMillis()); 
		ReservationManager reservationManager = ReservationManager.getInstance();
		String code = generateCode();
		int places = 0;
		if(box == 0)
		{
			places =  (int)cbxPlaces.getSelectedItem();
		}
		else
		{
			places =  (int)cbxPlaces2.getSelectedItem();
		}
		Reservation reservation = new Reservation(code, client, spectacle, places, timestamp);
		reservationManager.addReservation(reservation);
		MajPlaces(spectacle, places);
		
	}
	
	
	public void MajPlaces(Spectacle spectacle, int places) throws Exception
	{		    
		SpectacleManager spectacleManager = SpectacleManager.getInstance();
		spectacle.setPlacesDispos(spectacle.getPlacesDispos() - places);
		spectacleManager.updateSpectacle(spectacle);
	}
	
	
	
	public String generateCode()
	{
		int length = 5;
		
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
        StringBuffer pass = new StringBuffer();
        for(int x=0;x<length;x++)   {
           int i = (int)Math.floor(Math.random() * (chars.length() -1));
           pass.append(chars.charAt(i));
        }
        return pass.toString();
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
		JButton annulerButton = new JButton("Annuler");
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
