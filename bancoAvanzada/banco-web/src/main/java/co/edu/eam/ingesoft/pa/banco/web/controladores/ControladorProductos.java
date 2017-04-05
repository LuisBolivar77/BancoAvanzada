package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.List;
import javax.xml.ws.BindingProvider;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Banco;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.pa.negocio.exception.ExcepcionNegocio;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;
import co.edu.eam.pa.clientews.Sms;

@Named("controladorProductos")
@SessionScoped
public class ControladorProductos implements Serializable {

	/**
	 * 
	 */
	private double valor;

	private String codigovalidacion;

	/**
	 * 
	 */
	private String identificacion;

	/**
	 * 
	 */
	private String tipoSeleccionado;

	/**
	 * 
	 */
	private String nombreTitular;

	/**
	 * 
	 */
	private String bancoSeleccionado;

	/**
	 * 
	 */
	private List<Banco> bancos;

	/**
	 * 
	 */
	private String numeroCuenta;

	/**
	 * 
	 */
	private String nombreCuenta;

	/**
	 * 
	 */
	private String cuentaAsociadaSelec;

	/**
	 * 
	 */
	private List<CuentaAsociada> cuentasAsociadas;

	/**
	 * 
	 */
	private List<Product> cuentas;

	/**
	 * 
	 */
	private List<SavingAccount> cuentasCombo;

	/**
	 * 
	 */
	private String CuentaComboSelecionada;

	/**
	 * 
	 */
	private List<CreditCard> tarjetas;

	/**
	 * 
	 */
	private Usuario cliente;
	
	/**
	 * 
	 */
	private String codigoVali;

	/**
	 * 
	 */
	@EJB
	private ProductoEJB productoEJB;

	/**
	 * 
	 */
	@EJB
	private CuentaAsociadaEJB cuentaAsoEJB;

	/**
	 * 
	 * @param u
	 */
	public void listaCuentasAsociadas(Usuario u) {
		cuentasAsociadas = cuentaAsoEJB.listacuentasAsociadas(u);
	}

	/**
	 * 
	 */
	public void listaBancos() {
		bancos = cuentaAsoEJB.listaBancos();
	}

	/**
	 * 
	 * @param c
	 */
	public void listarCuentasInfo(Customer c) {
		cuentas = productoEJB.listaCuentas(c);
	}

	/**
	 * Lista de cuentas de un cliente
	 */
	public void cargarcuentas(Customer c) {
		cuentasCombo = productoEJB.listaCuentasCliente(c);
	}

	/**
	 * 
	 * @param c
	 */
	public void listarTarjetas(Customer c) {
		tarjetas = productoEJB.listaTarjetas(c);
	}

	/**
	 * 
	 * @param c
	 */
	private void listarTarjetasInfo(Customer c) {
		List<CreditCardConsume> listaCon = productoEJB.listaConsumosCliente(c);
		if (listaCon.isEmpty()) {
		}
	}

	@PostConstruct
	public void listarProductos() {
		cliente = Faces.getApplicationAttribute("usuario");
		listarCuentasInfo(cliente.getCustomer());
		listaBancos();
		listaCuentasAsociadas(cliente);
		cargarcuentas(cliente.getCustomer());
		listarTarjetas(cliente.getCustomer());

	}

	/**
	 * 
	 */
	public void crearCuentaAsociada() {

		String nombre = bancoSeleccionado;
		Banco banco = cuentaAsoEJB.buscarBanco(nombre);

		CuentaAsociada cuentaAso = new CuentaAsociada();
		cuentaAso.setEstado(true);
		cuentaAso.setNombreBanco(banco);
		cuentaAso.setNombreCuenta(nombreCuenta);
		cuentaAso.setNombreTitular(nombreTitular);
		cuentaAso.setNumDocumento(identificacion);
		cuentaAso.setNumeroCuenta(numeroCuenta);
		cuentaAso.setTipoDocumento(tipoSeleccionado);
		cuentaAso.setUsuario(cliente);

		cuentaAsoEJB.crearCuentaAsociada(cuentaAso);
		listaCuentasAsociadas(cliente);
		nombreCuenta = "";
		nombreTitular = "";
		identificacion = "";
		numeroCuenta = "";
		Messages.addFlashGlobalInfo(" La cuenta se ha registrado correctamente ");

	}

	public String cancelar() {
		return "/paginas/Segura/ResumenProductos.xhtml?faces-redirect=true";
	}

	public void borrarCuenta(CuentaAsociada cuentaAso) {
		cuentaAsoEJB.eliminarCuenta(cuentaAso);
		listaCuentasAsociadas(cliente);

	}
	
	/**
	 * 
	 */
	public void enviarCodigoValidacion(){
		codigoVali = productoEJB.numeroCodigoValidacion();
		NotificacionesService cliente = new NotificacionesService();
		Notificaciones servicio = cliente.getNotificacionesPort();
		
		String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
		
		Sms mensaje = new Sms();
		mensaje.setTexto(codigoVali);
		mensaje.setTo("3007121419");
		
		servicio.enviarSMS(mensaje);
		Messages.addFlashGlobalInfo(" Mensaje enviado con exito ");
		
	}

	public void transferencia() {
		String numCuenta = CuentaComboSelecionada;
		String nomCuentaAso = cuentaAsociadaSelec;

		Product proCuenta = productoEJB.buscarProducto(numCuenta);
		SavingAccount cuenta = (SavingAccount) proCuenta;
		try {
			if (codigovalidacion != null) {
				if (codigovalidacion.equals(codigoVali)) {
					productoEJB.transferenciaWeb(cuenta, valor);
					Messages.addFlashGlobalInfo(" La transferencia se ha realizado con exito ");
				} else {
					Messages.addFlashGlobalInfo(" El codigo de validacion ingresado no es el correcto ");
				}
			} else {
				Messages.addFlashGlobalInfo(" Es obligatorio ingresar el codigo de validacion ");
			}
		} catch (ExcepcionNegocio e) {
			e.getMessage();
		}
	}

	/**
	 * @return the cuentas
	 */
	public List<Product> getCuentas() {
		return cuentas;
	}

	/**
	 * @param cuentas
	 *            the cuentas to set
	 */
	public void setCuentas(List<Product> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * @return the cliente
	 */
	public Usuario getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the tarjetas
	 */
	public List<CreditCard> getTarjetas() {
		return tarjetas;
	}

	/**
	 * @param tarjetas
	 *            the tarjetas to set
	 */
	public void setTarjetas(List<CreditCard> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * @return the identificacion
	 */
	public String getIdentificacion() {
		return identificacion;
	}

	/**
	 * @param identificacion
	 *            the identificacion to set
	 */
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	/**
	 * @return the tipoSeleccionado
	 */
	public String getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	/**
	 * @param tipoSeleccionado
	 *            the tipoSeleccionado to set
	 */
	public void setTipoSeleccionado(String tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}

	/**
	 * @param nombreTitular
	 *            the nombreTitular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	/**
	 * @return the bancoSeleccionado
	 */
	public String getBancoSeleccionado() {
		return bancoSeleccionado;
	}

	/**
	 * @param bancoSeleccionado
	 *            the bancoSeleccionado to set
	 */
	public void setBancoSeleccionado(String bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}

	/**
	 * @return the bancos
	 */
	public List<Banco> getBancos() {
		return bancos;
	}

	/**
	 * @param bancos
	 *            the bancos to set
	 */
	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the nombreCuenta
	 */
	public String getNombreCuenta() {
		return nombreCuenta;
	}

	/**
	 * @param nombreCuenta
	 *            the nombreCuenta to set
	 */
	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	/**
	 * @return the cuentasAsociadas
	 */
	public List<CuentaAsociada> getCuentasAsociadas() {
		return cuentasAsociadas;
	}

	/**
	 * @param cuentasAsociadas
	 *            the cuentasAsociadas to set
	 */
	public void setCuentasAsociadas(List<CuentaAsociada> cuentasAsociadas) {
		this.cuentasAsociadas = cuentasAsociadas;
	}

	/**
	 * @return the cuentasCombo
	 */
	public List<SavingAccount> getCuentasCombo() {
		return cuentasCombo;
	}

	/**
	 * @param cuentasCombo
	 *            the cuentasCombo to set
	 */
	public void setCuentasCombo(List<SavingAccount> cuentasCombo) {
		this.cuentasCombo = cuentasCombo;
	}

	/**
	 * @return the cuentaComboSelecionada
	 */
	public String getCuentaComboSelecionada() {
		return CuentaComboSelecionada;
	}

	/**
	 * @param cuentaComboSelecionada
	 *            the cuentaComboSelecionada to set
	 */
	public void setCuentaComboSelecionada(String cuentaComboSelecionada) {
		CuentaComboSelecionada = cuentaComboSelecionada;
	}

	/**
	 * @return the cuentaAsociadaSelec
	 */
	public String getCuentaAsociadaSelec() {
		return cuentaAsociadaSelec;
	}

	/**
	 * @param cuentaAsociadaSelec
	 *            the cuentaAsociadaSelec to set
	 */
	public void setCuentaAsociadaSelec(String cuentaAsociadaSelec) {
		this.cuentaAsociadaSelec = cuentaAsociadaSelec;
	}

	/**
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * @return the codigovalidacion
	 */
	public String getCodigovalidacion() {
		return codigovalidacion;
	}

	/**
	 * @param codigovalidacion
	 *            the codigovalidacion to set
	 */
	public void setCodigovalidacion(String codigovalidacion) {
		this.codigovalidacion = codigovalidacion;
	}

}
