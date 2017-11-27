package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Models.Aluno;

public class AlunoDao {
	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Aluno aluno) {
		manager.persist(aluno);
	}
	
	public List<Aluno> listar(){
		return manager.createQuery(
				"select a from Aluno a", Aluno.class)
				.getResultList();
	}
	
	public Aluno buscaPeloId(Integer id){
		return manager.createQuery(
				"select p from Professor p where p.id=?1", Aluno.class)
				.setParameter(1, id)
				.getSingleResult();
	}
}
