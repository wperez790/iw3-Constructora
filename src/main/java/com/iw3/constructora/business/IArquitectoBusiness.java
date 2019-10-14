package com.iw3.constructora.business;

import java.util.List;

import com.iw3.constructora.model.Arquitecto;

public interface IArquitectoBusiness {
	public List<Arquitecto> list() throws BusinessException;
	public Arquitecto load(Integer idArquitecto) throws BusinessException, NotFoundException;
	public Arquitecto save(Arquitecto arquitecto) throws BusinessException;
	public void remove(Integer idArquitecto) throws BusinessException, NotFoundException;	
}
