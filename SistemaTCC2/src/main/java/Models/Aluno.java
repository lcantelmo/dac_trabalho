package Models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("aluno")
public class Aluno extends Usuario{
	
	public Aluno(){
	}
	
	public Aluno(Integer alunoId) {
		super.setId(alunoId);
	}
	
}
