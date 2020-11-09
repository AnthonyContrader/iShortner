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
				int id_user = resultSet.getInt("fk_user");
				u = new UrlTable(id, url, id_user);
				u.setId(id);
				urlList.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return urlList;
	}
	
	public static UrlTable insert(UrlTable urlToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		UrlTable urlTable = new UrlTable();
		try {	
			
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			//preparedStatement con setString id
			preparedStatement.setString(1, urlToInsert.getUrl());
			preparedStatement.setInt(2, urlToInsert.getFk_id_user());	
			preparedStatement.execute();
			connection = ConnectionSingleton.getInstance();
			preparedStatement = connection.prepareStatement("select *from url ORDER BY id_url DESC LIMIT 1");
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt("id_url");	
			urlTable.setId(id);
			return urlTable;
		} catch (SQLException e) {
			return urlTable;
		}

	}
	//todo
	public static List<UrlTable> read(int urlId) {
		List<UrlTable> urlList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		int id = 0;
		try {
			PreparedStatement prepStat = connection.prepareStatement(QUERY_READ);
			prepStat.setInt(1, urlId);
			ResultSet resultSet = prepStat.executeQuery();

			while(resultSet.next()) {
				id +=1;
				String url = resultSet.getString("url");
				UrlTable urlTable = new UrlTable();
				urlTable.setId(id);
				urlTable.setUrl(url);
				urlList.add(urlTable);
			}
			return urlList;
		} catch (SQLException e) {
			return null;
		}

	}

}
