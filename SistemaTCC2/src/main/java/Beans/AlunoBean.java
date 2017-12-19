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
	
	@Inject
	private AlunoDao dao;
	
	
	public void salvar() {
		aluno.setUserType("aluno");
		try {
			dao.salvar(aluno);
			FacesMessage fm = new FacesMessage("Aluno Cadastrado");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
			
		} catch (Exception e) {
			FacesMessage fm = new FacesMessage("Aluno não cadastrado");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
			System.out.println("Erro:   "+e.getStackTrace());
		}
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
