package Beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import DAO.AlunoDao;
import DAO.ProfessorDao;
import DAO.ProjetoDao;
import Models.Aluno;
import Models.Professor;
import Models.Projeto;

@Named
@RequestScoped
public class ProjetoBean {
	
	private Projeto projeto = new Projeto();
	private List<Long> alunosId = new ArrayList<>();
	private List<Long> professoresId = new ArrayList<>();
	
	@Inject
	private ProjetoDao dao;
	
	@Inject 
	private AlunoDao alunoDao;
	
	@Inject
	private ProfessorDao professorDao;
	
	@Inject
	private RedirectBean redirectBean;
	
	@Transactional
	public void salvar() {
		for(Long alunoId : alunosId) {
			projeto.getAlunos().add(new Aluno(alunoId));
		}
		for(Long professorId : professoresId) {
			projeto.getOrientadores().add(new Professor(professorId));
		}
		dao.salvar(projeto);
		System.out.println("Projeto Cadastrado" + projeto);
		this.projeto = new Projeto();
		this.alunosId = new ArrayList<>();
		this.professoresId = new ArrayList<>();
	}
	
	public List<Projeto> listar() {
		return dao.listar();
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public List<Long> getAlunosId() {
		return alunosId;
	}

	public void setAlunosId(List<Long> alunosId) {
		this.alunosId = alunosId;
	}

	public List<Long> getProfessoresId() {
		return professoresId;
	}

	public void setProfessoresId(List<Long> professorId) {
		this.professoresId = professorId;
	}
	
	public List<Aluno> getAlunos(){
		return alunoDao.listar();
	}
	
	public List<Professor> getProfessores(){
		return professorDao.listar();
	}
}
