package DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Models.Usuario;



/**
 *
 * @author MstfDryl
 */
@Stateless
public class UsersEJB {
    
    @PersistenceContext
    private EntityManager em;
    
    
	public String autenticar(String username, String password) {
		Usuario user = getUsername(username);
		
		if(user != null && user.getPassword().equals(password)) {
			if(user.getUserType().equals("ADM") ) {
				return "ADM";
			} else if(user.getUserType().equals("STD") ) {
				return "STD";
			}else if(user.getUserType().equals("PFS") ) {
				return "PFS"; 
			}
			
		}
		return "INVALIDO";
		
	}
  
     
    public List<Usuario> allUsers(){
        TypedQuery<Usuario> usuario = em.createNamedQuery("allUsers", Usuario.class);
        return usuario.getResultList();
    }
    
    public Usuario getUsername(String username){
    	TypedQuery<Usuario> user =  em.createNamedQuery("getUsername", Usuario.class).setParameter("username", username);
    		if(user != null) {
    			return user.getSingleResult();
    		}else
    			return null;
    }
    
    public Usuario getEmail(String email){
        return em.find(Usuario.class, email);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Usuario saveUser(Usuario user){
        em.persist(user);
        em.flush();
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Usuario mergeUser(Usuario user){
        
        System.out.println("--------------------5--------------------------------");
        System.out.println(user.getId());
        System.out.println("----------------------6------------------------------");
        em.merge(em.find(Usuario.class,user.getId()));
        
        System.out.println("------------------------7----------------------------");
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteUsers(Usuario user) throws Exception{
        try
        {
            em.remove(em.merge(user));
            em.flush();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Usuario editUsers(Usuario user) throws Exception {
        try
        {
            em.merge(user);
            em.flush();
            return user;
        }
        catch(Exception e)
        {
            
            System.out.println(e);
            return null;
        }
    }

    
}
