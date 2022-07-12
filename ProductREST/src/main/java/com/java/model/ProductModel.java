package com.java.model;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
//	private Long productId;
	
	@NotBlank(message = "Company name cannot be blank or null")
	@Pattern(regexp = "[A-Z a-z]{2,30}",message = "Invalid Company name")
	private String companyName;
	
//	@Pattern(regexp = "^[1-9]+[0-9]*$",message = "Invalid Product Price")
//	@Pattern(regexp = "[\\s]*[0-9]*[1-9]+",message="Invalid Product Price")
	@Min(value=1, message = "Price of Product can not be less than 1 or null" )
	@Digits(fraction = 0, integer = 8, message ="Product price can not be too big")
	private int productPrice;
	
	@NotBlank(message = "Product name cannot be blank or null")
	@Pattern(regexp = "[A-Z a-z 0-9]{2,30}",message = "Invalid Product name")
	private String productName;
	
//	private List<SparepartModel> spareparts;
}
