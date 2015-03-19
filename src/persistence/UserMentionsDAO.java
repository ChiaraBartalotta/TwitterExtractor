package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import elements.Hashtag;
import elements.UserMentions;

public class UserMentionsDAO implements UserMentionsRepository {
	public boolean insert(UserMentions user_mentions) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		String insert = "insert into usermentions(tweet_id,user_id,screen_name,trend) values(?,?,?,?)";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(insert);
			statement.setLong(1, Long.parseLong(user_mentions.getTweet_id()));
			statement.setLong(2, Long.parseLong(user_mentions.getUser_id()));
			statement.setString(3, user_mentions.getScreen_name());
			statement.setString(4, user_mentions.getTrend());
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
