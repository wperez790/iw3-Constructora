package com.iw3.constructora.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.constructora.model.Obrero;
import com.iw3.constructora.persistance.ObreroRepository;

@Service
public class ObreroBusiness implements IObreroBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private Obrero obreroAux = null;
	private List<Obrero> listObreroAux;
	
	@Autowired
	private ObreroRepository obreroDAO;

	@Override
	public List<Obrero> list() throws BusinessException {
		try {
			listObreroAux= obreroDAO.findAll();
			
			return listObreroAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public Obrero save(Obrero obrero) throws BusinessException {				
		boolean isNew = obrero.getId() == null;
		
		try {
			obreroAux = obreroDAO.save(obrero);
			
			if(!isNew) 
				log.info("UPDATE-OBRERO, objeto: "+ obreroAux.toString());
			
			else
				log.info("INSERT-OBRERO, objeto:" + obreroAux.toString());
				
			
			return obreroAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public Obrero load(Integer idObrero) throws BusinessException, NotFoundException {
		Optional<Obrero> op = null;
		try {
			op = obreroDAO.findById(idObrero);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la obrero con id= " + idObrero);
		}
		return op.get();
	}

	@Override
	public void remove(Integer idObrero) throws BusinessException, NotFoundException {
		Optional<Obrero> op = null;
		try {
			op = obreroDAO.findById(idObrero);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}

		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la Obrero con id= " + idObrero);
		}
		
		try {
			log.info("REMOVE-OBRERO, id: "+idObrero);
			obreroDAO.deleteById(idObrero);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
	}
	
	/*@Override
	public List<Obrero> orderByPriceAndRestaurante(String orden, String restaurante)
			throws BusinessException, NotFoundException {
		Optional<List<Obrero>> op = null;
		Optional<Obrero> opObrero = null;
		List<Obrero> list = new ArrayList<>();
		boolean valid = true;
		
		try {
			switch(orden) {				
				case "menor":
					
					if(restaurante.equals("ALL")) { 
						op= obreroDAO.findAllByOrderByPrecioAsc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay obreros cargadas");
						
						list = op.get();
					}
					else {
						opObrero= obreroDAO.findFirstByRestauranteNombreOrderByPrecioAsc(restaurante);
						if (!opObrero.isPresent()) 
							throw new NotFoundException("No hay obreros para el restaurante " + restaurante);
						
						list.add(opObrero.get());
					}
					break;
				
				
				case "mayor":
					
					if(restaurante.equals("ALL")) {
						op= obreroDAO.findAllByOrderByPrecioDesc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay obreros cargadas");
						
						list = op.get();
					}
					else {
						opObrero= obreroDAO.findFirstByRestauranteNombreOrderByPrecioDesc(restaurante);
						if (!opObrero.isPresent()) 
							throw new NotFoundException("No hay obreros para el restaurante " + restaurante);
						list.add(opObrero.get());
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
			throw new NotFoundException("No se encuentra la obrero con orden = "+orden+" y restaurante = "+restaurante );			
		
		
		return list;
		
	}

	@Override
	public List<Obrero> findObrerosByRestaurante(String nombre) throws BusinessException, NotFoundException{
		Optional<List<Obrero>> op = null;
		try {
			op = obreroDAO.findAllByRestauranteNombreOrderByNombreDesc(nombre);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
		if(!op.isPresent()) 
			throw new NotFoundException("No se encontro lista de obreros para el restaurante = "+nombre);
		
		return op.get(); 
	}*/

}
