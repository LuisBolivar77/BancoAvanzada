package co.edu.eam.ingesoft.pa.standalone.controlador;

import java.util.List;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Franchise;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICustomerRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IFranchiseRemote;
import co.edu.eam.ingesoft.pa.standalone.ServiceLocator;

public class ControladorCustomer {

	private ICustomerRemote clienteRemoto;
	private IFranchiseRemote franquisiaRemota;

	/**
	 * Constructor
	 * 
	 * @throws NamingException
	 */
	public ControladorCustomer() throws NamingException {
		clienteRemoto = (ICustomerRemote) ServiceLocator.buscarEJB("CustomerEJB",
				ICustomerRemote.class.getCanonicalName());
		franquisiaRemota = (IFranchiseRemote) ServiceLocator.buscarEJB("FranchiseEJB",
				IFranchiseRemote.class.getCanonicalName());
	}

	/**
	 * busca un cliente
	 * 
	 * @param id
	 *            del cliente a buscar
	 * @return el cliente que busca
	 */
	public Customer buscarCustomer(String id, String idType) {
		return clienteRemoto.buscarCliente(id, idType);
		
	}

	/**
	 * 1 crea un cliente
	 * 
	 * @param cliente
	 *            que se va a crear
	 */
	public void crearCustomer(Customer c) {
		clienteRemoto.crearCliente(c);

	}

	/**
	 * 
	 * @return
	 */
	public List<Franchise> listaFranchise1() {
		return franquisiaRemota.listaFranchise();
	}
	
	/**
	 * 
	 * @param u
	 */
	public void crearUsuario(Usuario u){
		clienteRemoto.crearUsuario(u);
	}

}
