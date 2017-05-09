package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_USUARIO")
@NamedQueries({
		@NamedQuery(name = Usuario.LISTA_CUSTOMERS_NOMUS, query = "SELECT u FROM Usuario u WHERE u.userName = ?1"), })
public class Usuario implements Serializable {

	public static final String LISTA_CUSTOMERS_NOMUS = "usuario.buscarUser";

	@Id
	@Column(name = "userName", nullable = false)
	private String userName;

	@Column(name = "password")
	private String password;

	@OneToOne
	@JoinColumns({ @JoinColumn(name = "idType", referencedColumnName = "identification_type"),
			@JoinColumn(name = "idNum", referencedColumnName = "identification_number"), })
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "idrol")
	private Rol rol;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param userName
	 * @param password
	 * @param customer
	 * @param rol
	 */
	public Usuario(String userName, String password, Customer customer, Rol rol) {
		super();
		this.userName = userName;
		this.password = password;
		this.customer = customer;
		this.rol = rol;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
