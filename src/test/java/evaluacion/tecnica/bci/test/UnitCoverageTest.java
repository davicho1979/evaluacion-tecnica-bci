package evaluacion.tecnica.bci.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import evaluacion.tecnica.bci.common.Common;
import evaluacion.tecnica.bci.models.entities.User;
import evaluacion.tecnica.bci.models.pojos.BodyRequestRegisterUser;

public class UnitCoverageTest extends BaseApplicationTest
{

	private static final Logger logger = LogManager.getLogger(UnitCoverageTest.class);

    @Autowired
    protected HttpHeaders requiredHeaderObj;
    
    @Autowired
    protected MockMvc mockMvc;
    
    @MockBean
    protected RestTemplate restTemplate;
    
    @Autowired
    protected ResourceLoader resourceLoader;

    @Autowired
    protected Common common;
    
 	@Test
	void registerUser1_success() 
	{
		MvcResult mvcResult;
		try 
		{
			logger.info("Execute test: registerUser1_success()");
			ObjectMapper mapper = new ObjectMapper(); 
			Resource resourceRequest = resourceLoader.getResource("classpath:" + "post_user_1.json");
			BodyRequestRegisterUser bodyRequestRegister = mapper.readValue(resourceRequest.getFile(), BodyRequestRegisterUser.class);

			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/users/save")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.headers(requiredHeaderObj)
					.content(mapper.writeValueAsString(bodyRequestRegister));
			
			mvcResult = mockMvc.perform(requestBuilder).andReturn();   
			MockHttpServletResponse a = mvcResult.getResponse();
			assertThat(a.getStatus()).isEqualTo(201);			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());	
		}
	}
 	
 	@Test
	void registerUser2_success() 
	{
		MvcResult mvcResult;
		try 
		{
			logger.info("Execute test: registerUser2_success()");
			ObjectMapper mapper = new ObjectMapper(); 
			Resource resourceRequest = resourceLoader.getResource("classpath:" + "post_user_2.json");
			BodyRequestRegisterUser bodyRequestRegister = mapper.readValue(resourceRequest.getFile(), BodyRequestRegisterUser.class);

			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/users/save")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.headers(requiredHeaderObj)
					.content(mapper.writeValueAsString(bodyRequestRegister));
			
			mvcResult = mockMvc.perform(requestBuilder).andReturn();   
			MockHttpServletResponse a = mvcResult.getResponse();
			assertThat(a.getStatus()).isEqualTo(201);			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());	
		}
	}
 	
 	@Test
	void registerUser3_failForPasswordRegex() 
	{
		MvcResult mvcResult;
		try 
		{
			logger.info("Execute test: registerUser3_failForPasswordRegex()");
			ObjectMapper mapper = new ObjectMapper(); 
			Resource resourceRequest = resourceLoader.getResource("classpath:" + "post_user_3.json");
			BodyRequestRegisterUser bodyRequestRegister = mapper.readValue(resourceRequest.getFile(), BodyRequestRegisterUser.class);

			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/users/save")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.headers(requiredHeaderObj)
					.content(mapper.writeValueAsString(bodyRequestRegister));
			
			mvcResult = mockMvc.perform(requestBuilder).andReturn();   
			MockHttpServletResponse a = mvcResult.getResponse();
			assertThat(a.getStatus()).isEqualTo(400);			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());	
		}
	}
 	
 	@Test
	void registerUser4_failForEmailRegex() 
	{
		MvcResult mvcResult;
		try 
		{
			logger.info("Execute test: registerUser4_failForEmailRegex()");
			ObjectMapper mapper = new ObjectMapper(); 
			Resource resourceRequest = resourceLoader.getResource("classpath:" + "post_user_4.json");
			BodyRequestRegisterUser bodyRequestRegister = mapper.readValue(resourceRequest.getFile(), BodyRequestRegisterUser.class);

			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post("/users/save")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.headers(requiredHeaderObj)
					.content(mapper.writeValueAsString(bodyRequestRegister));
			
			mvcResult = mockMvc.perform(requestBuilder).andReturn();   
			MockHttpServletResponse a = mvcResult.getResponse();
			assertThat(a.getStatus()).isEqualTo(400);			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());	
		}
	}
 	
 	@SuppressWarnings("unchecked")
	@Test
	void getAllUsers_success() 
	{
 		ObjectMapper mapper = new ObjectMapper(); 
		MvcResult mvcResult;
		try 
		{
			logger.info("Execute test: getAllUsers_success()");
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/users/getall")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.headers(requiredHeaderObj);
			
			mvcResult = mockMvc.perform(requestBuilder).andReturn();   
			MockHttpServletResponse a = mvcResult.getResponse();
			
			Iterable<User> response = mapper.readValue(a.getContentAsString(), Iterable.class);
					
		    assertAll(
		    	      "heading",
		    	      () -> assertThat(a.getStatus()).isEqualTo(200),
		    	      () -> assertThat(common.size(response)).isEqualTo(2)
		    	    );		
			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());	
		}
	} 
 	
 	@SuppressWarnings("unchecked")
	@Test
	void getUserByEmail_success() 
	{
 		ObjectMapper mapper = new ObjectMapper(); 
		MvcResult mvcResult;
		try 
		{
			logger.info("Execute test: getUserByEmail_success()");
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/users/getByEmail/feliz.972z@gmail.com")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.headers(requiredHeaderObj);
			
			mvcResult = mockMvc.perform(requestBuilder).andReturn();   
			MockHttpServletResponse a = mvcResult.getResponse();
			
			List<User> response = mapper.readValue(a.getContentAsString(), List.class);
					
		    assertAll(
		    	      "heading",
		    	      () -> assertThat(a.getStatus()).isEqualTo(200),
		    	      () -> assertThat(response.size()).isEqualTo(1)
		    	    );		
			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());	
		}
	} 
 	
	@Test
	void getUserByEmail_fail() 
	{
		MvcResult mvcResult;
		try 
		{
			logger.info("Execute test: getUserByEmail_fail()");
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/users/getByEmail/email.no.exists@gmail.com")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.headers(requiredHeaderObj);
			
			mvcResult = mockMvc.perform(requestBuilder).andReturn();   
			MockHttpServletResponse a = mvcResult.getResponse();
			
			//List<User> response = mapper.readValue(a.getContentAsString(), List.class);
					
			assertThat(a.getStatus()).isEqualTo(204);	
			
		} 
		catch (Exception e) 
		{
			logger.error(e.getMessage());	
		}
	}  	
}
