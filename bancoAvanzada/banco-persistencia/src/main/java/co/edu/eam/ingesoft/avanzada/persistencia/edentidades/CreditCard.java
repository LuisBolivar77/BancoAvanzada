package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_CREDITCARD")
@NamedQuery(name=CreditCard.LISTAR_TARJETAS, query="SELECT c FROM CreditCard c WHERE c.customer = ?1")
public class CreditCard extends Product implements Serializable{
	
	public static final String LISTAR_TARJETAS = "tarjetas.listar";
	
	@Column(name="cvc")
	private String cvc;
	
	@Temporal(TemporalType.DATE)
	@Column(name="expiration_date")
	private Date expirationDate;	
	
	@JoinColumn(name="franchise")
	@ManyToOne
	private Franchise franchise;
	
	@Column(name="remaining_ammount")
	private double cantidadRestante;
	
	public CreditCard() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param cvc
	 * @param expirationDate
	 * @param franchise
	 * @param cantidadRestante
	 */
	public CreditCard(String cvc, Date expirationDate, Franchise franchise, double cantidadRestante) {
		super();
		this.cvc = cvc;
		this.expirationDate = expirationDate;
		this.franchise = franchise;
		this.cantidadRestante = cantidadRestante;
	}





	/**
	 * @return the cvc
	 */
	public String getCvc() {
		return cvc;
	}


	/**
	 * @param cvc the cvc to set
	 */
	public void setCvc(String cvc) {
		this.cvc = cvc;
	}


	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}


	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}


	/**
	 * @return the franchise
	 */
	public Franchise getFranchise() {
		return franchise;
	}


	/**
	 * @param franchise the franchise to set
	 */
	public void setFranchise(Franchise franchise) {
		this.franchise = franchise;
	}


	/**
	 * @return the cantidadRestante
	 */
	public double getCantidadRestante() {
		return cantidadRestante;
	}


	/**
	 * @param cantidadRestante the cantidadRestante to set
	 */
	public void setCantidadRestante(double cantidadRestante) {
		this.cantidadRestante = cantidadRestante;
	}
	
	
	
	
}
