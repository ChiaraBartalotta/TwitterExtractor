package persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class IdBroker {
	private  Logger logger = Logger.getLogger("persistence.IdBroker");

	private final String query = "SELECT nextval('id') AS id";

	public Long getId(Connection connection) {
		long id = -1;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
		} catch (SQLException e) {
			logger.severe("Errore SQL: " + e.getMessage());
			try {
				throw new PersistenceException(e.getMessage());
			} catch (PersistenceException e1) {
				e1.printStackTrace();
			}
		}
		return new Long(id);
	}
}
