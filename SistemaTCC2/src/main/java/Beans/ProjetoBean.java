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
	private List<Integer> alunosId = new ArrayList<>();
	private List<Integer> professoresId = new ArrayList<>();
	
	@Inject
	private ProjetoDao dao;
	
	@Inject 
	private AlunoDao alunoDao;
	
	@Inject
	private ProfessorDao professorDao;
	
	@Transactional
	public void salvar() {
		for(Integer alunoId : alunosId) {
			projeto.getAlunos().add(new Aluno(alunoId));
		}
		for(Integer professorId : professoresId) {
			projeto.getOrientadores().add(new Professor(professorId));
		}
		dao.salvar(projeto);
		System.out.println("Projeto Cadastrado" + projeto);
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

	public List<Integer> getAlunosId() {
		return alunosId;
	}

	public void setAlunosId(List<Integer> alunosId) {
		this.alunosId = alunosId;
	}

	public List<Integer> getProfessoresId() {
		return professoresId;
	}

	public void setProfessoresId(List<Integer> professorId) {
		this.professoresId = professorId;
	}
	
	public List<Aluno> getAlunos(){
		return alunoDao.listar();
	}
	
	public List<Professor> getProfessores(){
		return professorDao.listar();
	}
}
