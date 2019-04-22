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

import br.com.agendamento.dao.AnimalDAO;
import br.com.agendamento.dao.ClienteDAO;
import br.com.agendamento.dao.PlanoAgendamentoDAO;
import br.com.agendamento.domain.Animal;
import br.com.agendamento.domain.Cliente;
import br.com.agendamento.domain.PlanoAgendamento;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class PlanoAgendamentoBean implements Serializable{

	private PlanoAgendamento planoagendamento;
	private PlanoAgendamento planoagendamentoSelecionado;
	private List<PlanoAgendamento> planosagendamento;// atributo para listar os agendamentos
	private List<Animal> animais;// atributo para listar animais
	private List<Cliente> clientes;

	// Getters e Setters
	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	//Getters e Setters
	public PlanoAgendamento getPlanoAgendamento() {
		return planoagendamento;
	}

	public void setPlanoAgendamento(PlanoAgendamento planoagendamento) {
		this.planoagendamento = planoagendamento;
	}

	public PlanoAgendamento getPlanoAgendamentoSelecionado() {
		return planoagendamentoSelecionado;
	}

	public void setPlanoAgendamentoSelecionado(PlanoAgendamento planoagendamentoSelecionado) {
		this.planoagendamentoSelecionado = planoagendamentoSelecionado;
	}

	public List<PlanoAgendamento> getPlanoAgendamentos() {
		return planosagendamento;
	}

	public void setPlanoAgendamentos(List<PlanoAgendamento> planosagendamento) {
		this.planosagendamento = planosagendamento;
	}

	//zera todos os campos de Animal
	public void novo() {
		try {
			planoagendamento = new PlanoAgendamento();

			// populando lista de clientes
			ClienteDAO clienteDAO = new ClienteDAO();
			clientes = clienteDAO.listar();
			
			AnimalDAO animalDAO = new AnimalDAO();
			animais = animalDAO.listar();

		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao adicionar um plano!");
			erro.printStackTrace();
		}
	}
	
	public void index() {
		try {
			Faces.redirect("./pages/planos.xhtml");
		} catch (IOException erro) {
			erro.printStackTrace();
		}

	}

	@PostConstruct // é chamado logo apos o construtor da classe
	public void listar() {
		try {
			PlanoAgendamentoDAO planoagendamentoDAO = new PlanoAgendamentoDAO();
			planosagendamento = planoagendamentoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao listar os planos de agendamento!");
			erro.printStackTrace();
		}
	}
	
////	public void animalCliente() {
//		Long codigo = planoagendamento.getCodAnimal().getCodCliente().getCodigo();
//		
//		try {
//			AnimalDAO animalDAO = new AnimalDAO();
//			//animais = animalDAO.listarAnimaisCliente(codigo);
//			
//		} catch (RuntimeException erro) {
//			Messages.addGlobalError("Ocorreu um erro ao listar os animais!");
//			erro.printStackTrace();
//		}
//		
//	}

	public void salvar() {
		try {
			PlanoAgendamentoDAO planoagendamentoDAO = new PlanoAgendamentoDAO();
			planoagendamentoDAO.merge(planoagendamento);

			novo();
			planosagendamento = planoagendamentoDAO.listar();

			Messages.addGlobalInfo("Operação Realizada com Sucesso!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao salvar o Plano!");
			erro.printStackTrace();
		}

	}

	public void onRowSelect(SelectEvent event) {
		planoagendamentoSelecionado = (PlanoAgendamento) event.getObject();
		// Messages.addGlobalInfo("Isso foi selecionado: " + test.getNome());
	}

	public void excluir(ActionEvent evento) {
		planoagendamento = (PlanoAgendamento) evento.getComponent().getAttributes().get("planoagendamentoSelecionado");

		try{
			PlanoAgendamentoDAO planoagendamentoDAO = new PlanoAgendamentoDAO();
			planoagendamentoDAO.excluir(planoagendamento);
			Messages.addGlobalInfo("Plano de Agendamento do animal " + planoagendamento.getCodAnimal() + " Excluído");
			
			planosagendamento = planoagendamentoDAO.listar();
		}catch (RuntimeException e) {
			Messages.addGlobalError("Não foi possivel excluir!");
			e.printStackTrace();
		}

	}
	
	public void alterar(ActionEvent evento) {
		
		
		try {
			planoagendamento = (PlanoAgendamento) evento.getComponent().getAttributes().get("planoagendamentoSelecionado");
			if(planoagendamento == null) {
				Messages.addGlobalError("Não foi possivel alterar!(Valor(es) Nulo(s))");
			}
			else {
				// populando lista de clientes
				ClienteDAO clienteDAO = new ClienteDAO();
				clientes = clienteDAO.listar();
			}


		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao adicionar um plano!");
			erro.printStackTrace();
		}
	}
	
}

