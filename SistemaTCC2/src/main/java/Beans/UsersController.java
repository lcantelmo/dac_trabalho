package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import DAO.UsersEJB;
import Models.Usuario;

@ManagedBean(name="users")
@RequestScoped
public class UsersController {
    FacesContext context = FacesContext.getCurrentInstance();
        
    private Usuario user;
    
    @EJB
    UsersEJB userEJB;
    
    private List<Usuario> userList = new ArrayList();
    
    public String LogIn() {
    		String type = null;
    		type  = userEJB.autenticar(user.getUserName(), user.getPassword());
    		if(type != null) {
    			if(type.equals("ADM")) {
    				return "administrador";
    			}else if (type.equals("STD")) {
    				return "aluno";
    			}else if (type.equals("PFS")) {
    				return "professor";
    			}else if(type.equals("INVALIDO")) {
    			FacesMessage fm = new FacesMessage("Login ou senha inválidos");
    			FacesContext.getCurrentInstance().addMessage("msg", fm);
    			}
    		}
    		
    		return "usuário inexistente";
    }
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String addUser(){
        try
        {
            if(!user.getEmail().contains("@") || !user.getEmail().contains("."))
            {
                context.addMessage(null, new FacesMessage("Geçerli e-Mail değil"));
                return null;
            }
                
            
            user = userEJB.saveUser(user);
            
            userList = userEJB.allUsers();
            
            context.addMessage(null, new FacesMessage("Kullanıcı Başarı ile Eklendi..."));
            return "index.xhtml?faces-redirect=true";
        }
        catch(Exception e)
        {
            context.addMessage(null, new FacesMessage("Kullanıcı Eklenirken Hata Oluştu... \n "+e));
            return null;
        }
    }
    
    @PostConstruct
    public void getAllUsersList(){
        user = new Usuario();
        userList = userEJB.allUsers();
        
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
