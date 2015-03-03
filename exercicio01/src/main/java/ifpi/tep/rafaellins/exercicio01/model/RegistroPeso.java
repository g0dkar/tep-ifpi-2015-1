package ifpi.tep.rafaellins.exercicio01.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class RegistroPeso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Usuario usuario;
	
	@Column(nullable = false, precision = 4, scale = 2)
	private double peso;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date horario;
}
