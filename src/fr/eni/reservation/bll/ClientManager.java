package fr.eni.reservation.bll;

import java.util.List;

import fr.eni.reservation.bo.Client;
import fr.eni.reservation.bo.Spectacle;

public class ClientManager {
	private ClientDAO daoClient;
	private static ClientManager _instance;
	
	private ClientManager(){
		daoClient = DAOFactory.getClientDAO();
	}
	
	public static SpectacleManager getInstance() 
	{
		if(_instance == null)
		{
			_instance = new ClientManager();
		}
		return _instance;		
	}
	
	public List<Client> getClients()
	{
		return daoClient.selectAll();
	}
	
	public Client getClient(int idClient){
		return daoClient.selectById(idClient);
	}
	public void addClient(Client client) 
	{
		daoClient.insert(client);
		
	}
	
	public void removeClient(Client client)
	{
		daoClient.delete(client.getIdClient());
	}
}
