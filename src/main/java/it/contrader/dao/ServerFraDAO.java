package it.contrader.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.ServerFra;
import it.contrader.model.User;

public class ServerFraDAO {

	private final String QUERY_ALL = "SELECT * FROM server";
	private final static String QUERY_CREATE = "INSERT INTO server (nome_citta, fk_id_url) VALUES (?,?)";
	private final String QUERY_READ = "SELECT * FROM server WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE server SET id=?, nome_citta=?";
	private final String QUERY_DELETE = "DELETE FROM server WHERE id=?";

	public ServerFraDAO() {

	}
	
//	public List<ServerFra> getAll() {
//		List<ServerFra> serverFraList = new ArrayList<>();
//		Connection connection = ConnectionSingleton.getInstance();
//		try {
//			Statement statement = connection.createStatement();
//			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
//			while (resultSet.next()) {
//				int id = resultSet.getInt("id");
//				String nome_citta = resultSet.getString("nome_citta");
//				int fk_id_url = resultSet.getInt("fk_id_url");
//				ServerFra s = new ServerFra(id, nome_citta, fk_id_url);
//				s.setId(id);
//				serverFraList.add(s);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return serverFraList;
//	}

	public static boolean insert(ServerFra serverToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			//preparedStatement.setInt(1, serverToInsert.getId());
			preparedStatement.setString(1, serverToInsert.getNomeCitta());
			preparedStatement.setString(2, serverToInsert.getFk());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public static void read(int serverId) {
		Connection connection = ConnectionSingleton.getInstance();
		/*try {


			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, serverId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int id, fk_id_url;
			String nome_citta;

			id = resultSet.getInt("id");
			nome_citta = resultSet.getString("nome_citta");
			fk_id_url = resultSet.getInt("fk_id_url");
			//ServerFra server = new ServerFra(id, nome_citta, fk_id_url);
			server.setId(resultSet.getInt("id"));

			return server;
		} catch (SQLException e) {
			return null;
		}*/

	}

	/*public boolean update(ServerFra serverToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Check if id is present
		if (serverToUpdate.getId() == 0)
			return false;

		ServerFra serverRead = read(serverToUpdate.getId());
		if (!serverRead.equals(serverToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (serverToUpdate.getId() == null || serverToUpdate.getNomeCitta().equals("")) {
					serverToUpdate.setUsername(userRead.getUsername());
				}

				if (userToUpdate.getPassword() == null || userToUpdate.getPassword().equals("")) {
					userToUpdate.setPassword(userRead.getPassword());
				}

				if (userToUpdate.getUsertype() == null || userToUpdate.getUsertype().equals("")) {
					userToUpdate.setUsertype(userRead.getUsertype());
				}

				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setString(1, userToUpdate.getUsername());
				preparedStatement.setString(2, userToUpdate.getPassword());
				preparedStatement.setString(3, userToUpdate.getUsertype());
				preparedStatement.setInt(4, userToUpdate.getId());
				int a = preparedStatement.executeUpdate();
				if (a > 0)
					return true;
				else
					return false;

			} catch (SQLException e) {
				return false;
			}
		}

		return false;

	}*/

	public boolean delete(int id) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, id);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}


}
