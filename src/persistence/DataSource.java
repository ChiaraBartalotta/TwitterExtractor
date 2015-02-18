package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

	private String dbURI = "jdbc:postgresql://localhost:5432/Twitter";
	private String userName = "postgres";
	private String password = "mossy";

	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
		    Class.forName("org.postgresql.Driver");
		    connection = DriverManager.getConnection(dbURI,userName, password);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch(SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println(e.getSQLState());
			throw new PersistenceException(e.getMessage());
			
		}
		return connection;
	}

}
