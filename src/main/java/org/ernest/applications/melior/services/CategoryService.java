package org.ernest.applications.melior.services;


import org.ernest.applications.melior.entities.Category;

import java.util.List;

public interface CategoryService {
    void createCategory(String name, List<String> consumablesIds);

    List<Category> getCategories();

    void deleteConsumable(String id);

    void updateCategory(Category category);
}
