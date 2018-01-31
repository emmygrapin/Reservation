package fr.eni.reservation.dal;

import java.util.List;

import fr.eni.reservation.bo.Client;
import fr.eni.reservation.bo.Reservation;

public interface ClientDAO {

	public Client selectById(int idClient) throws DALException;
	
	public List<Client> selectAll() throws DALException;
	
	public void insert(Reservation data) throws DALException;
	
	public void update(Reservation data) throws DALException;
	
	public void delete(Reservation data) throws DALException;
}
