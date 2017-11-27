package Beans;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import DAO.AlunoDao;
import Models.Aluno;

@Named
@RequestScoped
public class AlunoBean {
	private Aluno aluno = new Aluno();
	
	@Inject
	private AlunoDao dao;
	
	@Transactional
	public void salvar() {
		dao.salvar(aluno);
		System.out.println("Aluno Cadastrado: " + aluno);
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
