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
				log.info("UPDATE-OBRA, objeto: "+ obraAux.toString());
			
			else
				log.info("INSERT-OBRA, objeto:" + obraAux.toString());
				
			
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
			log.info("REMOVE-OBRA, id: "+idObra);
			obraDAO.deleteById(idObra);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new BusinessException(e);
		}
		
	}
	
}
