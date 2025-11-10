package controller;

import model.Request;
import java.util.List;

public class SearchHistoryController {

    public List<Request> searchHistory(String requestID) {

        // If no requestID entered → return all completed requests
        if (requestID == null || requestID.trim().isEmpty()) {
            return Request.searchHistoryAll(); // ✅ now exists
        }

        try {
            int id = Integer.parseInt(requestID);
            return Request.searchHistoryById(id); // ✅ now exists
        } catch (Exception e) {
            System.out.println("⚠ Invalid request ID: " + e.getMessage());
            return List.of();
        }
    }
}


