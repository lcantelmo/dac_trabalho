package Beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import DAO.ProfessorDao;
import Models.Professor;

@Named
@RequestScoped
public class ProfessorBean {
	private Professor professor = new Professor();
	
	@Inject
	private ProfessorDao dao;
	
	@Transactional
	public void salvar() {
		dao.salvar(professor);
		System.out.println("Professor Cadastrado: " + professor);
	}
	
	public List<Professor> listar() {
		return dao.listar();
	}
}
