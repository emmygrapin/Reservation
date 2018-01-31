package fr.eni.reservation.dal;

import java.util.List;

import fr.eni.reservation.bo.Client;

public interface ClientDAO {

	public Client selectById(int idClient) throws DALException;
	
	public List<Client> selectAll() throws DALException;
	
	public void insert(Client data) throws DALException;
	
	public void delete(int idClient) throws DALException;
}
