package Beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import DAO.UsersEJB;
import Models.Usuario;

@ManagedBean
@RequestScoped
public class RecuperarSenhaBean {
	private Usuario usuario = new Usuario();
	
	@Inject
	private UsersEJB dao;
	@Inject
	private EmailBean email;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public String enviaEmailRecuperacao() {
		try{
			this.usuario = dao.getMatricula(this.usuario.getMatricula());
			try {
				String to = usuario.getEmail();
				String subject = "Senha Sistema TCC Uff";
				String body = "Sua senha do Sistema TCC Uff é: "+usuario.getPassword();
				email.send(to, subject, body);
				FacesContext.getCurrentInstance().getExternalContext()
        			.getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage("E-mail enviado com sucesso para "+usuario.getEmail()));
				FacesContext.getCurrentInstance().getExternalContext().redirect("/SistemaTCC2/login.xhtml?faces-redirect=true");
				return null;
			}catch (Exception E) {
				FacesContext.getCurrentInstance().getExternalContext()
        		.getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage("Não foi possível enviar o e-mail!"));
			FacesContext.getCurrentInstance().getExternalContext().redirect("/SistemaTCC2/login.xhtml?faces-redirect=true");
			return null;
			}
		}catch (Exception E) {
			FacesContext.getCurrentInstance().getExternalContext()
        		.getFlash().setKeepMessages(true);
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage("Matricula Inválida!"));
			return null;
		}
	}
}
