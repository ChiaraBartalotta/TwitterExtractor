package elements;

import java.sql.Date;



public class Trends {
	Double id;
	String name;
	String query;
	String url;
	Double woeid;
	String searched_at;
	String woeid_name;
	
	public Trends(Double id, String name, String query, String url,
			Double woeid, String searched_at, String woeid_name) {
		super();
		this.id = id;
		this.name = name;
		this.query = query;
		this.url = url;
		this.woeid = woeid;
		this.searched_at = searched_at;
	}
	public Trends(){
	}
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Double getWoeid() {
		return woeid;
	}
	public void setWoeid(Double woeid) {
		this.woeid = woeid;
	}
	public String getSearched_at() {
		return searched_at;
	}
	public void setSearched_at(String searched_at) {
		this.searched_at = searched_at;
	}
	public String getWoeid_name() {
		return woeid_name;
	}
	public void setWoeid_name(String woeid_name) {
		this.woeid_name = woeid_name;
	}
}
