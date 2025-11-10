package controller;

import model.Request;
import java.util.List;

public class ViewHistoryController {

    // View request history (Completed only)
    public List<Request> viewHistory(String requestID) {
        try {
            if (requestID == null || requestID.trim().isEmpty()) {
                System.out.println("⚠ No Request ID entered.");
                return List.of();
            }

            int id = Integer.parseInt(requestID.trim());
            return Request.searchHistory(String.valueOf(id));
        } catch (Exception e) {
            System.out.println("⚠ Invalid Request ID: " + e.getMessage());
            return List.of();
        }
    }
}


