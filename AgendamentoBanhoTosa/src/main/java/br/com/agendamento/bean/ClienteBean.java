package br.com.agendamento.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

import br.com.agendamento.dao.ClienteDAO;
import br.com.agendamento.domain.Cliente;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

	// atributo para guardar os dados da view
	private Cliente cliente;

	private Cliente clienteSelecionado;

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setclienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	// atributo para listar clientes
	private List<Cliente> clientes;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public void novo() {
		cliente = new Cliente();
	}
	
	public void index() {
		try {
			Faces.redirect("./pages/clientes.xhtml");
		}
		catch (IOException erro) {
			erro.printStackTrace();
		}
		
	}

	@PostConstruct // é chamado logo apos o construtor da classe
	public void listar() {
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar clientes!");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			cliente.setDataInclusao(new Date());

			ClienteDAO clienteDAO = new ClienteDAO();

			if (!clienteDAO.verificaCpf(cliente.getCpf()).isEmpty()) {
				Messages.addGlobalError("Cliente já cadastrado com esse CPF!");
			} else {//Cadastra um novo cliente

				clienteDAO.merge(cliente);

				novo();
				clientes = clienteDAO.listar();

				Messages.addGlobalInfo("Operação Realizada com Sucesso!");
			}
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao Salvar Cliente!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		// aqui voce ja pode trabalhar com o event enviado e converter conforme
		// necessidade

		clienteSelecionado = (Cliente) event.getObject();

		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			clienteDAO.excluir(cliente);
			Messages.addGlobalInfo("Cliente " + cliente.getNome() + " Excluído");

			clientes = clienteDAO.listar();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel excluir!");
			e.printStackTrace();
		}

	}

	public void alterar(ActionEvent evento) {
		cliente = (Cliente) evento.getComponent().getAttributes().get("clienteSelecionado");

		if (cliente == null) {
			Messages.addGlobalError("Não foi possivel alterar!");
		}
	}

}