package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "T_CUENTA_ASOCIADA")
@NamedQueries({
		@NamedQuery(name = CuentaAsociada.CUENTAS_ASOCIADAS_USUARIO, query = "SELECT c FROM CuentaAsociada c WHERE c.customer = ?1 "),
		@NamedQuery(name = CuentaAsociada.LISTAR_CUENTAS_ASOCIADAS, query = "SELECT c FROM CuentaAsociada c WHERE c.customer = ?1 AND c.estado = 'Asociada'") })
public class CuentaAsociada implements Serializable {

	public static final String CUENTAS_ASOCIADAS_USUARIO = "lista.cuentasAsociadas";
	public static final String LISTAR_CUENTAS_ASOCIADAS = "CuentaAsociada.listar";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "NOMBRE_TITULAR")
	private String nombreTitular;

	@Column(name = "NUMERO_DOCUMENTO")
	private String numDocumento;

	@Column(name = "TIPO_DOCUMENTO")
	private String tipoDocumento;

	@ManyToOne
	@JoinColumn(name = "NOMBRE_BANCO")
	private Bank nombreBanco;

	@Column(name = "NUMERO_CUENTA")
	private String numeroCuenta;

	@Column(name = "NOMBRE_CUENTA")
	private String nombreCuenta;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "idType", referencedColumnName = "identification_type"),
			@JoinColumn(name = "idNum", referencedColumnName = "identification_number"), })
	private Customer customer;

	@Column(name = "estado")
	private String estado;

	public CuentaAsociada() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param nombreTitular
	 * @param numDocumento
	 * @param tipoDocumento
	 * @param nombreBanco
	 * @param numeroCuenta
	 * @param nombreCuenta
	 * @param customer
	 * @param estado
	 */
	public CuentaAsociada(int id, String nombreTitular, String numDocumento, String tipoDocumento, Bank nombreBanco,
			String numeroCuenta, String nombreCuenta, Customer customer, String estado) {
		super();
		this.id = id;
		this.nombreTitular = nombreTitular;
		this.numDocumento = numDocumento;
		this.tipoDocumento = tipoDocumento;
		this.nombreBanco = nombreBanco;
		this.numeroCuenta = numeroCuenta;
		this.nombreCuenta = nombreCuenta;
		this.customer = customer;
		this.estado = estado;
	}

	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}

	/**
	 * @param nombreTitular
	 *            the nombreTitular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	/**
	 * @return the numDocumento
	 */
	public String getNumDocumento() {
		return numDocumento;
	}

	/**
	 * @param numDocumento
	 *            the numDocumento to set
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the nombreBanco
	 */
	public Bank getNombreBanco() {
		return nombreBanco;
	}

	/**
	 * @param nombreBanco
	 *            the nombreBanco to set
	 */
	public void setNombreBanco(Bank nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the nombreCuenta
	 */
	public String getNombreCuenta() {
		return nombreCuenta;
	}

	/**
	 * @param nombreCuenta
	 *            the nombreCuenta to set
	 */
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	@Override
	public String toString() {
		return nombreCuenta;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado
	 *            the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

}
