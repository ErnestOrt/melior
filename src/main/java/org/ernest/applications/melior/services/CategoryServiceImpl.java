package org.ernest.applications.melior.services;


import org.ernest.applications.melior.dao.CategoryDao;
import org.ernest.applications.melior.entities.Category;
import org.ernest.applications.melior.entities.Consumable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryDao categoryServiceDao;

    @Override
    public void createCategory(String name, List<String> consumablesIds) {
        categoryServiceDao.createCategory(name, consumablesIds);
    }

    @Override
    public List<Category> getCategories() {
        return categoryServiceDao.getCategories().stream()
                                                 .sorted(Comparator.comparing(Category::getName))
                                                 .collect(Collectors.toList());
    }

    @Override
    public void deleteConsumable(String id) {
        categoryServiceDao.deleteConsumable(id);
    }

    @Override
    public void updateCategory(Category category) {
        categoryServiceDao.updateCategory(category);
    }
}
