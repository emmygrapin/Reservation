package fr.eni.reservation.bll;

import java.util.List;

import fr.eni.reservation.bo.Reservation;
import fr.eni.reservation.dal.DALException;
import fr.eni.reservation.dal.DAOFactory;
import fr.eni.reservation.dal.ReservationDAO;

public class ReservationManager {
	
	private ReservationDAO daoReservation;
	private static ReservationManager _instance;
	
	private ReservationManager()
	{
		daoReservation =  DAOFactory.getReservationDAO();
	}
	
	public static ReservationManager getInstance() 
	{
		if(_instance == null)
		{
			_instance = new ReservationManager();
		}
		return _instance;		
	}
	
	public List<Reservation> getReservations() throws DALException
	{
		return daoReservation.selectAll();
	}
	
	public void addReservation(Reservation reservation) throws Exception 
	{
		validerReservation(reservation);
		daoReservation.insert(reservation);
		
	}
	
	public void removeReservation(Reservation reservation) throws DALException
	{
		daoReservation.delete(reservation);
	}
	
	public void validerReservation(Reservation reservation) throws Exception
	{
		String error = "";
		
		if(reservation.getSpectacle().getPlacesDispos() < reservation.getNbPlacesReservation())
		{
			error += "Il n'y a pas suffisament de places.";
		}
		
		if (error != "")
		{
			throw new Exception(error);
		}
		
	}

	

}
