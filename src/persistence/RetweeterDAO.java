package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import elements.Follower;
import elements.Retweeter;

public class RetweeterDAO implements RetweeterRepository{
	public boolean insert(Retweeter retweeter) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		String insert = "insert into retweeter(tweet_id,user_id) values(?,?)";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(insert);
			statement.setLong(1, retweeter.getTweet_id());
			statement.setLong(2, retweeter.getUser_id());
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
