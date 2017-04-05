package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;

@Named("ControladorSesion")
@SessionScoped
public class ControladorSesion implements Serializable {

	/**
	 * 
	 */
	private String usuario;

	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	private Usuario user;

	@EJB
	private CustomerEJB customerEJB;
	

	public String iniciarSesion() {
		Usuario usu = customerEJB.buscarUsuarioLogIn(usuario);
		if (usu != null) {
			if (usu.getPassword().equals(password)) {
				user = usu;
				usuario = "";
				password = "";
				Faces.setApplicationAttribute("usuario", usu);
				Faces.setApplicationAttribute("cliente", usu.getCustomer());
				Messages.addFlashGlobalInfo("BIENVENIDO " + user.getUserName());
				return "/paginas/Segura/ResumenProductos.xhtml?faces-redirect=true";
				
				
			} else {
				Messages.addFlashGlobalError("EL ususario o contraseña Incorrecta");
			}
		} else {
			Messages.addFlashGlobalError("EL ususario o contraseña Incorrecta");
		}
		return null;
	}

	public String cerrarSesion() {
		user = null;
		HttpSession sesion;
		sesion = (HttpSession) Faces.getSession();
		sesion.invalidate();
		return "/paginas/General/LogIn.xhtml?faces-redirect=true";

	}
	
	public boolean isLogIn(){
		return user != null;
	}
	
	public boolean isLogOut(){
		if(user == null){
			return true;
		}
		return false;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * @return the user
	 */
	public Usuario getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(Usuario user) {
		this.user = user;
	}

	

}
