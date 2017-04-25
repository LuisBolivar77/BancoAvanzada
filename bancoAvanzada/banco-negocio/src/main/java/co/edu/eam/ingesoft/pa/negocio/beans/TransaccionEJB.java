package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CodigoValidacion;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Transaction;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ITransaccionRemote;
import co.edu.eam.ingesoft.pa.negocio.exception.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(ITransaccionRemote.class)
public class TransaccionEJB {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private CodigoValidacionEJB codigoEJB;

	@EJB
	private ProductoEJB productoEJB;

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

	public void transferir(String codigoVali, String numCuenta, String codigovalidacion, double valor) {

		CodigoValidacion codigo = codigoEJB.buscarCodigoValidacion(codigoVali);
		Date fecha = new Date();

		Product proCuenta = productoEJB.buscarProducto(numCuenta);
		SavingAccount cuenta = (SavingAccount) proCuenta;

		if (codigovalidacion != null) {
			if (codigo.getFecha().before(fecha)) {
				if (codigovalidacion.equals(codigoVali)) {
					productoEJB.transferenciaWeb(cuenta, valor);
				} else {
					throw new ExcepcionNegocio("El codigo de validacion ingresado no es el correcto");
				}
			} else {
				throw new ExcepcionNegocio("El codigo que ingrese a expirado, debes solicitar uno nuevo");
			}
		} else {
			throw new ExcepcionNegocio("Es obligatorio ingresar el codigo de validacion ");
		}

	}

}
