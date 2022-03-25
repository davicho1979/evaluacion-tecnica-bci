package evaluacion.tecnica.bci.models.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "UserEntity")
@Table(name="USER")
@Getter
@Setter
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USER")
	private String idUser;

	@Column(name="NAME")
	private String name;
	
	@Column(name="EMAIL")
	private String email;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="TOKEN")	
	private String token;
	
	@Column(name="ACTIVE")	
	private Boolean isActive;
	
	@Column(name="DATE_CREATION")
	private Timestamp dateCreation;

	@Column(name="DATE_UPDATE")
	private Timestamp dateUpdate;

	@OneToMany(cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Phone> phones;


}