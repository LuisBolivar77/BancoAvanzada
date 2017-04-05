package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Franchise;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ICustomerRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IFranchiseRemote;

@LocalBean
@Stateless
@Remote(IFranchiseRemote.class)
public class FranchiseEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Franchise> listaFranchise(){
		Query q = em.createNamedQuery(Franchise.LISTAR_FRANQUICIA);
		List<Franchise> lista = q.getResultList();
		return lista;
	}

}
