package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Product;
import com.example.service.ProductService;

@RestController
public class ProductController {
	Logger log = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<?> getProducts() {
		List<Product> products = null;
		try {
			products = productService.getProducts();
			log.info("Response of '/products'{}", products);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ResponseEntity<>(products, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		try {
			productService.save(product);
			log.info("Request {}", product);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable long productId) {
		Product product = null;
		try {
			product = productService.getProduct(productId);
			log.info("Response {}", product);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
}
