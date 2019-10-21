package com.iw3.constructora.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.constructora.model.TipoObra;
import com.iw3.constructora.persistance.TipoObraRepository;

@Service
public class TipoObraBusiness implements ITipoObraBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private TipoObra tipoObraAux = null;
	private List<TipoObra> listTipoObraAux;
	
	@Autowired
	private TipoObraRepository tipoObraDAO;

	@Override
	public List<TipoObra> list() throws BusinessException {
		try {
			listTipoObraAux= tipoObraDAO.findAll();
			
			return listTipoObraAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public TipoObra save(TipoObra tipoObra) throws BusinessException {				
		boolean isNew = tipoObra.getId() == null;
		
		try {
			tipoObraAux = tipoObraDAO.save(tipoObra);
			
			if(!isNew) 
				log.info("UPDATE-TIPOOBRA, objeto: "+ tipoObraAux.toString());
			
			else
				log.info("INSERT-TIPOOBRA, objeto:" + tipoObraAux.toString());
				
			
			return tipoObraAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public TipoObra load(Integer idTipoObra) throws BusinessException, NotFoundException {
		Optional<TipoObra> op = null;
		try {
			op = tipoObraDAO.findById(idTipoObra);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la tipoObra con id= " + idTipoObra);
		}
		return op.get();
	}

	@Override
	public void remove(Integer idTipoObra) throws BusinessException, NotFoundException {
		Optional<TipoObra> op = null;
		try {
			op = tipoObraDAO.findById(idTipoObra);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}

		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la TipoObra con id= " + idTipoObra);
		}
		
		try {
			log.info("REMOVE-TIPOOBRA, id: "+idTipoObra);
			tipoObraDAO.deleteById(idTipoObra);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
	}
	
	/*@Override
	public List<TipoObra> orderByPriceAndRestaurante(String orden, String restaurante)
			throws BusinessException, NotFoundException {
		Optional<List<TipoObra>> op = null;
		Optional<TipoObra> opTipoObra = null;
		List<TipoObra> list = new ArrayList<>();
		boolean valid = true;
		
		try {
			switch(orden) {				
				case "menor":
					
					if(restaurante.equals("ALL")) { 
						op= tipoObraDAO.findAllByOrderByPrecioAsc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay tipoObras cargadas");
						
						list = op.get();
					}
					else {
						opTipoObra= tipoObraDAO.findFirstByRestauranteNombreOrderByPrecioAsc(restaurante);
						if (!opTipoObra.isPresent()) 
							throw new NotFoundException("No hay tipoObras para el restaurante " + restaurante);
						
						list.add(opTipoObra.get());
					}
					break;
				
				
				case "mayor":
					
					if(restaurante.equals("ALL")) {
						op= tipoObraDAO.findAllByOrderByPrecioDesc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay tipoObras cargadas");
						
						list = op.get();
					}
					else {
						opTipoObra= tipoObraDAO.findFirstByRestauranteNombreOrderByPrecioDesc(restaurante);
						if (!opTipoObra.isPresent()) 
							throw new NotFoundException("No hay tipoObras para el restaurante " + restaurante);
						list.add(opTipoObra.get());
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
			throw new NotFoundException("No se encuentra la tipoObra con orden = "+orden+" y restaurante = "+restaurante );			
		
		
		return list;
		
	}

	@Override
	public List<TipoObra> findTipoObrasByRestaurante(String nombre) throws BusinessException, NotFoundException{
		Optional<List<TipoObra>> op = null;
		try {
			op = tipoObraDAO.findAllByRestauranteNombreOrderByNombreDesc(nombre);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
		if(!op.isPresent()) 
			throw new NotFoundException("No se encontro lista de tipoObras para el restaurante = "+nombre);
		
		return op.get(); 
	}*/

}
