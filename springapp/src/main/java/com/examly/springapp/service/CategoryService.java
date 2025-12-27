package com.examly.springapp.service;

import java.util.*;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Category;

@Service
public class CategoryService {

    private List<Category> categories = new ArrayList<>();
    private Long idCounter = 1L;

    public Category addCategory(Category category) {
        category.setCategoryId(idCounter++);
        categories.add(category);
        return category;
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public Category getCategoryById(Long id) {
        return categories.stream()
                .filter(c -> c.getCategoryId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
