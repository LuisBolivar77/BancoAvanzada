package co.edu.eam.ingesoft.pa.negocio.DTO;

import java.io.Serializable;

public class RecibirDTO implements Serializable{
	
	private String numCuenta;
	
	private double monto;
	
	public RecibirDTO() {
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
	 * @return the monto
	 */
	public double getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	

}
