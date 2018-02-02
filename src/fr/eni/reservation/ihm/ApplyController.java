package fr.eni.reservation.ihm;

import java.awt.Container;
import java.util.List;

import javax.swing.JScrollPane;

import fr.eni.reservation.dal.DALException;

public class ApplyController {
	
	public static ApplyController instance;
	
	private reservation ecr;
	private int panel = 0;
	
	
	private ApplyController() throws DALException
	{
	
		ClientController clientController = ClientController.getinstance();
		ReservationController reservationController = ReservationController.getinstance();
		SpectacleController spectacleController = SpectacleController.getInstance();
		
		ecr = new reservation();
		//ecr.setContentPane(reservationController.viewReservations());
		ecr.setContentPane(spectacleController.viewSpectacle());
		
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
	
	public void move(String menu, List liste) throws DALException
	{
		
		ClientController clientController = ClientController.getinstance();
		ReservationController reservationController = ReservationController.getinstance();
		SpectacleController spectacleController = SpectacleController.getInstance();
		

		switch(menu)

		{
		 	case "listSpec":
		 		ecr.setContentPane(spectacleController.viewSpectacle());
			break;
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

