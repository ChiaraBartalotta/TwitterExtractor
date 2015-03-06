package elements;

import org.json.simple.JSONArray;

public class Clique {
	String id;
	String greater_node_id;
	String nodes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGreater_node_id() {
		return greater_node_id;
	}
	public void setGreater_node_id(String greater_node_id) {
		this.greater_node_id = greater_node_id;
	}
	public String getNodes() {
		return nodes;
	}
	public void setNodes(String nodes_id) {
		this.nodes = nodes_id;
	}
}
