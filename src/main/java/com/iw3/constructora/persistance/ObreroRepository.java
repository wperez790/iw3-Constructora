package com.iw3.constructora.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.constructora.model.Obrero;

@Repository
public interface ObreroRepository extends JpaRepository<Obrero,Integer> {
	
	/*Optional<Comida> findFirstByRestauranteNombreOrderByPrecioAsc(String nombre);
	Optional<Comida> findFirstByRestauranteNombreOrderByPrecioDesc(String nombre);
	Optional<List<Comida>> findAllByOrderByPrecioAsc();
	Optional<List<Comida>> findAllByOrderByPrecioDesc();
	Optional<List<Comida>> findAllByRestauranteNombreOrderByNombreDesc(String nombre);*/
}
	