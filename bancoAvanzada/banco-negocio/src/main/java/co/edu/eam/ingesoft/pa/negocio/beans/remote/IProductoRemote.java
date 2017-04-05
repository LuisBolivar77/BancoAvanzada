package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import java.util.List;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardPaymentConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Franchise;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;

/**
 * Interface remota para acceder a las operaciones del EJB de productos
 * @author LuchoBolivar
 *
 */
public interface IProductoRemote {
	
	/**
	 * Metodo para agregar producto
	 * @param p Producto que se desea agregar
	 */
	public void agregarTarjeta (CreditCard p);
	
	/**
	 * Metodo para registrar una cuenta de ahorros
	 * @param sa Cuenta de ahorros que se desea registrar
	 */
	public void registrarCuenta (SavingAccount sa);
	
	/**
	 * Metodo para buscar un producto 
	 * @param num Numero del producto que se desea buscar
	 * @return El producto si lo encuentra, de lo contrario null
	 */
	public Product buscarProducto (String num);
	
	/**
	 * 
	 * @return
	 */
	public String numCvcAleatorio ();
	
	/**
	 * 
	 * @return
	 */
	public String numeroTarjeta();
	
	/**
	 * 
	 * @return
	 */
	public String numeroCuentaAhorro();
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public int validarProductosTarjetaCredit(Customer c);
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public int validarCuentaAhorro(Customer c);
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<Product> listaProductosCliente(Customer c);
	
	/**
	 * 
	 * @param sActual
	 * @param sConsig
	 * @return
	 */
	public double consignacionAhorro(double sActual, double sConsig);
	
	/**
	 * 
	 * @param ch
	 */
	public void editarProducto(Product ch);
	
	/**
	 * 
	 * @param saldo
	 * @param cTransferencia
	 * @return
	 */
	public double restaTranferencia(double saldo, double cTransferencia);
	
	/**
	 * 
	 * @param cc
	 */
	public void agregarTarjetaConsumo(CreditCardConsume cc);
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public double cuotaPagar(CreditCard c);
	
	/**
	 * 
	 * @param cc
	 */
	public void editarConsumo(CreditCardConsume cc);
	
	/**
	 * 
	 * @param cuota
	 * @param num
	 * @return
	 */
	public double valorRestar(double cuota, CreditCard num);
	
	/**
	 * 
	 * @param numCuotas
	 * @param valorConsumo
	 * @return
	 */
	public double valorTotalMasInteres (int numCuotas, double valorConsumo);
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<CreditCardConsume> listaConsumosCuotas(CreditCard c);
	
	/**
	 * 
	 * @param cc
	 */
	public void agregarTarjetaConsumoPago(CreditCardPaymentConsume cc);
	
	/**
	 * 
	 * @param cuota
	 * @return
	 */
	public double capitalAmmount(double cuota);
	
	/**
	 * 
	 * @param cuota
	 * @return
	 */
	public double interes(double cuota);
	
	/**
	 * 
	 * @param cuota
	 * @return
	 */
	public int restaCuotasWeb(int cuota);
	
	/**
	 * 
	 * @param ammount
	 * @param cuota
	 * @return
	 */
	public double restaPagoCuotaWeb(double ammount, double cuota);
	
	/**
	 * 
	 * @param numTarjeta
	 */
	public void realizarPagoConsumo (String numTarjeta, double valorAdicional);
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<CreditCardConsume> listaConsumosCliente(Customer c);

}
