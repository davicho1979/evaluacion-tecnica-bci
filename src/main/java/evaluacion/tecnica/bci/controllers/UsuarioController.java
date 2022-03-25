package evaluacion.tecnica.bci.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import evaluacion.tecnica.bci.common.Common;
import evaluacion.tecnica.bci.models.pojos.BodyRequestRegisterUser;
import evaluacion.tecnica.bci.services.UserService;

@RestController
public class UsuarioController 
{

	@Autowired
	private UserService usuarioService;

	@Autowired
	private Common common;
	
	private static final Logger logger = LogManager.getLogger(UsuarioController.class);
	
	@RequestMapping
	(
	 value = "/users/save", 
	 method = RequestMethod.POST,
	 produces = {MediaType.APPLICATION_JSON_VALUE},
	 consumes = MediaType.ALL_VALUE
	)	
	public @ResponseBody ResponseEntity<?> registryUser
	(
			@Valid @RequestBody BodyRequestRegisterUser request
	) 
	{
		ThreadContext.put("LAYER", "REQUEST");
		logger.info(common.prettyPrinterOneLine(request));
		return usuarioService.guardarUsuario(request);
	}	
  
	@GetMapping
	(
	 value = "/users/getall", 
	 produces = {MediaType.APPLICATION_JSON_VALUE},
	 consumes = MediaType.ALL_VALUE
	)	
	public @ResponseBody ResponseEntity<?> getAllUser() 
	{
		ThreadContext.put("LAYER", "REQUEST");
		logger.info(common.prettyPrinterOneLine(common.getPath()));
		return usuarioService.listarAllUsuarios();
	}
	
	@RequestMapping
	(
			value = "/users/getById/{userId}", 
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = MediaType.ALL_VALUE
	)	
	public @ResponseBody ResponseEntity<?> getUserById
	(
			@PathVariable(required = true, name="userId") String userId
	) 
	{
		ThreadContext.put("LAYER", "REQUEST");
		logger.info(common.prettyPrinterOneLine(common.getPath()));
		return usuarioService.obtenerUserById(userId);
	}
	
	@RequestMapping
	(
			value = "/users/getByEmail/{email}", 
			method = RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE},
			consumes = MediaType.ALL_VALUE
	)	
	public @ResponseBody ResponseEntity<?> getUserByEmail
	(
			@PathVariable(required = true, name="email") String email
	) 
	{
		ThreadContext.put("LAYER", "REQUEST");
		logger.info(common.prettyPrinterOneLine(common.getPath()));
		return usuarioService.obtenerUserByEmail(email);
	}	
}
