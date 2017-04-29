package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;

@LocalBean
@Stateless
public class CuentaAsociadaEJB {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private ProductoEJB productoEJB;

	/**
	 * 
	 * @param cuentaAso
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCuentaAsociada(CuentaAsociada cuentaAso) {
		em.persist(cuentaAso);
	}

	/**
	 * M�todo para buscar un producto
	 * 
	 * @param num
	 *            N�mero del producto que se desea buscar
	 * @return EL producto si lo encuentra, de lo contrario null
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CuentaAsociada buscarCuentaAso(int num) {
		return em.find(CuentaAsociada.class, num);
	}

	/**
	 * 
	 * @param nom
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Bank buscarBanco(String id) {
		return em.find(Bank.class, id);
	}

	/**
	 * 
	 * @param ca
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarCuenta(CuentaAsociada ca) {
		em.remove(em.contains(ca) ? ca : em.merge(ca));
	}

	/**
	 * 
	 * @param cu
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarCuentaAsociadda(CuentaAsociada cu) {
		em.merge(cu);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CuentaAsociada> listacuentasAsociadas(Customer c) {
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
	public List<CuentaAsociada> listacuentasAsociadasVerificadas(Customer c) {
		Query q = em.createNamedQuery(CuentaAsociada.LISTAR_CUENTAS_ASOCIADAS);
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
	public List<Bank> listaBancos() {
		Query q = em.createNamedQuery(Bank.NOMBRE_CUENTAS);
		List<Bank> lista = q.getResultList();
		return lista;
	}

	/**
	 * Verifica una cuenta de un cliente
	 * 
	 * @param num
	 *            N�mero de la cuenta
	 * @param id
	 *            N�mero de identificaci�nn
	 * @param tipoId
	 *            Tipo de identificaci�n
	 * @return true si la cuenta es del cliente, de lo contrario false
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean verificarCuenta(String num, String id, String tipoId) {
		SavingAccount sa = (SavingAccount) productoEJB.buscarProducto(num);
		if (sa != null) {
			String tipoDoc = casteoDocumento(sa.getCustomer().getIdType());
			if (sa.getCustomer().getIdNum().equals(id) && tipoDoc.equals(tipoId)) {
				return true;
			}
		}
		return false;
	}

	public String casteoDocumento(String doc) {

		String tipoDoc = null;
		if (doc.equals("Cedula")) {
			tipoDoc = "1";
		}
		if (doc.equals("Pasaporte")) {
			tipoDoc = "3";
		}
		if (doc.equals("Tarjeta de Identidad")) {
			tipoDoc = "2";
		}

		return tipoDoc;
	}
	
	public String casteoDocumentoSer(String doc) {

		String tipoDoc = null;
		if (doc.equals("1")) {
			tipoDoc = "Cedula";
		}
		if (doc.equals("3")) {
			tipoDoc = "Pasaporte";
		}
		if (doc.equals("2")) {
			tipoDoc = "Tarjeta de Identidad";
		}

		return tipoDoc;
	}
}
