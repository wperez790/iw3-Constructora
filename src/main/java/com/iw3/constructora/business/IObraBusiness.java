package com.iw3.constructora.business;

import java.util.List;

import com.iw3.constructora.model.Obra;

public interface IObraBusiness {
	
	public List<Obra> list() throws BusinessException;
	public Obra load(Integer idObra) throws BusinessException, NotFoundException;
	public Obra save(Obra obra) throws BusinessException;
	public void remove(Integer idObra) throws BusinessException, NotFoundException;	
}
