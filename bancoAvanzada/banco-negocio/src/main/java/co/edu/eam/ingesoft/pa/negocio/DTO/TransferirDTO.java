package co.edu.eam.ingesoft.pa.negocio.DTO;

import java.io.Serializable;

public class TransferirDTO implements Serializable{
	
	private int idCuentaAso;
	
	private double monto;
	
	public TransferirDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the idCuentaAso
	 */
	public int getIdCuentaAso() {
		return idCuentaAso;
	}

	/**
	 * @param idCuentaAso the idCuentaAso to set
	 */
	public void setIdCuentaAso(int idCuentaAso) {
		this.idCuentaAso = idCuentaAso;
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
