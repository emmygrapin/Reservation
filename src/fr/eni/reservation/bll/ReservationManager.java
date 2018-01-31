package fr.eni.reservation.bll;

import java.util.List;

import fr.eni.reservation.bo.Reservation;

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
	
	public List<Reservation> getReservation()
	{
		return daoReservation.selectAll();
	}
	
	public void addReservation(Reservation reservation) 
	{
		validerReservation();
		daoReservation.insert(reservation);
		
	}
	
	public void removeReservation(Reservation reservation)
	{
		daoReservation.delete(reservation.getIdReservation());
	}
	
	public void validerReservation(Reservation reservation)
	{
		String error = "";
		
		if(reservation.getSpectacle().getPlacesRestante() < reservation.getNbPlaces())
		{
			error += "Il n'y a pas suffisament de places.";
		}
		
		if (error != "")
		{
			throw new Exception(error);
		}
		
	}
	
	public List<Reservation> getClientReservation(int index)
	{
		return daoReservation.selectByIdClient(index);
	}
	

}
