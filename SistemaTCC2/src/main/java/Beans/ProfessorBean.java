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

import DAO.ProfessorDao;
import Models.Professor;

@ManagedBean
@SessionScoped
public class ProfessorBean {
	
	@Inject
	private ProfessorDao dao;
	
	@Inject
	private RedirectBean redirectBean;
	
	FacesContext context = FacesContext.getCurrentInstance();
	
	private Professor professor = new Professor();
	
	private Professor profSelecionado = new Professor();
	int indexProf = -1;
	
	private HtmlDataTable dataTable;
	
	private List<Professor> listaProfs = new ArrayList<>();
	   
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
	
	public String alterar(Professor professor) {
		 
		if(!professor.getEmail().contains("@") || !professor.getEmail().contains("."))
	        {
	            context.addMessage(null, new FacesMessage("Email inválido"));
	            return null;
	        }
		try {
			dao.editProfs(professor);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Não foi possível alterar o Professor "+e));
		}
		return "/aluno/alterarAluno.xhtml?faces-redirect=true";
	}
	
	public String alterarLinha() {
		setProfSelecionado((Professor) dataTable.getRowData());
		indexProf = dataTable.getRowIndex();
		return "/professor/formProfessor.xhtml?faces-redirect=true";
	}
	
	public void alterar() {
		Professor profSalvo= null ;
        System.out.println("Professor Selecionado  = "+getProfSelecionado().getId() + "|" + getProfSelecionado().getName());
        
       List<Professor> profs = listar();
       profSalvo = profs.get(indexProf);
       
       if(profSalvo!= null && !comparaProfALterado(profSalvo, getProfSelecionado())) {
        		try {
					dao.editProfs(profSelecionado);
					FacesMessage fm = new FacesMessage("Professor alterado com sucesso");
					FacesContext.getCurrentInstance().addMessage("msg", fm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }else {
        	FacesMessage fm = new FacesMessage("Nenhuma mudança");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
        }
	}
	
	private boolean comparaProfALterado(Professor profSalvo, Professor profSelecionado) {
		return profSalvo.getEmail().equals(profSelecionado.getEmail()) &&
				profSalvo.getMatricula().equals(profSelecionado.getMatricula()) &&
				profSalvo.getName().equals(profSelecionado.getName()) &&
				profSalvo.getUserType().equals(profSelecionado.getUserType());
	}
	
	public String excluirLinha() {
		Professor profSelecionado = (Professor) dataTable.getRowData();
		try {
			dao.deleteProfessor(dao.buscaPeloId(profSelecionado.getId()));
			this.listaProfs  = dao.listar();
			context.addMessage(null, new FacesMessage("Aluno excluído"));
			return "/aluno/alterarAluno.xhtml?faces-redirect=true";
		}
		catch(Exception e)
		{
			context.addMessage(null, new FacesMessage("Não foi possível excluir aluno"+e));
			return null;
		}
	}
	public List<Professor> listar() {
		listaProfs = dao.listar();
		return listaProfs;
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

	public Professor getProfSelecionado() {
		return profSelecionado;
	}

	public void setProfSelecionado(Professor profSelecionado) {
		this.profSelecionado = profSelecionado;
	}
}
