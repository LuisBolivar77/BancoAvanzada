package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "T_SAVING_ACCOUNT")

public class SavingAccount extends Product implements Serializable {

	@Column(name = "saving_interest", nullable = false)
	private double savingInterest;

	public SavingAccount() {

	}

	/**
	 * @param savingInterest
	 * @param ammount
	 */
	public SavingAccount(double savingInterest) {
		super();
		this.savingInterest = savingInterest;

	}

	/**
	 * @return the savingInterest
	 */
	public double getSavingInterest() {
		return savingInterest;
	}

	/**
	 * @param savingInterest
	 *            the savingInterest to set
	 */
	public void setSavingInterest(double savingInterest) {
		this.savingInterest = savingInterest;

	}
}
