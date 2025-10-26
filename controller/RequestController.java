package project.controller;

import project.entity.Request;
import project.entity.Match;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestController {
    private Map<Integer, Request> store = new HashMap<>();
    private Map<String, List<Match>> matchHistoryByUser = new HashMap<>();
    private AtomicInteger seq = new AtomicInteger(0);

    // In-memory shortlist per CSR (simplified)
    private List<Request> shortlist = new ArrayList<>();

    // ---- Methods used by CSRUI (already in your console flow; kept for completeness) ----
    public List<Request> searchRequests(String query) {
        String q = (query == null) ? "" : query.toLowerCase();
        List<Request> results = new ArrayList<>();
        for (Request r : store.values()) {
            if (r.summary().toLowerCase().contains(q)) {
                results.add(r);
            }
        }
        return results;
    }

    public void saveToShortlist(int id) {
        Request r = store.get(id);
        if (r != null) shortlist.add(r);
    }

    public List<Request> viewShortlist() {
        return new ArrayList<>(shortlist);
    }

    public List<Match> viewCompletedMatches() {
        // Return empty for now; integrate your real matching later
        return Collections.emptyList();
    }

    // ---- Methods used by PINUI (required to fix your errors) ----

    public Request createRequest(String username, String description) {
        int id = seq.incrementAndGet();
        Request r = new Request(id, username, description);
        store.put(id, r);
        return r;
    }

    public Request viewRequest(int id) {
        Request r = store.get(id);
        if (r != null) {
            // increment views (simple stat)
            r.incrementViews();
        }
        return r;
    }

    public Request updateRequest(int id, String newDescription) {
        Request r = store.get(id);
        if (r != null) {
            r.setDescription(newDescription);
        }
        return r;
    }

    public void deleteRequest(int id) {
        store.remove(id);
    }

    public String viewRequestStats(int id) {
        Request r = store.get(id);
        if (r == null) return "Request not found.";
        return "Views: " + r.getViews() + " | Shortlisted: " + countShortlisted(id);
    }

    private int countShortlisted(int id) {
        int count = 0;
        for (Request r : shortlist) {
            if (r.getId() == id) count++;
        }
        return count;
    }

    public List<Match> viewCompletedMatchesHistory(String username) {
        return new ArrayList<>(matchHistoryByUser.getOrDefault(username, Collections.emptyList()));
    }

    // Helper to add a match to history (optional, for future)
    public void addCompletedMatch(String username, Match match) {
        matchHistoryByUser.computeIfAbsent(username, k -> new ArrayList<>()).add(match);
    }
}
