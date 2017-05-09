package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="privilegio")
@NamedQuery(name="Privilegio.listarXRol",query="SELECT p FROM PrivilegioRol pxr JOIN pxr.privilegio p WHERE pxr.rol=?1")
public class Privilegio implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String accion;
	
	
	public Privilegio() {
	}


	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}


	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	
	
	

}
