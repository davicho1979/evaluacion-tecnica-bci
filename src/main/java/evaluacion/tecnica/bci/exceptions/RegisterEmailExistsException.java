package evaluacion.tecnica.bci.exceptions;

public class RegisterEmailExistsException extends RuntimeException 
{

	private static final long serialVersionUID = 1L;

	public RegisterEmailExistsException(String message) {
		super(message);
	}


}
