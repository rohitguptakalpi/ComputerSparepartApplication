package com.java.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long productId;
	@Column(name = "company_name")
	private String companyName;
	@Column(name = "product_price")
	private int productPrice;
	
	@Column(name = "product_name")
	private String productName;
	
//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@OneToMany
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private List<Sparepart> spareparts;

	
}
