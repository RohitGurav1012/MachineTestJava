// category controller
package com.example.demo.Controller;  // Ensure the correct package
import com.example.demo.model.Category;  // Ensure the correct import for Category model
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @PersistenceContext
    private EntityManager entityManager;

    // 1. Get all categories with pagination
    @GetMapping
    public List<Category> getAllCategories(@RequestParam(defaultValue = "0") int page, 
                                            @RequestParam(defaultValue = "2") int size) {
        return entityManager.createQuery("SELECT c FROM Category c", Category.class)
                            .setFirstResult(page * size)
                            .setMaxResults(size)
                            .getResultList();
    }

    // 2. Get a category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = entityManager.find(Category.class, id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    // 3. Create a new category
    @PostMapping
    @Transactional
    public Category createCategory(@RequestBody Category category) {
        entityManager.persist(category);
        return category;
    }

    // 4. Update category by ID
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category newCategory) {
        Category category = entityManager.find(Category.class, id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        category.setName(newCategory.getName());
        return ResponseEntity.ok(category);
    }

    // 5. Delete category by ID
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Category category = entityManager.find(Category.class, id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        entityManager.remove(category);
        return ResponseEntity.noContent().build();
    }
}
