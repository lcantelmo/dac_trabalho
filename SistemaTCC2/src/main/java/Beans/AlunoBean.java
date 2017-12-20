package Beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import DAO.AlunoDao;
import Models.Aluno;

@ManagedBean
@RequestScoped
public class AlunoBean {
	private Aluno aluno = new Aluno();
	  FacesContext context = FacesContext.getCurrentInstance();
	
	@Inject
	private AlunoDao dao;
	
	@Inject
	private RedirectBean redirectBean;
	
	
	public String salvar() {
		aluno.setUserType("aluno");
		if(!this.aluno.getEmail().contains("@") || !this.aluno.getEmail().contains("."))
        {
            context.addMessage(null, new FacesMessage("Invalid email"));
            return null;
        }
		try {
			dao.salvar(aluno);
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage("Aluno Cadastrado com Sucesso!"));
			System.out.println("Aluno Cadastrado" + aluno);
			redirectBean.goTo();
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Falha no Cadastrado do Aluno"));
			System.out.println("Erro:   "+e.getStackTrace());
			redirectBean.goTo();
		}
		
		return null;
	}
	
	public String salvarNovoUsuario() {
		aluno.setUserType("aluno");
		if(!this.aluno.getEmail().contains("@") || !this.aluno.getEmail().contains("."))
        {
			FacesContext.getCurrentInstance().getExternalContext()
	        	.getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("E-mail Inválido!"));
            return null;
        }
		try {
			dao.salvar(aluno);
			FacesContext.getCurrentInstance().getExternalContext()
	        	.getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage("Aluno Cadastrado com Sucesso!"));
			System.out.println("Aluno Cadastrado" + aluno);
			this.aluno = new Aluno();
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SistemaTCC2/login.xhtml?faces-redirect=true");
			return null;
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().getExternalContext()
	        	.getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage("Falha no Cadastro do Aluno"));
			this.aluno = new Aluno();
			System.out.println("Erro:   "+e.getStackTrace());
			return null;
		}
	}
	public String alterar() {
		 
		if(!this.aluno.getEmail().contains("@") || !this.aluno.getEmail().contains("."))
	        {
	            context.addMessage(null, new FacesMessage("Invalid email"));
	            return null;
	        }
		try {
			dao.editAlunos(this.aluno);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Não foi possível alterar aluno"+e));
		}
		return null;
	}
	
	public List<Aluno> listar() {
		return dao.listar();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
}
