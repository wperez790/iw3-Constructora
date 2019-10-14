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
import com.iw3.constructora.business.ITipoObraBusiness;
import com.iw3.constructora.business.NotFoundException;
import com.iw3.constructora.model.TipoObra;
import com.iw3.constructora.utils.Constantes;

@RestController
@RequestMapping(Constantes.URL_BASE_TIPO_OBRAS)
public class TipoObraRestController {
	
	@Autowired
	private ITipoObraBusiness tipoObrasBO;
	
	@GetMapping("")
	public ResponseEntity<List<TipoObra>> list() {
		try {
			return new ResponseEntity<List<TipoObra>>(tipoObrasBO.list(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<TipoObra>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<TipoObra> load(@PathVariable("id") int idcomida) {
		try {
			return new ResponseEntity<TipoObra>(tipoObrasBO.load(idcomida), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<TipoObra>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<TipoObra>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> insert(@RequestBody TipoObra comida) {
		try {
			tipoObrasBO.save(comida);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", Constantes.URL_BASE_TIPO_OBRAS + "/" + comida.getId());
			return new ResponseEntity<String>(responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "")
	public ResponseEntity<String> update(@RequestBody TipoObra comida) {
		try {
			tipoObrasBO.save(comida);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int idTipoObra) {
		try {
			tipoObrasBO.remove(idTipoObra);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	/*
	@GetMapping(value = "/menor_mayor_precio_restaurante")
	public ResponseEntity<List<TipoObra>> orderByPriceAndRestaurante(@RequestParam("orden") String o, @RequestParam("restaurante") String r) {
		try {
			
			return new ResponseEntity<List<TipoObra>>(tipoObrasBO.orderByPriceAndRestaurante(o, r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<TipoObra>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<TipoObra>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/tipoObras_por_restaurante")
	public ResponseEntity<List<TipoObra>> findTipoObrasByRestaurante(@RequestParam("restaurante") String r) {
		try {
			return new ResponseEntity<List<TipoObra>>(tipoObrasBO.findTipoObrasByRestaurante(r),HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<TipoObra>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (NotFoundException e) {
			return new ResponseEntity<List<TipoObra>>(HttpStatus.NOT_FOUND);
		}
	}*/

}
