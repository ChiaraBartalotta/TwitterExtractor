package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import elements.Follower;
import elements.Hashtag;
import elements.User;

public class FollowerDAO implements FollowerRepository {
	public boolean insert(Follower follower) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		String insert = "insert into follower(following, follower) values(?,?)";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(insert);
			statement.setLong(1, Long.parseLong(follower.getFollowingId()));
			statement.setLong(2, Long.parseLong(follower.getFollowerId()));
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
	public ArrayList<String> findFollower(String userFollowing) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		ArrayList<String> followers = new ArrayList<String>();
		String select = "select follower from follower where following = ?";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(select);
			statement.setLong(1,Long.parseLong(userFollowing));
			ResultSet result = statement.executeQuery();
			while(result.next()){
				followers.add(String.valueOf(result.getLong("follower")));
			}
			
			return followers;
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
	public ArrayList<String> findFollowing(String userFollower) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		ArrayList<String> followers = new ArrayList<String>();
		String select = "select following from follower where follower = ?";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(select);
			statement.setLong(1,Long.parseLong(userFollower));
			ResultSet result = statement.executeQuery();
			while(result.next()){
				followers.add(String.valueOf(result.getLong("following")));
			}
			
			return followers;
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
	@Override
	public ArrayList<String> findAllFollower() {
		DataSource datasource = new DataSource();
		Connection connection=null;
		ArrayList<String> followers = new ArrayList<String>();
		String select = "select distinct follower from follower";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				followers.add(String.valueOf(result.getLong("follower")));
			}
			
			return followers;
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
	@Override
	public ArrayList<String> findAllFollowing() {
		DataSource datasource = new DataSource();
		Connection connection=null;
		ArrayList<String> followers = new ArrayList<String>();
		String select = "select distinct following from follower";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				followers.add(String.valueOf(result.getLong("following")));
			}
			
			return followers;
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
