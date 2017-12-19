package Beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import DAO.ProfessorDao;
import Models.Professor;

@ManagedBean
@RequestScoped
public class ProfessorBean {
	
	private Professor professor = new Professor();
	
	@Inject
	private ProfessorDao dao;
	
	public void salvar() {
		getProfessor().setUserType("prof");
		try {
			dao.salvar(getProfessor());
			FacesMessage fm = new FacesMessage("Professor Cadastrado");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
			
		} catch (Exception e) {
			FacesMessage fm = new FacesMessage("Professor não cadastrado");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
			System.out.println("Erro:   "+e.getStackTrace());
		}
	}
	
	public List<Professor> listar() {
		return dao.listar();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
