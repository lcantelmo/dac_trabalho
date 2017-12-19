package Models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("aluno")
public class Aluno extends Usuario{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Aluno(){
	}
	
	public Aluno(Long alunoId) {
		super.setId(alunoId);
	}
	
}
