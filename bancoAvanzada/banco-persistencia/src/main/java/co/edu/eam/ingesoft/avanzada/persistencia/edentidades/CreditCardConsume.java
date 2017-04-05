package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_CREDITCARD_CONSUME")
@NamedQueries({ 
	
	@NamedQuery(name=CreditCardConsume.LISTAR_CONSUMOS, query="SELECT c FROM CreditCardConsume c WHERE c.creditCard = ?1 AND c.isPayed = false"),
	@NamedQuery(name=CreditCardConsume.LISTAR_CONSUMOS_CLIENTE, query="SELECT c FROM CreditCardConsume c JOIN c.creditCard cc WHERE cc.customer = ?1 AND c.isPayed = false")
	
})
public class CreditCardConsume implements Serializable {

	public static final String LISTAR_CONSUMOS = "consumos.listar";
	public static final String LISTAR_CONSUMOS_CLIENTE = "consumosCliente.listar";

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@JoinColumn(name = "creditcard_number")
	@ManyToOne(cascade = {})
	private CreditCard creditCard;

	@Temporal(TemporalType.TIME)
	@Column(name = "date_consume")
	private Date dateConsume;

	@Column(name = "number_shares")
	private int numberShares;

	@Column(name = "ammount")
	private double ammount;

	@Column(name = "interest")
	private double interest;

	@Column(name = "remaining_ammount")
	private double reamingAmmount;

	@Column(name = "is_payed")
	private boolean isPayed;

	public CreditCardConsume() {
		// TODO Auto-generated constructor stub
	}

	public CreditCardConsume(int id, CreditCard creditCard, Date dateConsume, int numberShares, double ammount,
			double interest, double reamingAmmount, boolean isPayed) {
		super();
		this.id = id;
		this.creditCard = creditCard;
		this.dateConsume = dateConsume;
		this.numberShares = numberShares;
		this.ammount = ammount;
		this.interest = interest;
		this.reamingAmmount = reamingAmmount;
		this.isPayed = isPayed;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Date getDateConsume() {
		return dateConsume;
	}

	public void setDateConsume(Date dateConsume) {
		this.dateConsume = dateConsume;
	}

	public int getNumberShares() {
		return numberShares;
	}

	public void setNumberShares(int numberShares) {
		this.numberShares = numberShares;
	}

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getReamingAmmount() {
		return reamingAmmount;
	}

	public void setReamingAmmount(double reamingAmmount) {
		this.reamingAmmount = reamingAmmount;
	}

	public boolean isPayed() {
		return isPayed;
	}

	public void setPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}

}
