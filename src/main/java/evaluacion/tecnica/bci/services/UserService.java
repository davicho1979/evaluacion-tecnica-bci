package evaluacion.tecnica.bci.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import evaluacion.tecnica.bci.common.Common;
import evaluacion.tecnica.bci.common.JWTGenerator;
import evaluacion.tecnica.bci.exceptions.RegisterEmailExistsException;
import evaluacion.tecnica.bci.exceptions.RegisterEmailInvalidException;
import evaluacion.tecnica.bci.models.entities.Phone;
import evaluacion.tecnica.bci.models.entities.User;
import evaluacion.tecnica.bci.models.pojos.BodyRequestRegisterUser;
import evaluacion.tecnica.bci.models.pojos.PhoneResources;
import evaluacion.tecnica.bci.repositories.UserRepository;

@Service
public class UserService 
{

    @Autowired
    private UserRepository usuarioRepository;
    
	@Autowired
	private Common common;
	
	@Autowired
	private JWTGenerator jwtGenerator;
	
	@Autowired
	private Environment env;
	
	private static final Logger logger = LogManager.getLogger(UserService.class);
	
    @SuppressWarnings("rawtypes")
	public ResponseEntity<?> guardarUsuario(BodyRequestRegisterUser usuarioArg) 
    {
    	if (!common.verificarEmail(usuarioArg.getEmail()))
    	{
    		throw new RegisterEmailInvalidException("Invalid email.  No match regex [regex.validate.email]");
    	}
    	
    	if (!common.verificarContrasena(usuarioArg.getPassword()))
    	{
    		throw new RegisterEmailInvalidException("Invalid password.  No match regex [regex.validate.password]");
    	}
    	
    	List<User> listUsersByEmail = usuarioRepository.findByEmail(usuarioArg.getEmail());
    	if (!listUsersByEmail.isEmpty())
    		throw new RegisterEmailExistsException("Email already exists");
    		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String uuid = common.generateUUID();
		Integer duration = Integer.valueOf(env.getProperty("app.milliseconds.jwt.duration"));
		
    	User user = new User();
    	user.setIdUser(uuid);
    	user.setName(usuarioArg.getName());
    	user.setEmail(usuarioArg.getEmail());
    	user.setPassword(usuarioArg.getPassword());
    	user.setDateCreation(timestamp);
    	user.setToken(jwtGenerator.createJWT(uuid, usuarioArg.getName(), usuarioArg.getEmail(), duration));
    	user.setIsActive(true);
    	
    	List<PhoneResources> phoneResources = usuarioArg.getPhones();
    	
    	if (phoneResources!=null)
    	{
        	List<Phone> phoneList = new ArrayList<Phone>();
        	for (Iterator iterator = phoneResources.iterator(); iterator.hasNext();) 
        	{
    			PhoneResources phoneResource = (PhoneResources) iterator.next();
    			Phone phoneAux = new Phone();
    			phoneAux.setIdUser(uuid);
    			phoneAux.setNumber(phoneResource.getNumber());
    			phoneAux.setCitycode(phoneResource.getCitycode());
    			phoneAux.setContrycode(phoneResource.getCountrycode());
    			phoneList.add(phoneAux);
    		}
        	
        	user.setPhones(phoneList);
    	}

    	User uu = usuarioRepository.save(user);
    	uu.setPassword("********");
    	ThreadContext.put("LAYER", "RESPONSE");
    	logger.info(common.prettyPrinterOneLine(ResponseEntity.status(HttpStatus.CREATED).body(uu)));
    	return ResponseEntity.status(HttpStatus.CREATED).body(uu);    	
    }   
      
    public ResponseEntity<?> listarAllUsuarios()
    {
        Iterable<User> userList = usuarioRepository.findAll();
    	ThreadContext.put("LAYER", "RESPONSE");
    	logger.info(common.prettyPrinterOneLine(ResponseEntity.status(HttpStatus.OK).body(userList)));
    	return ResponseEntity.status(HttpStatus.OK).body(userList);         
    } 
    
    public ResponseEntity<?> obtenerUserById(String userId)
    {
        Optional<User> user = usuarioRepository.findById(userId);
    	ThreadContext.put("LAYER", "RESPONSE");
    	
    	if (user.isEmpty())
    	{
    		logger.info(common.prettyPrinterOneLine(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)));
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);    		
    	}
    	else
    	{
    		logger.info(common.prettyPrinterOneLine(ResponseEntity.status(HttpStatus.OK).body(user.get())));
    		return ResponseEntity.status(HttpStatus.OK).body(user.get());  
    	}
    }
    
    public ResponseEntity<?> obtenerUserByEmail(String email)
    {
        List<User> usersList = usuarioRepository.findByEmail(email);
    	ThreadContext.put("LAYER", "RESPONSE");
    	
    	if (usersList.isEmpty())
    	{
    		logger.info(common.prettyPrinterOneLine(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)));
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);    		
    	}
    	else
    	{
    		logger.info(common.prettyPrinterOneLine(ResponseEntity.status(HttpStatus.OK).body(usersList)));
    		return ResponseEntity.status(HttpStatus.OK).body(usersList);  
    	}
    }     
}
