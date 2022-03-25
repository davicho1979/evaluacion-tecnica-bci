package evaluacion.tecnica.bci.models.pojos;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyRequestRegisterUser 
{
	@Valid
	@NotNull
	private String name;
	
	@Valid
	@NotNull	
	private String email;
	
	@Valid
	@NotNull	
	private String password;
	
	private List<PhoneResources> phones;
	
}
