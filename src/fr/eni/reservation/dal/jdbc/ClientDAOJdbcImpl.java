package fr.eni.reservation.dal.jdbc;

import java.util.List;

import fr.eni.reservation.bo.Client;
import fr.eni.reservation.bo.Reservation;
import fr.eni.reservation.dal.ClientDAO;
import fr.eni.reservation.dal.DALException;

public class ClientDAOJdbcImpl implements ClientDAO {
	private static final String sqlSelectById = "select idClient, nomClient, prenomClient, adresseClient, cpClient, villeClient, grammage, couleur, type "
			+ " from articles where idArticle = ?";
	private static final String sqlSelectAll = "select idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur, type "
			+ " from articles";
	private static final String sqlUpdate = "update articles set reference=?,marque=?,designation=?,prixUnitaire=?,qteStock=?,grammage=?,couleur=? where idArticle=?";
	private static final String sqlInsert = "insert into articles(reference,marque,designation,prixUnitaire,qteStock,type,grammage,couleur) values(?,?,?,?,?,?,?,?)";
	private static final String sqlDelete = "delete from articles where idArticle=?";
	
	
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
