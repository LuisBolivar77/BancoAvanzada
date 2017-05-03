package co.edu.eam.ingesoft.pa.banco.web.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.DTO.AsociarCuentaDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.RecibirDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.TransferirDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.VerificarDTO;
import co.edu.eam.ingesoft.pa.negocio.beans.BankEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CodigoValidacionEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.WebServicesEJB;

//Para invocar un servicio se necesita
/**
 * 
 * @author Didier_Narv�ez 1. la url del servicio: http://ip:puerto/<root>/
 *         <raizRest>/<pathclase>/<pathmetodo>
 */
// <pathclase>=Cliente
@Path("/serviciosRest")
public class ServiciosBancoRest {

	@EJB
	private ProductoEJB productoEJB;

	@EJB
	private WebServicesEJB webServicesEJB;

	@EJB
	private CuentaAsociadaEJB cuentaAsociadaEJB;

	@EJB
	private CustomerEJB customerEJB;

	@EJB
	private CodigoValidacionEJB codigoEJB;

	@EJB
	private BankEJB bancoEJB;

	@Path("/verificar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public String verificar(VerificarDTO verificarDTO) {
		boolean res = cuentaAsociadaEJB.verificarCuenta(verificarDTO.getNumCuenta(), verificarDTO.getCedula(),
				verificarDTO.getTipoCed());
		if (res == true) {
			return "OK";
		}
		return "ERROR";

	}

	@Path("/recibir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public String transferir(RecibirDTO recibirDTO) {

		Product pro = productoEJB.buscarProducto(recibirDTO.getNumCuenta());
		if (pro != null) {
			productoEJB.sumarMontoCuenta(recibirDTO.getNumCuenta(), recibirDTO.getMonto());
			return "OK";
		}
		return "ERROR";
	}
	
	@Path("/tranferir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public String transferir(TransferirDTO transferirDTO) {
		
		CuentaAsociada cuenta = cuentaAsociadaEJB.buscarCuentaAso(transferirDTO.getIdCuentaAso());
		boolean resp = webServicesEJB.transferirDinero(cuenta, transferirDTO.getMonto());
		if(resp == true ){
			return "OK";
		}
		return "ERROR";
	}

	@Path("/listarCuentasAsociadasVeriCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	public List<CuentaAsociada> listarCuentasAsociadasVeri(@QueryParam("id") String id,
			@QueryParam("tipoId") String tipoId) {

		List<CuentaAsociada> cuentas = new ArrayList<CuentaAsociada>();
		String tipoDoc = cuentaAsociadaEJB.casteoDocumentoSer(tipoId);
		Customer cus = customerEJB.buscarCliente(id, tipoDoc);
		if (cus != null) {
			cuentas = cuentaAsociadaEJB.listacuentasAsociadasVerificadas(cus);
		}

		return cuentas;
	}

	@Path("/listarCuentasAsociadasCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	public List<CuentaAsociada> listarCuentasAsociadas(@QueryParam("id") String id,
			@QueryParam("tipoId") String tipoId) {

		List<CuentaAsociada> cuentas = new ArrayList<CuentaAsociada>();
		String tipoDoc = cuentaAsociadaEJB.casteoDocumentoSer(tipoId);
		Customer cus = customerEJB.buscarCliente(id, tipoDoc);
		if (cus != null) {
			cuentas = cuentaAsociadaEJB.listacuentasAsociadas(cus);
		}

		return cuentas;
	}

	@Path("/listarCuentasCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	public List<SavingAccount> listarCuentasCliente(@QueryParam("id") String cedula,
			@QueryParam("tipoId") String tipoId) {

		List<SavingAccount> cuentas = new ArrayList<SavingAccount>();
		String tipoDoc = cuentaAsociadaEJB.casteoDocumentoSer(tipoId);

		Customer cus = customerEJB.buscarCliente(cedula, tipoDoc);
		cuentas = productoEJB.listaCuentasCliente(cus);
		return cuentas;

	}

	@Path("/enviarCodigoVerificacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	public boolean generarEnviarCodigoVali(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		String tipoDoc = cuentaAsociadaEJB.casteoDocumentoSer(tipoId);
		Customer cus = customerEJB.buscarCliente(cedula, tipoDoc);
		String codigo = codigoEJB.numeroCodigoValidacion();

		if (cus != null) {
			codigoEJB.enviarSms(codigo, cus.getTelefono());
			return true;
		}
		return false;

	}

	@Path("/listarBancos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bank> listarBancos() {
		return webServicesEJB.listarBancos();
	}

	@Path("/asociar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public String asociarCuenta(AsociarCuentaDTO cuentaAsociar) {
		CuentaAsociada cuenta = new CuentaAsociada();
		Bank banco = bancoEJB.buscar(cuentaAsociar.getIdBanco());
		Customer cliente = customerEJB.buscarCliente(cuentaAsociar.getIdCliente(), cuentaAsociar.getTipoId());
		if (cliente != null) {
			cuenta.setNombreBanco(banco);
			cuenta.setCustomer(cliente);
			cuenta.setNombreCuenta(cuentaAsociar.getNombreCuenta());
			cuenta.setNombreTitular(cuentaAsociar.getNombreTitular());
			cuenta.setNumDocumento(cuentaAsociar.getNumDocumento());
			cuenta.setTipoDocumento(cuentaAsociar.getTipoDocumento());
			cuentaAsociadaEJB.crearCuentaAsociada(cuenta);
			return "OK";
		} else {
			return "ERROR";
		}
	}

}
