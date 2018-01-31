package fr.eni.reservation.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import fr.eni.reservation.bo.Client;
import fr.eni.reservation.JdbcTools;
import fr.eni.reservation.bo.Reservation;
import fr.eni.reservation.dal.ClientDAO;
import fr.eni.reservation.dal.DALException;

public class ClientDAOJdbcImpl implements ClientDAO {
	private static final String sqlSelectById = "select cli_id, cli_nom, cli_prenom, cli_email, cli_adresse, cli_cp, cli_ville"
			+ " from client where cli_id = ?";
	private static final String sqlSelectAll = "select cli_id, cli_nom, cli_prenom, cli_email, cli_adresse, cli_cp, cli_ville"
			+ " from client";
	private static final String sqlInsert = "insert into client(cli_id, cli_nom, cli_prenom, cli_email, cli_adresse, cli_cp, cli_ville) values(?,?,?,?,?,?,?)";
	private static final String sqlDelete = "delete from client where cli_id = ?";
	
	private Connection connection;
	
	// Constructeur
	public ClientDAOJdbcImpl() {

	}

	public Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = JdbcTools.jdbcConnexion();
		}
		return connection;
	}
	
	public void closeConnection(){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection=null;
		}
	}
	
	@Override
	public Client selectById(int idClient) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Client client = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, idClient);
			rs = rqt.executeQuery();
			if (rs.next()) {
			
					client = new Client(rs.getInt("cli_id"), rs.getString("cli_nom"), rs.getString("cli_prenom"),
							rs.getString("cli_email"), rs.getString("cli_adresse"), rs.getString("cli_cp"),rs.getString("cli_ville")
							);
				}
			} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + idClient, e);
		} finally {
			try {
				
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();

		}
		return client;
	}

	@Override
	public List<Client> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Client> clients = new ArrayList<Client>();
		try{
			cnx = getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Client client = null;
			while (rs.next()){
				client = new Client(rs.getInt("cli_id"), rs.getString("cli_nom"), rs.getString("cli_prenom"),
						rs.getString("cli_email"), rs.getString("cli_adresse"), rs.getString("cli_cp"),rs.getString("cli_ville")
						);
			}
			clients.add(client);
		}
		catch(SQLException e){
			throw new DALException("selectAll failed ", e);
		} finally {
			try {
				
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeConnection();
		}
		return clients;
	}

	@Override
	public void insert(Client data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			rqt.setString(1, data.getNomClient());
			rqt.setString(2, data.getPrenomClient());
			rqt.setString(3, data.getEmailClient());
			rqt.setString(4, data.getAdresseClient());
			rqt.setString(5, data.getCpClient());
			rqt.setString(6, data.getVilleClient());
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					data.setIdClient(rs.getInt(1));
				}
			}
		}catch(SQLException e){
			throw new DALException("insert failed ", e);
		}finally {
			try {
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
			closeConnection();

		}
	}

	@Override
	public void delete(int idClient) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, idClient);
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + idClient, e);
		} finally {
			try {
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			closeConnection();

		}
		
	}

}
