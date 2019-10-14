package com.iw3.constructora.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.constructora.model.Obra;
import com.iw3.constructora.persistance.ObraRepository;

@Service
public class ObraBusiness implements IObraBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private Obra obraAux = null;
	private List<Obra> listObraAux;
	
	@Autowired
	private ObraRepository obraDAO;

	@Override
	public List<Obra> list() throws BusinessException {
		try {
			listObraAux= obraDAO.findAll();
			
			return listObraAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public Obra save(Obra obra) throws BusinessException {				
		boolean isNew = obra.getId() == null;
		
		try {
			obraAux = obraDAO.save(obra);
			
			if(!isNew) 
				log.info("UPDATE-COMIDA, objeto: "+ obraAux.toString());
			
			else
				log.info("INSERT-COMIDA, objeto:" + obraAux.toString());
				
			
			return obraAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public Obra load(Integer idObra) throws BusinessException, NotFoundException {
		Optional<Obra> op = null;
		try {
			op = obraDAO.findById(idObra);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la obra con id= " + idObra);
		}
		return op.get();
	}

	@Override
	public void remove(Integer idObra) throws BusinessException, NotFoundException {
		Optional<Obra> op = null;
		try {
			op = obraDAO.findById(idObra);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}

		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la Obra con id= " + idObra);
		}
		
		try {
			log.info("REMOVE-COMIDA, id: "+idObra);
			obraDAO.deleteById(idObra);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
	}
	
	/*@Override
	public List<Obra> orderByPriceAndRestaurante(String orden, String restaurante)
			throws BusinessException, NotFoundException {
		Optional<List<Obra>> op = null;
		Optional<Obra> opObra = null;
		List<Obra> list = new ArrayList<>();
		boolean valid = true;
		
		try {
			switch(orden) {				
				case "menor":
					
					if(restaurante.equals("ALL")) { 
						op= obraDAO.findAllByOrderByPrecioAsc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay obras cargadas");
						
						list = op.get();
					}
					else {
						opObra= obraDAO.findFirstByRestauranteNombreOrderByPrecioAsc(restaurante);
						if (!opObra.isPresent()) 
							throw new NotFoundException("No hay obras para el restaurante " + restaurante);
						
						list.add(opObra.get());
					}
					break;
				
				
				case "mayor":
					
					if(restaurante.equals("ALL")) {
						op= obraDAO.findAllByOrderByPrecioDesc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay obras cargadas");
						
						list = op.get();
					}
					else {
						opObra= obraDAO.findFirstByRestauranteNombreOrderByPrecioDesc(restaurante);
						if (!opObra.isPresent()) 
							throw new NotFoundException("No hay obras para el restaurante " + restaurante);
						list.add(opObra.get());
					}
					break;
	
				default:
					valid = false;
			}			
		}
		catch (Exception e) {
			if(e instanceof NotFoundException)
				throw e;
			
			log.error(e.getMessage(), e);
			throw new BusinessException(e);			
		}
		
		if(!valid) {
			log.error("BAD-REQUEST, orden:"+orden);
			throw new BusinessException("Bad request");
		}
		
		if (list.isEmpty()) 
			throw new NotFoundException("No se encuentra la obra con orden = "+orden+" y restaurante = "+restaurante );			
		
		
		return list;
		
	}

	@Override
	public List<Obra> findObrasByRestaurante(String nombre) throws BusinessException, NotFoundException{
		Optional<List<Obra>> op = null;
		try {
			op = obraDAO.findAllByRestauranteNombreOrderByNombreDesc(nombre);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
		if(!op.isPresent()) 
			throw new NotFoundException("No se encontro lista de obras para el restaurante = "+nombre);
		
		return op.get(); 
	}*/

}
