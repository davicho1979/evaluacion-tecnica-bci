package evaluacion.tecnica.bci.models.pojos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(Include.NON_NULL)
public class GenericResponse 
{
    private String timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
