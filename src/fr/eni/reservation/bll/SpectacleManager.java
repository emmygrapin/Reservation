package fr.eni.reservation.bll;

import java.util.List;

import fr.eni.reservation.bo.Reservation;
import fr.eni.reservation.bo.Spectacle;

public class SpectacleManager {
	
	private SpectacleDAO daoSpectacle;
	private static SpectacleManager _instance;
	
	private SpectacleManager()
	{
		daoSpectacle =  DAOFactory.getSpectacleDAO();
	}
	
	public static SpectacleManager getInstance() 
	{
		if(_instance == null)
		{
			_instance = new SpectacleManager();
		}
		return _instance;		
	}
	
	public List<Spectacle> getSpectacle()
	{
		return daoSpectacle.selectAll();
	}
	
	public void addSpectacle(Spectacle spectacle) 
	{
		daoSpectacle.insert(spectacle);
		
	}
	
	public void removeSpectacle(Spectacle spectacle)
	{
		daoSpectacle.delete(spectacle.getIdSpectacle());
	}
	
	
}
