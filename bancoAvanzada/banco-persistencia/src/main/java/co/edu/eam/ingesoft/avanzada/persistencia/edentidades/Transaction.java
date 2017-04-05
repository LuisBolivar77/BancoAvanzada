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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_TRANSACTION")
public class Transaction implements Serializable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne(cascade = {})
	@JoinColumn(name = "saving_account_number")
	private SavingAccount savingAccNumber;

	@Column(name = "ammount", nullable = false)
	private double ammount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;

	@Column(name = "source_transaction")
	private String sourceTransact;

	public Transaction(){
		
	}

	public Transaction(int id, SavingAccount savingAccNumber, double ammount, Date transactionDate,
			String sourceTransact) {
		super();
		this.id = id;
		this.savingAccNumber = savingAccNumber;
		this.ammount = ammount;
		this.transactionDate = transactionDate;
		this.sourceTransact = sourceTransact;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ammount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((savingAccNumber == null) ? 0 : savingAccNumber.hashCode());
		result = prime * result + ((sourceTransact == null) ? 0 : sourceTransact.hashCode());
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(ammount) != Double.doubleToLongBits(other.ammount))
			return false;
		if (id != other.id)
			return false;
		if (savingAccNumber == null) {
			if (other.savingAccNumber != null)
				return false;
		} else if (!savingAccNumber.equals(other.savingAccNumber))
			return false;
		if (sourceTransact == null) {
			if (other.sourceTransact != null)
				return false;
		} else if (!sourceTransact.equals(other.sourceTransact))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		return true;
	}

	/**
	 * @return the savingAccNumber
	 */
	public SavingAccount getSavingAccNumber() {
		return savingAccNumber;
	}

	/**
	 * @param savingAccNumber the savingAccNumber to set
	 */
	public void setSavingAccNumber(SavingAccount savingAccNumber) {
		this.savingAccNumber = savingAccNumber;
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

	/**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the sourceTransact
	 */
	public String getSourceTransact() {
		return sourceTransact;
	}

	/**
	 * @param sourceTransact the sourceTransact to set
	 */
	public void setSourceTransact(String sourceTransact) {
		this.sourceTransact = sourceTransact;
	}
	
	
	
	
}
