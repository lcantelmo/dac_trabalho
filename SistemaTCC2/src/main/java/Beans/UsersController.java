package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.UsersEJB;
import Models.Usuario;
import utils.SessionUtils;

@ManagedBean(name="users")
@RequestScoped
public class UsersController {
    FacesContext context = FacesContext.getCurrentInstance();
        
    private Usuario user = new Usuario();
    
    @EJB
    UsersEJB userEJB;
    
    private List<Usuario> userList = new ArrayList();
    
    public String LogIn() {
    		Usuario user= null;
    		user = userEJB.autenticar(this.user.getMatricula(), this.user.getPassword());
    		if(user != null) {
    			
    			if(user.getUserType().equals("INVALIDO")) {
	    			FacesMessage fm = new FacesMessage("Login ou senha inválidos");
	    			FacesContext.getCurrentInstance().addMessage("msg", fm);
    			}else {
    				
    				HttpSession session = SessionUtils.getSession();
    				session.setAttribute("name",user.getName());
    				
    				if(user.getUserType().equals("admin")) {
    					return "/admin/homeAdmin.xhtml?faces-redirect=true";
    				}else if (user.getUserType().equals("aluno")) {
    					return "/aluno/homeAluno.xhtml?faces-redirect=true";
    				}else if (user.getUserType().equals("prof")) {
    					return "/professor/homeProfessor.xhtml?faces-redirect=true";
    				}
    			}
    		}else {
    			FacesMessage fm = new FacesMessage("usuário inexistente");
    			FacesContext.getCurrentInstance().addMessage("msg", fm);
    		}
		return "/login?faces-redirect=true";
    }
    

  	public String logOut() {
  		HttpSession session = SessionUtils.getSession();
  		session.invalidate();
  		return "/login?faces-redirect=true";
  	}
	
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    
    public String editUsers(Usuario user){
        this.user = user; 
        return "edit.xhtml";
    }
    public String editUser(){
        System.out.println("--------------------1--------------------------------");
        System.out.println("--------------------1--------------------------------");
        System.out.println(this.user.getId());
        System.out.println("--------------------1--------------------------------");
        System.out.println("--------------------1--------------------------------");

        
        if(!this.user.getEmail().contains("@") || !this.user.getEmail().contains("."))
        {
            context.addMessage(null, new FacesMessage("Invalid email"));
            return null;
        }

        try
        {
        System.out.println("--------------------1--------------------------------");
        System.out.println("---------------------2-------------------------------");
            userEJB.editUsers(this.user);
            
        System.out.println("--------------------3--------------------------------");
            userList = userEJB.allUsers();
            
        System.out.println("---------------------4-------------------------------");
            return "index.xhtml?faces-redirect=true";
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage("Kullanıcı Güncellerken Hata Oluştu... \n "+e));
            return null;
        }
        
    }
    public String deleteUser(Usuario getUser){
        try
        {
            userEJB.deleteUsers(getUser);
            userList = userEJB.allUsers();
            
            context.addMessage(null, new FacesMessage("Kullanıcı Başarı ile Silindi..."));
            return "index.xhtml?faces-redirect=true";
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage("Kullanıcı Silinirken Hata Oluştu... \n "+e));
            return null;
        }
    }
    
   
    public List<Usuario> getUserList() {
        return userList;
    }

    public void setUserList(List<Usuario> userList) {
        this.userList = userList;
        }
    
}
