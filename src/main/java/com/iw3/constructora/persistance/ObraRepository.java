package com.iw3.constructora.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.iw3.constructora.model.Obra;

@Repository
public interface ObraRepository extends JpaRepository<Obra,Integer> {
	
	/*Optional<List<Obra>> findAllByTipoObraOrderByNombre();*/
	Optional<List<Obra>> findAllByArquitectoNombreLikeIgnoreCase(String nombre);
	
}
	