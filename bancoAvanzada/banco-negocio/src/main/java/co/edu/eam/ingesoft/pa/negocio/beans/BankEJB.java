package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardConsume;

@LocalBean
@Stateless
public class BankEJB {
	
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	/**
	 * Metodo para agregar una tarjeta de crdito
	 * 
	 * @param cc
	 *            Tarjeta de credito que se desea agregar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void agregarBanco(Bank b) {
		em.persist(b);
	}
	
	/**
	 * Busca un banco
	 * @param cod el código del banco
	 * @return el banco si lo encuentra, de lo contrario null
	 */
	public Bank buscar(String cod){
		return em.find(Bank.class, cod);
	}

}
