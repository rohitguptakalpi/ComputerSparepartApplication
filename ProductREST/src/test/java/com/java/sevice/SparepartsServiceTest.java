package com.java.sevice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.java.Application;
import com.java.entity.Sparepart;
import com.java.exceptions.SparepartNotFoundException;
import com.java.repositories.SparepartRepository;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = Application.class)
class SparepartsServiceTest {
	
	@InjectMocks
	private SparepartsService service;
	@Mock
	SparepartRepository sRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Rollback(value = false)
	void testListAll() {

		Sparepart sparepart =new Sparepart();
		
		sparepart.setSparepartCategory("Laptop");
		sparepart.setSparepartName("SSD");
		sparepart.setSparepartPrice(500);
		sparepart.setProductId(1l);
		
		Sparepart sparepart1 =new Sparepart();
		
		sparepart1.setSparepartCategory("Laptop");
		sparepart1.setSparepartName("SSD");
		sparepart1.setSparepartPrice(500);
		sparepart1.setProductId(1l);
		
		
		List<Sparepart> spareparts =new ArrayList<Sparepart>();
		spareparts.add(sparepart);
		spareparts.add(sparepart1);
		when(service.listAll()).thenReturn(spareparts);

		List<Sparepart> resultSparepart = service.listAll();
		
		assertEquals(resultSparepart, spareparts);
		assertEquals(2, resultSparepart.size());
		

	
		}

	@Test
	void testSave() {
	Sparepart sparepart =new Sparepart();
	
	sparepart.setSparepartCategory("Laptop");
	sparepart.setSparepartName("SSD");
	sparepart.setSparepartPrice(500);
	sparepart.setProductId(1l);
	
	when(sRepository.save(Mockito.any())).thenReturn(sparepart);
	service.save(sparepart);
	System.out.println("Test save successfully");
}

	@Test
	void testGet() throws SparepartNotFoundException {
		Sparepart sparepart =new Sparepart();
		
		sparepart.setSparepartCategory("Laptop");
		sparepart.setSparepartName("SSD");
		sparepart.setSparepartPrice(500);
		sparepart.setProductId(1l);
		
		when(sRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(sparepart));
		assertEquals(service.get(1l),sparepart);
		
	}
	
	 

	
}
