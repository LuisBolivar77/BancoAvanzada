package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Bank;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CodigoValidacion;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CodigoValidacionEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.TransaccionEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.WebServicesEJB;
import co.edu.eam.ingesoft.pa.negocio.exception.ExcepcionNegocio;

@Named("controladorProductos")
@SessionScoped
public class ControladorProductos implements Serializable {

	/**
	 * 
	 */
	private double valor;

	/**
	 * 
	 */
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
	private List<Bank> bancos;

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
	private int cuentaAsociadaSelec;

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
	 */
	@EJB
	private CodigoValidacionEJB codigoEJB;

	/**
	 * 
	 */
	@EJB
	private WebServicesEJB webServiceEJB;

	@EJB
	private TransaccionEJB transaccionEJB;

	/**
	 * 
	 * @param u
	 */
	public void listaCuentasAsociadas(Customer u) {
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
		listaCuentasAsociadas(cliente.getCustomer());
		cargarcuentas(cliente.getCustomer());
		listarTarjetas(cliente.getCustomer());

	}

	/**
	 * 
	 */
	public void crearCuentaAsociada() {

		String id = bancoSeleccionado;
		Bank banco = cuentaAsoEJB.buscarBanco(id);

		CuentaAsociada cuentaAso = new CuentaAsociada();
		cuentaAso.setEstado("PENDIENTE");
		cuentaAso.setNombreBanco(banco);
		cuentaAso.setNombreCuenta(nombreCuenta);
		cuentaAso.setNombreTitular(nombreTitular);
		cuentaAso.setNumDocumento(identificacion);
		cuentaAso.setNumeroCuenta(numeroCuenta);
		cuentaAso.setTipoDocumento(tipoSeleccionado);
		System.out.println("el clienteeeeeeeeeeeeeee = " + cliente.getUserName());
		cuentaAso.setCustomer(cliente.getCustomer());

		cuentaAsoEJB.crearCuentaAsociada(cuentaAso);

		listaCuentasAsociadas(cliente.getCustomer());
		Messages.addFlashGlobalInfo(" La cuenta se ha registrado correctamente ");

	}

	public String cancelar() {
		return "/paginas/Segura/ResumenProductos.xhtml?faces-redirect=true";
	}

	public void borrarCuenta(CuentaAsociada cuentaAso) {
		cuentaAsoEJB.eliminarCuenta(cuentaAso);
		listaCuentasAsociadas(cliente.getCustomer());

	}

	/**
	 * 
	 */
	public void enviarCodigoValidacion() {

		Date fecha = new Date();
		fecha.setMinutes(fecha.getMinutes() + 2);

		codigoVali = codigoEJB.numeroCodigoValidacion();
		CodigoValidacion codigo = new CodigoValidacion();
		codigo.setCodigo(codigoVali);
		codigo.setFecha(fecha);
		codigo.setUsuario(cliente);

		codigoEJB.crearCodigoValidacion(codigo);
		codigoEJB.enviarEmail(codigoVali, cliente.getCustomer().getEmail());
		// codigoEJB.enviarSms(codigoVali, cliente.getCustomer().getTelefono());

		System.out.println("hora de creaciooooooooooooon = " + fecha);

	}

	public void transferencia() {
		String numCuenta = CuentaComboSelecionada;
		int id = cuentaAsociadaSelec;
		CuentaAsociada cuenta = cuentaAsoEJB.buscarCuentaAso(id);
		if (codigovalidacion != null){
			try {
				boolean resp = webServiceEJB.transferirDinero(cuenta, valor);
				if (resp == true) {
					transaccionEJB.transferir(codigoVali, numCuenta, codigovalidacion, valor);
					Messages.addGlobalInfo("La transferencia se ha realizado exitosamente");
				}
			} catch (ExcepcionNegocio e) {
				e.getMessage();
			}
		} else {
			Messages.addGlobalInfo("Es obligatorio ingresar el codigo de validacion ");
		}
	}

	public void verificarCuenta(CuentaAsociada cuenta) {
		webServiceEJB.VerificarCuenta(cuenta);
		listaCuentasAsociadas(cliente.getCustomer());

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
	public List<Bank> getBancos() {
		return bancos;
	}

	/**
	 * @param bancos
	 *            the bancos to set
	 */
	public void setBancos(List<Bank> bancos) {
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
	public int getCuentaAsociadaSelec() {
		return cuentaAsociadaSelec;
	}

	/**
	 * @param cuentaAsociadaSelec
	 *            the cuentaAsociadaSelec to set
	 */
	public void setCuentaAsociadaSelec(int cuentaAsociadaSelec) {
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
