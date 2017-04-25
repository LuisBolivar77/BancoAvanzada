package co.edu.eam.ingesoft.pa.banco.web.servicios;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.TransaccionEJB;

@Path("/transferencias")
public class TransferenciaRest {

	@EJB
	private ProductoEJB productoEJB;

	@Path("/transferir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	public String transferir(@FormParam("cuenta") String numeroCuenta, @FormParam("cantidad") double cantidad) {
		SavingAccount cuenta = (SavingAccount) productoEJB.buscarProducto(numeroCuenta);
		if (cuenta != null) {
			productoEJB.transferenciaWeb(cuenta, cantidad);
			return "OK";
		}
		return "ERROR";
	}

}
