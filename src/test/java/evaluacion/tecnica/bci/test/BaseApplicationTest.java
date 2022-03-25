package evaluacion.tecnica.bci.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import evaluacion.tecnica.bci.EvaluacionTecnicaBCIApplication;

@ServletComponentScan
@SpringBootTest(classes = {EvaluacionTecnicaBCIApplication.class})
@ContextConfiguration(classes = {ContextConfiguration.class})
@TestPropertySource(locations = {"classpath:application_test.properties"})
@AutoConfigureMockMvc
public class BaseApplicationTest 
{ 	
	@Test
	void contextLoads() 
	{
		assertThat(true).isEqualTo(true);
	}
	
}
