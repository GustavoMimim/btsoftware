package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.agendamento.dao.UsuarioDAO;
import br.com.agendamento.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	// atributo para guardar os dados da view
	private Usuario usuario;

	// atributo para listar usuarios
	private List<Usuario> usuarios;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void novo() {
		usuario = new Usuario();
	}

	@PostConstruct //é chamado logo apos o construtor da classe
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar usuários!");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.salvar(usuario);

			novo();

			Messages.addGlobalInfo("Novo usuário cadastrado!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar usuário!");
			erro.printStackTrace();
		}

	}

}
