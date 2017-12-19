package Beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import DAO.AdminDao;
import Models.Administrador;

@ManagedBean
@RequestScoped
public class AdminBean {
	private Administrador admin = new Administrador();
	
	@Inject
	private AdminDao dao;
	
	
	public void salvar() {
		admin.setUserType("admin");
		try {
			dao.salvar(admin);
			FacesMessage fm = new FacesMessage("Administrador Cadastrado");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
			
		} catch (Exception e) {
			FacesMessage fm = new FacesMessage("Administrador não cadastrado");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
			System.out.println("Erro:   "+e.getStackTrace());
		}
	}
	
	public List<Administrador> listar() {
		return dao.listar();
	}

	public Administrador getAdministrador() {
		return admin;
	}

	public void setAdministrador(Administrador admin) {
		this.admin = admin;
	}
	
}
