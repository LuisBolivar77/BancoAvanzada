package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
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
import co.edu.eam.ingesoft.pa.negocio.exception.ExcepcionNegocio;
import co.edu.eam.pa.serviciosinterbancariosws.Banco;
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

	@EJB
	private ProductoEJB productoEJB;

	@EJB
	private BankEJB bancoEJB;

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

		RespuestaServicio resp = service.registrarCuentaAsociada(cuenta.getNombreBanco().getId(), tipoDoc,
				cuenta.getNumDocumento(), cuenta.getNombreCuenta(), cuenta.getNumeroCuenta());

		if (resp.getCodigo().equals("0000") || resp.getCodigo().equals("0001")) {
			cuenta.setEstado(resp.getMensaje());
			cuentaAsoEJB.editarCuentaAsociadda(cuenta);
		} else {
			cuentaAsoEJB.eliminarCuenta(cuenta);

		}

	}


	/**
	 * Obtiene la lista de bancos registrados
	 * @return la lista de los bancos
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Bank> listarBancos() {

		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS service = cliente.getInterbancarioWSPort();

		String endPointURL = "http://104.155.128.249:8080/interbancario/InterbancarioWS/InterbancarioWS";
		BindingProvider bp = (BindingProvider) service;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointURL);

		

		List<Banco> lista = service.listarBancos();
		List<Bank> bancos = new ArrayList<Bank>();

		for (Banco banco : lista) {
			Bank b = new Bank();
			b.setId(banco.getCodigo());
			b.setNombre(banco.getNombre());
			bancos.add(b);
			bancoEJB.agregarBanco(b);
		}
		

		return bancos;

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean transferirDinero(CuentaAsociada cuenta, double monto){
		
		InterbancarioWS_Service cliente = new InterbancarioWS_Service();
		InterbancarioWS service = cliente.getInterbancarioWSPort();
	

		String endPointURL = "http://104.155.128.249:8080/interbancario/InterbancarioWS/InterbancarioWS";
		BindingProvider bp = (BindingProvider) service;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointURL);
		
		RespuestaServicio resp = service.transferirMonto(cuenta.getNombreBanco().getId(), cuenta.getNumeroCuenta(), monto);
		System.out.println("mensajeeeeeeee = " + resp.getMensaje());
		System.out.println("numero cuentaaaaaaa = " + cuenta.getNumeroCuenta());
		if(resp.getCodigo().equals("0000")){
			return true;
		}
		
		return false;
	}

}
