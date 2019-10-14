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
import com.iw3.constructora.business.IObraBusiness;
import com.iw3.constructora.business.NotFoundException;
import com.iw3.constructora.model.Obra;
import com.iw3.constructora.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_OBRAS)
public class ObraRestController {
	
	@Autowired
	private IObraBusiness obrasBO;
	
	@GetMapping("")
	public ResponseEntity<List<Obra>> list() {
		try {
			return new ResponseEntity<List<Obra>>(obrasBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Obra>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Obra> load(@PathVariable("id") int idcomida) {
		try {
			return new ResponseEntity<Obra>(obrasBO.load(idcomida), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Obra>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Obra>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Obra comida) {
		try {
			obrasBO.save(comida);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_OBRAS + "/" + comida.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Obra comida) {
		try {
			obrasBO.save(comida);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idObra) {
		try {
			obrasBO.remove(idObra);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	/*
	@GetMapping(value = "/menor_mayor_precio_restaurante")
	public ResponseEntity<List<Obra>> orderByPriceAndRestaurante(@RequestParam("orden") String o, @RequestParam("restaurante") String r) {
		try {
			
			return new ResponseEntity<List<Obra>>(obrasBO.orderByPriceAndRestaurante(o, r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Obra>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Obra>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/obras_por_restaurante")
	public ResponseEntity<List<Obra>> findObrasByRestaurante(@RequestParam("restaurante") String r) {
		try {
			return new ResponseEntity<List<Obra>>(obrasBO.findObrasByRestaurante(r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Obra>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Obra>>(HttpStatus.NOT_FOUND);
		}
	}*/

}
