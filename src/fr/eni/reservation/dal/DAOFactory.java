package fr.eni.reservation.dal;

import fr.eni.reservation.dal.jdbc.ClientDAOJdbcImpl;
//import fr.eni.reservation.dal.jdbc.ReservationDAOJdbcImpl;
import fr.eni.reservation.dal.jdbc.SpectacleDAOJdbcImpl;

public class DAOFactory {

	public static void getReservationDAO()
	{
		//ReservationDAO reservationDAO = new ReservationDAOJdbcImpl();
		//return reservationDAO;
	}
	
	public static SpectacleDAO getSpectacleDAO()
	{
		SpectacleDAO spectacleDAO = new SpectacleDAOJdbcImpl();
		return spectacleDAO;
	}
	
	public static ClientDAO getClientDAO()
	{
		ClientDAO clientDAO = new ClientDAOJdbcImpl();
		return clientDAO;
	}
}
