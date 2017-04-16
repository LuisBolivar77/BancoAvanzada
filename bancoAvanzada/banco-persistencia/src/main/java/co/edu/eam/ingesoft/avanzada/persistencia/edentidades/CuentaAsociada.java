package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;

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

@Entity
@Table(name = "T_CUENTA_ASOCIADA")
@NamedQueries({
		@NamedQuery(name = CuentaAsociada.CUENTAS_ASOCIADAS_USUARIO, query = "SELECT c FROM CuentaAsociada c WHERE c.usuario = ?1 ") })
public class CuentaAsociada implements Serializable {

	public static final String CUENTAS_ASOCIADAS_USUARIO = "lista.cuentasAsociadas";

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
	private Banco nombreBanco;

	@Column(name = "NUMERO_CUENTA")
	private String numeroCuenta;

	@Column(name = "NOMBRE_CUENTA")
	private String nombreCuenta;

	@ManyToOne(cascade = {})
	@JoinColumn(name = "userName")
	private Usuario usuario;

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
	 * @param usuario
	 * @param estado
	 */
	public CuentaAsociada(String nombreTitular, String numDocumento, String tipoDocumento, Banco nombreBanco,
			String numeroCuenta, String nombreCuenta, Usuario usuario, String estado) {
		super();
		this.nombreTitular = nombreTitular;
		this.numDocumento = numDocumento;
		this.tipoDocumento = tipoDocumento;
		this.nombreBanco = nombreBanco;
		this.numeroCuenta = numeroCuenta;
		this.nombreCuenta = nombreCuenta;
		this.usuario = usuario;
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
	public Banco getNombreBanco() {
		return nombreBanco;
	}

	/**
	 * @param nombreBanco
	 *            the nombreBanco to set
	 */
	public void setNombreBanco(Banco nombreBanco) {
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
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}



	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	

}
