package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Banco;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;

@LocalBean
@Stateless
public class CuentaAsociadaEJB {

	@PersistenceContext
	private EntityManager em;
	
	/**
	 * h
	 * @param cuentaAso
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCuentaAsociada(CuentaAsociada cuentaAso){
		em.persist(cuentaAso);
	}
	
	/**
	 * 
	 * @param nom
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Banco buscarBanco(String nom){
		return em.find(Banco.class, nom);
	}
	
	
	/**
	 * 
	 * @param ca
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarCuenta(CuentaAsociada ca){
		em.remove(em.contains(ca) ? ca : em.merge(ca));
	}
	
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CuentaAsociada> listacuentasAsociadas(Usuario c){
		Query q = em.createNamedQuery(CuentaAsociada.CUENTAS_ASOCIADAS_USUARIO);
		q.setParameter(1, c);
		List<CuentaAsociada> lista = q.getResultList();
		return lista;
	}
	
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Banco> listaBancos(){
		Query q = em.createNamedQuery(Banco.NOMBRE_CUENTAS);
		List<Banco> lista = q.getResultList();
		return lista;
	}
	
	
}
