package Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

@Entity
public class Projeto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    @Lob
    private String tema;
    @ManyToMany
    private List<Aluno> alunos = new ArrayList<Aluno>();
    @ManyToMany
    private List<Professor> orientadores = new ArrayList<Professor>();
    private float nota_tcc1;
    private float nota_tcc2;
    //@Temporal(TemporalType.DATE)
    //private Date realizadoEm;
    private String local;
    private String versoesParciais;
    private int versaoFinal;
    
    public Projeto() {
    }

	public Projeto(String titulo, String tema, List<Aluno> alunos, List<Professor> orientadores, float nota_tcc1,
			float nota_tcc2, Date realizadoEm, String local, String versoesParciais, int versaoFinal) {
		super();
		this.titulo = titulo;
		this.tema = tema;
		this.alunos = alunos;
		this.orientadores = orientadores;
		this.nota_tcc1 = nota_tcc1;
		this.nota_tcc2 = nota_tcc2;
		//this.realizadoEm = realizadoEm;
		this.local = local;
		this.versoesParciais = versoesParciais;
		this.versaoFinal = versaoFinal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public void addAluno(Aluno aluno) {
		this.alunos.add(aluno);
	}

	public List<Professor> getOrientadores() {
		return orientadores;
	}

	public void setOrientadores(List<Professor> orientadores) {
		this.orientadores = orientadores;
	}
	
	public void addOrientador(Professor orientador) {
		this.orientadores.add(orientador);
	}

	public float getNota_tcc1() {
		return nota_tcc1;
	}

	public void setNota_tcc1(float nota_tcc1) {
		this.nota_tcc1 = nota_tcc1;
	}

	public float getNota_tcc2() {
		return nota_tcc2;
	}

	public void setNota_tcc2(float nota_tcc2) {
		this.nota_tcc2 = nota_tcc2;
	}

	/*public Date getRealizadoEm() {
		return realizadoEm;
	}

	public void setRealizadoEm(Date realizadoEm) {
		this.realizadoEm = realizadoEm;
	}*/

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getVersoesParciais() {
		return versoesParciais;
	}

	public void setVersoesParciais(String versoesParciais) {
		this.versoesParciais = versoesParciais;
	}

	public int getVersaoFinal() {
		return versaoFinal;
	}

	public void setVersaoFinal(int versaoFinal) {
		this.versaoFinal = versaoFinal;
	}

	@Override
	public String toString() {
		return "Projeto [id=" + id + ", titulo=" + titulo + ", tema=" + tema + ", alunos=" + alunos + ", orientadores="
				+ orientadores + ", nota_tcc1=" + nota_tcc1 + ", nota_tcc2=" + nota_tcc2 + ", local=" + local + ", versoesParciais=" + versoesParciais + ", versaoFinal="
				+ versaoFinal + "]";
	}
        
}
