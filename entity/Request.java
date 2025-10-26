package project.entity;

public class Request {
    private int id;
    private String pinId; // Person-in-Need ID
    private String description;
    private int views;
    private int shortlistCount;

    public Request(int id, String pinId, String description) {
        this.id = id;
        this.pinId = pinId;
        this.description = description;
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPinId() {
		return pinId;
	}
	public void setPinId(String pinId) {
		this.pinId = pinId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getShortlistCount() {
		return shortlistCount;
	}
	public void setShortlistCount(int shortlistCount) {
		this.shortlistCount = shortlistCount;
	}
    // Domain methods
    public void incrementViews() {
        this.views++;
    }

    public void incrementShortlistCount() {
        this.shortlistCount++;
    }

    public String summary() {
        return "Request #" + id + " by " + pinId + ": " + description;
    }
}