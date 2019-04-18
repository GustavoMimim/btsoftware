package br.com.agendamento.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
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
	private List<Usuario> usuarios;// atributo para listar usuarios
	private char tipo;

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	// Getters e Setters
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

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

	// Métodos
	public void novo() {
		usuario = new Usuario();
	}
	
	public void index() {
		try {
			Faces.redirect("./pages/usuarios.xhtml");
		}
		catch (IOException erro) {
			erro.printStackTrace();
		}
		
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

			//se o cod do usuario for null, novo usuario, ou seja, inserir
			if (usuario.getCodigo() == null) {

				if (!usuarioDAO.verificaUsuario(usuario.getUsuario()).isEmpty()) {
					Messages.addGlobalError("Nome de Usuário já cadastrado!");
				}

				else if (!usuarioDAO.verificaCpf(usuario.getCpf()).isEmpty()) {
					Messages.addGlobalError("CPF já cadastrado!");
				}

				// cadastrando novo
				else {
					usuarioDAO.merge(usuario);

					novo();
					usuarios = usuarioDAO.listar();

					Messages.addGlobalInfo("Operação Realizada com Sucesso!");
				}
			
			//senao alterar usuario
			} else {

				usuarioDAO.merge(usuario);

				novo();
				usuarios = usuarioDAO.listar();

				Messages.addGlobalInfo("Alteração Realizada com Sucesso!");

			}

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Salvar Usuario!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		usuarioSelecionado = (Usuario) event.getObject();
		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);
			Messages.addGlobalInfo("Usuário " + usuario.getUsuario() + " Excluído");

			usuarios = usuarioDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel excluir!");
			e.printStackTrace();
		}

	}

	public void alterar(ActionEvent evento) {
		usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

		if (usuario == null) {
			Messages.addGlobalError("Não foi possivel alterar!");
		}
	}

}
