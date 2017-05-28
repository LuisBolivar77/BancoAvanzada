package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCard;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CreditCardPaymentConsume;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CuentaAsociada;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Customer;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Product;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.SavingAccount;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Transaction;
import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.remote.IProductoRemote;
import co.edu.eam.ingesoft.pa.negocio.exception.ExcepcionNegocio;

@LocalBean
@Stateless
@Remote(IProductoRemote.class)
public class ProductoEJB {

	@PersistenceContext
	private EntityManager em;
	private List<Product> productoC;
	private List<Product> productoT;
	
	@EJB
	private TransaccionEJB transacionEJB;
	
	@EJB
	private CodigoValidacionEJB codigoEJB;

	/**
	 * M�todo para buscar un producto
	 * 
	 * @param num
	 *            N�mero del producto que se desea buscar
	 * @return EL producto si lo encuentra, de lo contrario null
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Product buscarProducto(String num) {
		return em.find(Product.class, num);
	}

	/**
	 * 
	 * @param ch
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarProducto(Product ch) {
		em.merge(ch);
	}

	/**
	 * 
	 * @param ch
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void editarConsumo(CreditCardConsume cc) {
		em.merge(cc);
	}

	/**
	 * Metodo para agregar una tarjeta de credito
	 * 
	 * @param cc
	 *            Tarjeta de credito que se desea agregar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void agregarTarjeta(CreditCard cc) {
		CreditCard cre = (CreditCard) buscarProducto(cc.getNumber());
		if (cre == null) {
			em.persist(cc);
		} else {
			throw new ExcepcionNegocio("Esta tarjeta ya esta registrada");
		}
	}

	/**
	 * Metodo para agregar una tarjeta de crdito
	 * 
	 * @param cc
	 *            Tarjeta de credito que se desea agregar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void agregarTarjetaConsumo(CreditCardConsume cc) {
		em.persist(cc);
	}

	/**
	 * Metodo para agregar una tarjeta de crdito
	 * 
	 * @param cc
	 *            Tarjeta de credito que se desea agregar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void agregarTarjetaConsumoPago(CreditCardPaymentConsume cc) {
		em.persist(cc);
	}

	/**
	 * Registra una cuenta de ahorros a un cliente
	 * 
	 * @param sa
	 *            Cuanta de ahorros que se desea registrar
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void registrarCuenta(SavingAccount sa) {
		SavingAccount cuenta = (SavingAccount) buscarProducto(sa.getNumber());
		if (cuenta == null) {
			em.persist(sa);
		} else {
			throw new ExcepcionNegocio("Esta cuenta ya esta registrada");
		}
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public int validarCuentaAhorro(Customer c) {
		int cant = 0;
		List<Product> productos = listaProductosCliente(c);
		if (productos.isEmpty()) {
			return cant;
		} else {
			for (Product product : productos) {
				if (product.getNumber().length() == 17) {
					cant++;
				}
			}
		}
		return cant;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public int validarProductosTarjetaCredit(Customer c) {
		int cant = 0;
		List<Product> productos = listaProductosCliente(c);
		if (productos.isEmpty()) {
			return cant;
		} else {
			for (Product product : productos) {
				if (product.getNumber().length() == 11) {
					cant++;
				}
			}
		}

		return cant;

	}

	/**
	 * Genera n�meros aleatorios de tres cifras
	 * 
	 * @return el n�mero aleatorio
	 */
	public String numCvcAleatorio() {

		String cadena = "";
		do {
			int num = ThreadLocalRandom.current().nextInt(0, 9 + 1);
			cadena += num + "";
		} while (cadena.length() < 3);
		return cadena;

	}

	/**
	 * Genera el n�mero de tarjeta de cr�dito aleatoriamente
	 * 
	 * @return el n�mero de la tarjeta de cr�dito
	 */
	public String numeroTarjeta() {
		String cadena = "";
		int resultado = 0;
		do {
			int num = ThreadLocalRandom.current().nextInt(0, 9);
			cadena += num + "";
			if (cadena.length() > 1) {
				resultado = num * resultado;
			}
		} while (cadena.length() <= 10);

		return cadena;
	}

	/**
	 * Genera el n�mero de tarjeta de cr�dito aleatoriamente
	 * 
	 * @return el n�mero de la tarjeta de cr�dito
	 */
	public String numeroCuentaAhorro() {
		String cadena = "";
		int resultado = 0;
		do {
			int num = ThreadLocalRandom.current().nextInt(0, 9);
			cadena += num + "";
			if (cadena.length() > 16) {
				resultado = num * resultado;
			}
		} while (cadena.length() <= 16);

		return cadena;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Product> listaProductosCliente(Customer c) {
		Query q = em.createNamedQuery(Product.LISTAR_PRODUCTOS);
		q.setParameter(1, c);
		List<Product> lista = q.getResultList();
		return lista;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CreditCardConsume> listaConsumosCuotas(CreditCard c) {
		Query q = em.createNamedQuery(CreditCardConsume.LISTAR_CONSUMOS);
		q.setParameter(1, c);
		List<CreditCardConsume> lista = q.getResultList();
		return lista;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CreditCardConsume> listaConsumosCliente(Customer c) {
		Query q = em.createNamedQuery(CreditCardConsume.LISTAR_CONSUMOS_CLIENTE);
		q.setParameter(1, c);
		List<CreditCardConsume> lista = q.getResultList();
		return lista;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Product> listaCuentas(Customer c) {
		Query q = em.createNamedQuery(Product.LISTAR_CUENTAS);
		q.setParameter(1, c);
		List<Product> lista = q.getResultList();
		return lista;

	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CreditCard> listaTarjetas(Customer c) {
		Query q = em.createNamedQuery(CreditCard.LISTAR_TARJETAS);
		q.setParameter(1, c);
		List<CreditCard> lista = q.getResultList();
		return lista;
	}

	/**
	 * 
	 * @param vConsig
	 * @param num
	 */
	public double consignacionAhorro(double sActual, double vConsig) {

		double total;
		total = sActual + vConsig;
		return total;

	}

	/**
	 * 
	 * @param saldo
	 * @param cTransferencia
	 * @return
	 */
	public double restaTranferencia(double saldo, double cTransferencia) {

		double total;
		total = saldo - cTransferencia;
		if (total >= 0) {
			return total;
		} else {
			throw new ExcepcionNegocio(" su saldo es insuficienta para esta cuenta ");
		}
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public double cuotaPagar(CreditCard c) {
		double cuota = 0;
		double numCuotas = 0;
		double monto = 0;
		List<CreditCardConsume> consumos = listaConsumosCuotas(c);
		for (CreditCardConsume creditCardConsume : consumos) {
			numCuotas = creditCardConsume.getNumberShares();
			monto = creditCardConsume.getAmmount();

			double divisionCuotas = monto / numCuotas;
			double cuotasIva = divisionCuotas * 0.036;
			cuota += divisionCuotas + cuotasIva;
		}
		return cuota;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public double cuotaPagarWeb(Customer c) {
		double cuota = 0;
		double numCuotas = 0;
		double monto = 0;
		List<CreditCardConsume> consumos = listaConsumosCliente(c);
		for (CreditCardConsume creditCardConsume : consumos) {
			numCuotas = creditCardConsume.getNumberShares();
			monto = creditCardConsume.getAmmount();

			double divisionCuotas = monto / numCuotas;
			double cuotasIva = divisionCuotas * 0.036;
			cuota += divisionCuotas + cuotasIva;
		}
		return cuota;
	}

	/**
	 * 
	 * @param cuota
	 * @param num
	 * @return
	 */
	public double valorRestar(double cuota, CreditCard num) {
		int cont = 0;
		double resul = 0;
		List<CreditCardConsume> consumos = listaConsumosCuotas(num);
		for (CreditCardConsume creditCardConsume : consumos) {
			cont++;
		}
		return resul = cuota / cont;

	}

	/**
	 * 
	 * @param cuota
	 * @param num
	 * @return
	 */
	public double valorRestarWeb(double cuota, Customer c) {
		int cont = 0;
		double resul = 0;
		List<CreditCardConsume> consumos = listaConsumosCliente(c);
		for (CreditCardConsume creditCardConsume : consumos) {
			cont++;
		}
		return resul = cuota / cont;

	}

	/**
	 * 
	 * @param numCuotas
	 * @param valorConsumo
	 * @return
	 */
	public double valorTotalMasInteres(int numCuotas, double valorConsumo) {
		double total = 0;
		double impuestoTotal = 0;

		impuestoTotal = (0.036 * numCuotas) * valorConsumo;
		total = impuestoTotal + valorConsumo;

		return total;

	}

	/**
	 * 
	 * @param cuenta
	 * @param cuota
	 * @return
	 */
	public double restaCuentaPago(SavingAccount cuenta, double cuota) {

		double total;
		double ammount = cuenta.getAmmount();
		total = ammount - cuota;

		if (total >= 0) {
			return total;
		} else {
			throw new ExcepcionNegocio(" su saldo es insuficiente");
		}

	}

	/**
	 * 
	 * @param saldo
	 * @param cTransferencia
	 * @return
	 */
	public double restaPagoCuotaWeb(double ammount, double cuota) {

		double total;
		total = ammount - cuota;
		return total;

	}

	/**
	 * 
	 * @param saldo
	 * @param cTransferencia
	 * @return
	 */
	public int restaCuotasWeb(int cuota) {

		int total;
		total = cuota - 1;

		return total;
	}

	/**
	 * 
	 * @param cuota
	 * @return
	 */
	public double capitalAmmount(double cuota) {

		double interes = cuota * 0.036;
		double restaCapital = cuota - interes;

		return restaCapital;
	}

	/**
	 * 
	 * @param cuota
	 * @return
	 */
	public double interes(double cuota) {

		double interes = cuota * 0.036;

		return interes;
	}

	/**
	 * Obtiene la lista de tarjetas de credito de un cliente
	 * 
	 * @param c
	 *            el cliente
	 * @return la lista de tarjetas del cliente
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CreditCard> listaTarjetasCliente(Customer c) {
		List<Product> listaProductos = listaProductosCliente(c);
		List<CreditCard> listaTarjetas = new ArrayList<CreditCard>();
		for (Product product : listaProductos) {
			if (product.getNumber().length() == 11) {
				CreditCard tarjeta = (CreditCard) product;
				listaTarjetas.add(tarjeta);
			}
		}
		return listaTarjetas;
	}

	/**
	 * Obtiene la lista de cuentas de un cliente
	 * 
	 * @param c
	 *            el cliente
	 * @return la lista de cuentas del cliente
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<SavingAccount> listaCuentasCliente(Customer c) {
		List<Product> listaProductos = listaProductosCliente(c);
		List<SavingAccount> listaCuentas = new ArrayList<SavingAccount>();
		for (Product product : listaProductos) {
			if (product.getNumber().length() == 17) {
				SavingAccount sa = (SavingAccount) product;
				listaCuentas.add(sa);
			}
		}
		return listaCuentas;
	}

//	/**
//	 * 
//	 * @param c
//	 * @return
//	 */
//	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
//	public List<CreditCard> deudaCliente(Customer c) {
//		List<CreditCard> tarjeta = listaTarjetas(c);
//		for (CreditCard creditCard : tarjeta) {
//		
//		}
//		
//		
//		
//	}

	/**
	 * 
	 * @param numTarjeta
	 */
	public void realizarPagoConsumo(String numTarjeta, double valorAdicional) {

		Product proT = buscarProducto(numTarjeta);
		CreditCard tarjeta = (CreditCard) proT;
		double cuotaPagar = cuotaPagar(tarjeta) + valorAdicional;
		double valorRestarConsu = valorRestar(cuotaPagar, tarjeta);
		double capitalAmmount = capitalAmmount(cuotaPagar);
		double interes = interes(cuotaPagar);
		Date paymentDate = new Date();

		CreditCardPaymentConsume consumoPago = new CreditCardPaymentConsume();

		consumoPago.setAmmount(cuotaPagar);
		consumoPago.setCapitalAmmount(capitalAmmount);
		consumoPago.setInterestAmmount(interes);
		consumoPago.setPaymentDate(paymentDate);

		List<CreditCardConsume> con = listaConsumosCuotas(tarjeta);
		for (CreditCardConsume cos : con) {
			double ammount = cos.getReamingAmmount();

			double nuevoAmmount = restaPagoCuotaWeb(ammount, valorRestarConsu);

			if (nuevoAmmount <= 0) {

				cos.setReamingAmmount(0);
				cos.setNumberShares(0);
				cos.setPayed(true);
				consumoPago.setIdConsume(cos);

				editarConsumo(cos);
				agregarTarjetaConsumoPago(consumoPago);

			} else {

				cos.setReamingAmmount(nuevoAmmount);
				consumoPago.setIdConsume(cos);

				editarConsumo(cos);
				agregarTarjetaConsumoPago(consumoPago);

			}
		}
	}

	/**
	 * 
	 * @param numTarjeta
	 */
	public void realizarPagoConsumosWeb(Customer cliente, double valorAdicional) {

		double cuotaPagar = cuotaPagarWeb(cliente) + valorAdicional;
		double valorRestarConsu = valorRestarWeb(cuotaPagar, cliente);
		double capitalAmmount = capitalAmmount(cuotaPagar);
		double interes = interes(cuotaPagar);
		Date paymentDate = new Date();

		CreditCardPaymentConsume consumoPago = new CreditCardPaymentConsume();

		consumoPago.setAmmount(cuotaPagar);
		consumoPago.setCapitalAmmount(capitalAmmount);
		consumoPago.setInterestAmmount(interes);
		consumoPago.setPaymentDate(paymentDate);

		List<CreditCardConsume> con = listaConsumosCliente(cliente);
		for (CreditCardConsume cos : con) {
			double ammount = cos.getReamingAmmount();
			double nuevoAmmount = restaPagoCuotaWeb(ammount, valorRestarConsu);

			if (nuevoAmmount <= 0) {

				cos.setReamingAmmount(0);
				cos.setNumberShares(0);
				cos.setPayed(true);
				consumoPago.setIdConsume(cos);

				editarConsumo(cos);
				agregarTarjetaConsumoPago(consumoPago);

			} else {

				cos.setReamingAmmount(nuevoAmmount);
				consumoPago.setIdConsume(cos);

				editarConsumo(cos);
				agregarTarjetaConsumoPago(consumoPago);

			}
		}
	}

	/**
	 * 
	 * @param numTarjeta
	 */
	public void pagoConsumoCompleto(String numTarjeta, String numCuenta) {

		Date fecha = new Date();
		Product productoT = buscarProducto(numTarjeta);
		Product productoS = buscarProducto(numCuenta);
		SavingAccount cuenta = (SavingAccount) productoS;
		CreditCard tarjeta = (CreditCard) productoT;
		List<CreditCardConsume> consumo = listaConsumosCuotas(tarjeta);

		CreditCardPaymentConsume pagoConsumo = new CreditCardPaymentConsume();
		pagoConsumo.setAmmount(tarjeta.getAmmount());
		pagoConsumo.setCapitalAmmount(tarjeta.getAmmount());
		pagoConsumo.setInterestAmmount(0);
		pagoConsumo.setPaymentDate(fecha);

		for (CreditCardConsume creditCardConsume : consumo) {
			if (creditCardConsume.getCreditCard().getNumber().equals(numTarjeta)) {
				creditCardConsume.setReamingAmmount(0);
				creditCardConsume.setNumberShares(0);
				creditCardConsume.setPayed(true);
				pagoConsumo.setIdConsume(creditCardConsume);
				double restaCuenta = restaCuentaPago(cuenta, creditCardConsume.getReamingAmmount());

				cuenta.setAmmount(restaCuenta);

				if (restaCuenta >= 0) {

					editarConsumo(creditCardConsume);
					agregarTarjetaConsumoPago(pagoConsumo);
					editarProducto(cuenta);

				} else {
					throw new ExcepcionNegocio("su saldo en la cuenta de ahorros es insuficiente");
				}
			}
		}

	}

	/**
	 * 
	 * @param tar
	 * @param cuenta
	 * @param valor
	 */
	public void avanceAhorrosWeb(CreditCard tar, SavingAccount cuenta, double valor) {
		
		double primerTotal = 0.30 * tar.getCantidadRestante();

		if (valor <= primerTotal) {

			Date fecha = new Date();

			CreditCardConsume tarConsumo = new CreditCardConsume();
			tarConsumo.setAmmount(valor);
			tarConsumo.setCreditCard(tar);
			tarConsumo.setDateConsume(fecha);
			tarConsumo.setInterest(0.036);
			tarConsumo.setNumberShares(24);
			tarConsumo.setPayed(false);
			tarConsumo.setReamingAmmount(valor);

			Transaction trans = new Transaction();
			trans.setAmmount(valor);
			trans.setSavingAccNumber(cuenta);
			trans.setSourceTransact("WEB");
			trans.setTransactionDate(fecha);

			tar.setCantidadRestante(tar.getCantidadRestante() - valor);

			cuenta.setAmmount(cuenta.getAmmount() + valor);

			editarProducto(tar);
			editarProducto(cuenta);
			transacionEJB.agregarTransaccion(trans);
			agregarTarjetaConsumo(tarConsumo);

		} else {
			throw new ExcepcionNegocio(" El valor es mayor al 30% de lo que resta en la tarjeta de credito");
		}

	}
	
	public void transferenciaWeb(SavingAccount sv, double valor){
		
		double resta = sv.getAmmount() - valor;
		
		if(resta >= 0){
			sv.setAmmount(resta);
			editarProducto(sv);
			
		}else{
			throw new ExcepcionNegocio(" Saldo Insuficiente");
		}		
		
	}
	
	public void sumarMontoCuenta (String cuenta, double cantidad, Customer cliente){
		SavingAccount sa = (SavingAccount) buscarProducto(cuenta);
		if (sa != null){
			sa.setAmmount(sa.getAmmount()+cantidad);
			String codigo = codigoEJB.numeroCodigoValidacion();
			codigoEJB.enviarSmsRecibido(cantidad, cliente.getTelefono());
			editarProducto(sa);
			
			
		}
	}
	
	public void restarMontoCuenta (String numCuenta, double monto){
		Product pro = buscarProducto(numCuenta);
		if(pro != null){
			double total = pro.getAmmount()-monto;
			if(total >= 0){
				pro.setAmmount(total);
				editarProducto(pro);
				
				
			}
		}
	}
	
	
}
