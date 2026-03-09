package init.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCliente;
	private String usuario;
	private String password;
	private String email;
	private int telefono;
	public Cliente(String usuario, String password, String email, int telefono) {
		this.usuario = usuario;
		this.password = password;
		this.email = email;
		this.telefono = telefono;
	}
	
}
