package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class Test {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException{
		try {
			try {
				Connection_DB conn = new Connection_DB("localhost", "Electoral Processe DB", "postgres", "postgres");
				Connection connection = conn.crateConnection();
				System.out.println("Conexion establecida...");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}


