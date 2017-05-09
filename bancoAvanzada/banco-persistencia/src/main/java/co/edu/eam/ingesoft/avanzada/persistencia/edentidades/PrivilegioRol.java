package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="privilegio_rol")
public class PrivilegioRol implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="idprivilegio")
	private Privilegio privilegio;
	
	@JoinColumn(name="idrol")
	@ManyToOne
	private Rol rol;
	
	public PrivilegioRol() {
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
	 * @return the privilegio
	 */
	public Privilegio getPrivilegio() {
		return privilegio;
	}

	/**
	 * @param privilegio the privilegio to set
	 */
	public void setPrivilegio(Privilegio privilegio) {
		this.privilegio = privilegio;
	}

	/**
	 * @return the rol
	 */
	public Rol getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	

}
