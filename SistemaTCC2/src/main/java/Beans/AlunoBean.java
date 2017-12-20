package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.spi.Bean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;

import DAO.AlunoDao;
import Models.Aluno;

@ManagedBean
@SessionScoped
public class AlunoBean {
	@Inject
	private AlunoDao dao;
	
	@Inject
	private RedirectBean redirectBean;
	
	FacesContext context = FacesContext.getCurrentInstance();
	
	private Aluno aluno = new Aluno();
	
	private Aluno alunoSelecionado = new Aluno();
	private int  indexAluno = -1;
	
	
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

	
	
	public String alterarLinha() {
		setAlunoSelecionado((Aluno) dataTable.getRowData());
		indexAluno = dataTable.getRowIndex();
		return "/aluno/formAluno.xhtml?faces-redirect=true";
	}
	
	public String alterar() {
		Aluno alunoSalvo = null ;
        System.out.println("Aluno Selecionado  = "+getAlunoSelecionado().getId() + "|" + getAlunoSelecionado().getName());
        
       List<Aluno> alunos = listar();
       alunoSalvo = alunos.get(indexAluno);
       
       if(alunoSalvo!= null && !comparaAlunoALterado(alunoSalvo, getAlunoSelecionado())) {
        		try {
					dao.editAlunos(alunoSelecionado);
					FacesMessage fm = new FacesMessage("Aluno alterado com sucesso");
					FacesContext.getCurrentInstance().addMessage("msg", fm);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }else {
        	FacesMessage fm = new FacesMessage("Nenhuma mudança");
			FacesContext.getCurrentInstance().addMessage("msg", fm);
        }
       return "/aluno/alterarAluno.xhtml?faces-redirect=true";
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

	public Aluno getAlunoSelecionado() {
		return alunoSelecionado;
	}

	public void setAlunoSelecionado(Aluno alunoSelecionado) {
		this.alunoSelecionado = alunoSelecionado;
	}         
	

	
}
