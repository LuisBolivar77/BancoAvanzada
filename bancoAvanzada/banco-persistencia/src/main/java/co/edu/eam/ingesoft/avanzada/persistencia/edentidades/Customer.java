package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;
import java.util.List;

import javax.management.Query;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUSTOMER")
@IdClass(CustomerPK.class)
@NamedQueries({
	@NamedQuery(name=Customer.LISTA_CUSTOMERS, query="SELECT c FROM Customer c"),
})
public class Customer implements Serializable {
	
	public static final String LISTA_CUSTOMERS = "lista.customer";

	@Id
	@Column(name="identification_type", length=50)
	private String idType;
	
	@Id
	@Column(name="identification_number", length=50)
	private String idNum;

	@Column(name = "name", nullable = false, length=10)
	private String name;

	@Column(name = "lastname", nullable = false, length=15)
	private String lastName;
	
	@OneToMany(mappedBy="customer", cascade={})
	private List<Product> products;

	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	
	


	/**
	 * @param idType
	 * @param idNum
	 * @param name
	 * @param lastName
	 * @param products
	 */
	public Customer(String idType, String idNum, String name, String lastName, List<Product> products) {
		super();
		this.idType = idType;
		this.idNum = idNum;
		this.name = name;
		this.lastName = lastName;
		this.products = products;
	}





	/**
	 * @return the idType
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * @param idType the idType to set
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	/**
	 * @return the idNum
	 */
	public String getIdNum() {
		return idNum;
	}

	/**
	 * @param idNum the idNum to set
	 */
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	

}
