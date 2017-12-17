package DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import Models.Users;



/**
 *
 * @author MstfDryl
 */
@Stateless
public class UsersEJB {
    
    @PersistenceContext
    private EntityManager em;
    
    
	public String autenticar(String username, String password) {
		Users user = getUsername(username);
		
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
  
     
    public List<Users> allUsers(){
        TypedQuery<Users> users = em.createNamedQuery("allUsers", Users.class);
        return users.getResultList();
    }
    
    public Users getUsername(String username){
    	TypedQuery<Users> user =  em.createNamedQuery("getUsername", Users.class).setParameter("username", username);
    	  
    	  return user.getSingleResult();
    }
    
    public Users getEmail(String email){
        return em.find(Users.class, email);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Users saveUser(Users user){
        em.persist(user);
        em.flush();
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Users mergeUser(Users user){
        
        System.out.println("--------------------5--------------------------------");
        System.out.println(user.getId());
        System.out.println("----------------------6------------------------------");
        em.merge(em.find(Users.class,user.getId()));
        
        System.out.println("------------------------7----------------------------");
        return user;
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deleteUsers(Users user) throws Exception{
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
    public Users editUsers(Users user) throws Exception {
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
