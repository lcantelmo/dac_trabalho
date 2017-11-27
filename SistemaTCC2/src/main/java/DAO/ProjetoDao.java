package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import Models.Projeto;

public class ProjetoDao {
	@PersistenceContext
	private EntityManager manager;
	
	public void salvar(Projeto projeto) {
		manager.persist(projeto);
	}
	
	public List<Projeto> listar(){
		return manager.createQuery(
				"select p from Projeto p", Projeto.class)
				.getResultList();
	}
	
	public Projeto buscaPeloId(Integer id){
		return manager.createQuery(
				"select p from Projeto p where p.id=?1", Projeto.class)
				.setParameter(1, id)
				.getSingleResult();
	}
}
