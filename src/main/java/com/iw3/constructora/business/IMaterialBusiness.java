package com.iw3.constructora.business;

import java.util.List;

import com.iw3.constructora.model.Material;

public interface IMaterialBusiness {
	public List<Material> list() throws BusinessException;
	public Material load(Integer idMaterial) throws BusinessException, NotFoundException;
	public Material save(Material material) throws BusinessException;
	public void remove(Integer idMaterial) throws BusinessException, NotFoundException;	
}
