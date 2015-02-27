package persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import elements.Tweet;
import elements.User;

public class TweetDAO implements TweetRepository {
	public boolean insert(Tweet tweet) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		String insert = "insert into tweet(tweet_id,user_id,created_at,text,retweet_count,favorite_count, retweeted_id) values(?,?,?,?,?,?,?)";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(insert);
			statement.setLong(1, Long.parseLong(tweet.getId()));
			statement.setLong(2, Long.parseLong(tweet.getUser_id()));
			SimpleDateFormat formatter =new SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy");
			String dateString = tweet.getCreated_at();		
		 
			try {
		 
				java.util.Date date = formatter.parse(dateString);
				java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
				statement.setTimestamp(3, sqlDate);
		 
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			
			statement.setString(4, tweet.getText());
			statement.setInt(5, tweet.getRetweet_count());
			statement.setInt(6, tweet.getFavourite_count());
			statement.setLong(7, Long.parseLong(tweet.getRetweeted_id()));
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
	public ArrayList<String> findTweetId() {
		DataSource datasource = new DataSource();
		Connection connection=null;
		ArrayList<String> tweets = new ArrayList<String>();
		String select = "select tweet_id from tweet where retweeted_id = 0";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				tweets.add(String.valueOf(result.getLong("tweet_id")));
			}
			
			return tweets;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
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
	public ArrayList<String> findRetweetId() {
		DataSource datasource = new DataSource();
		Connection connection=null;
		ArrayList<String> tweets = new ArrayList<String>();
		String select = "select tweet_id from tweet where retweeted_id != 0";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				tweets.add(String.valueOf(result.getLong("tweet_id")));
			}
			
			return tweets;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
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
	/*public User findByUser_id(String userId) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		String select = "select * from users where username = ?";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(select);
			statement.setString(1,userId);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				User u = new User();
				u.setEmail(result.getString("email"));
				u.setName(result.getString("name"));
				u.setPassword(result.getString("password"));
				u.setUserId(result.getString("userId"));
				return u;
			}
			return null;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
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

*/
}
