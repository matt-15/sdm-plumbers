package controller;

import model.Shortlist;

public class SaveRequestController {

    public boolean saveRequest(int requestID, int csrID) {
        return Shortlist.saveToShortlist(csrID, requestID);
    }
}






