package ifpi.tep.rafaellins.exercicio01.model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false, length = 128, unique = true)
	private String username;
	
	@Column(nullable = false, length = 128, unique = true)
	private String password;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	
	@OrderColumn
	@Fetch(FetchMode.SELECT)
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JoinTable(joinColumns = @JoinColumn(name="usuario_id"), inverseJoinColumns = @JoinColumn(name="registroPeso_id"))
	@OrderBy("horario ASC")
	private List<RegistroPeso> historicoPeso;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(final Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public static String hashPassword(final String password) {
		if (password != null) {
			String result;
			
			try {
				final MessageDigest md5 = MessageDigest.getInstance("MD5");
				final MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
				final Random random = new Random(password.hashCode() + password.length());
				byte[] data = password.getBytes();
				
				for (int i = (Math.abs(password.hashCode()) % 1000) + 2; i > 0; i--) {
					data = (random.nextBoolean() ? md5.digest(data) : sha512.digest(data));
				}
				
				data = sha512.digest(data);
				
				result = asHex(data);
			} catch (final NoSuchAlgorithmException e) {
				result = String.valueOf(password.hashCode());
				e.printStackTrace();
			}
			
			return result;
		}
		else {
			return null;
		}
	}
	
	private static String asHex(final byte[] bytes) {
		final StringBuilder str = new StringBuilder();
		
		for (final byte b : bytes) {
			str.append(b % 2 == 0 ? String.format("%02X", b) : String.format("%02x", b));
		}
		
		return str.toString();
	}
}
