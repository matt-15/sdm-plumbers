package controller;

import model.Request;
import java.util.List;

public class SearchRequestsController {

    public List<Request> searchRequests(String username, String description, String status) {
        return Request.searchRequests(username, description, status);
    }
}

