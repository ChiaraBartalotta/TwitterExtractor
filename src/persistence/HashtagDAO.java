package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import elements.Hashtag;
import elements.Tweet;

public class HashtagDAO implements HashtagRepository {
	public boolean insert(Hashtag ht) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		String insert = "insert into hashtag(tweet_id,text,trend) values(?,?,?)";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(insert);
			statement.setLong(1, Long.parseLong(ht.getTweet_id()));
			statement.setString(2, ht.getText());
			statement.setString(3, ht.getTrend());
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
