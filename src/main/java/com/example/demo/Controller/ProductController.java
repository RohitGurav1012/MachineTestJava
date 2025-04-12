package com.example.demo.Controller;  // Ensure the correct package
import com.example.demo.model.Product;  // Ensure the correct import for Category model
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

import com.example.demo.model.Category;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @PersistenceContext
    private EntityManager entityManager;

    // 1. GET all products with pagination
    @GetMapping
    public List<Product> getAllProducts(@RequestParam(defaultValue = "0") int page, 
                                         @RequestParam(defaultValue = "2") int size) {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                            .setFirstResult(page * size)
                            .setMaxResults(size)
                            .getResultList();
    }

    // 2. POST create a new product
//    @PostMapping
//    @Transactional
//    public Product createProduct(@RequestBody Product product) {
//        entityManager.persist(product);
//        return product;
//    }
//    
    @PostMapping
    @Transactional
    public Product createProduct(@RequestBody Product product) {
        // Fetch the full category from DB
        Long categoryId = product.getCategory().getId();
        Category category = entityManager.find(Category.class, categoryId);

        // Set the fully loaded category back to product
        product.setCategory(category);

        // Persist the product
        entityManager.persist(product);

        return product;
    }


    // 3. GET product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // 4. PUT update product by ID
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        // Update fields
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        Long categoryId = newProduct.getCategory().getId();
        Category category = entityManager.find(Category.class, categoryId);
        product.setCategory(category);

//        product.setCategory(newProduct.getCategory());
        return ResponseEntity.ok(product);
    }

    // 5. DELETE delete product by ID
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        entityManager.remove(product);
        return ResponseEntity.noContent().build();
    }
}
    
