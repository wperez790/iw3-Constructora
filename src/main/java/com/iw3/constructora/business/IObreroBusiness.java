package com.iw3.constructora.business;

import java.util.List;

import com.iw3.constructora.model.Obrero;

public interface IObreroBusiness {
	public List<Obrero> list() throws BusinessException;
	public Obrero load(Integer idObrero) throws BusinessException, NotFoundException;
	public Obrero save(Obrero obrero) throws BusinessException;
	public void remove(Integer idObrero) throws BusinessException, NotFoundException;	
}
