package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_DB {
	static String HOST;
	static String DATABASE;
	static String USER;
	static String PASS;
	
	public Connection_DB(String HOST, String DATABASE, String USER, String PASS) {
		super();
		Connection_DB.HOST = HOST;
		Connection_DB.DATABASE = DATABASE;
		Connection_DB.USER = USER;
		Connection_DB.PASS = PASS;
	}
	
	
	public Connection crateConnection() throws IOException, ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://" + HOST + ":5432/" + DATABASE;
		Connection connection = DriverManager.getConnection(url,USER,PASS);
		if(connection != null){
			return connection;
		}
		return null;
	}
	
	
}
