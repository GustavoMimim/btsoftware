package br.com.agendamento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

import br.com.agendamento.dao.UsuarioDAO;
import br.com.agendamento.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	// atributo para guardar os dados da view
	private Usuario usuario;

	private Usuario usuarioSelecionado;

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

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

	@PostConstruct // é chamado logo apos o construtor da classe
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
			usuarioDAO.merge(usuario);

			novo();
			usuarios = usuarioDAO.listar();

			Messages.addGlobalInfo("Novo usuário cadastrado!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar usuário!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		// aqui voce ja pode trabalhar com o event enviado e converter conforme
		// necessidade

		usuarioSelecionado = (Usuario) event.getObject();

		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

		try{
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);
			Messages.addGlobalInfo("Usuário " + usuario.getUsuario() + " Excluído");
			
			usuarios = usuarioDAO.listar();
		}catch (RuntimeException e) {
			Messages.addGlobalError("Nenhum usuário selecionado");
			e.printStackTrace();
		}

	}
	
	public void alterar(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");
		
		if(usuario == null) {
			Messages.addGlobalError("Nenhum Usuário Selecionado");
		}
	}

}
