//product class

package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
@Entity
public class Product {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private double price;

	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    //@JsonBackReference  // Prevents infinite recursion by ignoring this field in the Product response
	    @JsonIgnoreProperties("products")  // Prevents recursion, but includes category

	    private Category category;

	    // Getters and Setters
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }

	    public Category getCategory() {
	        return category;
	    }

	    public void setCategory(Category category) {
	        this.category = category;
	    }
	}