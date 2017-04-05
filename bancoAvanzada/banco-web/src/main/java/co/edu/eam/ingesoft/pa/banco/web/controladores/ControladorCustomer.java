package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Franchise;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.pa.negocio.exception.ExcepcionNegocio;

@Named("controladorCustomer")
@ViewScoped
public class ControladorCustomer implements Serializable {

	/**
	 * 
	 */
	private String nombre;

	/**
	 * 
	 */
	private String apellido;

	/**
	 * 
	 */
	private String username;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private String identificacion;

	/**
	 * 
	 */
	private String tipoSeleccionado;

	/**
	 * 
	 */
	@EJB
	private CustomerEJB customerEJB;

	/**
	 * 
	 */
	@EJB
	private ProductoEJB productoEJB;

	/**
	 * 
	 */
	private List<Customer> clientes;

	/**
	 * 
	 */
	@PostConstruct
	public void listarTablaClientes() {
		clientes = customerEJB.listaCustomer();
	}

	/**
	 * 
	 */
	public void crear() {

		
		
		Customer cliente = new Customer();
		cliente.setName(nombre);
		cliente.setLastName(apellido);
		cliente.setIdNum(identificacion);
		cliente.setIdType(tipoSeleccionado);
		
		
		Usuario usu = new Usuario();
		usu.setUserName(username);
		usu.setPassword(password);
		usu.setCustomer(cliente);

		try {
			customerEJB.crearCliente(cliente);
			customerEJB.crearUsuario(usu);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Creado con exito", null));
		} catch (ExcepcionNegocio e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null));
		}

		nombre = "";
		apellido = "";
		identificacion = "";
		tipoSeleccionado = null;
		password = "";
		username = "";

	}

	/**
	 * 
	 */
	public void buscar() {

		Customer cliente = customerEJB.buscarCliente(identificacion, tipoSeleccionado);
		if (cliente != null) {
			nombre = cliente.getName();
			apellido = cliente.getLastName();
			identificacion = cliente.getIdNum();
			tipoSeleccionado = cliente.getIdType();

		}

	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido
	 *            the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion
	 *            the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the tipoSeleccionado
	 */
	public String getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	/**
	 * @param tipoSeleccionado
	 *            the tipoSeleccionado to set
	 */
	public void setTipoSeleccionado(String tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the clientes
	 */
	public List<Customer> getClientes() {
		return clientes;
	}

	/**
	 * @param clientes the clientes to set
	 */
	public void setClientes(List<Customer> clientes) {
		this.clientes = clientes;
	}
	
	

}
