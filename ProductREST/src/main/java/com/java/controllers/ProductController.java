package com.java.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.entity.Product;
import com.java.entity.Sparepart;
import com.java.exceptions.ProductNotFoundException;
import com.java.model.ProductModel;
import com.java.model.SparepartModel;
import com.java.sevice.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	@GetMapping("/showAll")
	public ResponseEntity<List<Product>> Listall() {

		logger.info("Display All product");
		return ResponseEntity.ok(productService.listAll());

	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> get(@PathVariable Long id) throws ProductNotFoundException {

		Product product = productService.get(id);
		logger.info("Display product " + id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@PostMapping("/send")
	public ResponseEntity<String> add(@RequestBody @Valid ProductModel productModel) {

		Product product = new Product();
		product.setCompanyName(productModel.getCompanyName());
		product.setProductName(productModel.getProductName());
		product.setProductPrice(productModel.getProductPrice());

		logger.info("Added:: " + product);
		productService.save(product);
		return new ResponseEntity<String>("Product Inserted Successfully", HttpStatus.CREATED);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody @Valid ProductModel productModel, @PathVariable Long id)
			throws ProductNotFoundException {

		Product product = productService.get(id); 
		
//		product.setProductId(productModel.getProductId());
		product.setCompanyName(productModel.getCompanyName());
		product.setProductName(productModel.getProductName());
		product.setProductPrice(productModel.getProductPrice());

		productService.update(id, product);
		logger.info("Product with product id " + id + " updated Successfully");
		return new ResponseEntity<String>("Product with product id " + id + " updated Successfully",HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws ProductNotFoundException {
		Product product = productService.get(id);
		if (product.equals(null)) {
			logger.debug("Product" + id + " is not Available");
			return new ResponseEntity<String>("Product" + id + " is not Available", HttpStatus.NOT_FOUND);

		} else {
			logger.info("Deleted  product" + id);
			productService.delete(id);
			return new ResponseEntity<String>("Product Deleted Successfully", HttpStatus.OK);
		}

	}

}
