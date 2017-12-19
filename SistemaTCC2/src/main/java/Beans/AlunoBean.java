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
	
	
	public String salvar() {
		aluno.setUserType("aluno");
		if(!this.aluno.getEmail().contains("@") || !this.aluno.getEmail().contains("."))
        {
            context.addMessage(null, new FacesMessage("Invalid email"));
            return null;
        }
		try {
			dao.salvar(aluno);
			context.addMessage(null, new FacesMessage("Aluno Cadastrado"));
			System.out.println("Aluno Cadastrado" + aluno);
			this.aluno = new Aluno();
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Aluno não cadastrado"));
			System.out.println("Erro:   "+e.getStackTrace());
		}
		
		return null;
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
