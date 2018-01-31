package fr.eni.reservation.bll;

import java.util.List;

import fr.eni.reservation.bo.Client;
import fr.eni.reservation.bo.Spectacle;
import fr.eni.reservation.dal.ClientDAO;
import fr.eni.reservation.dal.DALException;
import fr.eni.reservation.dal.DAOFactory;

public class ClientManager {
	private ClientDAO daoClient;
	private static ClientManager _instance;
	
	private ClientManager(){
		daoClient = DAOFactory.getClientDAO();
	}
	
	public static ClientManager getInstance() 
	{
		if(_instance == null)
		{
			_instance = new ClientManager();
		}
		return _instance;		
	}
	
	public List<Client> getClients() throws DALException
	{
		return daoClient.selectAll();
	}
	
	public Client getClient(int idClient) throws DALException{
		return daoClient.selectById(idClient);
	}
	public void addClient(Client client) throws DALException 
	{
		daoClient.insert(client);
		
	}
	
	public void removeClient(Client client) throws DALException
	{
		daoClient.delete(client.getIdClient());
	}
}
