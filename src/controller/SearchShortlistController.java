package controller;

import model.Shortlist;
import model.Request;
import java.util.List;

public class SearchShortlistController {

    // Default CSR ID = 1 (replace later when login works)
    private static final int csrID = 1;

    // ✅ View all shortlist items for this CSR
    public List<Request> searchShortlistForCSR() {
        return Shortlist.viewShortlist(csrID);
    }

    // ✅ Search shortlist by Request ID
    public List<Request> searchShortlistByRequest(int requestID) {
        return Shortlist.viewShortlistByRequest(requestID, csrID);
    }
}



