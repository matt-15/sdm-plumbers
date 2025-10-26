package project.entity;

import java.time.LocalDateTime;

public class Match {
    private int id;
    private String pinId;
    private String volunteerId;
    private String requestId;
    private LocalDateTime completedAt;
    
    
    public Match(int id, String pinId, String volunteerId, int requestId) {
        this.id = id;
        this.pinId = pinId;
        this.volunteerId = volunteerId;
        this.requestId = String.valueOf(requestId); // store as String if you want
        this.completedAt = LocalDateTime.now();
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
	public String getVolunteerId() {
		return volunteerId;
	}
	public void setVolunteerId(String volunteerId) {
		this.volunteerId = volunteerId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public LocalDateTime getCompletedAt() {
		return completedAt;
	}
	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

    // Constructors, getters, setters
    // Domain methods
    public String summary() {
        return "Match #" + id + " | PIN: " + pinId + " | Volunteer: " + volunteerId + " | Request: " + requestId + " | Completed: " + completedAt;
    }
}
