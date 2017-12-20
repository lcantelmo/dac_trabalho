package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import DAO.AdminDao;
import Models.Administrador;

@ManagedBean
@SessionScoped
public class AdminBean {
	
	@Inject
	private AdminDao dao;
	
	@Inject
	private RedirectBean redirectBean;
	
	FacesContext context = FacesContext.getCurrentInstance();

	private Administrador admin = new Administrador();
	private Administrador adminSelecionado = new Administrador();
	
	private int  indexAdmin = -1;
	
	private HtmlDataTable dataTable;
	   
	private  List<Administrador> listaAdmin = new ArrayList<Administrador>();
	
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

	public String alterarLinha() {
		setAdminSelecionado((Administrador) dataTable.getRowData());
		indexAdmin = dataTable.getRowIndex();
		return "/admin/formAdmin.xhtml?faces-redirect=true";
	}
	
	public String alterar() {
		Administrador adminSalvo = null ;
        System.out.println("Administrador Selecionado  = "+getAdminSelecionado().getId() + "|" + getAdminSelecionado().getName());
        
       List<Administrador> alunos = listar();
       adminSalvo = alunos.get(indexAdmin);
       
       if(adminSalvo!= null && !comparaAdminALterado(adminSalvo, getAdminSelecionado())) {
        		try {
					dao.editAdmin(adminSelecionado);
					FacesMessage fm = new FacesMessage("Administrador alterado com sucesso");
					FacesContext.getCurrentInstance().addMessage("msg", fm);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }else {
        	FacesMessage fm = new FacesMessage("Nenhuma mudança");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
        }
       return "/admin/formAdmin.xhtml?faces-redirect=true";
	}
	
	private boolean comparaAdminALterado(Administrador adminSalvo, Administrador alunoSelecionado) {
		return adminSalvo.getEmail().equals(alunoSelecionado.getEmail()) &&
				adminSalvo.getMatricula().equals(alunoSelecionado.getMatricula()) &&
				adminSalvo.getName().equals(alunoSelecionado.getName()) &&
				adminSalvo.getUserType().equals(alunoSelecionado.getUserType());
}

public String excluirLinha() {
	Administrador alunoSelecionado = (Administrador) dataTable.getRowData();
	try {
		dao.deleteAdmin(dao.buscaPeloId(alunoSelecionado.getId()));
		this.listaAdmin = dao.listar();
		context.addMessage(null, new FacesMessage("Administrador excluído"));
		return "/admin/alterarAdmin.xhtml?faces-redirect=true";
	}
	catch(Exception e)
	{
		context.addMessage(null, new FacesMessage("Não foi possível excluir aluno"+e));
		return null;
	}
}

        
public List<Administrador> listar() {
	this.listaAdmin = dao.listar();
	return this.listaAdmin;
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

	public Administrador getAdminSelecionado() {
		return adminSelecionado;
	}

	public void setAdminSelecionado(Administrador adminSelecionado) {
		this.adminSelecionado = adminSelecionado;
	}
	
}
