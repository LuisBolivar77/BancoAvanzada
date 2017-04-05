package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Transaction;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ITransaccionRemote;

@LocalBean
@Stateless
@Remote(ITransaccionRemote.class)
public class TransaccionEJB {

	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * M�todo para agregar una tarjeta de cr�dito
	 * 
	 * @param cc
	 *            Tarjeta de cr�dito que se desea agregar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void agregarTransaccion(Transaction t) {
		em.persist(t);

	}

}
