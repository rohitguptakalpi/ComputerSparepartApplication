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
import com.java.entity.Sparepart;
import com.java.sevice.SparepartsService;

@ExtendWith(MockitoExtension.class)
class SparepartControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private SparepartController controller;
	@Mock
	private SparepartsService service;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void testSaveSuccess() throws JsonProcessingException, Exception {

		Sparepart sparepart = new Sparepart();

		sparepart.setProductId(1l);
		sparepart.setSparepartCategory("Laptop");
		sparepart.setSparepartId(1l);
		sparepart.setSparepartName("RAM");
		sparepart.setSparepartPrice(5000);

		Mockito.doNothing().when(service).save(ArgumentMatchers.any());

		mockMvc.perform(post("/spareparts/send").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(toJson(sparepart)).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isCreated());

	}

	@Test
	public void testFindSparepartSuccess() throws JsonProcessingException, Exception {

		Sparepart sparepart = new Sparepart();

		sparepart.setProductId(1l);
		sparepart.setSparepartCategory("Laptop");
		sparepart.setSparepartId(1l);
		sparepart.setSparepartName("RAM");
		sparepart.setSparepartPrice(5000);

		Mockito.when(service.get(ArgumentMatchers.anyLong())).thenReturn(sparepart);

		mockMvc.perform(get("/spareparts/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.sparepartCategory", is("Laptop")))
				.andExpect(jsonPath("$.sparepartName", is("RAM")));
	}

	@Test
	public void testNoSparepartFound() throws JsonProcessingException, Exception {
		Mockito.when(service.get(ArgumentMatchers.anyLong())).thenReturn(null);

		mockMvc.perform(get("/spareparts/1").accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
	}

	private String toJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

	@Test
	public void testFetchAllProductSuccess() throws Exception {
		Sparepart sparepart = new Sparepart();

		sparepart.setProductId(1l);
		sparepart.setSparepartCategory("Laptop");
		sparepart.setSparepartId(1l);
		sparepart.setSparepartName("RAM");
		sparepart.setSparepartPrice(5000);

		Sparepart sparepart2 = new Sparepart();

		sparepart2.setProductId(1l);
		sparepart2.setSparepartCategory("Laptop");
		sparepart2.setSparepartId(1l);
		sparepart2.setSparepartName("RAM");
		sparepart2.setSparepartPrice(5000);

		Mockito.when(service.listAll()).thenReturn(Arrays.asList(sparepart, sparepart2));

		mockMvc.perform(get("/spareparts/showAll")).andExpect(status().isOk());
	}

}
