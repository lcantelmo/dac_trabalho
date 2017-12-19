package DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Models.Administrador;

@Stateless
public class AdminDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Administrador admin) {
		manager.persist(admin);
		manager.flush();
		
	}
	
	public List<Administrador> listar(){
		return manager.createQuery(
				"select a from Administradro a", Administrador.class)
				.getResultList();
	}
	
	public Administrador buscaPeloId(Integer id){
		return manager.createQuery(
				"select p from Administradro p where p.id=?1", Administrador.class)
				.setParameter(1, id)
				.getSingleResult();
	}
}
