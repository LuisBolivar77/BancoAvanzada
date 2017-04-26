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

	@Path("/verificar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	public boolean verificar(@FormParam("cuenta") String cuenta, @FormParam("id") String cedula,
			@FormParam("tipoId") String tipoId) {
		return cuentaAsociadaEJB.verificarCuenta(cuenta, cedula, tipoId);
	}

	@Path("/transferir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	public String transferir(@FormParam("cuenta") String numeroCuenta, @FormParam("cantidad") double cantidad) {

		Product pro = productoEJB.buscarProducto(numeroCuenta);
		if (pro != null) {
			productoEJB.sumarMontoCuenta(numeroCuenta, cantidad);
			return "OK";
		}
		return "ERROR";
	}

	@Path("/listarCuentasAsociadasVeriCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	public List<CuentaAsociada> listarCuentasAsociadas(@QueryParam("id") String id,
			@QueryParam("tipoId") String tipoId) {

		List<CuentaAsociada> cuentas = new ArrayList<CuentaAsociada>();
		String tipoDoc = cuentaAsociadaEJB.casteoDocumentoSer(tipoId);
		Customer cus = customerEJB.buscarCliente(tipoId, tipoDoc);
		if (cus != null) {
			cuentas = cuentaAsociadaEJB.listacuentasAsociadasVerificadas(cus);
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

}
