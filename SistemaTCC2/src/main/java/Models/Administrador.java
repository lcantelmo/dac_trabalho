package Models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="admin")
public class Administrador extends Usuario{
	
	public Administrador() {
	}
	
	public Administrador(Long id) {
		super.setId(id);
	}

}
