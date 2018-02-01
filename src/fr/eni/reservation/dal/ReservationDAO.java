package fr.eni.reservation.dal;

import java.util.List;

import fr.eni.reservation.bo.Reservation;

public interface ReservationDAO {

	public Reservation selectById(String codeReservation) throws DALException;
	
	public List<Reservation> selectAll() throws DALException;
	
	public void insert(Reservation data) throws DALException;
	
	public void update(Reservation data) throws DALException;
	
	public void delete(Reservation data) throws DALException;
	
	public List<Reservation> selectByIdClient(int data) throws DALException;
}
