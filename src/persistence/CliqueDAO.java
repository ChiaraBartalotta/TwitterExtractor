package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import elements.Clique;

public class CliqueDAO implements CliqueRepository {
	public boolean insert(Clique clique) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		String insert = "insert into clique(id, greater_node_id, nodes) values(?,?,?)";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(insert);
			IdBroker broker = new IdBroker();
			statement.setLong(1, broker.getId(connection));
			statement.setLong(2, Long.parseLong(clique.getGreater_node_id()));
			statement.setString(3, clique.getNodes());
			return statement.executeUpdate()!=0;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				if (statement != null) 
					statement.close();
				if (connection!= null)
					connection.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
