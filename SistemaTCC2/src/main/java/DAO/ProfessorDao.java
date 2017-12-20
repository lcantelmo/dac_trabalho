package DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Models.Aluno;
import Models.Professor;

@Stateless
public class ProfessorDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Professor professor) {
		manager.persist(professor);
		manager.flush();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Professor editProfessores(Professor professor) throws Exception {
        try
        {
        		manager.merge(professor);
        		manager.flush();
        		return professor;
        }
        catch(Exception e)
        {
            
            System.out.println(e);
            return null;
        }
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
