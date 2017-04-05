package co.edu.eam.ingesoft.pa.standalone.controlador;

import java.util.List;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardPaymentConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Transaction;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IProductoRemote;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.ITransaccionRemote;
import co.edu.eam.ingesoft.pa.standalone.ServiceLocator;

/**
 * Clase controladora de la venta Gesti�n Productos
 * 
 * @author LuchoBolivar
 *
 */
public class ControladorVentanaGestionProducto {

	private IProductoRemote productoRemoto;
	private ITransaccionRemote transaccionRemota;

	public ControladorVentanaGestionProducto() throws NamingException {
		// TODO Auto-generated constructor stub
		productoRemoto = (IProductoRemote) ServiceLocator.buscarEJB("ProductoEJB",
				IProductoRemote.class.getCanonicalName());

		transaccionRemota = (ITransaccionRemote) ServiceLocator.buscarEJB("TransaccionEJB",
				ITransaccionRemote.class.getCanonicalName());
	}

	/**
	 * M�todo para buscar un producto
	 * 
	 * @param num
	 *            N�mero del producto que se desea buscar
	 * @return El producto si lo encuentra, de lo contrario null
	 */
	public Product buscarProducto(String num) {
		return productoRemoto.buscarProducto(num);
	}

	/**
	 * M�todo para agregar una tarjeta de cr�dito
	 * 
	 * @param p
	 *            tarjeta de cr�dito que se desea agregar
	 */
	public void registrarTarjeta(CreditCard cc) {
		productoRemoto.agregarTarjeta(cc);
	}

	/**
	 * M�todo para registrar una cuenta de ahorros
	 * 
	 * @param sa
	 *            Cuenta de ahorros que se desea registrar
	 */
	public void registrarCuenta(SavingAccount sa) {
		productoRemoto.registrarCuenta(sa);
	}

	/**
	 * 
	 * @return
	 */
	public String cvcNumAle() {
		return productoRemoto.numCvcAleatorio();

	}

	/**
	 * 
	 * @return
	 */
	public String numeroTarjeta() {
		return productoRemoto.numeroTarjeta();
	}

	/**
	 * 
	 * @return
	 */
	public String numeroCuentaAhorro() {
		return productoRemoto.numeroCuentaAhorro();
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<Product> listaProductosCliente(Customer c) {
		return productoRemoto.listaProductosCliente(c);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public int validarCuentaAhorro(Customer c) {
		return productoRemoto.validarCuentaAhorro(c);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public int validarProductosTarjetaCredit(Customer c) {
		return productoRemoto.validarProductosTarjetaCredit(c);
	}

	/**
	 * 
	 * @param sActual
	 * @param sConsig
	 * @return
	 */
	public double consignacionAhorro(double sActual, double sConsig) {
		return productoRemoto.consignacionAhorro(sActual, sConsig);
	}

	/**
	 * 
	 * @param ch
	 */
	public void editarProducto(Product ch) {
		productoRemoto.editarProducto(ch);
	}

	/**
	 * 
	 * @param saldo
	 * @param cTransferencia
	 * @return
	 */
	public double restaTranferencia(double saldo, double cTransferencia) {
		return productoRemoto.restaTranferencia(saldo, cTransferencia);
	}

	/**
	 * 
	 * @param t
	 */
	public void agregarTransaccion(Transaction t) {
		transaccionRemota.agregarTransaccion(t);
	}

	/**
	 * 
	 * @param cc
	 */
	public void agregarTarjetaConsumo(CreditCardConsume cc) {
		productoRemoto.agregarTarjetaConsumo(cc);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public double cuotaPagar(CreditCard c) {
		return productoRemoto.cuotaPagar(c);
	}

	/**
	 * 
	 * @param cuota
	 * @param num
	 * @return
	 */
	public double valorRestar(double cuota, CreditCard num) {
		return productoRemoto.valorRestar(cuota, num);
	}

	/**
	 * 
	 * @param cc
	 */
	public void editarConsumo(CreditCardConsume cc) {
		productoRemoto.editarConsumo(cc);
	}

	/**
	 * 
	 * @param numCuotas
	 * @param valorConsumo
	 * @return
	 */
	public double valorTotalMasInteres(int numCuotas, double valorConsumo) {
		return productoRemoto.valorTotalMasInteres(numCuotas, valorConsumo);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<CreditCardConsume> listaConsumosCuotas(CreditCard c) {
		return productoRemoto.listaConsumosCuotas(c);
	}

	/**
	 * 
	 * @param cc
	 */
	public void agregarTarjetaConsumoPago(CreditCardPaymentConsume cc) {
		productoRemoto.agregarTarjetaConsumoPago(cc);
	}

	/**
	 * 
	 * @param cuota
	 * @return
	 */
	public double capitalAmmount(double cuota) {
		return productoRemoto.capitalAmmount(cuota);
	}

	/**
	 * 
	 * @param cuota
	 * @return
	 */
	public double interes(double cuota) {
		return productoRemoto.interes(cuota);
	}
	
	/**
	 * 
	 * @param cuota
	 * @return
	 */
	public int restaCuotasWeb(int cuota){
		return productoRemoto.restaCuotasWeb(cuota);
	}
	
	/**
	 * 
	 * @param ammount
	 * @param cuota
	 * @return
	 */
	public double restaPagoCuotaWeb(double ammount, double cuota){
		return productoRemoto.restaPagoCuotaWeb(ammount, cuota);
	}
	
	/**
	 * 
	 * @param numTarjeta
	 */
	public void realizarPagoConsumo (String numTarjeta, double valorAdicional){
		productoRemoto.realizarPagoConsumo(numTarjeta, valorAdicional);
	}
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<CreditCardConsume> listaConsumosCliente(Customer c){
		return productoRemoto.listaConsumosCliente(c);
	}

}
