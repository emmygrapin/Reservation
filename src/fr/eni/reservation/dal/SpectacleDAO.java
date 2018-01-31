package fr.eni.reservation.dal;

import java.util.List;

import fr.eni.reservation.bo.Spectacle;

public interface SpectacleDAO {

	public Spectacle selectById(int idSpectacle) throws DALException;
	
	public List<Spectacle> selectAll() throws DALException;
	
//	public void insert(Spectacle data) throws DALException;
	
	public void update(Spectacle data) throws DALException;
	
//	public void delete(Spectacle data) throws DALException;
}
