package it.contrader.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.contrader.model.Server;
import it.contrader.utils.ConnectionSingleton;

public class ServerDAO {

	
	private final static String QUERY_CREATE = "INSERT INTO server (posizione, tipologia, date, fk_idurl) VALUES (?,?,?,?)";
	private final static String QUERY_READ = "SELECT * FROM server WHERE fk_idurl=?";
	private final static String QUERY_SUPPORT_READ = "SELECT * FROM url WHERE fk_iduser=?";


	public static void insert(Server server) {
		Connection connection = ConnectionSingleton.getInstance();
		try {	
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
			preparedStatement.setString(1, server.getPosizione());
			preparedStatement.setString(2, server.getTipologia());
			preparedStatement.setString(3, server.getData());
			preparedStatement.setInt(4, server.getFkIdUrl());
			System.out.println("1");
			preparedStatement.execute();
			System.out.println("2");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
     //CORRISPONDENZA 1 TO 1 OR 1 TO MANY?????
	public static List<Server> read(int serverId) {
		Connection connection = ConnectionSingleton.getInstance();
		List<Server> serverList = new ArrayList<Server>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SUPPORT_READ);
			preparedStatement.setInt(1, serverId);
			ResultSet resultSet = preparedStatement.executeQuery();
			PreparedStatement prep = connection.prepareStatement(QUERY_READ);
			while(resultSet.next()) {
				Server server = new Server();
				int id = resultSet.getInt("id");
				prep.setInt(1, id);
				ResultSet result = prep.executeQuery();
				result.next();
				String posizione = result.getString("posizione");
				String tipologia = result.getString("tipologia");
				String date = result.getString("date");
				int fk_idurl = result.getInt("fk_idurl");
				server.setPosizione(posizione);
				server.setTipologia(tipologia);
				server.setData(date);
				server.setFkIdUrl(fk_idurl);
				serverList.add(server);
			}
			return serverList;
		} catch (SQLException e) {
			return null;
		}
	}
}
