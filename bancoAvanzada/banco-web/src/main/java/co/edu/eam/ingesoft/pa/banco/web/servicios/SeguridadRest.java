package co.edu.eam.ingesoft.pa.banco.web.servicios;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.persistencia.edentidades.Usuario;
import co.edu.eam.ingesoft.pa.banco.web.DTO.RespuestaDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.LoginDTO;
import co.edu.eam.ingesoft.pa.negocio.DTO.LoginRespuestaDTO;
import co.edu.eam.ingesoft.pa.negocio.beans.SeguridadEJB;

@Path("/seguridad")
public class SeguridadRest {

	public static Map<String, Usuario> usuarios = new HashMap<String, Usuario>();;

	@EJB
	private SeguridadEJB ejb;

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO login(LoginDTO dto) {

		Usuario user = ejb.buscarUsuario(dto.getUser());
		if (user != null && user.getPassword().equals(dto.getPassword())) {
			String token = UUID.randomUUID().toString();
			usuarios.put(token, user);
			
			RespuestaDTO res = new RespuestaDTO();
			res.setCodigo("0");
			res.setMensaje("Exito");
			res.setObj(new LoginRespuestaDTO(token, user.getCustomer().getIdNum()));
			return res;
		} else {
			RespuestaDTO resp = new RespuestaDTO();
			resp.setCodigo("-403");
			resp.setMensaje("Credenciales erroneas");
			resp.setObj(null);
			return resp;
		}

	}
}
