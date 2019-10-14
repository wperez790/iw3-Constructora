package com.iw3.constructora.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "obra")
public class Obra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 30)
	private String nombre;
	@Column(length = 30)
	private String direccion;
	@Column(length = 30)
	private String barrio;
	@Column(length = 30)
	private String localidad;
	private int metrosCuadrados;
	@Column(length = 30)
	private String descripcion;
	
	
	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name="arquitecto_id", nullable = false)
	Arquitecto arquitecto;
	
	
	@JsonBackReference
	@ManyToOne()
	@JoinColumn(name="tipoObra_id", nullable = false)
	TipoObra tipoObra;
	
	@ManyToMany
	@JoinTable(
			  name = "obra-materiales", 
			  joinColumns = @JoinColumn(name = "obra_id"), 
			  inverseJoinColumns = @JoinColumn(name = "material_id"))
		List<Material> materiales;
	
	
	@OneToMany(mappedBy = "obra", cascade = CascadeType.ALL)
	@JsonManagedReference
	List<Obrero> obreros;

	
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


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	
	public int getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(int metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Arquitecto getArquitecto() {
		return arquitecto;
	}

	public void setArquitecto(Arquitecto arquitecto) {
		this.arquitecto = arquitecto;
	}
	
	
	public TipoObra getTipoObra() {
		return tipoObra;
	}

	public void setTipoObra(TipoObra tipoObra) {
		this.tipoObra = tipoObra;
	}
	

	public List<Material> getMaterial() {
		return materiales;
	}

	public void setMaterial(List<Material> material) {
		this.materiales = material;
	}

	@Override
	public String toString() {
		String obraJSON ; 
		obraJSON = "{id:"+this.id;
		obraJSON += ",nombre:"+this.nombre;
		obraJSON += ",direccion:"+this.direccion; 
		obraJSON += ",barrio:"+this.barrio;
		obraJSON += ",localidad:"+this.localidad;
		obraJSON += ",metrosCuadrados:"+this.metrosCuadrados;
		obraJSON += ",descripcion:"+this.descripcion+"}";
		return obraJSON;
	}
	
	


	
}
