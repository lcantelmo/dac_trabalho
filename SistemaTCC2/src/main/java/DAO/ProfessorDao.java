package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Models.Professor;

public class ProfessorDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Professor professor) {
		manager.persist(professor);
	}
	
	public List<Professor> listar(){
		return manager.createQuery(
				"select p from Professor p", Professor.class)
				.getResultList();
	}
	
	public Professor buscaPeloId(Integer id){
		return manager.createQuery(
				"select p from Professor p where p.id=?1", Professor.class)
				.setParameter(1, id)
				.getSingleResult();
	}
}
