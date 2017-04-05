package co.edu.eam.ingesoft.pa.negocio.beans.remote;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Transaction;

public interface ITransaccionRemote {

	/**
	 * 
	 * @param t
	 */
	public void agregarTransaccion(Transaction t);
	
}
