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
import co.edu.eam.ingesoft.pa.banco.web.DTO.RespuestaDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.AsociarCuentaDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.CuentaAsoDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.CuentasDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.RecibirDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.RegistroDTO;
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
			productoEJB.sumarMontoCuenta(recibirDTO.getNumCuenta(), recibirDTO.getMonto(), pro.getCustomer());
			return "OK";
		}
		return "ERROR";
	}

	@Path("/transferir")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public RespuestaDTO transferir(TransferirDTO transferirDTO) {

		CuentaAsociada cuenta = cuentaAsociadaEJB.buscarCuentaAso(transferirDTO.getIdCuentaAso());
		boolean resp = webServicesEJB.transferirDinero(cuenta, transferirDTO.getMonto());
		if (resp == true) {
			productoEJB.restarMontoCuenta(transferirDTO.getNumCuenta(), transferirDTO.getMonto());
			return new RespuestaDTO(true, "EXITO", "00");
		}
		return new RespuestaDTO(false, "la operación falló", "-1");
	}

	@Path("/listarCuentasAsociadasVeriCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	public RespuestaDTO listarCuentasAsociadasVeri(@QueryParam("id") String id, @QueryParam("tipoId") String tipoId) {

		List<CuentaAsociada> cuentas = new ArrayList<CuentaAsociada>();
		List<CuentaAsoDTO> cuentaDTO = new ArrayList<CuentaAsoDTO>();
		String tipoDoc = cuentaAsociadaEJB.casteoDocumentoSer(tipoId);
		Customer cus = customerEJB.buscarCliente(id, tipoDoc);
		if (cus != null) {
			cuentas = cuentaAsociadaEJB.listacuentasAsociadasVerificadas(cus);
			for (CuentaAsociada cuentaAsociada : cuentas) {
				CuentaAsoDTO cuent = new CuentaAsoDTO();
				cuent.setIdCuentaAso(cuentaAsociada.getId());
				cuent.setNombreCuenta(cuentaAsociada.getNombreCuenta());
				cuent.setNumCuenta(cuentaAsociada.getNumeroCuenta());

				cuentaDTO.add(cuent);
			}
		}

		return new RespuestaDTO(cuentaDTO, "la lista de Cuentas", "000");
	}

	@Path("/listarCuentasAsociadasCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	public RespuestaDTO listarCuentasAsociadas(@QueryParam("id") String id, @QueryParam("tipoId") String tipoId) {

		List<CuentaAsociada> cuentas = new ArrayList<CuentaAsociada>();
		List<CuentaAsoDTO> cuentaDTO = new ArrayList<CuentaAsoDTO>();

		String tipoDoc = cuentaAsociadaEJB.casteoDocumentoSer(tipoId);
		Customer cus = customerEJB.buscarCliente(id, tipoDoc);
		if (cus != null) {
			cuentas = cuentaAsociadaEJB.listacuentasAsociadas(cus);
			for (CuentaAsociada cuentaAsociada : cuentas) {
				CuentaAsoDTO cuent = new CuentaAsoDTO();
				cuent.setIdCuentaAso(cuentaAsociada.getId());
				cuent.setNombreCuenta(cuentaAsociada.getNombreCuenta());
				cuent.setNumCuenta(cuentaAsociada.getNumeroCuenta());

				cuentaDTO.add(cuent);
			}
		}

		return new RespuestaDTO(cuentaDTO, "Listado Cuentas", "00");
	}

	@Path("/listarCuentasCliente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	public RespuestaDTO listarCuentasCliente(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		List<SavingAccount> cuentas = new ArrayList<SavingAccount>();
		List<CuentasDTO> cuentasRes = new ArrayList<CuentasDTO>();
		String tipoDoc = cuentaAsociadaEJB.casteoDocumentoSer(tipoId);

		Customer cus = customerEJB.buscarCliente(cedula, tipoDoc);
		cuentas = productoEJB.listaCuentasCliente(cus);
		for (SavingAccount savingAccount : cuentas) {

			CuentasDTO cuenta = new CuentasDTO();
			cuenta.setNumCuenta(savingAccount.getNumber());
			cuenta.setNumId(savingAccount.getCustomer().getIdNum());
			cuenta.setTipoId(savingAccount.getCustomer().getIdType());

			cuentasRes.add(cuenta);
		}

		return new RespuestaDTO(cuentasRes, "listado Cuentas", "0");

	}

	@Path("/enviarCodigoVerificacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@GET
	public RespuestaDTO generarEnviarCodigoVali(@QueryParam("id") String cedula, @QueryParam("tipoId") String tipoId) {

		String tipoDoc = cuentaAsociadaEJB.casteoDocumentoSer(tipoId);
		Customer cus = customerEJB.buscarCliente(cedula, tipoDoc);
		String codigo = codigoEJB.numeroCodigoValidacion();

		if (cus != null) {
			codigoEJB.enviarSmsCodigoVal(codigo, cus.getTelefono());
			return new RespuestaDTO(codigo, "codigo generado", "0000");
		}
		return new RespuestaDTO(null, "Fallo", "-000");

	}

	@Path("/listarBancos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarBancos() {
		List<Bank> bancos = webServicesEJB.listarBancos();
		return new RespuestaDTO(bancos, "lista de bancos", "0");

	}

	@Path("/ ")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public RespuestaDTO asociarCuenta(AsociarCuentaDTO cuentaAsociar) {
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
			cuenta.setEstado("Asociada");
			cuenta.setNumeroCuenta(cuentaAsociar.getNumeroCuenta());
			cuentaAsociadaEJB.crearCuentaAsociada(cuenta);
			return new RespuestaDTO(cuenta);
		} else {
			return new RespuestaDTO(false, "El cliente no existe", "-1");
		}
	}

	@Path("/registrar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public RespuestaDTO registrarCliente(RegistroDTO registro) {

		Customer cus = customerEJB.buscarCliente(registro.getNumId(), registro.getTipoId());
		if (cus == null) {
			Customer cust = new Customer();
			cust.setName(registro.getNombre());
			cust.setLastName(registro.getApellido());
			cust.setIdNum(registro.getNumId());
			cust.setEmail(registro.getEmail());
			cust.setTelefono(registro.getTelefono());

			Usuario usu = new Usuario();
			usu.setCustomer(cust);
			usu.setUserName(registro.getUser());
			usu.setPassword(registro.getPass());

			customerEJB.crearCliente(cust);
			customerEJB.crearUsuario(usu);

			return new RespuestaDTO(cust);
		} else {
			return new RespuestaDTO(false, "El cliente que intenta registrar ya existe", "-2");
		}

	}

}
