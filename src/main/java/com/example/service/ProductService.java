package com.example.service;

import java.util.List;

import com.example.entity.Product;

public interface ProductService {

	List<Product> getProducts();

	void save(Product product);

	Product getProduct(long productId);

}
