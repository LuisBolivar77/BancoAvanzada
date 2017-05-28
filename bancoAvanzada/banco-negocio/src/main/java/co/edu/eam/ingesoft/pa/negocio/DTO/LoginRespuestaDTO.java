package co.edu.eam.ingesoft.pa.negocio.DTO;

public class LoginRespuestaDTO {

	private String token;
	private String numId;
	private String tipoId;

	public LoginRespuestaDTO() {
		// TODO Auto-generated constructor stub
	}

	public LoginRespuestaDTO(String token, String identificacion, String tipoId) {
		super();
		this.token = token;
		this.numId = identificacion;
		this.tipoId = tipoId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIdentificacion() {
		return numId;
	}

	public void setIdentificacion(String identificacion) {
		this.numId = identificacion;
	}

	/**
	 * @return the tipoId
	 */
	public String getTipoId() {
		return tipoId;
	}

	/**
	 * @param tipoId the tipoId to set
	 */
	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}
	
	

}
