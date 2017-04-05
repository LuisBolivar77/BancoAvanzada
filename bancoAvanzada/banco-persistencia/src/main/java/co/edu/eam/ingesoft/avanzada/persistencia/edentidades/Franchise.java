package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name="T_FRANCHISE")

@NamedQueries({
	@NamedQuery(name=Franchise.LISTAR_FRANQUICIA, query="SELECT f FROM Franchise f")
})
public class Franchise implements Serializable {

	
	public static final String LISTAR_FRANQUICIA = "franquicia.listar";

	@Id
	@Column(name="code")
	private String code;
	
	@Column(name="name")
	private String name;
	
	public Franchise() {
		// TODO Auto-generated constructor stub
	}

	public Franchise(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  name ;
	}
	
	
	
	
}
