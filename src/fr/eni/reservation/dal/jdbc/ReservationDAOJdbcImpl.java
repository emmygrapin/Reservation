package fr.eni.reservation.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import fr.eni.reservation.JdbcTools;
import fr.eni.reservation.bo.Reservation;
import fr.eni.reservation.dal.ClientDAO;
import fr.eni.reservation.dal.DALException;
import fr.eni.reservation.dal.DAOFactory;
import fr.eni.reservation.dal.ReservationDAO;
import fr.eni.reservation.dal.SpectacleDAO;

public class ReservationDAOJdbcImpl implements ReservationDAO {

	private static final String sqlSelectById = "select res_code_reservation, res_spe_id, res_nb_places, res_date_reservation, res_client "
			+ " from RESERVATIONS where res_code_reservation like ?";
	private static final String sqlSelectAll = "select res_code_reservation, res_spe_id, res_nb_places, res_date_reservation, res_client from RESERVATIONS";
	private static final String sqlInsert = "insert into RESERVATIONS(res_code_reservation, res_spe_id, res_nb_places, res_date_reservation, res_client) values(?,?,?,?,?)";
	private static final String sqlUpdate = "update RESERVATIONS set res_spe_id=?, res_nb_places=?,res_date_reservation=?,res_client=? where res_code_reservation=?";
	private static final String sqlDelete = "delete from RESERVATIONS where res_code_reservation=?";
	private static final String sqlSelectbyIdClient = "res_code_reservation, res_spe_id, res_nb_places, res_date_reservation, res_client from RESERVATIONS where res_client = ?";
	private static final String sqlSelectByIdSpectacle = "res_code_reservation, res_spe_id, res_nb_places, res_date_reservation, res_client from RESERVATIONS where res_spe_id = ?";

	private Connection cnx;
	
	static {
		// Chargement du driver
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ReservationDAOJdbcImpl() 
	{

	}

	public Connection getConnection() throws SQLException 
	{
		//test la connexion si null
		if(cnx == null) {
			cnx = JdbcTools.jdbcConnexion();
		}
			return cnx;
	}
	
	public void closeConnection(){
		if(cnx!=null){
			try {
				cnx.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cnx=null;
		}
	}

	@Override
	public Reservation selectById(String codeReservation) throws DALException 
	{
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Reservation reserv = null;
		ClientDAO clientDAO = DAOFactory.getClientDAO();
		SpectacleDAO spectacleDAO = DAOFactory.getSpectacleDAO();
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setString(1, codeReservation);

			rs = rqt.executeQuery();
			if (rs.next()) {
				
				reserv = new Reservation(rs.getString("res_code_reservation"),  	       // res_code_reservation    
										 clientDAO.selectById(rs.getInt("res_client")),    // res_client
										 spectacleDAO.selectById(rs.getInt("res_spe_id")), // res_spe_id                     
										 rs.getInt("res_nb_places"),         			   // res_nb_places        
										 rs.getDate("res_date_reservation")); 			   // res_date_reservation    
			}

		} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + codeReservation, e);
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
		return reserv;
	}

	@Override
	public List<Reservation> selectAll() throws DALException 
	{
		
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Reservation> liste = new ArrayList<Reservation>();
		try {
			cnx = getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Reservation reserv = null;
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			SpectacleDAO spectacleDAO = DAOFactory.getSpectacleDAO();

			while (rs.next()) {
				
				reserv = new Reservation(rs.getString("res_code_reservation"),             // res_code_reservation    
										 clientDAO.selectById(rs.getInt("res_client")),    // res_client
										 spectacleDAO.selectById(rs.getInt("res_spe_id")), // res_spe_id 
										 rs.getInt("res_nb_places"),                       // res_nb_places 
										 rs.getDate("res_date_reservation"));              // res_date_reservation
				liste.add(reserv);
			}
		} catch (SQLException e) {
			throw new DALException("selectAll failed - ", e);
		} finally {
			try {
				
				if (rqt != null) {
					rqt.close();
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			closeConnection();
		}
		return liste;

	}

	@Override
	public void insert(Reservation data) throws DALException 
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			rqt.setString(1, data.getCodeReservation());          // res_code_reservation
			rqt.setInt(2, data.getClient().getIdClient());        // res_client
			rqt.setInt(3, data.getSpectacle().getIdSpectacle());  // res_spe_id
			rqt.setInt(4, data.getNbPlacesReservation());         // res_nb_places
			rqt.setDate(5, data.getDateReservation());		      // res_date_reservation
			

		} catch (SQLException e) {
			throw new DALException("Insert article failed - " + data, e);
		} finally {
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
	public void update(Reservation data) throws DALException 
	{	
		
	}

	@Override
	public void delete(Reservation data) throws DALException 
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setString(1, data.getCodeReservation());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + data.getCodeReservation(), e);
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
	
	public List<Reservation> selectByIdClient(int idClient) throws DALException 
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Reservation> liste = new ArrayList<Reservation>();
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectbyIdClient);
			rqt.setInt(1, idClient );
			rs = rqt.executeQuery();
			Reservation reserv = null;
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			SpectacleDAO spectacleDAO = DAOFactory.getSpectacleDAO();

			while (rs.next()) {
				
				reserv = new Reservation(rs.getString("res_code_reservation"),             // res_code_reservation
										 clientDAO.selectById(rs.getInt("res_client")),    // res_client
										 spectacleDAO.selectById(rs.getInt("res_spe_id")), // res_spe_id
										 rs.getInt("res_nb_places"),                       // res_nb_places
										 rs.getDate("res_date_reservation"));              // res_date_reservation
				liste.add(reserv);
			}
		} catch (SQLException e) {
			throw new DALException("selectByMarque failed - ", e);
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
		return liste;
	}
	
	public List<Reservation> selectByIdSpectacle(int idSpectacle) throws DALException 
	{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Reservation> liste = new ArrayList<Reservation>();
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectbyIdClient);
			rqt.setInt(1, idSpectacle );
			rs = rqt.executeQuery();
			Reservation reserv = null;
			ClientDAO clientDAO = DAOFactory.getClientDAO();
			SpectacleDAO spectacleDAO = DAOFactory.getSpectacleDAO();

			while (rs.next()) {
				
				reserv = new Reservation(rs.getString("res_code_reservation"),             // res_code_reservation
										 clientDAO.selectById(rs.getInt("res_client")),    // res_client
										 spectacleDAO.selectById(rs.getInt("res_spe_id")), // res_spe_id
										 rs.getInt("res_nb_places"),                       // res_nb_places
										 rs.getDate("res_date_reservation"));              // res_date_reservation
				liste.add(reserv);
			}
		} catch (SQLException e) {
			throw new DALException("selectByMarque failed - ", e);
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
		return liste;
	}
 
}
