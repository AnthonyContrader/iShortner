package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.dto.UrlTableDTO;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.UrlTable;
import it.contrader.model.User;

/**
 * 
 * @author gianmarcopolizzi
 *
 */

public class UrlTableDAO {
	
	private final String QUERY_ALL = "SELECT * FROM url";
	private final static String QUERY_CREATE = "INSERT INTO url (url, fk_id_user) VALUES (?,?)";
	private final static String QUERY_READ = "SELECT * FROM url WHERE fk_id_user=?";
	private final String QUERY_UPDATE = "UPDATE url SET url=?, fk_user=?, WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM url WHERE id=?";
	private final static String QUERY_READ2 = "select * from user where id=?";
	
	//Costruttore di default
	public UrlTableDAO() {
		
	}
	
	public List<UrlTable> getAll() {
		List<UrlTable> urlList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			UrlTable u;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String url = resultSet.getString("url");
				String id_user = resultSet.getString("fk_user");
				u = new UrlTable(id, url, id_user);
				u.setId(id);
				urlList.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return urlList;
	}
	
	public static boolean insert(UrlTable urlToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			//preparedStatement con setString id
			preparedStatement.setString(1, urlToInsert.getUrl());
			preparedStatement.setString(2, urlToInsert.getFk_id_user());	
			preparedStatement.execute();		
			return true;
		} catch (SQLException e) {
			return false;
		}

	}
	
	public static List<UrlTable> read(int urlId) {
		List<UrlTable> urlList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		UrlTable urlTable;
		int id = 0;
		try {
			PreparedStatement prepStat = connection.prepareStatement(QUERY_READ2);
			prepStat.setInt(1, urlId);
			ResultSet result = prepStat.executeQuery();
			result.next();
			String username = result.getString("username");
			prepStat = connection.prepareStatement(QUERY_READ);
			prepStat.setString(1, username);
			ResultSet resultSet = prepStat.executeQuery();
			while(resultSet.next()) {
				id +=1;
				String url = resultSet.getString("url");
				urlTable = new UrlTable(id, url);
				urlTable.setUrl(url);
				urlList.add(urlTable);
			}
			return urlList;
		} catch (SQLException e) {
			return null;
		}

	}

	/*
	public boolean update(Url_Table urlToUpdate) {
		Connection connection = ConnectionSingleton.getInstance();

		// Controlla se l'id è presente
		if (urlToUpdate.getId() == 0)
			return false;

		Url_Table urlRead = read(urlToUpdate.getId());
		if (!urlRead.equals(urlToUpdate)) {
			try {
				// Fill the userToUpdate object
				if (urlToUpdate.getUrl() == null || urlToUpdate.getUrl().equals("")) {
					urlToUpdate.setUrl(urlRead.getUrl());
				}

				//Ho dubbi su questo controllo!
				if (urlToUpdate.getFk_id_user() == 0) {
					urlToUpdate.setFk_id_user(urlRead.getFk_id_user());
				}


				// Update the user
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
				preparedStatement.setInt(1, urlToUpdate.getId());
				preparedStatement.setString(2, urlToUpdate.getUrl());
				preparedStatement.setInt(3, urlToUpdate.getFk_id_user());
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

	}
	*/

	public boolean delete(int idUrl) {
		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
			preparedStatement.setInt(1, idUrl);
			int n = preparedStatement.executeUpdate();
			if (n != 0)
				return true;

		} catch (SQLException e) {
		}
		return false;
	}

}
