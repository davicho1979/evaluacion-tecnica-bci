package evaluacion.tecnica.bci.common;

import java.util.Collection;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Common 
{
	@Value("${app.secret.key}")
	private String secretKey;
	
	@Value("${regex.validate.email}")
	private String regexEmail;

	@Value("${regex.validate.password}")
	private String regexPassword;
	
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;
    
	@SuppressWarnings({ "rawtypes", "unused" })
	public int size(Iterable data) {
		 
	    if (data instanceof Collection) {
	        return ((Collection<?>) data).size();
	    }
	    int counter = 0;
	    for (Object i : data) {
	        counter++;
	    }
	    return counter;
	}
	
	public String generateUUID() 
	{
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public boolean verificarEmail(String email)
	{
	    Pattern pat = Pattern.compile(regexEmail);
	    Matcher mat = pat.matcher(email);
	    if (mat.find()) {
	        return true;
	    } else {
	        return false;
	    }		
	}
	
	public boolean verificarContrasena(String contrasena)
	{
	    Pattern pat = Pattern.compile(regexPassword);
	    Matcher mat = pat.matcher(contrasena);
	    if (mat.find()) {
	        return true;
	    } else {
	        return false;
	    }		
	}	

	public Session getCurrentSessionFromJPA() 
	{
		  org.hibernate.Session session = (Session) entityManager.getDelegate();
		  return session;
	}
	
	public String getPath() 
	{
		UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
		return builder.buildAndExpand().getPath();
	}
	
	public  String prettyPrinterOneLine(Object result) 
	{
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = null;
		try {
			jsonResult = mapper.writer().writeValueAsString(result);
		} catch (JsonProcessingException e) {
			//logger.error("Error writing Logs");
		}
		return jsonResult;
	}	
}
