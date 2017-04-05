package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.swing.JOptionPane;
import javax.validation.constraints.Pattern;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.omnifaces.util.Messages.Message;
import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardPaymentConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Transaction;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.CustomerEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.TransaccionEJB;
import co.edu.eam.ingesoft.pa.negocio.exception.ExcepcionNegocio;

@Named("controladorPagoTarjeta")
@ViewScoped
public class ControladorPagoTarjeta implements Serializable {

	/**
	 * 
	 */
	private double valorExtra;
	
	/**
	 * 
	 */
	private double valor;

	/**
	 * 
	 */
	private Usuario usuario;

	/**
	 * 
	 */
	private Customer cliente;

	/**
	 * 
	 */
	private String tarjetaSeleccionada;
	
	/**
	 * 
	 */
	private String tarjetaSelecAvance;
	
	/**
	 * 
	 */
	private String cuentaSelecAvance;

	/**
	 * 
	 */
	private String tarjetaSeleccionada2;

	/**
	 * 
	 */
	private List<CreditCard> tarjetas;

	/**
	 * 
	 */
	private List<SavingAccount> cuentas;

	/**
	 * 
	 */
	private List<CreditCardConsume> consumos;

	/**
	 * 
	 */
	private String cuentaSeleccionada;

	/**
	 * 
	 */
	@EJB
	private ProductoEJB productoEJB;

	/**
	 * 
	 */
	@EJB
	private CustomerEJB customerEJB;

	/**
	 * 
	 */
	@EJB
	private TransaccionEJB transaccionEJB;

	/**
	 * 
	 * @param c
	 */
	public void cargarListaConTajerta(CreditCard c) {
		consumos = productoEJB.listaConsumosCuotas(c);
	}

	/**
	 * Lista de cuentas de un cliente
	 */
	public void cargarcuentas(Customer c) {
		cuentas = productoEJB.listaCuentasCliente(c);
	}

	/**
	 * 
	 */
	public void cargarTarjeta(Customer c) {
		tarjetas = productoEJB.listaTarjetasCliente(c);
		for (int i = 0; i < tarjetas.size(); i++) {
			tarjetaSeleccionada2 = tarjetas.get(0).getNumber();
		}

	}

	/**
	 * 
	 */
	@PostConstruct
	public void buscar() {
		cliente = Faces.getApplicationAttribute("cliente");
		if (cliente != null) {
			cargarTarjeta(cliente);
			cargarcuentas(cliente);
			if (tarjetaSeleccionada2 != null) {
				if (tarjetaSeleccionada == null) {
					Product proT = productoEJB.buscarProducto(tarjetaSeleccionada2);
					CreditCard tar = (CreditCard) proT;
					cargarListaConTajerta(tar);
				} else {
					Product proT = productoEJB.buscarProducto(tarjetaSeleccionada);
					CreditCard tar = (CreditCard) proT;
					cargarListaConTajerta(tar);
				}
			}
		}

	}

	/**
	 * 
	 */
	public void pagar() {

		if (cliente != null) {

			Date fecha = new Date();
			double valorExtr = 0;
			String numTarjeta = tarjetaSeleccionada;
			String numCuenta = cuentaSeleccionada;

			Product proT = productoEJB.buscarProducto(numTarjeta);
			CreditCard tarjeta = (CreditCard) proT;

			Product proC = productoEJB.buscarProducto(numCuenta);
			SavingAccount cuenta = (SavingAccount) proC;

			double cuotaPagar = productoEJB.cuotaPagar(tarjeta);
			double restaACuenta = productoEJB.restaCuentaPago(cuenta, cuotaPagar);

			cuenta.setAmmount(restaACuenta);

			Transaction tran = new Transaction();
			tran.setAmmount(cuotaPagar);
			tran.setSavingAccNumber(cuenta);
			tran.setSourceTransact("Web");
			tran.setTransactionDate(fecha);

			if (valorExtra == 0) {
				productoEJB.editarProducto(cuenta);
				productoEJB.realizarPagoConsumo(numTarjeta, valorExtr);
				transaccionEJB.agregarTransaccion(tran);
				cargarListaConTajerta(tarjeta);
			} else {
				double valorExtr1 = valorExtra;
				double total = valorExtr1 + valorExtr;
				productoEJB.editarProducto(cuenta);
				productoEJB.realizarPagoConsumosWeb(cliente, total);
				transaccionEJB.agregarTransaccion(tran);
				cargarListaConTajerta(tarjeta);
			}
		} else {
			Messages.addFlashGlobalInfo(" Debe iniciar sesion para poder pagar ");
		}
	}

	public void pagoConsumoCompleto(CreditCardConsume ccc) {

		String numTar = tarjetaSeleccionada;
		Product proT = productoEJB.buscarProducto(numTar);
		CreditCard tarjeta = (CreditCard) proT;

		String numTarjeta = ccc.getCreditCard().getNumber();
		String numCuenta = cuentaSeleccionada;

		productoEJB.pagoConsumoCompleto(numTarjeta, numCuenta);
		Messages.addFlashGlobalInfo(" El pago completo del consumo, se ha realizado correctamente");
		cargarListaConTajerta(tarjeta);

	}

	public void cargarTablaCombo() {
		String numTar = tarjetaSeleccionada;
		Product proT = productoEJB.buscarProducto(numTar);
		CreditCard tarjeta = (CreditCard) proT;

		cargarListaConTajerta(tarjeta);

	}
	
	public void avance(){
		String numTarjeta = tarjetaSelecAvance;
		String numCuenta = cuentaSelecAvance;
		
		Product proTajeta = productoEJB.buscarProducto(numTarjeta);
		CreditCard tarjeta = (CreditCard) proTajeta;
		
		Product proCuenta = productoEJB.buscarProducto(numCuenta);
		SavingAccount cuenta = (SavingAccount) proCuenta;

		System.out.println("valor Avanceeeeeeeeee = " + valor + "Tarjetaaaa = " + tarjeta.getNumber() + " cuentaaaaaa = " + cuenta.getNumber());
		
		try{
			productoEJB.avanceAhorrosWeb(tarjeta, cuenta, valor);
			Messages.addFlashGlobalInfo(" El avance se realizo correctamente ");
		}catch (ExcepcionNegocio e){
			e.getMessage();
		}
	}
	
	
	public String cancelar(){
		return "/paginas/Segura/ResumenProductos.xhtml?faces-redirect=true";
	}

	/**
	 * @return the tarjetaSeleccionada
	 */
	public String getTarjetaSeleccionada() {
		return tarjetaSeleccionada;
	}

	/**
	 * @param tarjetaSeleccionada
	 *            the tarjetaSeleccionada to set
	 */
	public void setTarjetaSeleccionada(String tarjetaSeleccionada) {
		this.tarjetaSeleccionada = tarjetaSeleccionada;
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
	 * @return the cuentas
	 */
	public List<SavingAccount> getCuentas() {
		return cuentas;
	}

	/**
	 * @param cuentas
	 *            the cuentas to set
	 */
	public void setCuentas(List<SavingAccount> cuentas) {
		this.cuentas = cuentas;
	}

	/**
	 * @return the cuentaSeleccionada
	 */
	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	/**
	 * @param cuentaSeleccionada
	 *            the cuentaSeleccionada to set
	 */
	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	/**
	 * @return the valorExtra
	 */
	public double getValorExtra() {
		return valorExtra;
	}

	/**
	 * @param valorExtra
	 *            the valorExtra to set
	 */
	public void setValorExtra(double valorExtra) {
		this.valorExtra = valorExtra;
	}

	/**
	 * @return the consumos
	 */
	public List<CreditCardConsume> getConsumos() {
		return consumos;
	}

	/**
	 * @param consumos
	 *            the consumos to set
	 */
	public void setConsumos(List<CreditCardConsume> consumos) {
		this.consumos = consumos;
	}

	/**
	 * @return the valor
	 */
	public double getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(double valor) {
		this.valor = valor;
	}

	/**
	 * @return the tarjetaSelecAvance
	 */
	public String getTarjetaSelecAvance() {
		return tarjetaSelecAvance;
	}

	/**
	 * @param tarjetaSelecAvance the tarjetaSelecAvance to set
	 */
	public void setTarjetaSelecAvance(String tarjetaSelecAvance) {
		this.tarjetaSelecAvance = tarjetaSelecAvance;
	}

	/**
	 * @return the cuentaSelecAvance
	 */
	public String getCuentaSelecAvance() {
		return cuentaSelecAvance;
	}

	/**
	 * @param cuentaSelecAvance the cuentaSelecAvance to set
	 */
	public void setCuentaSelecAvance(String cuentaSelecAvance) {
		this.cuentaSelecAvance = cuentaSelecAvance;
	}
	
	

}
