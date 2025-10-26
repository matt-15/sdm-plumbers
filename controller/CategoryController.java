package project.controller;

import project.entity.Category;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryController {
    private Map<Integer, Category> store = new HashMap<>();
    private AtomicInteger seq = new AtomicInteger(0);

    public Category createCategory(String name, String description) {
        int id = seq.incrementAndGet();
        Category c = new Category(id, name, description);
        store.put(id, c);
        return c;
    }

    public Category viewCategory(int id) {
        return store.get(id);
    }

    public Category updateCategory(int id, String newName, String newDescription) {
        Category c = store.get(id);
        if (c != null) {
            c.setName(newName);
            c.setDescription(newDescription);
        }
        return c;
    }

    public void deleteCategory(int id) {
        store.remove(id);
    }

    public List<Category> searchCategories(String query) {
        String q = (query == null) ? "" : query.toLowerCase();
        List<Category> results = new ArrayList<>();
        for (Category c : store.values()) {
            if ((c.getName() != null && c.getName().toLowerCase().contains(q)) ||
                (c.getDescription() != null && c.getDescription().toLowerCase().contains(q))) {
                results.add(c);
            }
        }
        return results;
    }
}
