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

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CuentaAsociada;
import co.edu.eam.pa.serviciosinterbancariosws.InterbancarioWS;
import co.edu.eam.pa.serviciosinterbancariosws.InterbancarioWS_Service;
import co.edu.eam.pa.serviciosinterbancariosws.RespuestaServicio;
import co.edu.eam.pa.serviciosinterbancariosws.TipoDocumentoEnum;

@LocalBean
@Stateless
public class WebServicesEJB {

	@PersistenceContext
	private EntityManager em;

	private TipoDocumentoEnum tipoDoc;

	@EJB
	private CuentaAsociadaEJB cuentaAsoEJB;

	/**
	 * 
	 * @param nombre
	 * @param numCuenta
	 * @param tipoDocumento
	 * @param numDocumento
	 * @param banco
	 * @param nombreCuenta
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void VerificarCuenta(CuentaAsociada cuenta) {

		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS service = cliente.getInterbancarioWSPort();

		String endPointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider) service;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointURL);

		if (cuenta.getTipoDocumento().equals(TipoDocumentoEnum.CC)) {
			tipoDoc = TipoDocumentoEnum.CC;
		}
		if (cuenta.getTipoDocumento().equals(TipoDocumentoEnum.CE)) {
			tipoDoc = TipoDocumentoEnum.CE;
		}
		if (cuenta.getTipoDocumento().equals(TipoDocumentoEnum.PAS)) {
			tipoDoc = TipoDocumentoEnum.PAS;
		}
		if (cuenta.getTipoDocumento().equals(TipoDocumentoEnum.TI)) {
			tipoDoc = TipoDocumentoEnum.TI;
		}

		RespuestaServicio resp = service.registrarCuentaAsociada(cuenta.getNombreBanco().getId(), tipoDoc,
				cuenta.getNumDocumento(), cuenta.getNombreCuenta(), cuenta.getNumeroCuenta());

		System.out.println("Mensajeeeeeeeeeeee = " + resp.getMensaje());
		cuenta.setEstado(resp.getMensaje());
		cuentaAsoEJB.editarCuentaAsociadda(cuenta);

	}

	/**
	 * Verifica si una cuenta esta asociada a un usuario
	 * @param num Número de la cuenta
	 * @param ced Nuúmero de identificación del usuario
	 * @param tipoId Tipo de identificación del usuario
	 * @return true, si la cuenta pertenece a este usuario, de lo contrario false
	 */
	public boolean buscar(String num, String ced, String tipoId) {
		Query q = em.createNamedQuery(CuentaAsociada.LISTAR_CUENTAS_ASOCIADAS);
		List<CuentaAsociada> lista = q.getResultList();
		for (CuentaAsociada cuenta : lista) {
			if (cuenta.getNumeroCuenta().equals(num) && cuenta.getNumDocumento().equals(ced)
					&& cuenta.getTipoDocumento().equals(tipoId)) {
				return true;
			}
		}
		return false;
	}

}
