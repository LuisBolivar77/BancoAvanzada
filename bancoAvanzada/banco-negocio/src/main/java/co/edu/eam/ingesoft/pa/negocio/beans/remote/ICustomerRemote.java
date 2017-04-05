package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;

/**
 * Interface remota para acceder a las operaciones del EJB
 * @author LuchoBolivar
 *
 */
public interface ICustomerRemote {

	/**
	 * 
	 * @param c
	 */
	public void crearCliente(Customer c);
	
	/**
	 * 
	 * @param id
	 * @param idType
	 * @return
	 */
	public Customer buscarCliente (String id, String idType);
	
	/**
	 * 
	 * @param u
	 */
	public void crearUsuario(Usuario u);
	
	
}
