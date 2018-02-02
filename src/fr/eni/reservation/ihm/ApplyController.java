package fr.eni.reservation.ihm;

import java.awt.Color;
import java.awt.Container;
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
	
	
	private ApplyController() throws DALException
	{
	
		ClientController clientController = ClientController.getinstance();
		ReservationController reservationController = ReservationController.getinstance();
		
		ecr = new reservation();
		ecr.setContentPane(reservationController.viewReservations());
		
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
	
	public void move(String menu) throws DALException
	{
		
		ClientController clientController = ClientController.getinstance();
		ReservationController reservationController = ReservationController.getinstance();
		

		switch(menu)
		{
			case "listResa":
				ecr.setContentPane(reservationController.viewReservations());
			break;
			case "listClient":
				ecr.setContentPane(clientController.viewClient());
			break;
		}
		
		ecr.validate();
		ecr.repaint();
		
	}
	
}

