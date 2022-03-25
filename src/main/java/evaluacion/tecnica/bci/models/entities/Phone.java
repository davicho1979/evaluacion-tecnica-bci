package evaluacion.tecnica.bci.models.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "PhoneEntity")
@Table(name="PHONE")
@Getter
@Setter
public class Phone implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotFound(action = NotFoundAction.IGNORE)
	@SequenceGenerator(name="T_GENERATOR", sequenceName="SEQ_PHONE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="T_GENERATOR")	
	@Column(name="ID_PHONE")
	private int idPhone;

	//@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
	//@NotFound(action = NotFoundAction.IGNORE)
	//@JoinColumn(name="ID_USER")
	@Column(name="ID_USER")
	private String idUser;
	
	@Column(name="CITY_CODE")
	private String citycode;

	@Column(name="COUNTRY_CODE")
	private String contrycode;

	@Column(name="NUMBER")
	private String number;



}