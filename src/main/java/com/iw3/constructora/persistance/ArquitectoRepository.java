package com.iw3.constructora.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.constructora.model.Arquitecto;

@Repository
public interface ArquitectoRepository extends JpaRepository<Arquitecto,Integer> {
	
	/*Optional<List<Arquitecto>> findFirstByPuntuacion();*/
	
}
	