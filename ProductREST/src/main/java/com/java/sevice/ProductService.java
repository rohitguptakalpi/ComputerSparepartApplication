package com.java.sevice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entity.Product;
import com.java.entity.Sparepart;
import com.java.exceptions.ProductNotFoundException;
import com.java.repositories.ProductRepository;
import com.java.repositories.SparepartRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;

	@Autowired
	private SparepartsService sparepartsService;
	
	@Autowired
	private SparepartRepository sparepartRepo;

	public List<Product> listAll() {
		return repo.findAll();
	}

	public void save(Product product) {
		repo.save(product);
	}

	public Product get(long id) throws ProductNotFoundException {
		Product product= repo.findByProductId(id);
		if ( product != null ) {
			return product;
		}else {
			throw new ProductNotFoundException("Product not found "+ id);
		}
	}

	public void delete(Long id) throws ProductNotFoundException {	
		Product product = repo.findByProductId(id);
		
		
		if ( product != null ) {			
			List<Sparepart> spareparts = product.getSpareparts();
			System.out.println(spareparts);
			
			for(Sparepart s:spareparts) {
				System.out.println(s);
				sparepartRepo.deleteById(s.getSparepartId());
			}
			
			Product product1 = repo.findByProductId(id);
			
			
			System.out.println(product1);
			
			repo.deleteById(product1.getProductId());
			
		}else {
			throw new ProductNotFoundException("Product not found "+ id);
		}
	}

	public void update(Long id, Product newProduct) {
		Product product = repo.findByProductId(id);

		product.setCompanyName(newProduct.getCompanyName());
		product.setProductName(newProduct.getProductName());
		product.setProductPrice(newProduct.getProductPrice());
		repo.save(product);

		
		

	}

	
}
