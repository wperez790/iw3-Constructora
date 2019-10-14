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

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "arquitecto")
public class Arquitecto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 20)
	private String nombre;
	
	@Column
	private double puntuacion;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "arquitecto",
		cascade = CascadeType.ALL)
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

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}
	
	
	@Override
	public String toString() {
		String res = "";
		res += "{id:"+id;
		res += ",nombre:"+nombre;
		res += ",puntuacion:"+puntuacion+"}";				
		return res;
	}
	
}
