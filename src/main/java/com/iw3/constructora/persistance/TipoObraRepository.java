package com.iw3.constructora.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iw3.constructora.model.TipoObra;

@Repository
public interface TipoObraRepository extends JpaRepository<TipoObra,Integer> {
	
	
}
	