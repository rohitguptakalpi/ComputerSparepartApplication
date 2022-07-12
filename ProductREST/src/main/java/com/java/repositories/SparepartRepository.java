package com.java.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.Sparepart;

public interface SparepartRepository extends JpaRepository<Sparepart, Long>{

	Sparepart findBySparepartId(Long id);

	List<Sparepart> findAllByProductId(Long id);

}
