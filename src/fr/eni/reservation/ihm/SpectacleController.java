package fr.eni.reservation.ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.eni.reservation.bll.SpectacleManager;
import fr.eni.reservation.bo.Spectacle;
import fr.eni.reservation.dal.DALException;

public class SpectacleController {
	
	private JTextField fieldArtiste;

	private JButton btnSearchArtiste;
	private JButton btnReservation;
	
	private static SpectacleController _instance;
	
	private SpectacleController()
	{
		
	}
	
	public static SpectacleController getInstance()
	{
		if(_instance ==  null)
		{
			SpectacleController._instance = new SpectacleController();
		}
		return _instance;
		
	}
	
	//Panel d'affichage des spectacles 
		public JPanel viewSpectacle() throws DALException
		{
			
			SpectacleManager spectacleManager = SpectacleManager.getInstance();
			
			List<Spectacle> spectacles = spectacleManager.getSpectacles();
			
			
			JPanel panelSpectacles = new JPanel();
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
			panelSpectacles.add(this.getFieldSearchArtiste(), gbc);
			gbc.gridx = 2;
			panelSpectacles.add(this.getBtnSearchArtiste(), gbc);		
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
		
	//Panel d'affichage pour un spectacle
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
			
			panelSpectacle.add(this.getBtnReservation(spectacle), gbc);
			panelSpectacle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
			
			return panelSpectacle;
		}
		
		public JButton getBtnReservation(Spectacle spectacle)
		{
			if (spectacle.getPlacesDispos() > 0) {
				btnReservation = new JButton("Reservations");
				btnReservation.setBackground(new Color(10, 200, 50));
				btnReservation.setForeground(new Color(255, 255, 255));
				btnReservation.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						List<Spectacle> listeSpectacle = new ArrayList<>();
						listeSpectacle.add(spectacle);
						try {
							ApplyController.getInstance().move("listSpec", listeSpectacle );
						} catch (DALException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
			} else {
				btnReservation = new JButton("Indisponible");
				btnReservation.setBackground(new Color(200, 20, 50));
				btnReservation.setForeground(new Color(255, 255, 255));
			}	
			
			return btnReservation;
			
		}
		
		public JTextField getFieldSearchArtiste()
		{
		
			if (fieldArtiste == null) {
				fieldArtiste = new JTextField(20);
				fieldArtiste.addActionListener(new ActionListener() {
					
				
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println("test");
					}
				});
			}
			return fieldArtiste;
			
		}
		
		public JButton getBtnSearchArtiste() 
		{
			if (btnSearchArtiste == null) {
				btnSearchArtiste = new JButton("OK");
				btnSearchArtiste.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			return btnSearchArtiste;
			
		}
}

