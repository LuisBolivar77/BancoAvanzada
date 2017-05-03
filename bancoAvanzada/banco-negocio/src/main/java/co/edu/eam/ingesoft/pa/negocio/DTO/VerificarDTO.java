package co.edu.eam.ingesoft.pa.negocio.DTO;

import java.io.Serializable;

public class VerificarDTO implements Serializable{
	
	private String numCuenta;
	
	private String cedula;
	
	private String tipoCed;
	
	
	public VerificarDTO() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the numCuenta
	 */
	public String getNumCuenta() {
		return numCuenta;
	}


	/**
	 * @param numCuenta the numCuenta to set
	 */
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}


	/**
	 * @return the cedula
	 */
	public String getCedula() {
		return cedula;
	}


	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	/**
	 * @return the tipoCed
	 */
	public String getTipoCed() {
		return tipoCed;
	}


	/**
	 * @param tipoCed the tipoCed to set
	 */
	public void setTipoCed(String tipoCed) {
		this.tipoCed = tipoCed;
	}
	
	
	
	

}
