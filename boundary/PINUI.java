package project.boundary;

import project.controller.RequestController;
import project.entity.Request;
import project.entity.Match;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PINUI extends JFrame {
    private RequestController requestController;
    private String username;
    private static final long serialVersionUID = 1L;


    public PINUI(RequestController requestController, String username) {
        this.requestController = requestController;
        this.username = username;

        setTitle("PIN Panel - " + username);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton createBtn = new JButton("Create Request");
        JButton viewBtn = new JButton("View Request");
        JButton updateBtn = new JButton("Update Request");
        JButton deleteBtn = new JButton("Delete Request");
        JButton statsBtn = new JButton("View Request Stats");
        JButton historyBtn = new JButton("View Completed Matches History");
        JButton exitBtn = new JButton("Exit");

        JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
        panel.add(createBtn);
        panel.add(viewBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(statsBtn);
        panel.add(historyBtn);
        panel.add(exitBtn);

        add(panel);
        setVisible(true);

        createBtn.addActionListener(e -> createRequest());
        viewBtn.addActionListener(e -> viewRequest());
        updateBtn.addActionListener(e -> updateRequest());
        deleteBtn.addActionListener(e -> deleteRequest());
        statsBtn.addActionListener(e -> viewStats());
        historyBtn.addActionListener(e -> viewHistory());
        exitBtn.addActionListener(e -> dispose());
    }

    private void createRequest() {
        String desc = JOptionPane.showInputDialog(this, "Enter request description:");
        if (desc != null && !desc.isBlank()) {
            Request r = requestController.createRequest(username, desc);
            JOptionPane.showMessageDialog(this, "Created: " + r.summary());
        }
    }

    private void viewRequest() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter request ID:"));
            Request r = requestController.viewRequest(id);
            JOptionPane.showMessageDialog(this, r != null ? r.summary() : "Request not found.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    private void updateRequest() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter request ID:"));
            String newDesc = JOptionPane.showInputDialog(this, "New description:");
            Request r = requestController.updateRequest(id, newDesc);
            JOptionPane.showMessageDialog(this, r != null ? "Updated: " + r.summary() : "Request not found.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void deleteRequest() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter request ID:"));
            requestController.deleteRequest(id);
            JOptionPane.showMessageDialog(this, "Request deleted: " + id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    private void viewStats() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter request ID:"));
            String stats = requestController.viewRequestStats(id);
            JOptionPane.showMessageDialog(this, stats);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    private void viewHistory() {
        List<Match> matches = requestController.viewCompletedMatchesHistory(username);
        if (matches.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No completed matches.");
        } else {
            StringBuilder sb = new StringBuilder("Completed Matches:\n");
            matches.forEach(m -> sb.append(m.summary()).append("\n"));
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }
}
