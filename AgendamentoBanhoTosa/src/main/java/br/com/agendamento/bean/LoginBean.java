package br.com.agendamento.bean;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.agendamento.dao.UsuarioDAO;
import br.com.agendamento.domain.Usuario;

@ManagedBean
@SessionScoped
public class LoginBean {
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
	}

	public void autenticar() {
		try {
			Faces.redirect("./pages/usuarios.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
	
	public void logar() {	
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuario = usuarioDAO.autenticar(usuario.getUsuario(), usuario.getSenha());
		
		if(usuario == null) {
			Messages.addGlobalError("Não foi possivel entrar!");
			iniciar();
		} else {
			Messages.addGlobalInfo("Entrando");
			autenticar();
		}
	}
	
	public void sair() {
		try {
			Faces.redirect("./pages/login.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}
	}
}
