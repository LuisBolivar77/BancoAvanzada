package co.edu.eam.ingesoft.pa.banco.web.servicios;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.WebServicesEJB;

//Para invocar un servicio se necesita
/**
 * 
 * @author Didier_Narv�ez 1. la url del servicio:
 *         http://ip:puerto/<root>/<raizRest>/<pathclase>/<pathmetodo>
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
		// SavingAccount cuenta = (SavingAccount)
		// productoEJB.buscarProducto(numeroCuenta);
		// if (cuenta != null) {
		boolean validado = webServicesEJB.recibirDineroWS("77", numeroCuenta, cantidad);
		if (validado) {
			return "OK";
		}
		// }
		return "ERROR";
	}

}
