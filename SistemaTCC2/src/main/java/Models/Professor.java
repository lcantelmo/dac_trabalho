package Models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("prof")
public class Professor extends Usuario{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Professor() {
	}
	
	public Professor(Long id) {
		super.setId(id);
	}
}
