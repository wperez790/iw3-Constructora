package com.iw3.constructora.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tipoObra")
public class TipoObra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 30)
	private String nombre;
	private double precioMetroCuadradoUSD; 
	@JsonManagedReference
	@OneToMany(mappedBy = "tipoObra")
	@JsonIgnore
	private List<Obra> obras;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioMetroCuadrado() {
		return precioMetroCuadradoUSD;
	}

	public void setPrecioMetroCuadrado(double precioMetroCuadrado) {
		this.precioMetroCuadradoUSD = precioMetroCuadrado;
	}

	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}
	

	@Override
	public String toString() {
		String tipoObraJSON ; 
		tipoObraJSON = "{id:"+this.id;
		tipoObraJSON += ",nombre:"+this.nombre;
		tipoObraJSON += ",precioMetroCuadrado:"+this.precioMetroCuadradoUSD+"}";
		return tipoObraJSON;
	}
	
	


	
}
