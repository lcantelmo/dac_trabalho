package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import DAO.AlunoDao;
import Models.Aluno;

@ManagedBean
@ViewScoped
public class AlunoBean {
	private Aluno aluno = new Aluno();
	  FacesContext context = FacesContext.getCurrentInstance();
	
	@Inject
	private AlunoDao dao;
	
	@Inject
	private RedirectBean redirectBean;
	
	private HtmlDataTable dataTable;
	   
	private  List<Aluno> listaAlunos = new ArrayList<Aluno>();
	
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

	public String alterar(Aluno aluno) {
		 
		if(!aluno.getEmail().contains("@") || !aluno.getEmail().contains("."))
	        {
	            context.addMessage(null, new FacesMessage("Email inválido"));
	            return null;
	        }
		try {
			dao.editAlunos(aluno);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage("Não foi possível alterar aluno"+e));
		}
		return null;
	}
	
	public void alterarLinha() {
		Aluno alunoSelecionado = (Aluno) dataTable.getRowData();
		Aluno alunoSalvo = null ;
        System.out.println("Aluno Selecionada  = "+alunoSelecionado.getId() + "|" + alunoSelecionado.getName());
        
        if(listaAlunos != null) {
        	 alunoSalvo	= listaAlunos.get(dataTable.getRowIndex());
        }
        if(alunoSalvo!= null && !comparaAlunoALterado(alunoSalvo, alunoSelecionado)) {
        		alterar(alunoSelecionado);
        }else {
        	FacesMessage fm = new FacesMessage("Nenhuma mudança");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
        }
	}
	
	private boolean comparaAlunoALterado(Aluno alunoSalvo, Aluno alunoSelecionado) {
			return alunoSalvo.getEmail().equals(alunoSelecionado.getEmail()) &&
					alunoSalvo.getMatricula().equals(alunoSelecionado.getMatricula()) &&
					alunoSalvo.getName().equals(alunoSelecionado.getName()) &&
					alunoSalvo.getUserType().equals(alunoSelecionado.getUserType());
	}

	public String excluirLinha() {
		Aluno alunoSelecionado = (Aluno) dataTable.getRowData();
		try {
			dao.deleteAluno(dao.buscaPeloId(alunoSelecionado.getId()));
			this.listaAlunos = dao.listar();
			context.addMessage(null, new FacesMessage("Aluno excluído"));
			return "/aluno/alterarAluno.xhtml?faces-redirect=true";
		}
		catch(Exception e)
		{
			context.addMessage(null, new FacesMessage("Não foi possível excluir aluno"+e));
			return null;
		}
	}
	
            
	public List<Aluno> listar() {
		this.listaAlunos = dao.listar();
		return this.listaAlunos;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public HtmlDataTable getDataTable() {
		return dataTable;
	}
	
	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}            
	
}
