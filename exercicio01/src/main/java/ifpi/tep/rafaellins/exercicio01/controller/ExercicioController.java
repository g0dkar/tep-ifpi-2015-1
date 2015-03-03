package ifpi.tep.rafaellins.exercicio01.controller;

import ifpi.tep.rafaellins.exercicio01.model.Usuario;
import ifpi.tep.rafaellins.exercicio01.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.HttpResult;
import br.com.caelum.vraptor.view.Results;

@Controller
public class ExercicioController {
	private final Result result;
	private final HttpServletRequest request;
	private final HttpServletResponse response;
	private final Session session;
	
	/** Obrigatório existir por causa do CDI @deprecated */
	@Deprecated ExercicioController() { this(null, null, null, null); }
	
	@Inject
	public ExercicioController(final Result result, final HttpServletRequest request, final HttpServletResponse response, final Session session) {
		this.result = result;
		this.request = request;
		this.response = response;
		this.session = session;
	}
	
	@Path("/")
	public void index() {
		
	}
	
	@Path("/auth")
	public void auth(final String username, final String password) {
		final String user = username != null ? username.replaceAll("\\W", "") : null;
		
		if (user != null && user.length() > 0) {
			try {
				final Usuario usuario = (Usuario) query("FROM Usuario WHERE username = :uname").setString("uname", user).uniqueResult();
				
				if (usuario != null) {
					final String pwdHash = Usuario.hashPassword(password);
					if (pwdHash != null && usuario.getPassword().equals(pwdHash)) {
						request.getSession().setAttribute("USUARIO", usuario);
						jsonResponse(usuario);
					}
					else {
						jsonResponse(false);
					}
				}
				else {
					jsonResponse(false);
				}
			} catch (final Exception e) {
				jsonResponse(false);
			}
		}
		else {
			jsonResponse(false);
		}
	}
	
	/**
	 * Helper para criar {@link Query Queries} do Hibernate.
	 */
	private Query query(final String query) {
		return session.createQuery(query);
	}
	
	/**
	 * Envia o JSON do objeto como resposta ao Request atual, com o Content-Type {@code "application/json"}
	 * 
	 * @param response The object to be encoded and sent
	 */
	private HttpResult jsonResponse(final Object response) {
		this.response.setCharacterEncoding("UTF-8");
		this.response.setContentType("application/json");
		final String jsonString = JsonUtils.toJson(response);
		return result.use(Results.http()).body(jsonString);
	}
	
	/**
	 * Helper para mandar <code>{"success":&lt;true|false&gt;}</code> como resposta.
	 * @param response
	 * @return
	 */
	private HttpResult jsonResponse(final boolean response) {
		final Map<String, Object> res = new HashMap<String, Object>(1);
		res.put("success", response);
		return jsonResponse(res);
	}
}
