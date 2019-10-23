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
import com.iw3.constructora.business.IObreroBusiness;
import com.iw3.constructora.business.NotFoundException;
import com.iw3.constructora.model.Obrero;
import com.iw3.constructora.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_OBREROS)
public class ObreroRestController {
	
	@Autowired
	private IObreroBusiness obrerosBO;
	
	@GetMapping("")
	public ResponseEntity<List<Obrero>> list() {
		try {
			return new ResponseEntity<List<Obrero>>(obrerosBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Obrero>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Obrero> load(@PathVariable("id") int idobrero) {
		try {
			return new ResponseEntity<Obrero>(obrerosBO.load(idobrero), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Obrero>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Obrero>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Obrero obrero) {
		try {
			obrerosBO.save(obrero);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_OBREROS + "/" + obrero.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Obrero obrero) {
		try {
			obrerosBO.save(obrero);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idObrero) {
		try {
			obrerosBO.remove(idObrero);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	/*
	@GetMapping(value = "/menor_mayor_precio_restaurante")
	public ResponseEntity<List<Obrero>> orderByPriceAndRestaurante(@RequestParam("orden") String o, @RequestParam("restaurante") String r) {
		try {
			
			return new ResponseEntity<List<Obrero>>(obrerosBO.orderByPriceAndRestaurante(o, r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Obrero>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Obrero>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/obreros_por_restaurante")
	public ResponseEntity<List<Obrero>> findObrerosByRestaurante(@RequestParam("restaurante") String r) {
		try {
			return new ResponseEntity<List<Obrero>>(obrerosBO.findObrerosByRestaurante(r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Obrero>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Obrero>>(HttpStatus.NOT_FOUND);
		}
	}*/

}
