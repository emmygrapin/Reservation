package fr.eni.reservation.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.reservation.JdbcTools;
import fr.eni.reservation.bo.Spectacle;
import fr.eni.reservation.dal.DALException;
import fr.eni.reservation.dal.SpectacleDAO;

public class SpectacleDAOJdbcImpl implements SpectacleDAO {
	
	private static final String sqlSelectById = "select spe_id, spe_titre, spe_artiste, spe_lieu, spe_date, spe_places_dispos"
			+ " from spectacle where spe_id = ?";
	private static final String sqlSelectAll = "select spe_id, spe_titre, spe_artiste, spe_lieu, spe_date, spe_places_dispos"
			+ " from spectacle";
	private static final String sqlUpdate = "update spectacle set spe_places_dispos=? where spe_id=?";
//private static final String sqlDelete = "delete from spectacle where spe_id = ?";
	
	private Connection connection;
	
	public SpectacleDAOJdbcImpl(){
		
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
	public Spectacle selectById(int idSpectacle) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Spectacle spectacle = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, idSpectacle);
			rs = rqt.executeQuery();
			if (rs.next()) {
			
					spectacle = new Spectacle(rs.getInt("spe_id"), rs.getString("spe_titre"), rs.getString("spe_artiste"),
							rs.getString("spe_lieu"), rs.getDate("spe_date"), rs.getInt("spe_places_dispos"));
				}
			} catch (SQLException e) {
			throw new DALException("selectById failed - id = " + idSpectacle, e);
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
		return spectacle;
	}

	@Override
	public List<Spectacle> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Spectacle> spectacles = new ArrayList<Spectacle>();
		try{
			cnx = getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Spectacle spectacle = null;
			while (rs.next()){
				spectacle = new Spectacle(rs.getInt("spe_id"), rs.getString("spe_titre"), rs.getString("spe_artiste"),
						rs.getString("spe_lieu"), rs.getDate("spe_date"), rs.getInt("spe_places_dispos"));
			}
			spectacles.add(spectacle);
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
		return spectacles;
	}

	public void update(Spectacle data) throws DALException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setInt(1, data.getPlacesDispos());
			rqt.setInt(2, data.getIdSpectacle());
			rqt.executeUpdate();
		}catch(SQLException e){
			throw new DALException("Update spectacle failed - " + data, e);
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
	};

}
