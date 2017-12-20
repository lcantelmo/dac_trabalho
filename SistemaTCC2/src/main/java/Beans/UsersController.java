package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DAO.UsersEJB;
import Models.Usuario;
import utils.SessionUtils;

@ManagedBean(name="users")
@SessionScoped
public class UsersController {
        
    private Usuario user = new Usuario();
    
    @EJB
    UsersEJB userEJB;
    
    RedirectBean redirectBean = new RedirectBean();
    
    private List<Usuario> userList = new ArrayList();
    
    public String LogIn() {
    		Usuario user= null;
    		user = userEJB.autenticar(this.user.getMatricula(), this.user.getPassword());
    		if(user != null) {
    			
    			if(user.getUserType().equals("INVALIDO")) {
    				FacesMessage fm = new FacesMessage("Login ou senha Inválidos");
    				FacesContext.getCurrentInstance().addMessage("msg", fm);
    			}else {
    				
    				HttpSession session = SessionUtils.getSession();
    				session.setAttribute("name",user.getName());
    				session.setAttribute("user_type",user.getUserType());
    				
    				return redirectBean.goTo(user.getUserType());
    			}
    		}
    		FacesMessage fm = new FacesMessage("Usuário inexistente");
    		FacesContext.getCurrentInstance().addMessage("msg", fm);
		return "";
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
    
   
    public List<Usuario> getUserList() {
        return userList;
    }

    public void setUserList(List<Usuario> userList) {
        this.userList = userList;
        }
    
}
