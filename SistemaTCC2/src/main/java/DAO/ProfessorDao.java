package DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Models.Professor;

@Stateless
public class ProfessorDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Professor prof) {
		manager.persist(prof);
		manager.flush();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Professor editProfs(Professor prof) throws Exception {
        try
        {
        		manager.merge(prof);
        		manager.flush();
        		return prof;
        }
        catch(Exception e)
        {
            
            System.out.println(e);
            return null;
        }
    }
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteProfessor(Professor prof) {
		 try
	        {
	            manager.remove(manager.merge(prof));
	            manager.flush();
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	}
	
	public List<Professor> listar(){
		return manager.createQuery(
				"select a from Professor a", Professor.class)
				.getResultList();
	}
	
	public Professor buscaPeloId(Long id){
		try {
			Professor prof = manager.find(Professor.class, id);
			return prof;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
