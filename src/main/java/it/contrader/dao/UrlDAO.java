package it.contrader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.contrader.model.UrlTable;
import it.contrader.model.User;
import it.contrader.utils.ConnectionSingleton;

public class UrlDAO {
	
	private final String QUERY_ALL = "SELECT * FROM url";
	private final static String QUERY_CREATE = "INSERT INTO url (long_url, short_url, fk_iduser) VALUES (?,?,?)";
	private final static String QUERY_READ = "SELECT * FROM url WHERE fk_iduser=?";
	//private final String QUERY_UPDATE = "UPDATE url SET shot_url=?, fk_iduser=?, WHERE id=?";
	//private final String QUERY_DELETE = "DELETE FROM url WHERE id=?";
	private final static String QUERY_SUPPORT_CREATE = "SELECT * FROM url ORDER BY id DESC LIMIT 1";
	
	public UrlDAO() {
		
	}
	
	public List<UrlTable> getAll() {
		List<UrlTable> urlList = new ArrayList<>();
		Connection connection = ConnectionSingleton.getInstance();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(QUERY_ALL);
			UrlTable url;
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String longUrl = resultSet.getString("long_url");
				String shortUrl = resultSet.getString("short_url");
				int fkIdUser = resultSet.getInt("fk_iduser");
				url = new UrlTable(longUrl, shortUrl, fkIdUser);
				url.setId(id);
				urlList.add(url);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return urlList;
	}
	
	
		public List<UrlTable> read(int urlId) {
			List<UrlTable> urlList = new ArrayList<>();
			Connection connection = ConnectionSingleton.getInstance();
			int id = 0;
			try {
				PreparedStatement prepStat = connection.prepareStatement(QUERY_READ);
				prepStat.setInt(1, urlId);
				ResultSet resultSet = prepStat.executeQuery();

				while(resultSet.next()) {
					id +=1;
					String longUrl = resultSet.getString("long_url");
					String shortUrl = resultSet.getString("short_url");
					UrlTable urlTable = new UrlTable();
					urlTable.setId(id);
					urlTable.setLongUrl(longUrl);
					urlTable.setShortUrl(shortUrl);
					urlList.add(urlTable);
				}
				return urlList;
			} catch (SQLException e) {
				return null;
			}
		}

		
	public static UrlTable insert(UrlTable urlToInsert) {
		Connection connection = ConnectionSingleton.getInstance();
		UrlTable urlTable = new UrlTable();	
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, urlToInsert.getLongUrl());
			preparedStatement.setString(2, urlToInsert.getShortUrl());
			preparedStatement.setInt(3, urlToInsert.getFkIdUser());
			preparedStatement.execute();
			connection = ConnectionSingleton.getInstance();
			//Per non usare la JOIN sulla tabella del Server
			preparedStatement = connection.prepareStatement(QUERY_SUPPORT_CREATE);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt("id");	
			urlTable.setId(id);	
			urlTable.setLongUrl(urlToInsert.getLongUrl());
			urlTable.setShortUrl(urlToInsert.getShortUrl());
			return urlTable;
			
		} catch (SQLException e) {
			return urlTable;
		}

	}
	


}
