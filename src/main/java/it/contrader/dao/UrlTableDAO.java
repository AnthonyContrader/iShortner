package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.main.ConnectionSingleton;
import it.contrader.model.Url_Table;

/**
 * 
 * @author gianmarcopolizzi
 *
 */

public class UrlTableDAO {
	
	private final String QUERY_ALL = "SELECT * FROM url";
	private final String QUERY_CREATE = "INSERT INTO url (url, fk_id_user) VALUES (?,?)";
	private final String QUERY_READ = "SELECT * FROM url WHERE id=?";
	private final String QUERY_UPDATE = "UPDATE url SET url=?, fk_id_user=?, WHERE id=?";
	private final String QUERY_DELETE = "DELETE FROM url WHERE id=?";
	
	//Costruttore di default
	public UrlTableDAO() {
		
	}
	
	public List<Url_Table> getAll() {
		List<Url_Table> urlList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			Url_Table u;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String url = resultSet.getString("url");
				int id_user = resultSet.getInt("fk_id_user");
				u = new Url_Table(id, url, id_user);
				u.setId(id);
				urlList.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return urlList;
	}
	
	public boolean insert(Url_Table urlToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setInt(1, urlToInsert.getId());
			preparedStatement.setString(2, urlToInsert.getUrl());
			preparedStatement.setInt(3, urlToInsert.getFk_id_user());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}

	}
	
	public Url_Table read(int urlId) {
		Connection connection = ConnectionSingleton.getInstance();
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
			preparedStatement.setInt(1, urlId);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String url;
			int fk_id_user;

			url = resultSet.getString("url");
			fk_id_user = resultSet.getInt("user_id");
			Url_Table url_tab = new Url_Table(url, fk_id_user);
			url_tab.setId(resultSet.getInt("id"));

			return url_tab;
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
