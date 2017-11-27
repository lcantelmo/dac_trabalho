package Models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="prof")
public class Professor extends Usuario{
	
	public Professor() {
	}
	
	public Professor(Integer id) {
		super.setId(id);
	}
}
