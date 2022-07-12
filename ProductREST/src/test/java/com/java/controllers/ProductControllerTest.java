 package com.java.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.entity.Product;
import com.java.sevice.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private ProductController controller;
	
	@Mock
	private ProductService service;
	
	@BeforeEach
	public void setup() {
		this.mockMvc =MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void testSaveSuccess() throws JsonProcessingException, Exception{
		Product product =new Product();
		product.setProductId(Long.valueOf(1));
		product.setCompanyName("hp");
		product.setProductName("g500");
		product.setProductPrice(5200);
		
		

		Mockito.doNothing().when(service).save(ArgumentMatchers.any());

		mockMvc.perform(post("/products/send").contentType(MediaType.APPLICATION_JSON_VALUE).content(toJson(product))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());
	
		
	}

	@Test
	public void testFindProductSuccess() throws JsonProcessingException, Exception {
		

		Product product =new Product();
		product.setProductId(Long.valueOf(1));
		product.setCompanyName("hp");
		product.setProductName("g500");
		product.setProductPrice(5200);
		
		Mockito.when(service.get(ArgumentMatchers.anyLong())).thenReturn(product);

		mockMvc.perform(get("/products/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.companyName", is("hp"))).andExpect(jsonPath("$.productId", is(1)));
	}
	
	@Test
	public void testNoProductFound() throws JsonProcessingException, Exception{
		Mockito.when(service.get(ArgumentMatchers.anyLong())).thenReturn(null);
		
		mockMvc.perform(get("/products/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}
	
	
	@Test
	public void testFetchAllProductSuccess() throws Exception {
		Product product =new Product();
		product.setProductId(Long.valueOf(1));
		product.setCompanyName("hp");
		product.setProductName("g500");
		product.setProductPrice(5200);
		
		Product product2 =new Product();
		product2.setProductId(Long.valueOf(1));
		product2.setCompanyName("hp");
		product2.setProductName("g500");
		product2.setProductPrice(5200);
		
	
		
		Mockito.when(service.listAll()).thenReturn(Arrays.asList(product,product2));

		mockMvc.perform(get("/products/showAll")).andExpect(status().isOk());
	}

	
	private String toJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
