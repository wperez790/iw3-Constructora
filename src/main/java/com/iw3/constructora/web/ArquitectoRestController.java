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
import com.iw3.constructora.business.IArquitectoBusiness;
import com.iw3.constructora.business.NotFoundException;
import com.iw3.constructora.model.Arquitecto;
import com.iw3.constructora.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_ARQUITECTOS)
public class ArquitectoRestController {
	
	@Autowired
	private IArquitectoBusiness arquitectosBO;
	
	@GetMapping("")
	public ResponseEntity<List<Arquitecto>> list() {
		try {
			return new ResponseEntity<List<Arquitecto>>(arquitectosBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Arquitecto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Arquitecto> load(@PathVariable("id") int idcomida) {
		try {
			return new ResponseEntity<Arquitecto>(arquitectosBO.load(idcomida), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<Arquitecto>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<Arquitecto>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody Arquitecto comida) {
		try {
			arquitectosBO.save(comida);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_ARQUITECTOS + "/" + comida.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody Arquitecto comida) {
		try {
			arquitectosBO.save(comida);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idArquitecto) {
		try {
			arquitectosBO.remove(idArquitecto);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	/*
	@GetMapping(value = "/menor_mayor_precio_restaurante")
	public ResponseEntity<List<Arquitecto>> orderByPriceAndRestaurante(@RequestParam("orden") String o, @RequestParam("restaurante") String r) {
		try {
			
			return new ResponseEntity<List<Arquitecto>>(arquitectosBO.orderByPriceAndRestaurante(o, r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Arquitecto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Arquitecto>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/arquitectos_por_restaurante")
	public ResponseEntity<List<Arquitecto>> findArquitectosByRestaurante(@RequestParam("restaurante") String r) {
		try {
			return new ResponseEntity<List<Arquitecto>>(arquitectosBO.findArquitectosByRestaurante(r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Arquitecto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<Arquitecto>>(HttpStatus.NOT_FOUND);
		}
	}*/

}
