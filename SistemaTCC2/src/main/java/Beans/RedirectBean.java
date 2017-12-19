package Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import utils.SessionUtils;

/**
 * Session Bean implementation class RedirectBean
 */
@ManagedBean(name="redirect")
@RequestScoped
public class RedirectBean {
	FacesContext context = FacesContext.getCurrentInstance();
	
    public RedirectBean() {
    }

    public String goTo() {
    	HttpSession session = SessionUtils.getSession();
		String type = (String) session.getAttribute("user_type");
		
		if(type.equals("admin")) {
			return "/admin/homeAdmin.xhtml?faces-redirect=true";
		}else if (type.equals("aluno")) {
			return "/aluno/homeAluno.xhtml?faces-redirect=true";
		}else {
			return "/professor/homeProfessor.xhtml?faces-redirect=true";
    	}
    }
    
    public String goTo(String userType) {
		
    		if(userType.equals("aluno")) {
    			return "/aluno/homeAluno.xhtml?faces-redirect=true";
    		}
    		return "";
}
}
