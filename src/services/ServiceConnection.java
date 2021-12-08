package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import connection.Connection_DB;
import util.ReadFile;

public class ServiceConnection {
	static LinkedList<String> properties = new LinkedList<>();
	
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException{
		properties = ReadFile.connectionProperties();
		Connection_DB conn = new Connection_DB(properties.get(0), properties.get(1), properties.get(2), properties.get(3));
		Connection connection = conn.crateConnection();
		return connection;
	}
}
