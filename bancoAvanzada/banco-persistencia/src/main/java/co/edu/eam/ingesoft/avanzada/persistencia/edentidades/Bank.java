package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="T_BANCO")
@NamedQueries({
	@NamedQuery(name=Bank.NOMBRE_CUENTAS, query="SELECT b FROM Bank b ")
})
public class Bank implements Serializable{
	
	public static final String NOMBRE_CUENTAS = "lista.nomCuentas";
	
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="NOMBRE_CUENTA")
	private String nombre;
	
	
	public Bank() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param id
	 * @param nombre
	 */
	public Bank(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	@Override
	public String toString() {
		return nombre;
	}
	

}
