package Models;

import javax.persistence.Entity;

@Entity
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
