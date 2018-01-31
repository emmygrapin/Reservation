package fr.eni.reservation.dal.jdbc;

import java.util.List;

import fr.eni.reservation.bo.Client;
import fr.eni.reservation.bo.Reservation;
import fr.eni.reservation.dal.ClientDAO;
import fr.eni.reservation.dal.DALException;

public class ClientDAOJdbcImpl implements ClientDAO {

	@Override
	public Client selectById(int idClient) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Reservation data) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Reservation data) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Reservation data) throws DALException {
		// TODO Auto-generated method stub

	}

}
