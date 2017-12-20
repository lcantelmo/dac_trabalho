package DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Models.Aluno;

@Stateless
public class AlunoDao {
	@PersistenceContext
	private EntityManager manager;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Aluno aluno) {
		manager.persist(aluno);
		manager.flush();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Aluno editAlunos(Aluno aluno) throws Exception {
        try
        {
        		manager.merge(aluno);
        		manager.flush();
        		return aluno;
        }
        catch(Exception e)
        {
            
            System.out.println(e);
            return null;
        }
    }
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAluno(Aluno aluno) {
		 try
	        {
	            manager.remove(manager.merge(aluno));
	            manager.flush();
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	}
	
	public List<Aluno> listar(){
		return manager.createQuery(
				"select a from Aluno a", Aluno.class)
				.getResultList();
	}
	
	public Aluno buscaPeloId(Long id){
		try {
			Aluno aluno = manager.find(Aluno.class, id);
			return aluno;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
