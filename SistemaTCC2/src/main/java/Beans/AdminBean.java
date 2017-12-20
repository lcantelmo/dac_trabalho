package Beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import DAO.AdminDao;
import Models.Administrador;

@ManagedBean
@RequestScoped
public class AdminBean {
	FacesContext context = FacesContext.getCurrentInstance();
	private Administrador admin = new Administrador();
	
	@Inject
	private AdminDao dao;
	
	@Inject
	private RedirectBean redirectBean;
	
	private HtmlDataTable dataTable;
	   
	public String salvar() {
		admin.setUserType("admin");
		if(!this.admin.getEmail().contains("@") || !this.admin.getEmail().contains("."))
        {
            context.addMessage(null, new FacesMessage("Invalid email"));
            return null;
        }
		try {
			dao.salvar(admin);
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage("Administrador Cadastrado com Sucesso!"));
			System.out.println("Administrador Cadastrado" + admin);
			redirectBean.goTo();
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Falha no Cadastrado do Administrador"));
			System.out.println("Erro:   "+e.getStackTrace());
			redirectBean.goTo();
		}
		
		return null;
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

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
	
}
