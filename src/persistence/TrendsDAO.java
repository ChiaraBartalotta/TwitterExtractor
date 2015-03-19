package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import elements.Trends;

public class TrendsDAO implements TrendsRepository {
	public boolean insert(Trends trend) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		String insert = "insert into trends(id,name,query,url,woeid,searched_at, woeid_name) values(?,?,?,?,?,?,?)";
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(insert);
			IdBroker broker = new IdBroker();
			statement.setLong(1, broker.getId(connection));
			statement.setString(2,trend.getName());
			statement.setString(3,trend.getQuery());
			statement.setString(4,trend.getUrl());
			statement.setDouble(5,trend.getWoeid());


			// 1) create a java calendar instance
			Calendar calendar = Calendar.getInstance();

			// 2) get a java.util.Date from the calendar instance.
			//			    this date will represent the current instant, or "now".
			java.util.Date now = calendar.getTime();

			// 3) a java current time (now) instance
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

			statement.setTimestamp(6, currentTimestamp);
			//			SimpleDateFormat formatter =new SimpleDateFormat("YYYY-MM-DD'T'HH:MM:SSX");
			//			String dateString = trend.getSearched_at();		
			//			
			//			
			//			try {
			//		 
			//				java.util.Date date = formatter.parse(dateString);
			//				java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
			//				statement.setTimestamp(6, sqlDate);
			//		 
			//			} catch (ParseException e) {
			//				e.printStackTrace();
			//			}
			statement.setString(7,trend.getWoeid_name());
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

	@Override
	public ArrayList<String> getUrls_byTimestamp(Timestamp t) {
		DataSource datasource = new DataSource();
		Connection connection=null;
		ArrayList<String> urls = new ArrayList<String>();
		String select = "select url from trends where searched_at > "+t;
		PreparedStatement statement=null;
		try {
			connection = datasource.getConnection();
			statement = connection.prepareStatement(select);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				urls.add(result.getString("url"));
			}

			return urls;
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
