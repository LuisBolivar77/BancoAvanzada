package co.edu.eam.ingesoft.pa.negocio.beans;

import java.awt.Window.Type;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CustomerPK;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICustomerRemote;
import co.edu.eam.ingesoft.pa.negocio.exception.ExcepcionNegocio;



@LocalBean
@Stateless
@Remote(ICustomerRemote.class)
public class CustomerEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Busca un cliente en la base de datos
	 * @param id Identificacion del cliente
	 * @return el cliente si lo encuentra, de lo contrario null
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Customer buscarCliente (String id, String idType){
		CustomerPK pk = new CustomerPK();
		pk.setIdNum(id);
		pk.setIdType(idType);
		return em.find(Customer.class, pk);
	}
	
	/**
	 * Registra un cliente
	 * @param c Cliente que se desea registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCliente(Customer u){
		Customer cli = buscarCliente(u.getIdNum(), u.getIdType());
		if (cli == null){
			em.persist(u);
		} else {
			throw new ExcepcionNegocio("El cliente que desea registrar ya se encuentra registrado");
		}
	}
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public Usuario buscarUsuarioLogIn(String user) {
		List<Usuario>us= em.createNamedQuery(Usuario.LISTA_CUSTOMERS_NOMUS).setParameter(1, user).getResultList();
		if(us.isEmpty()){
			return null;
		}else{
			return us.get(0);
		}
	}
	
	/**
	 * 
	 * @param u
	 */
	public void crearUsuario(Usuario u){
		Usuario us = buscarUsuarioLogIn(u.getUserName());
		if(us == null){
			em.persist(u);
		}else{
			throw new ExcepcionNegocio(" Este nombre de usuario ya existe");
		}
		
		
	}
	
	
	/**
	 * 
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Customer> listaCustomer (){
		Query q = em.createNamedQuery(Customer.LISTA_CUSTOMERS);
		List<Customer> clientes = q.getResultList();
		return clientes;
	}
	
}
