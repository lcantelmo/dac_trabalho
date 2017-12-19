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
