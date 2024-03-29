package com.iw3.constructora.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.constructora.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material,Integer> {
	
	Optional<List<Material>> findAllByNombreLikeOrderByNombre(String nombre);

}
	