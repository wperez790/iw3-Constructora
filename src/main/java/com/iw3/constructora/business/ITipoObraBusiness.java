package com.iw3.constructora.business;

import java.util.List;

import com.iw3.constructora.model.TipoObra;

public interface ITipoObraBusiness {
	
	public List<TipoObra> list() throws BusinessException;
	public TipoObra load(Integer idTipoObra) throws BusinessException, NotFoundException;
	public TipoObra save(TipoObra tipoObra) throws BusinessException;
	public void remove(Integer idTipoObra) throws BusinessException, NotFoundException;	
}
