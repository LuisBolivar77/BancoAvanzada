package co.edu.eam.ingesoft.avanzada.persistencia.edentidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="T_CODIGO_VALIDACION")
public class CodigoValidacion implements Serializable{

	@Id
	@Column(name="CODIGO_VALIDACION")
	private String codigo;
	
	@Temporal(TemporalType.TIME)
	@Column(name="FECHA_CREACION")
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name="USUARIO")
	private Usuario usuario;
	
	@Column(name="UTILIZADO")
	private boolean utilizado;
	
	
	public CodigoValidacion() {
		// TODO Auto-generated constructor stub
	}


	


	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}


	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}


	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}


	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	} 
	
	
	
	
}
