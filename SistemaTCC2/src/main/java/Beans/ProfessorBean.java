package Beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import DAO.ProfessorDao;
import Models.Professor;

@ManagedBean
@RequestScoped
public class ProfessorBean {
	FacesContext context = FacesContext.getCurrentInstance();
	private Professor professor = new Professor();
	
	@Inject
	private ProfessorDao dao;
	
	@Inject
	private RedirectBean redirectBean;
	
	private HtmlDataTable dataTable;
	   
	public String salvar() {
		professor.setUserType("prof");
		if(!this.professor.getEmail().contains("@") || !this.professor.getEmail().contains("."))
        {
            context.addMessage(null, new FacesMessage("Invalid email"));
            return null;
        }
		try {
			dao.salvar(professor);
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage("Professor Cadastrado com Sucesso!"));
			System.out.println("Professor Cadastrado" + professor);
			redirectBean.goTo();
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Falha no Cadastrado do professor"));
			System.out.println("Erro:   "+e.getStackTrace());
			redirectBean.goTo();
		}
		
		return null;
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

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}
}
