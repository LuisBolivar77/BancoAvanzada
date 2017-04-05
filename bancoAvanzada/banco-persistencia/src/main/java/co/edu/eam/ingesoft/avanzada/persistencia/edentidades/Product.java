package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_PRODUCT")
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name=Product.LISTAR_PRODUCTOS, query="SELECT p FROM Product p WHERE p.customer = ?1"),
	@NamedQuery(name=Product.LISTAR_CUENTAS, query="SELECT p FROM Product p WHERE p.customer = ?1 AND LENGTH(p.number) = 17"),
})
public class Product implements Serializable {
	
	public static final String LISTAR_PRODUCTOS = "producto.listar";
	public static final String LISTAR_CUENTAS = "cuentas.listar";
	
	@Id
	@Column(name="number")	
	protected String number;
	
	@Temporal(TemporalType.DATE)
	@Column(name="expedition_date")
	protected Date expeditionDate;
	
	@JoinColumns({
		@JoinColumn(name="holder_idtype", referencedColumnName="identification_type"),
		@JoinColumn(name="holder_idnumber", referencedColumnName="identification_number")	
	})	
	@ManyToOne
	protected Customer customer;
	
	
	@Column(name = "ammount", nullable = false)
	private double ammount;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @param number
	 * @param expeditionDate
	 * @param customer
	 * @param ammount
	 */
	public Product(String number, Date expeditionDate, Customer customer, double ammount) {
		super();
		this.number = number;
		this.expeditionDate = expeditionDate;
		this.customer = customer;
		this.ammount = ammount;
	}



	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the expeditionDate
	 */
	public Date getExpeditionDate() {
		return expeditionDate;
	}

	/**
	 * @param expeditionDate the expeditionDate to set
	 */
	public void setExpeditionDate(Date expeditionDate) {
		this.expeditionDate = expeditionDate;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return number;
	}



	/**
	 * @return the ammount
	 */
	public double getAmmount() {
		return ammount;
	}



	/**
	 * @param ammount the ammount to set
	 */
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	
	
	
	
	
}
