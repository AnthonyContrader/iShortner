package it.contrader.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.contrader.main.ConnectionSingleton;
import it.contrader.model.User;

/**
 * 
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class LoginDAO {

	private final String QUERY_LOGIN = "SELECT * FROM user WHERE username = ? AND password = ?";

	
	public User login (String username, String password) {

		Connection connection = ConnectionSingleton.getInstance();
		User user = new User();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);
			
			statement.setString(1, username); 
			statement.setString(2, password);

			
			ResultSet resultSet;
			
			if(statement.executeQuery().next()) {
				resultSet = statement.executeQuery();
				resultSet.next();
				int id = resultSet.getInt("id");
				username = resultSet.getString("username");
				password = resultSet.getString("password");
				String usertype = resultSet.getString("usertype");	
				user = new User(id, username, password, usertype);
				return user;
			}

			return user;
		}
		
		catch (SQLException e) {
			
			return null;
		}
	}
}
