package com.java.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entity.Sparepart;
import com.java.exceptions.SparepartNotFoundException;
import com.java.repositories.SparepartRepository;

@Service
public class SparepartsService {
	
	@Autowired
	private SparepartRepository repo;
	
	public List<Sparepart> listAll(){
		return repo.findAll();
	}
	public void save(Sparepart sparepart) {
		repo.save(sparepart);
	}
	
	public Sparepart get(Long id) throws com.java.exceptions.SparepartNotFoundException {
		Sparepart sparepart= repo.findBySparepartId(id);
		
		if (sparepart !=null) {
			return sparepart;
			
		}
		else {
			throw new SparepartNotFoundException("sparepart not found "+ id);
		}
	}
	public void delete(Long Id) {
		repo.deleteById(Id);
	}
	
	public List<Sparepart> getByProductid(Long id){
		return repo.findAllByProductId(id);
	}
	public void update(Sparepart newSparepart) {
		
		Sparepart sparepart = repo.findById(newSparepart.getSparepartId()).get();
		
		sparepart.setSparepartCategory(newSparepart.getSparepartCategory());
		sparepart.setSparepartName(newSparepart.getSparepartName());
		sparepart.setSparepartPrice(newSparepart.getSparepartPrice());
		sparepart.setProductId(newSparepart.getProductId());
		
		repo.save(sparepart);
		
	}
	
	
	

}
