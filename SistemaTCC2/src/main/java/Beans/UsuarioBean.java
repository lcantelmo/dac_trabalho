package Beans;

import java.util.List;

import javax.inject.Named;
import Models.Usuario;

@Named
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	
	public void salvar() {
		System.out.println("Usuario Cadastrado: "+ usuario);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
