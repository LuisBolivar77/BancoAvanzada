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

		String endPointURL = "http://104.155.128.249:8080/interbancario/InterbancarioWS/InterbancarioWS";
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

		RespuestaServicio resp = service.registrarCuentaAsociada(cuenta.getNombreBanco().getId(), TipoDocumentoEnum.CC,
				cuenta.getNumDocumento(), cuenta.getNombreCuenta(), cuenta.getNumeroCuenta());

		if(resp.getMensaje().equals("0003")){
			cuentaAsoEJB.eliminarCuenta(cuenta);
		}
		
		if(resp.getMensaje().equals("0010")){
			cuentaAsoEJB.eliminarCuenta(cuenta);
		}

		cuenta.setEstado(resp.getMensaje());
		cuentaAsoEJB.editarCuentaAsociadda(cuenta);

	}

}
