package com.java.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "sparepart")
public class Sparepart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sparepart_id")
	private Long sparepartId;
	
	@Column(name = "sparepart_category")
	private String sparepartCategory;
	
	@Column(name = "sparepart_name")
	private String sparepartName;
	@Column(name = "sparepart_price")
	private int sparepartPrice;
	
	@Column(name = "product_id")
	private Long productId;
	
}
