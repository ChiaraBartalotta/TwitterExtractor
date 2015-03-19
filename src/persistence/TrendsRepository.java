package persistence;

import java.sql.Timestamp;
import java.util.ArrayList;

import elements.Trends;

public interface TrendsRepository {
	boolean insert(Trends trend);
	ArrayList<String> getUrls_byTimestamp (Timestamp t);
}
