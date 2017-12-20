package DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import Models.Administrador;
import Models.Aluno;

@Stateless
public class AdminDao {
	
	@PersistenceContext
	private EntityManager manager;
	

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Administrador admin) {
		manager.persist(admin);
		manager.flush();
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Administrador editAdmin(Administrador admin) throws Exception {
        try
        {
        		manager.merge(admin);
        		manager.flush();
        		return admin;
        }
        catch(Exception e)
        {
            
            System.out.println(e);
            return null;
        }
    }
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAdmin(Administrador admin) {
		 try
	        {
	            manager.remove(manager.merge(admin));
	            manager.flush();
	        }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	}
	
	public List<Administrador> listar(){
		return manager.createQuery(
				"select a from Administrador a", Administrador.class)
				.getResultList();
	}
	
	public Administrador buscaPeloId(Long id){
		try {
			Administrador admin = manager.find(Administrador.class, id);
			return admin;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
