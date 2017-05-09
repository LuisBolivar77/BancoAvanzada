package co.edu.eam.ingesoft.pa.negocio.DTO;

public class CuentaAsociadaDTO {
	
	private String idCliente;
	
	private String tipoIdCliente;

	/**
	 * @param idCliente
	 * @param tipoIdCliente
	 */
	public CuentaAsociadaDTO(String idCliente, String tipoIdCliente) {
		super();
		this.idCliente = idCliente;
		this.tipoIdCliente = tipoIdCliente;
	}

	/**
	 * @return the idCliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * @return the tipoIdCliente
	 */
	public String getTipoIdCliente() {
		return tipoIdCliente;
	}

	/**
	 * @param tipoIdCliente the tipoIdCliente to set
	 */
	public void setTipoIdCliente(String tipoIdCliente) {
		this.tipoIdCliente = tipoIdCliente;
	}
	
	

}
