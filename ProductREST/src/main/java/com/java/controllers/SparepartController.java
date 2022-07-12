package com.java.controllers;

import java.util.List;
import java.util.NoSuchElementException;

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
import com.java.exceptions.SparepartNotFoundException;
import com.java.model.SparepartModel;
import com.java.sevice.ProductService;
import com.java.sevice.SparepartsService;

@RestController
@RequestMapping("/spareparts")
public class SparepartController {

	private static final Logger logger = LoggerFactory.getLogger(SparepartController.class);

	@Autowired
	private SparepartsService sparepartService;

	@Autowired
	private ProductService productService;

	@GetMapping("/showAll")
	public List<Sparepart> listall() {
		logger.info("Display All Spareparts");
		return sparepartService.listAll();
	}

	@GetMapping("/sparepart/{id}")
	public ResponseEntity<Sparepart> get(@PathVariable Long id) throws SparepartNotFoundException {

		Sparepart sparepart = sparepartService.get(id);
		logger.info("Display Sparepart " + id);
		return new ResponseEntity<Sparepart>(sparepart, HttpStatus.OK);

	}

	@GetMapping("/productId/{id}")
	public List<Sparepart> listAllByProductId(Long id) {
		return sparepartService.getByProductid(id);
	}

	@PostMapping("/send")
	public ResponseEntity<String> add(@RequestBody @Valid SparepartModel sparepartModel)
			throws ProductNotFoundException {
		Sparepart sparepart = new Sparepart();

		
		Product p = productService.get(sparepartModel.getProductId());
		System.out.println(p);
		sparepart.setProductId(sparepartModel.getProductId());
		sparepart.setSparepartCategory(sparepartModel.getSparepartCategory());
//		sparepart.setSparepartId(sparepart.getSparepartId());
		sparepart.setSparepartName(sparepartModel.getSparepartName());
		sparepart.setSparepartPrice(sparepartModel.getSparepartPrice());

		logger.info("Added:: " + sparepart);
		sparepartService.save(sparepart);
		return new ResponseEntity<String>("Sparepart Inserted Successfully", HttpStatus.CREATED);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@RequestBody @Valid SparepartModel sparepartModel, @PathVariable Long id) throws SparepartNotFoundException, ProductNotFoundException {

		Sparepart sparepart = sparepartService.get(id);
		Product p = productService.get(sparepartModel.getProductId());
		
		sparepart.setProductId(sparepartModel.getProductId());
		sparepart.setSparepartCategory(sparepartModel.getSparepartCategory());
		sparepart.setSparepartName(sparepartModel.getSparepartName());
		sparepart.setSparepartPrice(sparepartModel.getSparepartPrice());

		sparepartService.update(sparepart);
		logger.info("Sparepart id " + id + " updated Successfully");

		return new ResponseEntity<>("Sparepart id " + id + " updated Successfully",HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) throws SparepartNotFoundException {
		Sparepart sparepart = sparepartService.get(id);

		if (sparepart.equals(null)) {
			logger.debug("Product" + id + " is not Available");
			return new ResponseEntity<String>("Sparepart " + id + " is not Available", HttpStatus.NOT_FOUND);
		} else {
			logger.info("Deleted  Sparepart" + id);
			sparepartService.delete(id);
			return new ResponseEntity<String>("Sparepart Deleted Successfully", HttpStatus.OK);
		}

	}

}
