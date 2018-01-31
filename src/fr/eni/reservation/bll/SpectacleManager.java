package fr.eni.reservation.bll;

import java.util.List;

import fr.eni.reservation.bo.Spectacle;
import fr.eni.reservation.dal.DALException;
import fr.eni.reservation.dal.DAOFactory;
import fr.eni.reservation.dal.SpectacleDAO;

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
	
	public List<Spectacle> getSpectacles() throws DALException
	{
		return daoSpectacle.selectAll();
	}
	
	public Spectacle getSpectacle(int idSpectacle) throws DALException{
		return daoSpectacle.selectById(idSpectacle);
	}
	
	public void updateSpectacle(Spectacle spectacle) throws DALException{
		 daoSpectacle.update(spectacle);
	}
	
	
//	public void addSpectacle(Spectacle spectacle) 
//	{
//		daoSpectacle.insert(spectacle);
//		
//	}
//	
//	public void removeSpectacle(Spectacle spectacle)
//	{
//		daoSpectacle.delete(spectacle.getIdSpectacle());
//	}
	
	
}
