package elements;

public class Follower {
	public Follower(String followingId, String followerId) {
		super();
		this.followingId = followingId;
		this.followerId = followerId;
	}
	String followingId;
	String followerId;
	public String getFollowingId() {
		return followingId;
	}
	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}
	public String getFollowerId() {
		return followerId;
	}
	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}
}
