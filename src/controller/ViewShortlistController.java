package controller;

import model.Request;
import model.Session;
import model.Shortlist;
import java.util.List;

public class ViewShortlistController {

    public List<Request> viewShortlist() {
        return Shortlist.viewShortlist(Session.getUserID());
    }

    public boolean removeFromShortlist(int requestID) {
        return Shortlist.removeShortlist(Session.getUserID(), requestID);
    }
}




