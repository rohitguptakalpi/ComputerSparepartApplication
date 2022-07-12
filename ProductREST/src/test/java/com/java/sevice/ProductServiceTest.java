package com.java.sevice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
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
import com.java.entity.Product;
import com.java.exceptions.ProductNotFoundException;
import com.java.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = Application.class)
class ProductServiceTest {

	@InjectMocks
	private ProductService pService;
	@Mock
	ProductRepository pRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Rollback(value = false)
	public void testSaveProduct() {
		Product product = new Product();
		product.setCompanyName("asus");
		product.setProductName("5060");
		product.setProductPrice(52000);
		when(pRepository.save(Mockito.any())).thenReturn(product);
		pService.save(product);

	}
	@Test
    public void testFetchAllProduct() {
        Product entity = new Product();
        entity.setCompanyName("Hp");
        entity.setProductId(5l);
        entity.setProductPrice(10000);
        when(pRepository.findAll()).thenReturn(Arrays.asList(entity));
        List<Product> beans = pService.listAll();
        assertEquals(1, beans.size());
        assertEquals(5l, beans.get(0).getProductId());
    }
	@Test
	public void testFindCompanyName() throws ProductNotFoundException {
		Product entity = new Product();
		entity.setProductId(1l);
        entity.setCompanyName("Hp");
        entity.setProductPrice(100000);
        when(pRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(entity));
        Product bean = pService.get(1l);
        assertEquals(1, bean.getProductId());
        assertEquals("Hp", bean.getCompanyName());
	}
//	@Test
//    public void testProductNotAvailable() {
//        when(pRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
//        Product product = pService.get(1);
//        assertEquals(null, product);
//    }
//	

}
