package co.edu.eam.ingesoft.pa.negocio.DTO;

public class RegistroDTO {
	
	private String nombre;
	 
	private String Apellido;
	
	private String tipoId;
	
	private String numId;
	
	private String user;
	
	private String pass;
	
	private String email;
	
	private String telefono;

	/**
	 * 
	 */
	public RegistroDTO() {
		super();
	}
	
	

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return Apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		Apellido = apellido;
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

	/**
	 * @return the numId
	 */
	public String getNumId() {
		return numId;
	}

	/**
	 * @param numId the numId to set
	 */
	public void setNumId(String numId) {
		this.numId = numId;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}



	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}



	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	

}
