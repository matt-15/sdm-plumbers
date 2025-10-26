package project.boundary;

import project.controller.RequestController;
import project.entity.Request;
import project.entity.Match;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CSRUI extends JFrame {
    private RequestController requestController;
    private static final long serialVersionUID = 1L;


    public CSRUI(RequestController requestController) {
        this.requestController = requestController;

        setTitle("CSR Panel");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton searchBtn = new JButton("Search Requests");
        JButton shortlistBtn = new JButton("Save to Shortlist");
        JButton viewShortlistBtn = new JButton("View Shortlist");
        JButton viewMatchesBtn = new JButton("View Completed Matches");
        JButton exitBtn = new JButton("Exit");

        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));
        panel.add(searchBtn);
        panel.add(shortlistBtn);
        panel.add(viewShortlistBtn);
        panel.add(viewMatchesBtn);
        panel.add(exitBtn);

        add(panel);
        setVisible(true);

        // Button actions
        searchBtn.addActionListener(e -> searchRequests());
        shortlistBtn.addActionListener(e -> saveToShortlist());
        viewShortlistBtn.addActionListener(e -> viewShortlist());
        viewMatchesBtn.addActionListener(e -> viewCompletedMatches());
        exitBtn.addActionListener(e -> dispose());
    }

    private void searchRequests() {
        String query = JOptionPane.showInputDialog(this, "Enter search query:");
        if (query != null && !query.isBlank()) {
            List<Request> results = requestController.searchRequests(query);
            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No requests found.");
            } else {
                StringBuilder sb = new StringBuilder("Search Results:\n");
                results.forEach(r -> sb.append(r.summary()).append("\n"));
                JOptionPane.showMessageDialog(this, sb.toString());
            }
        }
    }

    private void saveToShortlist() {
        String input = JOptionPane.showInputDialog(this, "Enter request ID to shortlist:");
        if (input != null && !input.isBlank()) {
            try {
                int id = Integer.parseInt(input);
                requestController.saveToShortlist(id);
                JOptionPane.showMessageDialog(this, "Request " + id + " saved to shortlist.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.");
            }
        }
    }

    private void viewShortlist() {
        List<Request> shortlist = requestController.viewShortlist();
        if (shortlist.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Shortlist is empty.");
        } else {
            StringBuilder sb = new StringBuilder("Shortlist:\n");
            shortlist.forEach(r -> sb.append(r.summary()).append("\n"));
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }

    private void viewCompletedMatches() {
        List<Match> matches = requestController.viewCompletedMatches();
        if (matches.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No completed matches.");
        } else {
            StringBuilder sb = new StringBuilder("Completed Matches:\n");
            matches.forEach(m -> sb.append(m.summary()).append("\n"));
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }
}
