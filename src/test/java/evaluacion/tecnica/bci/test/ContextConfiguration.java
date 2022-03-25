package evaluacion.tecnica.bci.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class ContextConfiguration 
{
		@Bean(name="requiredHeaderObj")
		public HttpHeaders getRequiredHeader() 
		{
		    HttpHeaders headers = new HttpHeaders();
		    headers.set("Content-Type", "application/json");
		    return headers;
		}		
		
		@Bean(name="isUnitTest")
		public boolean isUnitTest() 
		{
		    return true;
		}		
}
