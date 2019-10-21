package com.iw3.constructora.business;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iw3.constructora.model.Material;
import com.iw3.constructora.persistance.MaterialRepository;

@Service
public class MaterialBusiness implements IMaterialBusiness {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private Material materialAux = null;
	private List<Material> listMaterialAux;
	
	@Autowired
	private MaterialRepository materialDAO;

	@Override
	public List<Material> list() throws BusinessException {
		try {
			listMaterialAux= materialDAO.findAll();
			
			return listMaterialAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public Material save(Material material) throws BusinessException {				
		boolean isNew = material.getId() == null;
		
		try {
			materialAux = materialDAO.save(material);
			
			if(!isNew) 
				log.info("UPDATE-MATERIAL, objeto: "+ materialAux.toString());
			
			else
				log.info("INSERT-MATERIAL, objeto:" + materialAux.toString());
				
			
			return materialAux;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
	}


	@Override
	public Material load(Integer idMaterial) throws BusinessException, NotFoundException {
		Optional<Material> op = null;
		try {
			op = materialDAO.findById(idMaterial);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la material con id= " + idMaterial);
		}
		return op.get();
	}

	@Override
	public void remove(Integer idMaterial) throws BusinessException, NotFoundException {
		Optional<Material> op = null;
		try {
			op = materialDAO.findById(idMaterial);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}

		if (!op.isPresent()) {
			throw new NotFoundException("No se encuentra la Material con id= " + idMaterial);
		}
		
		try {
			log.info("REMOVE-MATERIAL, id: "+idMaterial);
			materialDAO.deleteById(idMaterial);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
	}
	
	/*@Override
	public List<Material> orderByPriceAndRestaurante(String orden, String restaurante)
			throws BusinessException, NotFoundException {
		Optional<List<Material>> op = null;
		Optional<Material> opMaterial = null;
		List<Material> list = new ArrayList<>();
		boolean valid = true;
		
		try {
			switch(orden) {				
				case "menor":
					
					if(restaurante.equals("ALL")) { 
						op= materialDAO.findAllByOrderByPrecioAsc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay materials cargadas");
						
						list = op.get();
					}
					else {
						opMaterial= materialDAO.findFirstByRestauranteNombreOrderByPrecioAsc(restaurante);
						if (!opMaterial.isPresent()) 
							throw new NotFoundException("No hay materials para el restaurante " + restaurante);
						
						list.add(opMaterial.get());
					}
					break;
				
				
				case "mayor":
					
					if(restaurante.equals("ALL")) {
						op= materialDAO.findAllByOrderByPrecioDesc();
						if (!op.isPresent()) 
							throw new NotFoundException("No hay materials cargadas");
						
						list = op.get();
					}
					else {
						opMaterial= materialDAO.findFirstByRestauranteNombreOrderByPrecioDesc(restaurante);
						if (!opMaterial.isPresent()) 
							throw new NotFoundException("No hay materials para el restaurante " + restaurante);
						list.add(opMaterial.get());
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
			throw new NotFoundException("No se encuentra la material con orden = "+orden+" y restaurante = "+restaurante );			
		
		
		return list;
		
	}

	@Override
	public List<Material> findMaterialsByRestaurante(String nombre) throws BusinessException, NotFoundException{
		Optional<List<Material>> op = null;
		try {
			op = materialDAO.findAllByRestauranteNombreOrderByNombreDesc(nombre);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
		if(!op.isPresent()) 
			throw new NotFoundException("No se encontro lista de materials para el restaurante = "+nombre);
		
		return op.get(); 
	}*/

}
