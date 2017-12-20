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
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Administrador editAdministradors(Administrador admin) throws Exception {
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
	public void deleteAdministradorAdministrador(Administrador admin) {
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
		return manager.createQuery(
				"select p from Administrador p where p.id=?1", Administrador.class)
				.setParameter(1, id)
				.getSingleResult();
	}
	
	public void editarDados(Long id, String nome, String matricula, String email) {
		Administrador admin = this.buscaPeloId(id);
		admin.setName(nome);
		admin.setMatricula(matricula);
		admin.setEmail(email);
		manager.persist(admin);
		manager.flush();
				
	}
}
