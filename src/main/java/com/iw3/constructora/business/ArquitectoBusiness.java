package com.iw3.constructora.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.constructora.model.Arquitecto;
import com.iw3.constructora.persistance.ArquitectoRepository;

@Service
public class ArquitectoBusiness implements IArquitectoBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private Arquitecto arquitectoAux = null;
	private List<Arquitecto> listArquitectoAux;
	private final int minPuntaje = 6;
	
	@Autowired
	private ArquitectoRepository arquitectoDAO;

	@Override
	public List<Arquitecto> list() throws BusinessException {
		try {
			listArquitectoAux= arquitectoDAO.findAll();
			
			return listArquitectoAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public Arquitecto save(Arquitecto arquitecto) throws BusinessException {				
		boolean isNew = arquitecto.getId() == null;
		
		try {
			if(arquitecto.getPuntuacion() < minPuntaje) {
				throw new BusinessException("El puntaje necesario debe ser de "+minPuntaje+" o más y el puntaje del arquitecto es: "+arquitecto.getPuntuacion());
			}
			else {
				arquitectoAux = arquitectoDAO.save(arquitecto);
			
				if(!isNew) 
					log.info("UPDATE-ARQUITECTO, objeto: "+ arquitectoAux.toString());
			
				else
					log.info("INSERT-ARQUITECTO, objeto:" + arquitectoAux.toString());
				
			
			return arquitectoAux;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public Arquitecto load(Integer idArquitecto) throws BusinessException, NotFoundException {
		Optional<Arquitecto> op = null;
		try {
			op = arquitectoDAO.findById(idArquitecto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la arquitecto con id= " + idArquitecto);
		}
		return op.get();
	}

	@Override
	public void remove(Integer idArquitecto) throws BusinessException, NotFoundException {
		Optional<Arquitecto> op = null;
		try {
			op = arquitectoDAO.findById(idArquitecto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}

		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la Arquitecto con id= " + idArquitecto);
		}
		
		try {
			log.info("REMOVE-ARQUITECTO, id: "+idArquitecto);
			arquitectoDAO.deleteById(idArquitecto);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
	}
	
	/*@Override
	public List<Arquitecto> orderByPriceAndRestaurante(String orden, String restaurante)
			throws BusinessException, NotFoundException {
		Optional<List<Arquitecto>> op = null;
		Optional<Arquitecto> opArquitecto = null;
		List<Arquitecto> list = new ArrayList<>();
		boolean valid = true;
		
		try {
			switch(orden) {				
				case "menor":
					
					if(restaurante.equals("ALL")) { 
						op= arquitectoDAO.findAllByOrderByPrecioAsc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay arquitectos cargadas");
						
						list = op.get();
					}
					else {
						opArquitecto= arquitectoDAO.findFirstByRestauranteNombreOrderByPrecioAsc(restaurante);
						if (!opArquitecto.isPresent()) 
							throw new NotFoundException("No hay arquitectos para el restaurante " + restaurante);
						
						list.add(opArquitecto.get());
					}
					break;
				
				
				case "mayor":
					
					if(restaurante.equals("ALL")) {
						op= arquitectoDAO.findAllByOrderByPrecioDesc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay arquitectos cargadas");
						
						list = op.get();
					}
					else {
						opArquitecto= arquitectoDAO.findFirstByRestauranteNombreOrderByPrecioDesc(restaurante);
						if (!opArquitecto.isPresent()) 
							throw new NotFoundException("No hay arquitectos para el restaurante " + restaurante);
						list.add(opArquitecto.get());
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
			throw new NotFoundException("No se encuentra la arquitecto con orden = "+orden+" y restaurante = "+restaurante );			
		
		
		return list;
		
	}

	@Override
	public List<Arquitecto> findArquitectosByRestaurante(String nombre) throws BusinessException, NotFoundException{
		Optional<List<Arquitecto>> op = null;
		try {
			op = arquitectoDAO.findAllByRestauranteNombreOrderByNombreDesc(nombre);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
		if(!op.isPresent()) 
			throw new NotFoundException("No se encontro lista de arquitectos para el restaurante = "+nombre);
		
		return op.get(); 
	}*/

}
