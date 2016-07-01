package org.ernest.applications.melior.dao;


import org.ernest.applications.melior.entities.Category;

import java.util.List;

public interface CategoryDao {
    void createCategory(String name, List<String> consumablesIds);

    List<Category> getCategories();

    void deleteConsumable(String id);

    void updateCategory(Category category);
}
