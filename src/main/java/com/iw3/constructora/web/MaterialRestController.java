package com.iw3.constructora.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iw3.constructora.business.BusinessException;
import com.iw3.constructora.business.IMaterialBusiness;
import com.iw3.constructora.business.NotFoundException;
import com.iw3.constructora.model.Material;
import com.iw3.constructora.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_MATERIALES)
public class MaterialRestController {
	
	@Autowired
	private IMaterialBusiness materialsBO;
	
	@GetMapping("")
	public ResponseEntity<List<Material>> list() {
		try {
			return new ResponseEntity<List<Material>>(materialsBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Material>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Material> load(@PathVariable("id") int idmaterial) {
		try {
			return new ResponseEntity<Material>(materialsBO.load(idmaterial), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Material>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Material>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Material material) {
		try {
			materialsBO.save(material);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_MATERIALES + "/" + material.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Material material) {
		try {
			materialsBO.save(material);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idMaterial) {
		try {
			materialsBO.remove(idMaterial);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	/*
	@GetMapping(value = "/menor_mayor_precio_restaurante")
	public ResponseEntity<List<Material>> orderByPriceAndRestaurante(@RequestParam("orden") String o, @RequestParam("restaurante") String r) {
		try {
			
			return new ResponseEntity<List<Material>>(materialsBO.orderByPriceAndRestaurante(o, r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Material>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Material>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/materials_por_restaurante")
	public ResponseEntity<List<Material>> findMaterialsByRestaurante(@RequestParam("restaurante") String r) {
		try {
			return new ResponseEntity<List<Material>>(materialsBO.findMaterialsByRestaurante(r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Material>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Material>>(HttpStatus.NOT_FOUND);
		}
	}*/

}
