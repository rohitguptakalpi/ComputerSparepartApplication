package com.java.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparepartModel {

	@NotBlank(message = "sparepartCategory  cannot be blank or null")
	@Pattern(regexp = "[A-Z a-z]{2,30}",message = "Invalid sparepartCategory")
	private String sparepartCategory;
	
	@NotBlank(message = "sparepartName cannot be blank or null")
	@Pattern(regexp = "[A-Z a-z]{2,30}",message = "Invalid sparepartName")
	private String sparepartName;
	
	@Min(value=1, message = "sparepartPrice can not be less than 1 or null" )
	@Digits(fraction = 0, integer = 9, message ="Invalid sparepartPrice")
	private int sparepartPrice;
	
	@Min(value=1, message = "productId can not be less than 1 or null" )
	@Digits(fraction = 0, integer = 9, message ="Invalid productId")
	private Long productId;

}
