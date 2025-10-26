package project.boundary;

import project.controller.CategoryController;
import project.controller.ReportController;
import project.entity.Category;
import project.entity.Report;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlatformUI extends JFrame {
    private CategoryController categoryController;
    private ReportController reportController;
    private static final long serialVersionUID = 1L;


    public PlatformUI(CategoryController categoryController, ReportController reportController) {
        this.categoryController = categoryController;
        this.reportController = reportController;

        setTitle("Platform Management Panel");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton createBtn = new JButton("Create Category");
        JButton viewBtn = new JButton("View Category");
        JButton updateBtn = new JButton("Update Category");
        JButton deleteBtn = new JButton("Delete Category");
        JButton searchBtn = new JButton("Search Categories");
        JButton dailyBtn = new JButton("Generate Daily Report");
        JButton weeklyBtn = new JButton("Generate Weekly Report");
        JButton monthlyBtn = new JButton("Generate Monthly Report");
        JButton exitBtn = new JButton("Exit");

        JPanel panel = new JPanel(new GridLayout(9, 1, 5, 5));
        panel.add(createBtn);
        panel.add(viewBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(searchBtn);
        panel.add(dailyBtn);
        panel.add(weeklyBtn);
        panel.add(monthlyBtn);
        panel.add(exitBtn);

        add(panel);
        setVisible(true);

        // Button actions
        createBtn.addActionListener(e -> createCategory());
        viewBtn.addActionListener(e -> viewCategory());
        updateBtn.addActionListener(e -> updateCategory());
        deleteBtn.addActionListener(e -> deleteCategory());
        searchBtn.addActionListener(e -> searchCategories());

        dailyBtn.addActionListener(e -> {
            Report r = reportController.generateDailyReport();
            JOptionPane.showMessageDialog(this, r.summary());
        });

        weeklyBtn.addActionListener(e -> {
            Report r = reportController.generateWeeklyReport();
            JOptionPane.showMessageDialog(this, r.summary());
        });

        monthlyBtn.addActionListener(e -> {
            Report r = reportController.generateMonthlyReport();
            JOptionPane.showMessageDialog(this, r.summary());
        });

        exitBtn.addActionListener(e -> dispose());


    }

    private void createCategory() {
        String name = JOptionPane.showInputDialog(this, "Category name:");
        String desc = JOptionPane.showInputDialog(this, "Description:");
        if (name != null && desc != null) {
            Category c = categoryController.createCategory(name, desc);
            JOptionPane.showMessageDialog(this, "Category created: " + c.summary());
        }
    }

    private void viewCategory() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter category ID:"));
            Category c = categoryController.viewCategory(id);
            JOptionPane.showMessageDialog(this, c != null ? c.summary() : "Category not found.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    private void updateCategory() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter category ID:"));
            String newName = JOptionPane.showInputDialog(this, "New name:");
            String newDesc = JOptionPane.showInputDialog(this, "New description:");
            Category c = categoryController.updateCategory(id, newName, newDesc);
            JOptionPane.showMessageDialog(this, c != null ? "Updated: " + c.summary() : "Category not found.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void deleteCategory() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter category ID:"));
            categoryController.deleteCategory(id);
            JOptionPane.showMessageDialog(this, "Category deleted: " + id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid ID.");
        }
    }

    private void searchCategories() {
        String query = JOptionPane.showInputDialog(this, "Search query:");
        List<Category> results = categoryController.searchCategories(query);
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No categories found.");
        } else {
            StringBuilder sb = new StringBuilder("Search Results:\n");
            results.forEach(c -> sb.append(c.summary()).append("\n"));
            JOptionPane.showMessageDialog(this, sb.toString());
        }
    }
}
