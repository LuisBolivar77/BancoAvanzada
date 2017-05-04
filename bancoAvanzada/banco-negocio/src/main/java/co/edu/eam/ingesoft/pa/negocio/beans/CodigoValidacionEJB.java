package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.concurrent.ThreadLocalRandom;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.BindingProvider;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.CodigoValidacion;
import co.edu.eam.pa.clientews.Mail;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;
import co.edu.eam.pa.clientews.Sms;

@LocalBean
@Stateless
public class CodigoValidacionEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	
	/**
	 * 
	 * @param cuentaAso
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCodigoValidacion(CodigoValidacion codigoVali){
		em.persist(codigoVali);
	}

	/**
	 * 
	 * @param nom
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public CodigoValidacion buscarCodigoValidacion(String cod){
		return em.find(CodigoValidacion.class, cod);
	}
	
	/**
	 * 
	 * @param codigo
	 * @param email
	 */
	public void enviarEmail(String codigo, String email){
		
		NotificacionesService cliente = new NotificacionesService();
		Notificaciones service = cliente.getNotificacionesPort();
		
		String endPointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider) service;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointURL);
		
		Mail correo = new Mail();
		correo.setBody(codigo);
		correo.setFrom("HelloMoto@gmail.com");
		correo.setTo(email);
		correo.setSubject("Segunda Clave QUINDI BANCO");
		
		RespuestaNotificacion res = service.enviarMail(correo);
		System.out.println("mensajeeeeeeeeeee = " + res.getMensaje());

	}
	
	/**
	 * 
	 * @param codigo
	 */
	public void enviarSmsCodigoVal(String codigo, String numTelefono){
		
		NotificacionesService cliente = new NotificacionesService();
		Notificaciones service = cliente.getNotificacionesPort();
		
		String endPointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider) service;
		bp.getResponseContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointURL);
		
		Sms mensaje = new Sms();
		mensaje.setTexto(codigo);
		mensaje.setTo(numTelefono);
		
		RespuestaNotificacion res = service.enviarSMS(mensaje);
		System.out.println(res.getMensaje());
		
		
	}
	
	/**
	 * 
	 * @param codigo
	 */
	public void enviarSmsRecibido(double monto, String numTelefono){
		
		NotificacionesService cliente = new NotificacionesService();
		Notificaciones service = cliente.getNotificacionesPort();
		
		String endPointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider) service;
		bp.getResponseContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPointURL);
		
		Sms mensaje = new Sms();
		mensaje.setTexto("Se le ha sumado a su cuenta el monto de: "+ monto);
		mensaje.setTo(numTelefono);
		
		RespuestaNotificacion res = service.enviarSMS(mensaje);
		System.out.println(res.getMensaje());
		
		
	}
	
	
	/**
	 * Genera el n�mero de tarjeta de cr�dito aleatoriamente
	 * 
	 * @return el n�mero de la tarjeta de cr�dito
	 */
	public String numeroCodigoValidacion() {
		String cadena = "";
		int resultado = 0;
		do {
			int num = ThreadLocalRandom.current().nextInt(0, 9);
			cadena += num + "";
			if (cadena.length() > 1) {
				resultado = num * resultado;
			}
		} while (cadena.length() <= 5);

		return cadena;
	}
	
	
	
	
}
