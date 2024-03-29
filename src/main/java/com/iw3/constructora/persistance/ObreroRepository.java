package com.iw3.constructora.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.constructora.model.Obrero;

@Repository
public interface ObreroRepository extends JpaRepository<Obrero,Integer> {
	
	Optional<List<Obrero>> findAllByAñosExperienciaBetween(int añosMinimos, int añosMaximos);
	
}
	